package com.hzl.base.skill.hongmao;

import com.hzl.base.attacker.SkillBody;
import com.hzl.base.attacker.element.FireElement;
import com.hzl.base.role.FightRole;
import com.hzl.base.skill.ActiveSkill;

public class FireBlade implements ActiveSkill {
    private String name = "火焰刀";
    private int lv = 1;
    private int maxLv = 50;
    private int cost = 5;
    private int type = 1;  // 物理

    public FireBlade(int lv) {
        this.lv = lv;
    }

    @Override
    public SkillBody generateSkillBody(FightRole role, FightRole... to) {
        SkillBody body = new SkillBody();
        body.setFrom(role);
        body.setTo(to[0]);

        body.setSkill(this);
        body.setMainElement(1);
        FireElement fireElement = new FireElement();
        fireElement.setAdditionAtk(10 + lv * 2);
        body.setFireElement(fireElement);
        body.setMainElement(1);

        role.costMp(cost);
        return body;
    }

//    @Override
//    public DamageBody onEffect(FightRole role, FightRole to, SkillBody skillBody) {
//        DamageBody db = defaultDamageBody(role, to, skillBody);
//        EleDam eleDam = db.getEleDam();
//
//        return null;
//    }

    @Override
    public int getCost() {
        return cost;
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
