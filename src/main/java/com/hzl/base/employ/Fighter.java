package com.hzl.base.employ;

import com.hzl.base.attacker.WuliAttacker;
import com.hzl.base.battle.PopMachine;
import com.hzl.base.role.FightRole;

import java.util.List;
import java.util.Random;

public class Fighter extends FightRole {

    private List<Object> list;
    Random random = new Random();
    boolean isAtkUp = false;

    @Override
    public void init() {
        fatk = getAtk();
        fdef = getDef();
        fmatk = getMatk();
        fmdef = getMdef();
        attacker = new WuliAttacker();
    }

    /**
     * 50%概率提升攻击力
     * @param fightRoles
     */
    @Override
    public void onAttack(FightRole... fightRoles) {
        if (random.nextBoolean()) {
            this.fatk += getLv();
            isAtkUp = true;
        }
    }

    @Override
    public void afterAttack(PopMachine popMachine) {
        if (isAtkUp) {
            fatk -= getLv();
        }
        super.afterAttack(popMachine);
    }
}
