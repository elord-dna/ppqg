package com.hzl.base.attacker;

/**
 * 伤害类型枚举
 */
public class DamageType {
    private DamageFamily damageFamily;
    private ElementType elementType;

    public DamageType(DamageFamily damageFamily, ElementType elementType) {
        this.damageFamily = damageFamily;
        this.elementType = elementType;
    }

    enum ElementType {
        NONE,      // 无
        FIRE,      // 火
        WATER,     // 水
        ELECTRIC,  // 电
        WIND,      // 风
        LIGHT,     // 光
        DARK,      // 暗
        THUNDER,   // 雷
        LIFE,      // 生命
        EARTH,     // 地
        METAL,     // 金属
        POISON;    // 毒
    }

    enum DamageFamily {
        HEAL,      // 治疗
        WULI,      // 物理伤害
        MOFA,      // 魔法伤害
        HUNHE,     // 混合伤害
        HUNDUN;    // 混沌伤害
    }
}
