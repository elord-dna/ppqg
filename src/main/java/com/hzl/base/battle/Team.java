package com.hzl.base.battle;

import com.hzl.base.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 队伍
 */
public class Team {
    private String id;
    private String name;

    private List<Role> roleList;  // 队伍中所有的角色

    public Team(String name) {
        id = "team_" + UUID.randomUUID().toString();
        this.name = name;
    }

    /**
     * 向队伍中添加角色
     * @param r
     * @return
     */
    public Team add(Role r) {
        if (roleList == null) {
            roleList = new ArrayList<>();
        }
        roleList.add(r);
        return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

}
