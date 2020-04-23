package com.eroad.project.web;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eroad.project.core.Result;
import com.eroad.project.core.ResultGenerator;
import com.eroad.project.model.SSysLog;
import com.eroad.project.service.SSysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* Created by cyt on 2018/12/11.
*/
@RestController
@RequestMapping("/s/sys/log")
public class SSysLogController {
	@Resource
    private SSysLogService sysLogService;

    @PostMapping("/add")
    public Result add(SSysLog sysLog) {
        sysLogService.save(sysLog);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        sysLogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SSysLog sysLog) {
        sysLogService.update(sysLog);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        SSysLog sysLog = sysLogService.findById(id);
        return ResultGenerator.genSuccessResult(sysLog);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Map<String, Object>> list = sysLogService.findAllSysLog();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
