package com.hzl.base.attacker;

import com.hzl.base.role.FightRole;

/**
 * 这个是伤害结果包装类
 */
public class DamageResult {
    private DamageType damageType;
    private int wuliValue;
    private int mofaValue;
    private int hundunValue;
    private int healValue;
    private FightRole from;  // 伤害来源
    private FightRole to;    // 伤害对象

    public DamageResult() {}

    public DamageResult(FightRole from, FightRole to) {
        this.from = from;
        this.to = to;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public int getWuliValue() {
        return wuliValue;
    }

    public void setWuliValue(int wuliValue) {
        this.wuliValue = wuliValue;
    }

    public int getMofaValue() {
        return mofaValue;
    }

    public void setMofaValue(int mofaValue) {
        this.mofaValue = mofaValue;
    }

    public int getHundunValue() {
        return hundunValue;
    }

    public void setHundunValue(int hundunValue) {
        this.hundunValue = hundunValue;
    }

    public int getHealValue() {
        return healValue;
    }

    public void setHealValue(int healValue) {
        this.healValue = healValue;
    }

    public FightRole getFrom() {
        return from;
    }

    public void setFrom(FightRole from) {
        this.from = from;
    }

    public FightRole getTo() {
        return to;
    }

    public void setTo(FightRole to) {
        this.to = to;
    }
}
