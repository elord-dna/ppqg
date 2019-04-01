package com.hzl.base.listener;

/**
 * 战斗事件的一些监听器
 */
public interface FightListener {

    /**
     * 进入战斗触发
     */
    void onFight();

    /**
     * 被攻击时候触发
     */
    void onAtk();

    /**
     * 被指定为攻击目标的时候触发
     */
    void onFocus();

    /**
     * 被治疗时触发
     */
    void onHeal();

    /**
     * 死亡时触发
     */
    void onDeath();
}
