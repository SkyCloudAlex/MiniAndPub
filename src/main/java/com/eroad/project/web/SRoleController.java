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
import com.eroad.project.model.SRole;
import com.eroad.project.service.SMenuService;
import com.eroad.project.service.SRoleMenuService;
import com.eroad.project.service.SRoleService;
import com.eroad.project.service.SUserRoleService;
import com.eroad.project.util.CommonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* Created by cyt on 2018/12/11.
*/
@RestController
@RequestMapping("/s/role")
public class SRoleController {
	 @Resource
	    private SRoleService roleService;
	    
	    @Resource
	    private SMenuService menuService;
	    
	    @Resource
	    private SUserRoleService userRoleService;
	    
	    @Resource
	    private SRoleMenuService roleMenuService;

	    @PostMapping("/add")
	    public Result add(SRole role) {
	    	role.setrId(CommonUtil.getUUID());
	        roleService.save(role);
	        return ResultGenerator.genSuccessResult();
	    }

	    @PostMapping("/delete")
	    public Result delete(String rIds) {
	    	if (StringUtils.isBlank(rIds)) {
	    		return ResultGenerator.genFailResult("参数异常");
	    	}
	    	
	    	// 删除用户角色关联
	    	userRoleService.deleteUserRole(rIds, "rId");
	    	// 删除角色菜单关联
	    	roleMenuService.deleteRoleMenu(rIds, "rId");
	    	// 删除角色
	    	roleService.deleteRoles(rIds);
	        return ResultGenerator.genSuccessResult();
	    }

	    @PostMapping("/update")
	    public Result update(SRole role) {
	        roleService.update(role);
	        return ResultGenerator.genSuccessResult();
	    }

	    @PostMapping("/detail")
	    public Result detail(String rId) {
	        SRole role = roleService.findById(rId);
	        return ResultGenerator.genSuccessResult(role);
	    }

	    @PostMapping("/list")
	    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
	        PageHelper.startPage(page, size);
	        List<Map<String, Object>> list = roleService.findAllRole();
	        PageInfo pageInfo = new PageInfo(list);
	        return ResultGenerator.genSuccessResult(pageInfo);
	    }
	    
	    @PostMapping("/getRoleMenu")
	    public Result getRoleMenu(String rId) {
	    	List<Map<String, Object>> elsMenuList = null;
	    	if (roleService.findById(rId) != null) {
	    		elsMenuList = menuService.getRoleMenu(rId);
	    	}
	    	
	    	return ResultGenerator.genSuccessResult(elsMenuList);
	    }
	    
	    @PostMapping("/roleList")
	    public Result roleList() {
	    	List<Map<String, Object>> list = roleService.roleList();
	    	return ResultGenerator.genSuccessResult(list);
	    }
}
