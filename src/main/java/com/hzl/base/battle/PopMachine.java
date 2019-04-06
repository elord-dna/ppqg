package com.hzl.base.battle;

import com.hzl.base.role.FightRole;
import com.hzl.base.role.Role;

import java.util.*;

/**
 * 控制行动顺序
 */
public class PopMachine {

    private static final double MAX_POS = 100;
    private List<RolePosition> positions = new ArrayList<>();

    /**
     * 将角色添加到速度控制器
     *
     * @param role
     */
    public void add(FightRole role) {
        RolePosition pos = generate(role);
        positions.add(pos);
    }

    /**
     * 返回最前面到一个(暂时使用带参数的方法
     *
     * @return
     */
    public FightRole pop() {
        return null;
    }

    /**
     * 返回最前到一个角色
     *
     * @param delta 时间间隔
     * @return 返回空表示目前没有可行动角色
     */
    public FightRole pop(long delta) {
        for (RolePosition pos : positions) {
            pos.pos += delta * pos.role.getSd() / 1000.0;
//            System.out.println("position mark " + pos.role.getName() + ": " + pos.pos);
        }
//            positions.forEach(pos -> {
//                pos.pos += delta * pos.role.getSd() / 1000;
//            });
        Collections.sort(positions);
        if (positions.get(0).pos >= MAX_POS) {
            return positions.get(0).role;
        }
        return null;
    }

    public void updatePop(FightRole role, int move) {
        for (RolePosition position : positions) {
            if (position.role.equals(role)) {
                position.pos -= move;
                System.out.println(position.role.getName() + ": " + position.pos);
                break;
            }
        }
    }

    /**
     * 检查添加是否合法，一个角色只能被添加一次
     *
     * @param role
     * @return true 合法; false 非法
     */
    private boolean validCheck(FightRole role) {
        if (positions == null || positions.isEmpty()) {
            return true;
        }
        for (RolePosition pos : positions) {
            if (pos.id.equals(role.getId())) {
                return false;
            }
        }
        return true;
    }

    private RolePosition generate(FightRole role) {
        RolePosition pos = new RolePosition();
        pos.id = role.getId();
        pos.pos = role.getStartPos();
        pos.role = role;
        return pos;
    }


    class RolePosition implements Comparable<RolePosition> {
        String id;
        double pos = 0; // 坐标
        FightRole role;

        @Override
        public int compareTo(RolePosition o) {
            if (pos > o.pos) {
                return -1;
            } else if (pos < o.pos) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
