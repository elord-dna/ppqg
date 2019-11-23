package com.hzl.base.attacker.element;

// 属性，当该属性为主属性时，对应的攻击和防御才生效
// 元素的附带伤害和伤害减少总是计算的，对应的元素伤害都要根据百dep进行调整
public abstract class AbstractElement {
    private int additionAtk = 0;  // 元素额外攻击力
    private double adp = 0;      // 元素额外攻击力百分比

    private int additionDef = 0;  // 元素额外防御力

    private int additionDam = 0; // 元素额外伤害
    private int additionAbs = 0; // 元素伤害减少

    private double dep = 0;      // 元素额外伤害减少百分比 范围为-0.5,0.5

    public int getAdditionAtk() {
        return additionAtk;
    }

    public void setAdditionAtk(int additionAtk) {
        this.additionAtk = additionAtk;
    }

    public double getAdp() {
        return adp;
    }

    // 范围为-0.5,0.5
    public void setAdp(double adp) {
        if (adp > 0.5) {
            adp = 0.5;
        } else if (adp < -0.5) {
            adp = -0.5;
        }
        this.adp = adp;
    }

    public int getAdditionDef() {
        return additionDef;
    }

    public void setAdditionDef(int additionDef) {
        this.additionDef = additionDef;
    }

    public double getDep() {
        return dep;
    }

    public void setDep(double dep) {
        this.dep = dep;
    }

    public int getAdditionDam() {
        return additionDam;
    }

    public void setAdditionDam(int additionDam) {
        this.additionDam = additionDam;
    }

    public int getAdditionAbs() {
        return additionAbs;
    }

    public void setAdditionAbs(int additionAbs) {
        this.additionAbs = additionAbs;
    }
}
