package com.eroad.project.web;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eroad.project.core.Result;
import com.eroad.project.core.ResultGenerator;
import com.eroad.project.model.SMenu;
import com.eroad.project.model.SRole;
import com.eroad.project.model.SUser;
import com.eroad.project.model.SUserRole;
import com.eroad.project.model.SVcode;
import com.eroad.project.service.OAuthUserService;
import com.eroad.project.service.SMenuService;
import com.eroad.project.service.SRoleService;
import com.eroad.project.service.SSysLogService;
import com.eroad.project.service.SUserRoleService;
import com.eroad.project.service.SUserService;
import com.eroad.project.service.SVcodeService;
import com.eroad.project.util.CommonUtil;
import com.eroad.project.util.Des3Util;
import com.eroad.project.util.MD5Salt;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* Created by cyt on 2018/12/11.
*/
@RestController
@RequestMapping("/s/user")
public class SUserController {
	@Resource
    private SUserService userService;

    @Resource
    private SRoleService roleService;
    
    @Resource
    private SUserRoleService userRoleService;
    
    @Resource
    private SMenuService menuService;

    @Resource
    private SVcodeService vcodeService;
    
    @Resource
    private SSysLogService sysLogService;
    
    @Resource
    private OAuthUserService oAuthUserService;
    
    private Logger logger = Logger.getLogger(SUserController.class);
    
    private static final String DEFAULT_PASSWORD = "666666";

    /**
     * 登录
     * @param uMobileNo
     * @param uPassword
     * @param vcode
     * @return
     */
    @PostMapping("/login")
    public Result login(String uMobileNo, String uPassword, String vcode) {
    	// 参数校验
    	if (StringUtils.isBlank(uMobileNo) || StringUtils.isBlank(uPassword) || StringUtils.isBlank(vcode)) {
			return ResultGenerator.genFailResult("参数异常");
		}
    	
    	// 手机号
    	String decodeUMobileNo = "";
    	// 密码
    	String decodeUPassword = "";
    	// MD5密码
    	String encodeUPassword = "";
    	
    	try {
    		decodeUMobileNo = Des3Util.decode(uMobileNo);
    		decodeUPassword = Des3Util.decode(uPassword);
    		encodeUPassword = MD5Salt.encryptSalt(decodeUMobileNo, decodeUPassword);
    	} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResultGenerator.genFailResult("加解密异常");
		}
    	
    	// 短信验证码校验
    	String vType = "0";
    	SVcode elsVcode = vcodeService.checkVcode(uMobileNo, vcode, vType);
    	
    	if (elsVcode != null) {
    		// 短信验证码状态更新为已验证
    		elsVcode.setvStatus("1");
    		vcodeService.update(elsVcode);
    	} else {
    		return ResultGenerator.genFailResult("短信验证码错误");
    	}
    	
    	// 根据手机号和密码查询用户
    	SUser elsUser = userService.getUserByMobileAndPwd(uMobileNo, encodeUPassword);
    	
    	if (elsUser != null) {
    		if ("0".equals(elsUser.getuStatus())) {
    			return ResultGenerator.genFailResult("当前用户已被禁用，请联系超级管理员");
    		}
    		
    		String uId = elsUser.getuId();
    		// 删除认证信息
    		oAuthUserService.delOAuthClient(decodeUMobileNo);
    		
    		// 插入认证信息
    		oAuthUserService.addOAuthClient(decodeUMobileNo, decodeUPassword);
    		
    		// 是否首次登录
    		boolean firstLogin = sysLogService.firstLogin(uId);
    		
    		Map<String, Object> resultMap = new HashMap<String, Object>();
    		resultMap.put("uId", uId);
    		resultMap.put("firstLogin", firstLogin);
    		
    		// 插入运管日志
    		sysLogService.saveSysLog("系统登录", uId);
    		
    		return ResultGenerator.genSuccessResult(resultMap);
    	} else {
    		return ResultGenerator.genFailResult("用户名或密码错误");
    	}
    }
    
    /**
     * 获取用户基本信息、菜单、角色
     * @param uId
     * @return
     */
    @PostMapping("/userInfo")
    public Result userInfo(String uId) {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	if (StringUtils.isBlank(uId)) {
    		return ResultGenerator.genFailResult("参数异常");
    	}
    	
    	SUser elsUser = userService.findById(uId);
    	if (elsUser == null) {
    		return ResultGenerator.genFailResult("未查询到该用户");
    	} else {
    		resultMap.put("elsUser", elsUser);
    		
    		// 获取用户关联角色
    		List<SRole> roleList = roleService.getUserRole(uId);
    		// 获取用户关联菜单
    		List<SMenu> menuList = menuService.getUserMenu(uId);
    		
    		resultMap.put("roleList", roleList);
    		resultMap.put("menuList", menuList);
    	}
    	
        return ResultGenerator.genSuccessResult(resultMap);
    }
    
    /**
     * 修改密码
     * @param uId
     * @param uPassword
     * @param vcode
     * @return
     */
    @PostMapping("/changePwd")
    public Result changePwd(String uId, String uPassword, String vcode) {
    	if (StringUtils.isBlank(uId) || StringUtils.isBlank(uPassword) || StringUtils.isBlank(vcode)) {
    		return ResultGenerator.genFailResult("参数异常");
    	}
    	
    	SUser elsUser = userService.findById(uId);
    	if (elsUser == null) {
    		return ResultGenerator.genFailResult("未查询到该用户");
    	} else {
    		String uMobileNo = elsUser.getuMobileNo();
    		String decodeUPassword = "";
        	String encodeUPassword = "";
        	String decodeUMobileNo = "";
        	try {
    			decodeUPassword = Des3Util.decode(uPassword);
    			decodeUMobileNo = Des3Util.decode(uMobileNo);
    			encodeUPassword = MD5Salt.encryptSalt(decodeUMobileNo, decodeUPassword);
    		} catch (Exception e) {
    			logger.error(e.getMessage(), e);
    			return ResultGenerator.genFailResult("加解密异常");
    		}
        	
        	// 短信验证码校验
        	String vType = "4";
        	SVcode elsVcode = vcodeService.checkVcode(uMobileNo, vcode, vType);
        	
        	if (elsVcode != null) {
        		// 短信验证码状态更新为已验证
        		elsVcode.setvStatus("1");
        		vcodeService.update(elsVcode);
        	} else {
        		return ResultGenerator.genFailResult("短信验证码错误");
        	}
        	
        	// 修改密码
        	elsUser = new SUser();
        	elsUser.setuId(uId);
        	elsUser.setuPassword(encodeUPassword);
        	userService.update(elsUser);
        	
        	// 删除认证信息
    		oAuthUserService.delOAuthClient(decodeUMobileNo);
    		
    		// 插入认证信息
    		oAuthUserService.addOAuthClient(decodeUMobileNo, decodeUPassword);
    	}
    	
        return ResultGenerator.genSuccessResult();
    }
    
    @PostMapping("/logout")
    public Result logout(String uId) {
    	if (StringUtils.isBlank(uId)) {
    		return ResultGenerator.genFailResult("参数异常");
    	}
    	
    	SUser elsUser = userService.findById(uId);
    	if (elsUser != null) {
    		// 插入运管日志
    		sysLogService.saveSysLog("系统注销", uId);
    	}
    	
        return ResultGenerator.genSuccessResult();
    }
    
    @PostMapping("/add")
    public Result add(SUser elsUser, String rId) {
    	String uId = CommonUtil.getUUID();
    	if (StringUtils.isBlank(rId)) {
    		return ResultGenerator.genFailResult("参数异常");
    	} else {
    		// 添加用户基本信息
    		try {
				elsUser.setuPassword(MD5Salt.encryptSalt(Des3Util.decode(elsUser.getuMobileNo()), DEFAULT_PASSWORD));
			} catch (Exception e) {
				e.printStackTrace();
			}
    		elsUser.setuId(uId);
    		userService.save(elsUser);
    		// 添加用户角色关联
    		SUserRole elsUserRole = new SUserRole();
    		elsUserRole.setUrId(CommonUtil.getUUID());
    		elsUserRole.setuId(uId);
    		elsUserRole.setrId(rId);
    		userRoleService.save(elsUserRole);
    	}
        
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(String uIds) {
    	if (StringUtils.isBlank(uIds)) {
    		return ResultGenerator.genFailResult("参数异常");
    	}
    	
    	// 删除用户角色关联
    	userRoleService.deleteUserRole(uIds, "uId");
    	// 删除用户
        userService.deleteUsers(uIds);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SUser elsUser, String rId) {
    	String uId = elsUser.getuId();
    	if (StringUtils.isBlank(uId)) {
    		return ResultGenerator.genFailResult("参数异常");
    	}
    	
    	// 修改用户基本信息
        userService.update(elsUser);
        // 清空用户角色关联
        if (StringUtils.isNotBlank(rId)) {
        	userRoleService.deleteUserRole(uId, "uId");
        	// 新增用户角色关联
            SUserRole elsUserRole = new SUserRole();
            elsUserRole.setUrId(CommonUtil.getUUID());
            elsUserRole.setuId(uId);
            elsUserRole.setrId(rId);
            userRoleService.save(elsUserRole);
        }
        
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(String uId) {
        Map<String, Object> elsUser = userService.findUserById(uId);
        return ResultGenerator.genSuccessResult(elsUser);
    }
    
    @PostMapping("/resetPwd")
    public Result resetPwd(String uId) {
    	SUser elsUser = userService.findById(uId);
    	
    	if (elsUser == null) {
    		return ResultGenerator.genFailResult("未查询到该用户");
    	} else {
    		String uMobileNo = elsUser.getuMobileNo();
    		String decodeUMobileNo = "";
			try {
				decodeUMobileNo = Des3Util.decode(uMobileNo);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		String encodeUPassword = MD5Salt.encryptSalt(decodeUMobileNo, DEFAULT_PASSWORD);
        	
            elsUser = new SUser();
            elsUser.setuId(uId);
            elsUser.setuPassword(encodeUPassword);
            
            userService.update(elsUser);
    	}
    	
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 用户列表
     * @param page
     * @param size
     * @param coId
     * @param deptId
     * @param rId
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, String coId, String deptId, String rId) {
        PageHelper.startPage(page, size);
        
        List<Map<String, Object>> list = userService.findAllUser(coId, deptId, rId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
