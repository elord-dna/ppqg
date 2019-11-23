package com.hzl.base.buff;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来管理buff的结构
 */
public class BuffList {
    private List<Buff> debuffList;  // 负面
    private List<Buff> buffList;    // 正面, 一般

    public BuffList() {
        debuffList = new ArrayList<>();
        buffList = new ArrayList<>();
    }

    public void add(Buff buff) {
        List<Buff> list;
        if (buff.type.equals(BuffType.NEFF) || buff.type.equals(BuffType.DAMAGE)) {
            list = debuffList;
        } else {
            list = buffList;
        }
        Buff b = checkUnique(list, buff);
        if (b != null) {
            // 有重复的
            if (b.isUnique()) {
                // 如果是唯一, 选择时间长的哪那一个, 更新的那一个
                if (buff.getDuringTime() > b.getDuringTime()) {
                    list.remove(b);
                    list.add(buff);
                }
            } else {
                // 如果不唯一
            }
        }
    }

    /**
     * 检查buff是否已经存在
     *
     * @param list
     * @param buff
     * @return
     */
    private Buff checkUnique(List<Buff> list, Buff buff) {
        for (Buff b : list) {
            if (compareBuff(b, buff, buff.isUnique())) {
                return b;
            }
        }
        return null;
    }

    /**
     * 比较两个buff是否相同
     *
     * @param a
     * @param b
     * @param unique 是否唯一
     * @return true 相同
     */
    private boolean compareBuff(Buff a, Buff b, boolean unique) {
        boolean type = a.type.equals(b.type);
        boolean name = a.name.equals(b.name);
        boolean from = a.getFrom().getId().equals(b.getFrom().getId());
        boolean to = a.getTo().getId().equals(b.getTo().getId());
        if (unique) {
            return type && name && to;
        } else {
            return type && name && from && to;
        }
    }
}
