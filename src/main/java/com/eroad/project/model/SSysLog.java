package com.eroad.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_sys_log")
public class SSysLog {
    /**
     * 日志编号
     */
    @Id
    @Column(name = "l_id")
    private String lId;

    /**
     * 日志内容
     */
    @Column(name = "l_content")
    private String lContent;

    /**
     * 日志时间
     */
    @Column(name = "l_time")
    private Date lTime;

    /**
     * 操作用户
     */
    @Column(name = "u_id")
    private String uId;

    /**
     * 获取日志编号
     *
     * @return l_id - 日志编号
     */
    public String getlId() {
        return lId;
    }

    /**
     * 设置日志编号
     *
     * @param lId 日志编号
     */
    public void setlId(String lId) {
        this.lId = lId;
    }

    /**
     * 获取日志内容
     *
     * @return l_content - 日志内容
     */
    public String getlContent() {
        return lContent;
    }

    /**
     * 设置日志内容
     *
     * @param lContent 日志内容
     */
    public void setlContent(String lContent) {
        this.lContent = lContent;
    }

    /**
     * 获取日志时间
     *
     * @return l_time - 日志时间
     */
    public Date getlTime() {
        return lTime;
    }

    /**
     * 设置日志时间
     *
     * @param lTime 日志时间
     */
    public void setlTime(Date lTime) {
        this.lTime = lTime;
    }

    /**
     * 获取操作用户
     *
     * @return u_id - 操作用户
     */
    public String getuId() {
        return uId;
    }

    /**
     * 设置操作用户
     *
     * @param uId 操作用户
     */
    public void setuId(String uId) {
        this.uId = uId;
    }
}