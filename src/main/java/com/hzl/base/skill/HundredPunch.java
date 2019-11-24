package com.hzl.base.skill;

import com.hzl.base.attacker.SkillBody;
import com.hzl.base.role.FightRole;

public class HundredPunch implements ActiveSkill {
    private String name = "百万吨拳击";
    private int lv = 1;
    private int cost = 0;  // 消耗
    // 允许目标 todo
    private String targetPermission;

    private int type = 1; // 物理

    public HundredPunch(int lv) {
        this.lv = lv;
    }
    /**
     * 增加额外攻击与攻击率
     * @param role
     * @return
     */
    @Override
    public SkillBody generateSkillBody(FightRole role, FightRole... to) {
        SkillBody body = new SkillBody();
        body.setFrom(role);
        body.setTo(to[0]);

        body.setAdditionAtk(2+(int)(lv*2));
        body.setAtkp(0.2 + 0.1*lv);

        body.setSkill(this);
        return body;
    }

//    @Override
//    public DamageBody onEffect(FightRole role, FightRole to, SkillBody skillBody) {
//        return null;
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
