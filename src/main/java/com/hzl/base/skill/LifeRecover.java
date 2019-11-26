package com.hzl.base.skill;

import com.hzl.base.attacker.DamageBody;
import com.hzl.base.attacker.SkillBody;
import com.hzl.base.role.FightRole;
import com.hzl.base.skill.target.TargetPermission;
import com.hzl.base.skill.target.TargetType;

/**
 * 生命回复术
 */
public class LifeRecover implements ActiveSkill {

    private String name = "生命回复术";
    private int lv = 1; // 技能等级
    private int cost = 5;

    private int type = 4; // 治疗
    private TargetPermission targetPermission;

    public LifeRecover(int lv) {
        this();
        this.lv = lv;
    }

    private LifeRecover() {
        targetPermission = new TargetPermission(TargetType.TEAM);
    }
    @Override
    public SkillBody generateSkillBody(FightRole role, FightRole... to) {
        SkillBody body = new SkillBody();
        body.setFrom(role);
        body.setTo(to[0]);

        body.setSkill(this);

        role.costMp(cost);
        return body;
    }

    @Override
    public DamageBody onEffect(FightRole role, FightRole to, SkillBody skillBody) {
        DamageBody db = defaultDamageBody(role, to, skillBody);
        int value = (int)(8 + lv * 2.5);
        value = randomDamage(value);
        db.setHealValue(value);
        return db;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public TargetPermission getTargetPermission() {
        return targetPermission;
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
