package com.hzl.base.employ;

import com.hzl.base.attacker.WuliAttacker;
import com.hzl.base.role.FightRole;
import com.hzl.base.skill.HeavyHit;
import com.hzl.base.skill.Skill;

import java.util.List;

public class Fighter extends FightRole {

    private List<Object> list;
//    Random random = new Random();
//    boolean isAtkUp = false;

    @Override
    public void init() {
        fatk = getAtk();
        fdef = getDef();
        fmatk = getMatk();
        fmdef = getMdef();
        attacker = new WuliAttacker();
        // 自带被动技能
        Skill skill_1 = new HeavyHit(this.getLv());
        addSkill(skill_1);
    }

}
