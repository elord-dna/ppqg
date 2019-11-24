package com.hzl.base.role;

import com.hzl.base.attacker.DamageBody;

/**
 * 用来处理战斗流程的一些方法
 */
public class FightHandle {

    /**
     * 处理吸血
     * @param role
     * @param db
     */
    public static void handleHealthDrink(FightRole role, DamageBody db) {
        int hd = db.getHealthDrink(); // 吸血
        if (hd <= 0) {
            return;
        }
        int chp = role.getChp() + hd;
        if (chp > role.getMhp()) {
            chp = role.getMhp();
        }
        role.setChp(chp);
        if (hd > 0) {
            System.err.println(String.format("[%s]吸取[%s]点生命值，当前 %d/%d", role.getName(), hd, chp, role.getMhp()));
        }
    }
}
