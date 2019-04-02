package com.hzl.base.attacker;

import com.hzl.base.role.Role;

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
