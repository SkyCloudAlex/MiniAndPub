package com.eroad.project.model;

import javax.persistence.*;

@Table(name = "s_role_menu")
public class SRoleMenu {
    /**
     * 关联编号
     */
    @Id
    @Column(name = "rm_id")
    private String rmId;

    /**
     * 角色编号
     */
    @Column(name = "r_id")
    private String rId;

    /**
     * 菜单编号
     */
    @Column(name = "m_id")
    private String mId;

    /**
     * 获取关联编号
     *
     * @return rm_id - 关联编号
     */
    public String getRmId() {
        return rmId;
    }

    /**
     * 设置关联编号
     *
     * @param rmId 关联编号
     */
    public void setRmId(String rmId) {
        this.rmId = rmId;
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

    /**
     * 获取菜单编号
     *
     * @return m_id - 菜单编号
     */
    public String getmId() {
        return mId;
    }

    /**
     * 设置菜单编号
     *
     * @param mId 菜单编号
     */
    public void setmId(String mId) {
        this.mId = mId;
    }
}