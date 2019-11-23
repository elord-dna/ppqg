package com.hzl.base.skill;

import com.hzl.base.attacker.DamageBody;
import com.hzl.base.attacker.SkillBody;
import com.hzl.base.role.FightRole;

public interface ActiveSkill extends Skill {
    SkillBody generateSkillBody(FightRole role, FightRole... to);
    default DamageBody onEffect(FightRole role, FightRole to, SkillBody skillBody) {
        DamageBody body = new DamageBody();
        body.setFrom(role);
        body.setTo(to);

        Skill skill = skillBody.getSkill();
        int type = skill.getType();

        if (type == 1) {
            int value = role.getAtk() + skillBody.getAdditionAtk() - to.getDef();
            body.setWuliValue(value>0?(int)(value*(1+skillBody.getAtkp())):0);
        } else if (type == 2) {
            int value = role.getFatk() + skillBody.getAdditionMatk() - to.getFdef();
            body.setMofaValue(value>0?(int)(value*(1+skillBody.getMatkp())):0);
        } else if (type == 3) {
            int v1 = role.getAtk() + skillBody.getAdditionAtk() - to.getDef();
            int v2 = role.getFatk() + skillBody.getAdditionMatk() - to.getFdef();
            body.setWuliValue(v1>0?(int)(v1*(1+skillBody.getAtkp())):0);
            body.setMofaValue(v2>0?(int)(v2*(1+skillBody.getMatkp())):0);
        }

        // todo 处理属性伤害

        return body;
    }

    default void onBind(FightRole role) {
        //
    }

    default void onUnBind(FightRole role) {
        //
    }

    default int getCost() {
        return 0;
    }

}
