package com.hzl.base.role;

import com.alibaba.fastjson.JSONObject;
import com.hzl.base.attacker.DamageResult;
import com.hzl.base.attacker.DamageType;
import com.hzl.base.attacker.WuliAttacker;
import com.hzl.base.battle.PopMachine;
import com.hzl.base.skill.PassiveSkill;
import com.hzl.base.skill.Skill;

import java.util.ArrayList;
import java.util.List;

public class FightRole extends Role {
    protected int fatk = 1;
    protected int fdef = 1;
    protected int fmatk = 1;
    protected int fmdef = 1;

    private int houyao = 100;  // 攻击后摇

    private List<PassiveSkill> passiveSkillList = new ArrayList<>();

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
     * @param skill
     * @param fightRoles
     */
    public void onCast(Skill skill, FightRole... fightRoles) {
        // todo
    }

    /**
     * 被施法后触发
     *
     * @param skill
     * @param originRole
     */
    public void onAimed(Skill skill, FightRole originRole) {
        // todo
    }

    public void attack(FightRole role) {
        onAttack(role);
        DamageResult dr = attacker.attacker(this, role);
        role.onDefend(dr);
        System.out.println(String.format("[%s]造成了[%d]点伤害, [%s]到剩余生命值: %d/%d", getName(), dr.getSumValue(),
                role.getName(), role.getChp(), role.getMhp()));
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

    public int addAtk(int up) {
        this.fatk += up;
        // todo 并且触发攻击上升带来的其他效果
        return up;
    }

    public int reduceAtk(int val) {
        this.fatk -= val;
        return val;
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

    public int getHouyao() {
        return houyao;
    }

    public void setHouyao(int houyao) {
        this.houyao = houyao;
    }

    @Override
    public String toString() {
        return JSONObject.toJSON(this).toString();
    }
}
