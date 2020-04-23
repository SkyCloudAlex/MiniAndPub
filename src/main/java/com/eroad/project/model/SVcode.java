package com.eroad.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_vcode")
public class SVcode {
    /**
     * 验证码编号
     */
    @Id
    @Column(name = "v_id")
    private String vId;

    /**
     * 验证码
     */
    @Column(name = "v_code")
    private String vCode;

    /**
     * 手机号
     */
    @Column(name = "v_mobile_no")
    private String vMobileNo;

    /**
     * 验证码类型
     */
    @Column(name = "v_type")
    private String vType;

    /**
     * 发送时间
     */
    @Column(name = "v_send_time")
    private Date vSendTime;

    /**
     * 过期时间
     */
    @Column(name = "v_expire_time")
    private Date vExpireTime;

    /**
     * 状态.0=未验证;1=已验证
     */
    @Column(name = "v_status")
    private String vStatus;

    /**
     * 获取验证码编号
     *
     * @return v_id - 验证码编号
     */
    public String getvId() {
        return vId;
    }

    /**
     * 设置验证码编号
     *
     * @param vId 验证码编号
     */
    public void setvId(String vId) {
        this.vId = vId;
    }

    /**
     * 获取验证码
     *
     * @return v_code - 验证码
     */
    public String getvCode() {
        return vCode;
    }

    /**
     * 设置验证码
     *
     * @param vCode 验证码
     */
    public void setvCode(String vCode) {
        this.vCode = vCode;
    }

    /**
     * 获取手机号
     *
     * @return v_mobile_no - 手机号
     */
    public String getvMobileNo() {
        return vMobileNo;
    }

    /**
     * 设置手机号
     *
     * @param vMobileNo 手机号
     */
    public void setvMobileNo(String vMobileNo) {
        this.vMobileNo = vMobileNo;
    }

    /**
     * 获取验证码类型
     *
     * @return v_type - 验证码类型
     */
    public String getvType() {
        return vType;
    }

    /**
     * 设置验证码类型
     *
     * @param vType 验证码类型
     */
    public void setvType(String vType) {
        this.vType = vType;
    }

    /**
     * 获取发送时间
     *
     * @return v_send_time - 发送时间
     */
    public Date getvSendTime() {
        return vSendTime;
    }

    /**
     * 设置发送时间
     *
     * @param vSendTime 发送时间
     */
    public void setvSendTime(Date vSendTime) {
        this.vSendTime = vSendTime;
    }

    /**
     * 获取过期时间
     *
     * @return v_expire_time - 过期时间
     */
    public Date getvExpireTime() {
        return vExpireTime;
    }

    /**
     * 设置过期时间
     *
     * @param vExpireTime 过期时间
     */
    public void setvExpireTime(Date vExpireTime) {
        this.vExpireTime = vExpireTime;
    }

    /**
     * 获取状态.0=未验证;1=已验证
     *
     * @return v_status - 状态.0=未验证;1=已验证
     */
    public String getvStatus() {
        return vStatus;
    }

    /**
     * 设置状态.0=未验证;1=已验证
     *
     * @param vStatus 状态.0=未验证;1=已验证
     */
    public void setvStatus(String vStatus) {
        this.vStatus = vStatus;
    }
}