package com.hzl.base.skill;

import com.hzl.base.attacker.DamageBody;
import com.hzl.base.attacker.SkillBody;
import com.hzl.base.role.FightRole;
import com.hzl.base.skill.target.TargetPermission;
import com.hzl.base.skill.target.TargetType;
import com.hzl.base.util.RandomUtil;

public interface ActiveSkill extends Skill {
    SkillBody generateSkillBody(FightRole role, FightRole... to);

    default DamageBody onEffect(FightRole role, FightRole to, SkillBody skillBody) {
        return defaultDamageBody(role, to, skillBody);
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

    default TargetPermission getTargetPermission() {
        return new TargetPermission(TargetType.OPPONENT);
    }

    /**
     * 生成随机分布的伤害，在0.85-1.00之间
     * 有的技能可能拥有更大或者更小的范围
     *
     * @return
     */
    default int randomDamage(int dmg) {
        double v = 0.85 + RandomUtil.randDouble() * 0.15;
        int rd = (int) (dmg * v);
        rd = rd > 0 ? rd : 1;
        return rd;
    }

    /**
     * 不要重写，不要重写！
     * @param role
     * @param to
     * @param skillBody
     * @return
     */
    default DamageBody defaultDamageBody(FightRole role, FightRole to, SkillBody skillBody) {
        DamageBody body = new DamageBody();
        body.setFrom(role);
        body.setTo(to);
        body.setType(0);

        Skill skill = skillBody.getSkill();
        int type = skill.getType();

        // todo 命中判定
//        if (false) {
//            return body;
//        }

        // 暴击判定
        if (role.getCt() + skillBody.getAdditionCt() > RandomUtil.randDouble()) {
            body.setCt(true);
        }

        if (type == 1) {
            int value = role.getAtk() + skillBody.getAdditionAtk() - to.getDef();
            value = value > 0 ? (int) (value * (1 + skillBody.getAtkp())) : 1;
            if (body.isCt()) {
                value = (int) (value * (1 + role.getCt() + skillBody.getAdditionCt()));
            }
            body.setWuliValue(randomDamage(value));
        } else if (type == 2) {
            int value = role.getFatk() + skillBody.getAdditionMatk() - to.getFdef();
            value = value > 0 ? (int) (value * (1 + skillBody.getMatkp())) : 1;
            if (body.isCt()) {
                value = (int) (value * (1 + role.getCt() + skillBody.getAdditionCt()));
            }
            body.setMofaValue(randomDamage(value));
        } else if (type == 3) {
            int v1 = role.getAtk() + skillBody.getAdditionAtk() - to.getDef();
            int v2 = role.getFatk() + skillBody.getAdditionMatk() - to.getFdef();
            v1 = v1 > 0 ? (int) (v1 * (1 + skillBody.getAtkp())) : 1;
            v2 = v2 > 0 ? (int) (v2 * (1 + skillBody.getMatkp())) : 1;
            if (body.isCt()) {
                v1 = (int) (v1 * (1 + role.getCt() + skillBody.getAdditionCt()));
                v2 = (int) (v2 * (1 + role.getCt() + skillBody.getAdditionCt()));
            }
            body.setWuliValue(randomDamage(v1));
            body.setMofaValue(randomDamage(v2));
        } else if (type == 4) {
            body.setType(1);
        }

        // todo 处理属性伤害

        return body;
    }

}
