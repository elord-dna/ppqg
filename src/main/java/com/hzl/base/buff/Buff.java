package com.hzl.base.buff;

import com.hzl.base.role.Role;

/**
 * buff中的内容
 */
public class Buff {
    private String id;  // 区分不同buff的唯一id
    private String name;
    private String maxLayer;  // 最大叠加层数

    private Role from;  // 来源
    private Role to;  // 作用目标

    private int type;  // 类型
}
