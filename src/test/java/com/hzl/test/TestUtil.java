package com.hzl.test;

import com.hzl.base.employ.Fighter;
import com.hzl.base.role.FightRole;
import com.hzl.base.skill.NormalAttack;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class TestUtil {

    /**
     * 生成一个随机的战斗角色
     *
     * @param lv
     * @return
     */
    public static FightRole randomRole(int lv) {
        Random random = new Random();
        FightRole role = new FightRole();
        role.setLv(lv);
        role.setAtk(5 + Math.max(random.nextInt(lv + 1), 2) * (lv + 1));
        role.setDef(3 + Math.max(random.nextInt(lv + 1), 2) * lv);
        role.setMatk(4 + Math.max(random.nextInt(lv + 1), 2) * (lv + 1));
        role.setMdef(2 + Math.max(random.nextInt(lv + 1), 2) * lv + 1);
        role.setMhp(50 + random.nextInt(lv) * lv);
        role.setMmp(20 + random.nextInt(lv) * lv);

        role.setSd(random.nextInt(lv) + 10);

        role.setChp(role.getMhp());
        role.bindActiveSkill(new NormalAttack());
        return role;
    }

    public static  <T extends FightRole> T randomRole(int lv, Class<T> clazz) {
        Random random = new Random();
//        clazz.getConstructor(null);
        T role = null;
        try {
            role = clazz.getConstructor(null).newInstance(null);
            role.setLv(lv);
            role.setAtk(5 + Math.max(random.nextInt(lv + 1), 2) * (lv + 1));
            role.setDef(3 + Math.max(random.nextInt(lv + 1), 2) * lv);
            role.setMatk(4 + Math.max(random.nextInt(lv + 1), 2) * (lv + 1));
            role.setMdef(2 + Math.max(random.nextInt(lv + 1), 2) * lv + 1);
            role.setMhp(50 + random.nextInt(lv) * lv);
            role.setMmp(20 + random.nextInt(lv) * lv);

            role.setSd(random.nextInt(lv) + 10);

            role.setChp(role.getMhp());

            role.bindActiveSkill(new NormalAttack());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }
}
