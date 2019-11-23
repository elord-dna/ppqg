package com.hzl.base.skill;

import com.hzl.base.attacker.SkillBody;
import com.hzl.base.role.FightRole;
import com.hzl.base.util.RandomUtil;

/**
 * 一个测试技能，重击
 * 被动技能
 */
public class HeavyHit implements PassiveSkill {
    private String name = "重击";
    private int lv; // 技能等级

    private double possibility = 0.5;  // 触发概率
    private int delta = 0;  // 实际攻击变化数值
    private boolean isTrigger = false;

    private int type = 1; // 物理类型

    public HeavyHit(int lv) {
        this.lv = lv;
    }

    @Override
    public void onAttack(FightRole triggerRole, FightRole... targetRoles) {
        isTrigger = RandomUtil.randDouble() < possibility;
        if (isTrigger) {
            int up = 3 * lv + 2;
            delta = triggerRole.addAtk(up);
            System.err.println(String.format("天啦！[%s]触发了[%s]的攻击上升了", triggerRole.getName(), name));
        }
    }

    @Override
    public void afterAttack(FightRole triggerRole, FightRole... targetRoles) {
        if (isTrigger) {
            triggerRole.reduceAtk(delta);
            System.err.println(String.format("[%s]重击效果结束，[%s]的攻击恢复", name, triggerRole.getName()));
        }
    }

    @Override
    public void onCast(FightRole triggerRole, SkillBody body, FightRole... targetRoles) {
        isTrigger = RandomUtil.randDouble() < possibility;
        if (isTrigger) {
            int up = 3 * lv + 2;
            body.setAdditionAtk(up);
            System.err.println(String.format("天啦！[%s]触发了[%s]", triggerRole.getName(), name));
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
