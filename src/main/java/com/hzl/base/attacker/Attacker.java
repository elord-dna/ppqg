package com.hzl.base.attacker;

import com.hzl.base.role.FightRole;

public interface Attacker {

    default void attacker() {
        // todo
    }

    default DamageResult attacker(FightRole a, FightRole b) {
        if (b.isAlive()) {

        }
        return null;
    }
}
