package com.hzl.base.role;

import com.alibaba.fastjson.JSONObject;
import com.hzl.base.attacker.DamageResult;
import com.hzl.base.attacker.DamageType;
import com.hzl.base.attacker.WuliAttacker;
import com.hzl.base.battle.PopMachine;
import com.hzl.base.skill.Skill;

public class FightRole extends Role {
    private int fatk = 1;
    private int fdef = 1;
    private int fmatk = 1;
    private int fmdef = 1;

    private int houyao = 100;  // 攻击后摇

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
        // todo
    }

    public void onDefend(DamageResult damageResult) {
        // todo
        DamageType dt = damageResult.getDamageType();
        if (dt.getDamageFamily().equals(DamageType.DamageFamily.WULI)) {
            this.setChp(getChp() - damageResult.getWuliValue());
        } else if (dt.getDamageFamily().equals(DamageType.DamageFamily.MOFA)) {
            this.setChp(getChp() - damageResult.getMofaValue());
        }
        // checkDeath 放在最后
    }

    public void afterAttack(PopMachine popMachine) {
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
    }

    private void checkDeath() {
        if (getChp() <= 0) {
            setChp(0);
        }
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
