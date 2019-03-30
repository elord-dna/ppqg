package com.hzl.base.attacker;

import com.hzl.base.Role;

public interface Attacker {

    default void attacker() {
        // todo
    }

    default boolean attacker(Role a, Role b) {
        if (b.isAlive()) {

        }
        return false;
    }
}
