package com.hzl.base.battle;

import com.hzl.base.attacker.DamageBody;
import com.hzl.base.attacker.SkillBody;
import com.hzl.base.role.FightRole;
import com.hzl.base.skill.NormalAttack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 战斗管理器
 */
public class BattleManager {
    private String id;

    private Map<Integer, Team> teams;
    private Map<String, Team> rmt;  // role map team 角色队伍映射
    private Map<String, Integer> tmt;  // team map team 队伍与队伍的映射
    private static final int MAX_TEAM_SIZE = 4;  // 最大队伍数量
    private PopMachine popMachine;
    private static final long TIMESTAMP = 30;  // 时间间隔

    private Random random = new Random();

    /**
     * 对战器初始化
     */
    public void init() {
        teams = new HashMap<>(2);
        popMachine = new PopMachine();
    }

    /**
     * 向管理器中添加队伍，需要预先定义队伍
     * 推荐使用默认的队伍
     *
     * @param t
     * @return
     */
    public Team addTeam(Team t) {
        if (teams.size() >= MAX_TEAM_SIZE) {
            return teams.get(MAX_TEAM_SIZE);
        } else {
            int n = teams.size() + 1;
            teams.put(n, t);
            addTmt(t, n);
            return t;
        }
    }

    /**
     * 向管理器中添加队伍，生成默认的队伍
     *
     * @return
     */
    public Team addTeam() {
        if (teams.size() >= MAX_TEAM_SIZE) {
            return teams.get(MAX_TEAM_SIZE);
        } else {
            Team t = generateTeam();
            int n = teams.size() + 1;
            teams.put(n, t);
            addTmt(t, n);
            return t;
        }
    }

    /**
     * 向管理器中添加角色
     * 如果没有给定队伍，则会新建一支队伍；
     * 如果队伍数量超过上限，则会添加到最后一支队伍
     *
     * @param r
     * @return
     */
    public FightRole addRole(FightRole r) {
        Team t = addTeam();
        addRmt(r, t);
        popMachine.add(r);
        return r;
    }

    public FightRole addRole(FightRole r, Team t) {
        if (teams.containsValue(t)) {
            addRmt(r, t);
            popMachine.add(r);
        } else {
            System.err.println("添加失败，没有该队伍");
        }
        return r;
    }

    public FightRole addRole(FightRole r, int n) {
        if (teams.containsKey(n)) {
            teams.get(n).add(r);
            popMachine.add(r);
        } else {
            System.err.println("添加失败，没有该队伍");
        }
        return r;
    }

    public void start() {
        // todo
    }

    public void afterHandle(FightRole role, FightRole to, DamageBody db) {
        to.afterCasted(db);
        role.afterCastedHandle(db);
    }

    public void autoStart() throws InterruptedException {
        // todo
        while (checkGameOver()) {
            Thread.sleep(TIMESTAMP);
            // 当前是否有角色能够行动
            FightRole role = popMachine.pop(TIMESTAMP);
            // todo buff check
            if (role != null) {
                // 攻击一个随机的敌人
                FightRole enemy = getRandomOpponentRole(role);
//                System.out.println(String.format("[%s]攻击[%s]", role.getName(), enemy.getName()));
                // 改一些方法测试，将attack改为cast模式
//                role.attack(enemy);
//                SkillBody body = role.cast(new NormalAttack(), enemy);  // 攻击，攻击方生成攻击信息
                SkillBody body = role.castRandom(enemy);
                System.out.println(String.format("[%s]使用[%s]攻击[%s]", role.getName(), body.getSkill().getSkillId(), enemy.getName()));
                enemy.onAimed(body);                                    // 被攻击方处理攻击信息
                DamageBody db = body.getSkill().onEffect(role, enemy, body);  // 生成伤害信息
                afterHandle(role, enemy, db);                                 // 处理伤害信息

                // 用简单的方法替代，afterAttack方法不应该带popMachine
//                role.afterAttack(popMachine);
                popMachine.updatePop(role, 60);
            }
        }
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

    /**
     * 判断游戏是否结束，目前判断是否只剩下不到一个队伍又存活队员
     *
     * @return
     */
    private boolean checkGameOver() {
        int leftTeam = 0;
        Team team = null;
        for (Map.Entry<Integer, Team> entry : teams.entrySet()) {
            Team t = entry.getValue();
            if (t.isAlive()) {
                leftTeam++;
                team = t;
            }
        }
        if (leftTeam > 1) {
            return true;
        } else if (leftTeam == 1) {
            System.err.println(String.format("对战结束, 胜利队伍是[%s]", team.getName()));
        } else {
            System.err.println(String.format("对战结束, 没有胜利队伍"));
        }
        return false;
    }

    /**
     * 随机返回一个随机敌方队伍
     * 队伍中必须有存活的队员
     *
     * @param role
     * @return
     */
    private Team getOpponentTeam(FightRole role) {
        Team t = getRoleTeam(role);
        if (t == null) {
            return null;
        }
        return getOpponentTeam(t);
    }

    /**
     * 随机返回一个随机敌方队伍
     * 队伍中必须有存活的队员
     *
     * @param team
     * @return
     */
    private Team getOpponentTeam(Team team) {
        if (tmt == null || !tmt.containsKey(team.getId())) {
            return null;
        }
        int n = tmt.get(team.getId());
        int rand = random.nextInt(teams.size()) + 1;
//        System.out.println(rand);
//        System.out.println(teams.keySet());
//        System.out.println(teams.get(rand) == null);
        while (rand == n || !teams.get(rand).isAlive()) {
            rand = random.nextInt(teams.size()) + 1;
        }
        return teams.get(rand);
    }

    /**
     * 获取一个随机的敌人
     *
     * @param role
     * @return
     */
    private FightRole getRandomOpponentRole(FightRole role) {
        Team team = getOpponentTeam(role);
        if (team != null) {
            List<FightRole> roles = team.getRoleList();
            FightRole r = null;
            while (r == null || !r.isAlive()) {
                int rand = random.nextInt(roles.size());
                r = roles.get(rand);
            }
            return r;
        }
        return null;
    }

    /**
     * 返回队员所在的队伍
     *
     * @param role
     * @return
     */
    private Team getRoleTeam(FightRole role) {
        if (rmt == null) {
            return null;
        }
        return rmt.get(role.getId());
    }

    /**
     * 添加队伍与队员，队员与队伍之间的对应关系
     *
     * @param r
     * @param t
     */
    private void addRmt(FightRole r, Team t) {
        t.add(r);
        if (rmt == null) {
            rmt = new HashMap<>();
        }
        rmt.put(r.getId(), t);
    }

    /**
     * 添加队伍与队伍的映射
     *
     * @param t
     * @param n
     */
    private void addTmt(Team t, int n) {
        if (tmt == null) {
            tmt = new HashMap<>();
        }
        tmt.put(t.getId(), n);
    }

}
