package com.hzl.base.attacker;

import com.hzl.base.attacker.element.EleDam;
import com.hzl.base.role.Role;

// 这是一个伤害结果的数据载体
// 需要一些伤害判定后的触发器处理这个信息
public class DamageBody {
    private int wuliValue = 0;
    private int mofaValue = 0;
    private EleDam eleDam;

    private Role from;
    private Role to;

    private int type = 0; // 0表示伤害 1表示治疗

    // 是否暴击
    private boolean ct = false;
    private boolean miss = false;

    private int healthDrink = 0;  // 如果有吸血，吸血的数值

    private int healValue = 0;  // todo 暂时不用这个数值

    /**
     * 是否命中
     * @return
     */
    public boolean isMiss() {
        return miss;
    }

    public int getWuliValue() {
        return wuliValue;
    }

    public void setWuliValue(int wuliValue) {
        this.wuliValue = wuliValue;
    }

    public int getMofaValue() {
        return mofaValue;
    }

    public void setMofaValue(int mofaValue) {
        this.mofaValue = mofaValue;
    }

    public EleDam getEleDam() {
        return eleDam;
    }

    public void setEleDam(EleDam eleDam) {
        this.eleDam = eleDam;
    }

    public int getHealValue() {
        return healValue;
    }

    public void setHealValue(int healValue) {
        this.healValue = healValue;
    }

    public Role getFrom() {
        return from;
    }

    public void setFrom(Role from) {
        this.from = from;
    }

    public Role getTo() {
        return to;
    }

    public void setTo(Role to) {
        this.to = to;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isCt() {
        return ct;
    }

    public void setCt(boolean ct) {
        this.ct = ct;
    }

    public void setMiss(boolean miss) {
        this.miss = miss;
    }

    public int getHealthDrink() {
        return healthDrink;
    }

    public void setHealthDrink(int healthDrink) {
        this.healthDrink = healthDrink;
    }
}
