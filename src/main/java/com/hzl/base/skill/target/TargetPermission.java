package com.hzl.base.skill.target;

/**
 * 目标允许
 */
public class TargetPermission {
    // 目标允许数量 最大值
    private int num = 1;
    private TargetType targetType;

    public TargetPermission(TargetType targetType) {
        this.targetType = targetType;
    }

    public TargetPermission(TargetType targetType, int num) {
        this.targetType = targetType;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }
}
