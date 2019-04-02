package com.hzl.base.battle;

import com.hzl.base.Role;

import java.util.*;

/**
 * 控制行动顺序
 */
public class PopMachine {

    private static final double MAX_POS = 100;
    private List<RolePosition> positions;

    /**
     * 将角色添加到速度控制器
     * @param role
     */
    public void add(Role role) {
        if (positions == null) {
            positions = new ArrayList<>();
        }
        RolePosition pos = generate(role);
        positions.add(pos);
    }

    /**
     * 返回最前面到一个(暂时使用带参数的方法
     * @return
     */
    public Role pop() {
        return null;
    }

    /**
     * 返回最前到一个角色
     * @param delta 时间间隔
     * @return 返回空表示目前没有可行动角色
     */
    public Role pop(long delta) {
        positions.forEach(pos -> {
            pos.pos += delta * pos.role.getSd() / 1000;
        });
        Collections.sort(positions);
        if (positions.get(0).pos >= MAX_POS) {
            return positions.get(0).role;
        }
        return null;
    }

    /**
     * 检查添加是否合法，一个角色只能被添加一次
     * @param role
     * @return true 合法; false 非法
     */
    private boolean validCheck(Role role) {
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

    private RolePosition generate(Role role) {
        RolePosition pos = new RolePosition();
        pos.id = role.getId();
        pos.pos = role.getStartPos();
        pos.role = role;
        return pos;
    }


    class RolePosition implements Comparable<RolePosition> {
        String id;
        double pos = 0; // 坐标
        Role role;

        @Override
        public int compareTo(RolePosition o) {
            if (pos > o.pos) {
                return 1;
            }
            return 0;
        }
    }
}
