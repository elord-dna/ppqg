package com.hzl.base.skill;

import com.hzl.base.attacker.SkillBody;
import com.hzl.base.role.FightRole;

public interface NewPassiveSkill extends Skill {

    default void onAttack(FightRole role, SkillBody body, FightRole... to) {

    }
}
