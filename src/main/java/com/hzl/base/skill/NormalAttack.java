package com.hzl.base.skill;

import com.hzl.base.attacker.SkillBody;
import com.hzl.base.role.FightRole;

/**
 * 普通攻击
 */
public class NormalAttack implements ActiveSkill {
    private final String name = "普通攻击";  // 技能名字
    private int lv;       // 技能等级
    private int additionLv = 0; // 额外等级

    private int targetNum = 1;

    private int type = 1;  // 物理
    @Override
    public SkillBody generateSkillBody(FightRole role, FightRole... to) {
        SkillBody body = new SkillBody();
        body.setFrom(role);
        body.setTo(to[0]);

        // 简直没有任何特别的东西
        body.setSkill(this);
        return body;
    }

//    @Override
//    public DamageBody onEffect(FightRole role, FightRole to, SkillBody skillBody) {
//        DamageBody damageBody = new DamageBody();
//        int wuliValue = role.getAtk() - to.getDef();
//
//        return damageBody;
//    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getSkillId() {
        return name;
    }
}
