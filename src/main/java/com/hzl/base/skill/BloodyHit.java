package com.hzl.base.skill;

import com.hzl.base.role.FightRole;
import com.hzl.base.util.RandomUtil;

public class BloodyHit implements PassiveSkill {

    private String name = "血之打击";
    private int lv; // 技能等级

    private double possibility = 0.6;  // 触发概率
    private boolean isTrigger = false;

    private int type = 1; // 物理

    @Override
    public void onAttack(FightRole triggerRole, FightRole... targetRoles) {
        for (FightRole role : targetRoles) {
            if (RandomUtil.randDouble() < possibility) {
                //
            }
        }
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getSkillId() {
        return name;
    }
}
