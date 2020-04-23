package com.eroad.project.model;

import javax.persistence.*;

@Table(name = "s_organization")
public class SOrganization {
    /**
     * 结构编号
     */
    @Id
    @Column(name = "o_id")
    private String oId;

    /**
     * 上级结构编号
     */
    @Column(name = "o_parent_id")
    private String oParentId;

    /**
     * 组织名称
     */
    @Column(name = "o_name")
    private String oName;

    /**
     * 组织类型.0=公司;1=部门
     */
    @Column(name = "o_type")
    private String oType;

    /**
     * 排序
     */
    @Column(name = "o_sort")
    private String oSort;

    /**
     * 状态.0=禁用;1=启用
     */
    @Column(name = "o_status")
    private String oStatus;

    /**
     * 获取结构编号
     *
     * @return o_id - 结构编号
     */
    public String getoId() {
        return oId;
    }

    /**
     * 设置结构编号
     *
     * @param oId 结构编号
     */
    public void setoId(String oId) {
        this.oId = oId;
    }

    /**
     * 获取上级结构编号
     *
     * @return o_parent_id - 上级结构编号
     */
    public String getoParentId() {
        return oParentId;
    }

    /**
     * 设置上级结构编号
     *
     * @param oParentId 上级结构编号
     */
    public void setoParentId(String oParentId) {
        this.oParentId = oParentId;
    }

    /**
     * 获取组织名称
     *
     * @return o_name - 组织名称
     */
    public String getoName() {
        return oName;
    }

    /**
     * 设置组织名称
     *
     * @param oName 组织名称
     */
    public void setoName(String oName) {
        this.oName = oName;
    }

    /**
     * 获取组织类型.0=公司;1=部门
     *
     * @return o_type - 组织类型.0=公司;1=部门
     */
    public String getoType() {
        return oType;
    }

    /**
     * 设置组织类型.0=公司;1=部门
     *
     * @param oType 组织类型.0=公司;1=部门
     */
    public void setoType(String oType) {
        this.oType = oType;
    }

    /**
     * 获取排序
     *
     * @return o_sort - 排序
     */
    public String getoSort() {
        return oSort;
    }

    /**
     * 设置排序
     *
     * @param oSort 排序
     */
    public void setoSort(String oSort) {
        this.oSort = oSort;
    }

    /**
     * 获取状态.0=禁用;1=启用
     *
     * @return o_status - 状态.0=禁用;1=启用
     */
    public String getoStatus() {
        return oStatus;
    }

    /**
     * 设置状态.0=禁用;1=启用
     *
     * @param oStatus 状态.0=禁用;1=启用
     */
    public void setoStatus(String oStatus) {
        this.oStatus = oStatus;
    }
}