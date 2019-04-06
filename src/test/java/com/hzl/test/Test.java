package com.hzl.test;

import com.hzl.base.battle.BattleManager;
import com.hzl.base.role.FightRole;

public class Test {
    public static void main(String[] args) {
        FightRole role = TestUtil.randomRole(3);
        role.setName("role1");
        role.init();

        FightRole role2 = TestUtil.randomRole(3);
        role2.setName("role2");
        role2.init();

        BattleManager bm = new BattleManager();
        bm.init();
        bm.addRole(role);
        bm.addRole(role2);

        try {
            bm.autoStart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(role);
    }
}
