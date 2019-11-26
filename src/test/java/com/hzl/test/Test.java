package com.hzl.test;

import com.hzl.base.battle.BattleManager;
import com.hzl.base.employ.Fighter;
import com.hzl.base.role.FightRole;
import com.hzl.base.skill.BloodyHit;
import com.hzl.base.skill.HundredPunch;
import com.hzl.base.skill.LifeRecover;

public class Test {
    public static void main(String[] args) {
        FightRole role = TestUtil.randomRole(4);
        role.setName("黄培钧");
        role.init();

        FightRole role2 = TestUtil.randomRole(5);
        role2.setName("黄自力");
        role2.init();

        FightRole role3 = TestUtil.randomRole(3, Fighter.class);
        role3.setName("丑人");
        role3.init();

        BloodyHit bh = new BloodyHit(3);
        role.bindActiveSkill(bh);
        role.bindActiveSkill(new HundredPunch(2));
        role.bindActiveSkill(new LifeRecover(2));

        HundredPunch hp = new HundredPunch(4);
        role2.bindActiveSkill(hp);

        role3.bindActiveSkill(new LifeRecover(2));

        BattleManager bm = new BattleManager();
        bm.init();
        bm.addRole(role);
        bm.addRole(role2);
        bm.addRole(role3);

        try {
            bm.autoStart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(role);
    }
}
