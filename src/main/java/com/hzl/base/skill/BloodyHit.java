package com.hzl.base.skill;

import com.hzl.base.attacker.DamageBody;
import com.hzl.base.attacker.SkillBody;
import com.hzl.base.role.FightRole;
import com.hzl.base.skill.target.TargetPermission;
import com.hzl.base.skill.target.TargetType;

public class BloodyHit implements ActiveSkill {

    private String name = "血之打击";
    private int lv = 1; // 技能等级
    private int cost = 4;

    private double possibility = 0.6;  // 触发概率
    private boolean isTrigger = false;

    private int type = 1; // 物理
    private TargetPermission targetPermission;

    public BloodyHit(int lv) {
        this();
        this.lv = lv;
    }

    private BloodyHit() {
        targetPermission = new TargetPermission(TargetType.OPPONENT);
    }
    @Override
    public SkillBody generateSkillBody(FightRole role, FightRole... to) {
        SkillBody body = new SkillBody();
        body.setFrom(role);
        body.setTo(to[0]);

        body.setAdditionCt(0.1 + 0.02 * lv);
        body.setAdditionCte(0.1 + 0.05 * lv);

        body.setSkill(this);

        role.costMp(cost);
        return body;
    }

    @Override
    public DamageBody onEffect(FightRole role, FightRole to, SkillBody skillBody) {
        DamageBody db = defaultDamageBody(role, to, skillBody);
        int hd = db.getWuliValue() * 5 / 10;
        db.setHealthDrink(hd);
        return db;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String getSkillId() {
        return name;
    }
}
