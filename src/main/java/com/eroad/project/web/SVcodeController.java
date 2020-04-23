package com.eroad.project.web;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eroad.project.core.Result;
import com.eroad.project.core.ResultGenerator;
import com.eroad.project.model.SVcode;
import com.eroad.project.service.SVcodeService;
import com.eroad.project.util.Des3Util;
import com.eroad.project.util.SmsUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* Created by cyt on 2018/12/11.
*/
@RestController
@RequestMapping("/s/vcode")
public class SVcodeController {
	@Resource
    private SVcodeService vcodeService;
	
	private Logger logger = Logger.getLogger(SVcodeController.class);
	
	/**
     * 获取短信验证码
     * @param mobileNo
     * @return
     */
    @PostMapping("/getVcode")
    public Result getVcode(String vMobileNo, String vType) {
    	if (StringUtils.isBlank(vMobileNo) || StringUtils.isBlank(vType)) {
    		return ResultGenerator.genFailResult("参数异常");
    	}
    	
    	String decodeMobileNo = "";
    	try {
    		decodeMobileNo = Des3Util.decode(vMobileNo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResultGenerator.genFailResult("加解密异常");
		}
    	
    	// 6位随机数
		int vcode = (int) ((Math.random() * 9 + 1) * 100000);
		
		String msg = "您的短信验证码为：" + vcode;
		
		// 失效该类型未验证短信验证码
		vcodeService.failVcode(vMobileNo, vType);
		
		// 新增验证码信息
		vcodeService.saveVcode(vcode, vMobileNo, vType);
    	
    	SmsUtil.sendSms(decodeMobileNo, msg);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/add")
    public Result add(SVcode vocde) {
        vcodeService.save(vocde);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        vcodeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SVcode vocde) {
        vcodeService.update(vocde);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        SVcode vocde = vcodeService.findById(id);
        return ResultGenerator.genSuccessResult(vocde);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SVcode> list = vcodeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
