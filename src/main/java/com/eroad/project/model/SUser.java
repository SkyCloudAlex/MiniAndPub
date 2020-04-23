package com.eroad.project.model;

import javax.persistence.*;

@Table(name = "s_user")
public class SUser {
    /**
     * 用户编号
     */
    @Id
    @Column(name = "u_id")
    private String uId;

    /**
     * 姓名
     */
    @Column(name = "u_name")
    private String uName;

    /**
     * 用户名
     */
    @Column(name = "u_username")
    private String uUsername;

    /**
     * 微信昵称
     */
    @Column(name = "u_nickname")
    private String uNickname;

    /**
     * 手机号
     */
    @Column(name = "u_mobile_no")
    private String uMobileNo;

    /**
     * 登录密码
     */
    @Column(name = "u_password")
    private String uPassword;

    /**
     * 用户状态.0=禁用;1=启用
     */
    @Column(name = "u_status")
    private String uStatus;

    /**
     * 公司编号
     */
    @Column(name = "u_co_id")
    private String uCoId;
    
    /**
     * 部门编号
     */
    @Column(name = "u_dept_id")
    private String uDeptId;

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
     * 获取姓名
     *
     * @return u_name - 姓名
     */
    public String getuName() {
        return uName;
    }

    /**
     * 设置姓名
     *
     * @param uName 姓名
     */
    public void setuName(String uName) {
        this.uName = uName;
    }

    /**
     * 获取用户名
     *
     * @return u_username - 用户名
     */
    public String getuUsername() {
        return uUsername;
    }

    /**
     * 设置用户名
     *
     * @param uUsername 用户名
     */
    public void setuUsername(String uUsername) {
        this.uUsername = uUsername;
    }

    /**
     * 获取微信昵称
     *
     * @return u_nickname - 微信昵称
     */
    public String getuNickname() {
        return uNickname;
    }

    /**
     * 设置微信昵称
     *
     * @param uNickname 微信昵称
     */
    public void setuNickname(String uNickname) {
        this.uNickname = uNickname;
    }

    /**
     * 获取手机号
     *
     * @return u_mobile_no - 手机号
     */
    public String getuMobileNo() {
        return uMobileNo;
    }

    /**
     * 设置手机号
     *
     * @param uMobileNo 手机号
     */
    public void setuMobileNo(String uMobileNo) {
        this.uMobileNo = uMobileNo;
    }

    /**
     * 获取登录密码
     *
     * @return u_password - 登录密码
     */
    public String getuPassword() {
        return uPassword;
    }

    /**
     * 设置登录密码
     *
     * @param uPassword 登录密码
     */
    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    /**
     * 获取用户状态.0=禁用;1=启用
     *
     * @return u_status - 用户状态.0=禁用;1=启用
     */
    public String getuStatus() {
        return uStatus;
    }

    /**
     * 设置用户状态.0=禁用;1=启用
     *
     * @param uStatus 用户状态.0=禁用;1=启用
     */
    public void setuStatus(String uStatus) {
        this.uStatus = uStatus;
    }

    /**
     * 获取所属组织编号
     *
     * @return o_id - 所属组织编号
     */
    public String getUCoId() {
        return uCoId;
    }

    /**
     * 设置所属组织编号
     *
     * @param oId 所属组织编号
     */
    public void setUCoId(String uCoId) {
        this.uCoId = uCoId;
    }
    
    public String getUDeptId() {
    	return uDeptId;
    }
    
    public void setUDeptId(String uDeptId) {
    	this.uDeptId = uDeptId;
    }
}