package com.hzl.base;

import com.hzl.base.attacker.Attacker;

import java.util.UUID;

public class Role {
    private String id; // 角色id，玩家、NPC都有唯一都id
    private String name;  // 角色名称
    private int lv = 0;  // 角色等级，最低等级0
    private int mhp = 1;  // 角色最大生命值
    private int mmp = 0;  // 角色最大法力值
    private int chp;  // 角色当前生命值
    private int cmp;  // 角色当前法力值

    private int atk = 0;  // 角色物理攻击力
    private int def = 0;  // 角色物理防御力
    private int matk = 0;  // 角色魔法攻击力
    private int mdef = 0;  // 角色魔法防御力

    private int sd = 1;  // 角色速度
    private int startPos = 0;  // 角色初始位置，数值越大则越可能先行动

    private Attacker attacker;

    public Role() {
        this("test");
    }

    public Role(String name) {
        this(name, 0, 1, 0);
    }

    public Role(String name, int lv, int mhp, int mmp) {
        this(name, 0, mhp, mmp, 0, 0, 0, 0);
    }

    public Role(String name, int lv, int mhp, int mmp, int atk, int def, int matk, int mdef) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.lv = lv;
        this.mhp = mhp;
        this.chp = mhp;
        this.mmp = mmp;
        this.cmp = mmp;
        this.atk = atk;
        this.def = def;
        this.matk = matk;
        this.mdef = mdef;
    }

    /**
     * 判断角色是否存活
     *
     * @return true 活着
     */
    public boolean isAlive() {
        return chp > 0 && mhp > 0;
    }

    public void attack(Role role) {
        attacker.attacker(this, role);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getMhp() {
        return mhp;
    }

    public void setMhp(int mhp) {
        this.mhp = mhp;
    }

    public int getMmp() {
        return mmp;
    }

    public void setMmp(int mmp) {
        this.mmp = mmp;
    }

    public int getChp() {
        return chp;
    }

    public void setChp(int chp) {
        this.chp = chp;
    }

    public int getCmp() {
        return cmp;
    }

    public void setCmp(int cmp) {
        this.cmp = cmp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getMatk() {
        return matk;
    }

    public void setMatk(int matk) {
        this.matk = matk;
    }

    public int getMdef() {
        return mdef;
    }

    public void setMdef(int mdef) {
        this.mdef = mdef;
    }

    public int getSd() {
        return sd;
    }

    public void setSd(int sd) {
        this.sd = sd;
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }
}
