package com.eroad.project.model;

import javax.persistence.*;

@Table(name = "s_user_role")
public class SUserRole {
    /**
     * 关联编号
     */
    @Id
    @Column(name = "ur_id")
    private String urId;

    /**
     * 用户编号
     */
    @Column(name = "u_id")
    private String uId;

    /**
     * 角色编号
     */
    @Column(name = "r_id")
    private String rId;

    /**
     * 获取关联编号
     *
     * @return ur_id - 关联编号
     */
    public String getUrId() {
        return urId;
    }

    /**
     * 设置关联编号
     *
     * @param urId 关联编号
     */
    public void setUrId(String urId) {
        this.urId = urId;
    }

    /**
     * 获取用户编号
     *
     * @return u_id - 用户编号
     */
    public String getuId() {
        return uId;
    }

    /**
     * 设置用户编号
     *
     * @param uId 用户编号
     */
    public void setuId(String uId) {
        this.uId = uId;
    }

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
}