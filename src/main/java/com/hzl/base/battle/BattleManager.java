package com.hzl.base.battle;

import com.hzl.base.Role;

import java.util.HashMap;
import java.util.Map;

/**
 * 战斗管理器
 */
public class BattleManager {
    private String id;

    private Map<Integer, Team> teams;
    private static final int MAX_TEAM_SIZE = 4;  // 最大队伍数量

    /**
     * 对战器初始化
     */
    public void init() {
        teams = new HashMap<>(2);
    }

    /**
     * 向管理器中添加队伍，需要预先定义队伍
     * 推荐使用默认的队伍
     * @param t
     * @return
     */
    public Team addTeam(Team t) {
        if (teams.size() >= MAX_TEAM_SIZE) {
            return teams.get(MAX_TEAM_SIZE);
        } else {
            teams.put(teams.size() + 1, t);
            return t;
        }
    }

    /**
     * 向管理器中添加队伍，生成默认的队伍
     * @return
     */
    public Team addTeam() {
        if (teams.size() >= MAX_TEAM_SIZE) {
            return teams.get(MAX_TEAM_SIZE);
        } else {
            Team t = generateTeam();
            teams.put(teams.size() + 1, t);
            return t;
        }
    }

    /**
     * 向管理器中添加角色
     * 如果没有给定队伍，则会新建一支队伍；
     * 如果队伍数量超过上限，则会添加到最后一支队伍
     * @param r
     * @return
     */
    public Role addRole(Role r) {
        Team t = addTeam();
        t.add(r);
        return r;
    }

    public Role addRole(Role r, Team t) {
        if (teams.containsValue(t)) {
            t.add(r);
        } else {
            System.err.println("添加失败，没有该队伍");
        }
        return r;
    }

    public Role addRole(Role r, int n) {
        if (teams.containsKey(n)) {
            teams.get(n).add(r);
        } else {
            System.err.println("添加失败，没有该队伍");
        }
        return r;
    }

    // ------------ private ---------------

    private Team generateTeam() {
        int l = teams.size() + 1;
        String teamName = String.format("team_%d", l);
        while (!checkTeamName(teamName)) {
            teamName = String.format("team_%d", ++l);
        }
        Team team = new Team(teamName);
        return team;
    }

    private boolean checkTeamName(String name) {
        for (Team t : teams.values()) {
            if (name.equals(t.getName())) {
                return false;
            }
        }
        return true;
    }

}
