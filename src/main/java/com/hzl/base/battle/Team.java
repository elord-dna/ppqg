package com.hzl.base.battle;

import com.hzl.base.role.FightRole;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 队伍
 */
public class Team {
    private String id;
    private String name;

    private List<FightRole> roleList;  // 队伍中所有的角色

    public Team(String name) {
        id = "team_" + UUID.randomUUID().toString();
        this.name = name;
    }

    /**
     * 向队伍中添加角色
     * @param r
     * @return
     */
    public Team add(FightRole r) {
        if (roleList == null) {
            roleList = new ArrayList<>();
        }
        roleList.add(r);
        return this;
    }

    /**
     * 检查队伍是否存在存活的队员
     * @return true 还有存活的队员; false 没有存活的队员
     */
    public boolean isAlive() {
        if (roleList == null || roleList.isEmpty()) {
            return false;
        } else {
            for (FightRole role : roleList) {
                if (role.isAlive()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<FightRole> getRoleList() {
        return roleList;
    }

}
