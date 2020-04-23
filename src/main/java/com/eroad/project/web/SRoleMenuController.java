package com.eroad.project.web;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eroad.project.core.Result;
import com.eroad.project.core.ResultGenerator;
import com.eroad.project.model.SRoleMenu;
import com.eroad.project.service.SRoleMenuService;
import com.eroad.project.service.SRoleService;
import com.eroad.project.util.CommonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* Created by cyt on 2018/12/11.
*/
@RestController
@RequestMapping("/s/role/menu")
public class SRoleMenuController {
	@Resource
    private SRoleMenuService roleMenuService;
    
    @Resource
    private SRoleService roleService;

    @PostMapping("/add")
    public Result add(SRoleMenu roleMenu) {
        roleMenuService.save(roleMenu);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        roleMenuService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SRoleMenu roleMenu) {
        roleMenuService.update(roleMenu);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        SRoleMenu roleMenu = roleMenuService.findById(id);
        return ResultGenerator.genSuccessResult(roleMenu);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SRoleMenu> list = roleMenuService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    
    @PostMapping("/setRoleMenu")
    public Result setRoleMenu(String rId, String mIds) {
    	if (roleService.findById(rId) != null) {
    		String[] mIdArr = mIds.split(",");
    		if (mIdArr != null && mIdArr.length > 0) {
    			// 清空角色已关联菜单
    			roleMenuService.deleteByRId(rId);
    			
    			// 循环插入角色关联菜单
    			for (String mId: mIds.split(",")) {
        			SRoleMenu roleMenu = new SRoleMenu();
        			roleMenu.setRmId(CommonUtil.getUUID());
        			roleMenu.setrId(rId);
        			roleMenu.setmId(mId);
        			
        			roleMenuService.save(roleMenu);
        		}
    		}
    	}
    	
    	return ResultGenerator.genSuccessResult();
    }
}
