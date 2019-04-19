package com.hzl.base.attacker;

import com.hzl.base.role.FightRole;

/**
 * 普通的物理攻击
 */
public class WuliAttacker implements Attacker {
    @Override
    public DamageResult attacker(FightRole a, FightRole b) {
        DamageResult dr = new DamageResult(a, b);
        // 造成无属性的物理伤害
        DamageType dt = new DamageType(DamageType.DamageFamily.WULI, DamageType.ElementType.NONE);
        int value = a.getFatk() - b.getFdef();
        if (value < 1) {
            value = 1;
        }
        dr.setDamageType(dt);
        dr.setWuliValue(value);
        return dr;
    }
}
