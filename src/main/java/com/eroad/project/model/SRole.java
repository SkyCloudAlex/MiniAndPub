package com.eroad.project.model;

import javax.persistence.*;

@Table(name = "s_role")
public class SRole {
    /**
     * 角色编号
     */
    @Id
    @Column(name = "r_id")
    private String rId;

    /**
     * 角色名称
     */
    @Column(name = "r_name")
    private String rName;

    /**
     * 角色说明
     */
    @Column(name = "r_comment")
    private String rComment;

    /**
     * 角色状态.0=禁用;1=启用
     */
    @Column(name = "r_status")
    private String rStatus;

    /**
     * 获取角色编号
     *
     * @return r_id - 角色编号
     */
    public String getrId() {
        return rId;
    }

    /**
     * 设置角色编号
     *
     * @param rId 角色编号
     */
    public void setrId(String rId) {
        this.rId = rId;
    }

    /**
     * 获取角色名称
     *
     * @return r_name - 角色名称
     */
    public String getrName() {
        return rName;
    }

    /**
     * 设置角色名称
     *
     * @param rName 角色名称
     */
    public void setrName(String rName) {
        this.rName = rName;
    }

    /**
     * 获取角色说明
     *
     * @return r_comment - 角色说明
     */
    public String getrComment() {
        return rComment;
    }

    /**
     * 设置角色说明
     *
     * @param rComment 角色说明
     */
    public void setrComment(String rComment) {
        this.rComment = rComment;
    }

    /**
     * 获取角色状态.0=禁用;1=启用
     *
     * @return r_status - 角色状态.0=禁用;1=启用
     */
    public String getrStatus() {
        return rStatus;
    }

    /**
     * 设置角色状态.0=禁用;1=启用
     *
     * @param rStatus 角色状态.0=禁用;1=启用
     */
    public void setrStatus(String rStatus) {
        this.rStatus = rStatus;
    }
}