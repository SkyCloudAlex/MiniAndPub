package com.eroad.project.model;

import javax.persistence.*;

@Table(name = "s_menu")
public class SMenu {
    /**
     * 菜单编号
     */
    @Id
    @Column(name = "m_id")
    private String mId;

    /**
     * 菜单名称
     */
    @Column(name = "m_name")
    private String mName;

    /**
     * 菜单说明
     */
    @Column(name = "m_comment")
    private String mComment;

    /**
     * 链接地址
     */
    @Column(name = "m_url")
    private String mUrl;

    /**
     * 菜单排序
     */
    @Column(name = "m_sort")
    private String mSort;

    /**
     * 菜单类型.0=菜单;1=功能;2=操作
     */
    @Column(name = "m_type")
    private String mType;
    
    @Column(name = "m_layout")
    private String mLayout;

    /**
     * 菜单状态.0=禁用;1=启用
     */
    @Column(name = "m_status")
    private String mStatus;
    
    @Column(name = "m_icon")
    private String mIcon;

    /**
     * 上级菜单编号
     */
    @Column(name = "m_parent_id")
    private String mParentId;

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

    /**
     * 获取菜单名称
     *
     * @return m_name - 菜单名称
     */
    public String getmName() {
        return mName;
    }

    /**
     * 设置菜单名称
     *
     * @param mName 菜单名称
     */
    public void setmName(String mName) {
        this.mName = mName;
    }

    /**
     * 获取菜单说明
     *
     * @return m_comment - 菜单说明
     */
    public String getmComment() {
        return mComment;
    }

    /**
     * 设置菜单说明
     *
     * @param mComment 菜单说明
     */
    public void setmComment(String mComment) {
        this.mComment = mComment;
    }

    /**
     * 获取链接地址
     *
     * @return m_url - 链接地址
     */
    public String getmUrl() {
        return mUrl;
    }

    /**
     * 设置链接地址
     *
     * @param mUrl 链接地址
     */
    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    /**
     * 获取菜单排序
     *
     * @return m_sort - 菜单排序
     */
    public String getmSort() {
        return mSort;
    }

    /**
     * 设置菜单排序
     *
     * @param mSort 菜单排序
     */
    public void setmSort(String mSort) {
        this.mSort = mSort;
    }

    /**
     * 获取菜单类型.0=菜单;1=功能;2=操作
     *
     * @return m_type - 菜单类型.0=菜单;1=功能;2=操作
     */
    public String getmType() {
        return mType;
    }

    /**
     * 设置菜单类型.0=菜单;1=功能;2=操作
     *
     * @param mType 菜单类型.0=菜单;1=功能;2=操作
     */
    public void setmType(String mType) {
        this.mType = mType;
    }

    /**
     * 获取菜单状态.0=禁用;1=启用
     *
     * @return m_status - 菜单状态.0=禁用;1=启用
     */
    public String getmStatus() {
        return mStatus;
    }

    /**
     * 设置菜单状态.0=禁用;1=启用
     *
     * @param mStatus 菜单状态.0=禁用;1=启用
     */
    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    /**
     * 获取上级菜单编号
     *
     * @return m_parent_id - 上级菜单编号
     */
    public String getmParentId() {
        return mParentId;
    }

    /**
     * 设置上级菜单编号
     *
     * @param mParentId 上级菜单编号
     */
    public void setmParentId(String mParentId) {
        this.mParentId = mParentId;
    }
    
    public String getmLayout() {
    	return mLayout;
    }
    
    public void setmLayout(String mLayout) {
    	this.mLayout = mLayout;
    }
    
    public String getmIcon() {
    	return mIcon;
    }
    
    public void setmIcon(String mIcon) {
    	this.mIcon = mIcon;
    }
}