package com.hzl.base.skill;

import com.hzl.base.attacker.DamageResult;
import com.hzl.base.attacker.DamageType;
import com.hzl.base.role.FightRole;
import com.hzl.base.util.RandomUtil;

/**
 * 攻击格挡
 */
public class AtkBlock implements PassiveSkill {
    private String name = "格挡";
    private int lv;

    private double possibility = 0.3;

    public AtkBlock(int lv) {
        this.lv = lv;
    }

    @Override
    public void onDefend(DamageResult damageResult) {
        if (RandomUtil.randDouble() < possibility) {
            FightRole defendRole = damageResult.getTo();
            // 30%格挡值 + 固定值, 但是不小于3
            if (damageResult.getDamageType().getDamageFamily().equals(DamageType.DamageFamily.WULI)) {
                int value = damageResult.getWuliValue();
                value -= lv * 2;
                if (value < 3) {
                    value = 3;
                }
                damageResult.setWuliValue(value);
                System.out.println(String.format("[%s]触发了格挡", defendRole.getName()));
            }
        }
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public String getSkillId() {
        return name;
    }
}
