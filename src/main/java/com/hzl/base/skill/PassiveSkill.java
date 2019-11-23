package com.hzl.base.skill;

import com.hzl.base.attacker.DamageResult;
import com.hzl.base.attacker.SkillBody;
import com.hzl.base.role.FightRole;

/**
 * 被动技能 todo 提取出一个新的被动，分成攻击型被动和防御型被动
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

    /**
     * 新的触发方式
     * @param triggerRole
     * @param body
     * @param targetRoles
     */
    default void onCast(FightRole triggerRole, SkillBody body, FightRole... targetRoles) {

    }
    default void afterCast() {
    }

    default void onDefend(DamageResult damageResult) {
    }
}
