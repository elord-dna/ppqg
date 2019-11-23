package com.hzl.base.attacker;

import com.hzl.base.attacker.element.FireElement;
import com.hzl.base.attacker.element.LightingElement;
import com.hzl.base.role.FightRole;
import com.hzl.base.skill.ActiveSkill;
import com.hzl.base.skill.Skill;

import java.util.List;

// 这是一个使用方的数据结构载体
// 需要一些受到伤害时的触发器来处理这个数据
public class SkillBody {
    private FightRole from; // 技能来源
    private FightRole to;   // 技能当前目标
    private FightRole next; // 技能下个目标
    private List<FightRole> targets;  // 技能的目标列表

    private ActiveSkill skill;  // 使用的技能
    private Skill additionSkill;  // 附带的技能，最多为一个，废案 // notice

    private int additionAtk = 0;  // 额外攻击力
    private double atkp = 0;  // 额外攻击力百分比

    private int additionMatk = 0;
    private double matkp = 0;

    private int additionHeal = 0;  // 额外治疗
    private double ahp = 0;   // 额外治疗百分比

    private int mainElement = 0; // 主要元素，todo 需要仔细定义
    private int additionType = -1; // 附带技能的元素，没有附带技能时不生效

    private double additionCt = 0;  // 额外暴击率
    private double additionCte = 0;  // 额外暴击伤害


    // 暂时定义两种属性 并默认 0为fire 1为lighting
    private FireElement fireElement;
    private LightingElement lightingElement;

    public FightRole getFrom() {
        return from;
    }

    public void setFrom(FightRole from) {
        this.from = from;
    }

    public FightRole getTo() {
        return to;
    }

    public void setTo(FightRole to) {
        this.to = to;
    }

    public FightRole getNext() {
        return next;
    }

    public void setNext(FightRole next) {
        this.next = next;
    }

    public List<FightRole> getTargets() {
        return targets;
    }

    public void setTargets(List<FightRole> targets) {
        this.targets = targets;
    }

    public ActiveSkill getSkill() {
        return skill;
    }

    public void setSkill(ActiveSkill skill) {
        this.skill = skill;
    }

    public Skill getAdditionSkill() {
        return additionSkill;
    }

    public void setAdditionSkill(Skill additionSkill) {
        this.additionSkill = additionSkill;
    }

    public int getAdditionAtk() {
        return additionAtk;
    }

    public void setAdditionAtk(int additionAtk) {
        this.additionAtk = additionAtk;
    }

    public double getAtkp() {
        return atkp;
    }

    public void setAtkp(double atkp) {
        this.atkp = atkp;
    }

    public int getAdditionMatk() {
        return additionMatk;
    }

    public void setAdditionMatk(int additionMatk) {
        this.additionMatk = additionMatk;
    }

    public double getMatkp() {
        return matkp;
    }

    public void setMatkp(double matkp) {
        this.matkp = matkp;
    }

    public int getAdditionHeal() {
        return additionHeal;
    }

    public void setAdditionHeal(int additionHeal) {
        this.additionHeal = additionHeal;
    }

    public double getAhp() {
        return ahp;
    }

    public void setAhp(double ahp) {
        this.ahp = ahp;
    }

    public int getMainElement() {
        return mainElement;
    }

    public void setMainElement(int mainElement) {
        this.mainElement = mainElement;
    }

    public int getAdditionType() {
        return additionType;
    }

    public void setAdditionType(int additionType) {
        this.additionType = additionType;
    }

    public FireElement getFireElement() {
        return fireElement;
    }

    public void setFireElement(FireElement fireElement) {
        this.fireElement = fireElement;
    }

    public LightingElement getLightingElement() {
        return lightingElement;
    }

    public void setLightingElement(LightingElement lightingElement) {
        this.lightingElement = lightingElement;
    }
}
