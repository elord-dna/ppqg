package com.hzl.base.skill;

import com.hzl.base.attacker.DamageBody;
import com.hzl.base.attacker.SkillBody;
import com.hzl.base.role.FightRole;

public class BloodyHit implements ActiveSkill {

    private String name = "血之打击";
    private int lv = 1; // 技能等级

    private double possibility = 0.6;  // 触发概率
    private boolean isTrigger = false;

    private int type = 1; // 物理

    public BloodyHit(int lv) {
        this.lv = lv;
    }

    @Override
    public SkillBody generateSkillBody(FightRole role, FightRole... to) {
        SkillBody body = new SkillBody();
        body.setFrom(role);
        body.setTo(to[0]);

        body.setAdditionCt(0.1 + 0.02 * lv);
        body.setAdditionCte(0.1 + 0.05 * lv);

        body.setSkill(this);
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
    public String getSkillId() {
        return name;
    }
}
