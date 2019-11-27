package com.hzl.base.role;

import com.alibaba.fastjson.JSONObject;
import com.hzl.base.attacker.*;
import com.hzl.base.attacker.element.EleDam;
import com.hzl.base.battle.PopMachine;
import com.hzl.base.buff.Buff;
import com.hzl.base.buff.BuffList;
import com.hzl.base.skill.ActiveSkill;
import com.hzl.base.skill.PassiveSkill;
import com.hzl.base.skill.Skill;
import com.hzl.base.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class FightRole extends Role {
    protected int fatk = 1;
    protected int fdef = 1;
    protected int fmatk = 1;
    protected int fmdef = 1;

    protected double ct = 0.1;
    protected double cte = 0.5;

    private int houyao = 100;  // 攻击后摇

    // 被动技能
    private List<PassiveSkill> passiveSkillList = new ArrayList<>();
    // 主动技能
    private List<ActiveSkill> activeSkillList = new ArrayList<>();
    // buff 列表
    private BuffList buffList = new BuffList();

    public void init() {
        fatk = getAtk();
        fdef = getDef();
        fmatk = getMatk();
        fmdef = getMdef();
        attacker = new WuliAttacker();
    }

    /**
     * 攻击之前触发
     *
     * @param fightRoles
     */
    public void onAttack(FightRole... fightRoles) {
        for (PassiveSkill skill : passiveSkillList) {
            skill.onAttack(this, fightRoles);
        }
        // todo buff触发部分, 可能最终会将被动技能并入到buff部分, 使用相同到触发方式
    }

    public void onDefend(DamageResult damageResult) {
        //
        for (PassiveSkill skill : passiveSkillList) {
            skill.onDefend(damageResult);
        }
        DamageType dt = damageResult.getDamageType();
        if (dt.getDamageFamily().equals(DamageType.DamageFamily.WULI)) {
            // 其实还需要判断属性之类的更复杂的东西，但是目前属性伤害还没有系统化
            this.setChp(getChp() - damageResult.getWuliValue());
        } else if (dt.getDamageFamily().equals(DamageType.DamageFamily.MOFA)) {
            this.setChp(getChp() - damageResult.getMofaValue());
        }
        // checkDeath 放在最后
    }

    public void afterAttack(PopMachine popMachine, FightRole... fightRoles) {
        for (PassiveSkill skill : passiveSkillList) {
            skill.afterAttack(this, fightRoles);
        }
        popMachine.updatePop(this, 100);
    }
    /**
     * 施法前触发
     *
     * @param skill 使用的技能，可能删除
     * @param body 技能体
     * @param fightRoles 目标
     */
    public void onCast(Skill skill, SkillBody body, FightRole... fightRoles) {
        // todo
        for (PassiveSkill ps : passiveSkillList) {
            ps.onCast(this, body, fightRoles);
        }
    }

    /**
     * 被施法后触发
     *
     * @param body
     */
    public void onAimed(SkillBody body) {
        // todo 触发被指定为目标后的一些触发，比如防御被动
    }

    public void attack(FightRole role) {
        onAttack(role);
        DamageResult dr = attacker.attacker(this, role);
        role.onDefend(dr);
        System.out.println(String.format("[%s]造成了[%d]点伤害, [%s]到剩余生命值: %d/%d", getName(), dr.getSumValue(),
                role.getName(), role.getChp(), role.getMhp()));
    }

    /**
     * 目前还是只用一个目标
     * @param skill
     * @param to
     * @return
     */
    public SkillBody cast(ActiveSkill skill, FightRole to) {
        SkillBody body = skill.generateSkillBody(this, to);
//        System.out.println(String.format("[%s]使用了[%s]", this.getName(), skill.getSkillId()));
        onCast(skill, body, to);
        return body;
    }

    /**
     * 被攻击，处理DamageBody，感觉这部分可以拿到battleManager里
     * @param db
     * @return
     */
    public boolean afterCasted(DamageBody db) {
        int w = db.getWuliValue();
        int m = db.getMofaValue();
        int h = db.getHealValue();
        EleDam ed = db.getEleDam();
        int ev = 0;
        if (ed != null) {
            ev = ed.getValue();
        }
        // todo 目前只处理物理伤害，和治疗，还有元素伤害
        int type = db.getType();
        if (type == 0) {
            this.setChp(this.getChp() - w - ev);
            checkDeath();
            if (db.isCt()) {
                System.err.println(String.format("[%s]暴击了！造成了[%d]点伤害, [%s]的剩余生命值: %d/%d",
                        db.getFrom().getName(), w + m + ev, getName(), getChp(), getMhp()));
            } else {
                System.out.println(String.format("[%s]造成了[%d]点伤害, [%s]的剩余生命值: %d/%d",
                        db.getFrom().getName(), w + m + ev, getName(), getChp(), getMhp()));
            }
            boolean death = getChp() == 0;
            if (death) {
                onDeath();
            }
            return getChp() == 0;
        } else if (type == 1) {
            // 治疗
            heal(db.getFrom(), h);
        }
        return false;
    }

    /**
     * 攻击后，处理DamageBody
     * 发生在afterCasted之后，也可以拿到bm里空值
     * @param db
     */
    public void afterCastedHandle(DamageBody db) {
        if (db.isMiss()) {
            // 未命中触发
        } else {
            if (db.isCt()) {
                // 暴击预处理
            }

            // 通常触发
            FightHandle.handleHealthDrink(this, db);
        }
    }

    /**
     * 触发死亡
     */
    public void onDeath() {
        // todo
        System.err.println(String.format("OMG！[%s]狗带了！", this.getName()));
    }

    public FightRole addBuff(Buff buff) {
        return this;
    }

    private void checkDeath() {
        if (getChp() <= 0) {
            setChp(0);
        }
    }

    /**
     * 添加技能
     * @param skill
     * @return
     */
    public FightRole addSkill(Skill skill) {
        // todo
        if (skill instanceof PassiveSkill) {
            passiveSkillList.add((PassiveSkill) skill);
        }
        return this;
    }

    /**
     * 绑定主动技能
     * @param skill
     * @return
     */
    public FightRole bindActiveSkill(ActiveSkill skill) {
        ActiveSkill as = hasActiveSkill(skill);
        if (as != null) {
            this.unbindActiveSkill(as);
        }
        activeSkillList.add(skill);
        // 触发onBind效果
        skill.onBind(this);
        return this;
    }

    /**
     * 解绑主动技能
     * @param skill
     * @return
     */
    public FightRole unbindActiveSkill(ActiveSkill skill) {
        if (activeSkillList.contains(skill)) {
            activeSkillList.remove(skill);
            skill.onUnBind(this);
        }
        return this;
    }

    /**
     * 是否拥有某主动技能
     * @param skill
     * @return
     */
    public ActiveSkill hasActiveSkill(ActiveSkill skill) {
        for (ActiveSkill as : activeSkillList) {
            if (skill.getSkillId().equals(as.getSkillId())) {
                return as;
            }
        }
        return null;
    }

    /**
     * 测试方法，随机释放一个主动技能
     * @param to 目标
     * @return
     */
    public SkillBody castRandom(FightRole to) {
        int len = activeSkillList.size();
        int pos = RandomUtil.randInt(len);
        return this.cast(activeSkillList.get(pos), to);
    }

    public ActiveSkill randomSkill() {
        int len = activeSkillList.size();
        int pos = RandomUtil.randInt(len);
        return activeSkillList.get(pos);
    }

    public int addAtk(int up) {
        this.fatk += up;
        // todo 并且触发攻击上升带来的其他效果
        return up;
    }

    public int reduceAtk(int val) {
        this.fatk -= val;
        return val;
    }

    public void heal(Role from, int value) {
        // 受到到治疗
        onHeal(from, value);
        if (getMhp() - getChp() >= value) {
            setChp(getChp() + value);
        } else {
            setChp(getMhp());
        }
        System.err.println(String.format("[%s]受到了治疗，回复了%d点生命，当前生命%d/%d",getName(), value,
                getChp(), getMhp()));
    }

    public void onHeal(Role role, int value) {
        // todo 受到治疗时的连锁反应
    }

    public void costMp(int value) {
        setCmp(getCmp() - value);
    }

    public int getFatk() {
        return fatk;
    }

    public void setFatk(int fatk) {
        this.fatk = fatk;
    }

    public int getFdef() {
        return fdef;
    }

    public void setFdef(int fdef) {
        this.fdef = fdef;
    }

    public int getFmatk() {
        return fmatk;
    }

    public void setFmatk(int fmatk) {
        this.fmatk = fmatk;
    }

    public int getFmdef() {
        return fmdef;
    }

    public void setFmdef(int fmdef) {
        this.fmdef = fmdef;
    }

    public double getCt() {
        return ct;
    }

    public void setCt(double ct) {
        this.ct = ct;
    }

    public double getCte() {
        return cte;
    }

    public void setCte(double cte) {
        this.cte = cte;
    }

    public int getHouyao() {
        return houyao;
    }

    public void setHouyao(int houyao) {
        this.houyao = houyao;
    }

    public List<ActiveSkill> getActiveSkillList() {
        return activeSkillList;
    }

    @Override
    public String toString() {
        return JSONObject.toJSON(this).toString();
    }
}
