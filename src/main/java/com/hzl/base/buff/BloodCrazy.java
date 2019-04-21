package com.hzl.base.buff;

import com.hzl.base.attacker.DamageResult;
import com.hzl.base.attacker.DamageType;
import com.hzl.base.role.FightRole;

/**
 * 简单来说，就是流血buff
 * 造成与目标攻击力相关都魔法伤害，同时会触发目标角色收到伤害的效果
 */
public class BloodCrazy extends Buff {

    private BloodCrazy() {
        this.name = "血之狂暴";
        this.type = BuffType.DAMAGE;
        setDuringTime(6);
        setDuration(120);
        setMaxLayer(1);
    }

    public BloodCrazy(FightRole from, FightRole to) {
        this();
        setFrom(from);
        setTo(to);
    }

    @Override
    public void activate() {
        FightRole from = (FightRole) getFrom();
        FightRole to = (FightRole) getTo();
        DamageResult dr = new DamageResult(from, to);
        DamageType dt = new DamageType(DamageType.DamageFamily.MOFA, DamageType.ElementType.LIFE);
        dr.setDamageType(dt);
        int value = from.getFmatk() / 20 + 2;
        if (value < 0) {
            value = 0;
        }
        dr.setMofaValue(value);

        to.onDefend(dr);
    }
}
