package com.hzl.base.buff;

import com.hzl.base.role.Role;

/**
 * buff中的内容
 */
public abstract class Buff implements IBuff {
    private String id;  // 区分不同buff的唯一id
    public String name;
    private int maxLayer;  // 最大叠加层数

    private Role from;  // 来源
    private Role to;  // 作用目标

    public BuffType type;  // 类型

    private double duringTime = -1;  // 持续时间
    private int duration = -1;  // 持续时间都计数器表达

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxLayer() {
        return maxLayer;
    }

    public void setMaxLayer(int maxLayer) {
        this.maxLayer = maxLayer;
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

    public BuffType getType() {
        return type;
    }

    public void setType(BuffType type) {
        this.type = type;
    }

    public double getDuringTime() {
        return duringTime;
    }

    public void setDuringTime(double duringTime) {
        this.duringTime = duringTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
