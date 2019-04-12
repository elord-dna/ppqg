package com.hzl.base.skill;

import com.hzl.base.role.FightRole;

/**
 * 被动技能
 */
public interface PassiveSkill extends Skill {
    /**
     * 攻击前触发
     * @param triggerRole 触发对象
     * @param targetRoles 目标对象
     */
    default void onAttack(FightRole triggerRole, FightRole... targetRoles) {
    }
    // 攻击后触发
    default void afterAttack(FightRole triggerRole, FightRole... targetRoles) {
    }

    default void onCast() {
    }
    default void afterCast() {
    }
}
