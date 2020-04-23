package com.eroad.project.web;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eroad.project.core.Result;
import com.eroad.project.core.ResultGenerator;
import com.eroad.project.model.SMenu;
import com.eroad.project.service.SMenuService;
import com.eroad.project.service.SRoleMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Condition;

/**
* Created by cyt on 2018/12/11.
*/
@RestController
@RequestMapping("/s/menu")
public class SMenuController {
	@Resource
    private SMenuService menuService;
    
    @Resource
    private SRoleMenuService roleMenuService;
    
    /**
     * 菜单移动
     * @param mId
     * @param pos up/down
     * @return
     */
    @PostMapping("/move")
    public Result move(String mId, String pos) {
    	if (StringUtils.isBlank(mId) || StringUtils.isBlank(pos)) {
    		return ResultGenerator.genFailResult("参数异常");
    	}
    	
    	menuService.move(mId, pos);
        
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 菜单新增
     * @param elsMenu
     * @return
     */
    @PostMapping("/add")
    public Result add(SMenu elsMenu) {
    	String mParentId = elsMenu.getmParentId();
    	if (StringUtils.isBlank(mParentId) || "null".contentEquals(mParentId)) {
    		mParentId = "";
    		elsMenu.setmParentId(mParentId);
    	}
    	String mId = menuService.getNewMId(mParentId);
    	
    	// 默认排序取菜单编号（同一父节点下编号+1）
    	elsMenu.setmId(mId);
    	elsMenu.setmSort(mId);
        menuService.save(elsMenu);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(String mId) {
    	// 删除角色菜单关联
    	roleMenuService.deleteRoleMenu(mId, "mId");
    	
        menuService.deleteById(mId);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SMenu elsMenu) {
		menuService.update(elsMenu);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(String mId) {
        SMenu elsMenu = menuService.findById(mId);
        return ResultGenerator.genSuccessResult(elsMenu);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(SMenu.class);
        condition.orderBy("mSort").asc();
        List<SMenu> list = menuService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
