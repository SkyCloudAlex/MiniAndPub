package com.eroad.project.web;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eroad.project.core.Result;
import com.eroad.project.core.ResultGenerator;
import com.eroad.project.model.SOrganization;
import com.eroad.project.service.SOrganizationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Condition;

/**
* Created by cyt on 2018/12/11.
*/
@RestController
@RequestMapping("/s/organization")
public class SOrganizationController {
	@Resource
    private SOrganizationService organizationService;

    @PostMapping("/add")
    public Result add(SOrganization organization) {
    	String oParentId = organization.getoParentId();
		String oId = organizationService.getNewOId(oParentId);
    	
    	// 默认排序取组织结构编号（同一父节点下编号+1）
    	organization.setoId(oId);
    	organization.setoSort(oId);
    	
        organizationService.save(organization);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(String oId) {
        organizationService.deleteById(oId);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SOrganization organization) {
        organizationService.update(organization);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        SOrganization organization = organizationService.findById(id);
        return ResultGenerator.genSuccessResult(organization);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(SOrganization.class);
        condition.orderBy("oSort").asc();
        List<SOrganization> list = organizationService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    
    /**
     * 可用公司列表
     * @return
     */
    @PostMapping("/coList")
    public Result coList() {
        List<Map<String, Object>> list = organizationService.getCoList();
        return ResultGenerator.genSuccessResult(list);
    }
    
    /**
     * 可用部门列表
     * @param oId
     * @return
     */
    @PostMapping("/deptList")
    public Result deptList(String oId) {
    	if (StringUtils.isBlank(oId)) {
    		return ResultGenerator.genFailResult("参数异常");
    	}
        List<Map<String, Object>> list = organizationService.getDeptList(oId);
        return ResultGenerator.genSuccessResult(list);
    }
    
    /**
     * 组织机构移动
     * @param oId
     * @param pos up/down
     * @return
     */
    @PostMapping("/move")
    public Result move(String oId, String pos) {
    	if (StringUtils.isBlank(oId) || StringUtils.isBlank(pos)) {
    		return ResultGenerator.genFailResult("参数异常");
    	}
    	
    	organizationService.move(oId, pos);
        
        return ResultGenerator.genSuccessResult();
    }
    
}
