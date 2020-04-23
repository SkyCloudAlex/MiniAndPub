package com.eroad.project.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eroad.project.core.AbstractService;
import com.eroad.project.dao.SVcodeMapper;
import com.eroad.project.model.SVcode;
import com.eroad.project.service.SVcodeService;
import com.eroad.project.util.CommonUtil;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


/**
 * Created by cyt on 2018/12/11.
 */
@Service
@Transactional
public class SVcodeServiceImpl extends AbstractService<SVcode> implements SVcodeService {
	@Resource
    private SVcodeMapper vcodeMapper;
    
    @Override
	public SVcode checkVcode(String uMobileNo, String vcode, String vType) {
    	Condition vcodeCon = new Condition(SVcode.class);
    	Example.Criteria vcodeCri = vcodeCon.createCriteria();
    	vcodeCri.andEqualTo("vMobileNo", uMobileNo);
    	vcodeCri.andEqualTo("vCode", vcode);
    	vcodeCri.andEqualTo("vType", vType);
    	vcodeCri.andEqualTo("vStatus", "0");
    	
    	List<SVcode> vcodeList = vcodeMapper.selectByCondition(vcodeCon);
    	
    	if (vcodeList != null && vcodeList.size() > 0) {
    		return vcodeList.get(0);
    	} else {
    		return null;
    	}
	}

	@Override
	public void failVcode(String vMobileNo, String vType) {
		SVcode sVcode = new SVcode();
		sVcode.setvStatus("1");
		
		Condition condition = new Condition(SVcode.class);
		Example.Criteria criteria = condition.createCriteria();
		criteria.andEqualTo("vMobileNo", vMobileNo);
		criteria.andEqualTo("vType", vType);
		criteria.andEqualTo("vStatus", "0");
		
		vcodeMapper.updateByConditionSelective(sVcode, condition);
	}

	@Override
	public void saveVcode(int vcode, String mobileNo, String vType) {
		Date now = new Date();
		SVcode sVcode = new SVcode();
		sVcode.setvId(CommonUtil.getUUID());
		sVcode.setvCode(String.valueOf(vcode));
		sVcode.setvMobileNo(mobileNo);
		sVcode.setvType(vType);
		sVcode.setvSendTime(now);
		sVcode.setvExpireTime(now);
		sVcode.setvStatus("0");
		vcodeMapper.insertSelective(sVcode);
	}

	@Override
	public SVcode findAbleCode(String mobileNo, String type) {
		Condition condition = new Condition(SVcode.class);
		Criteria criteria = condition.createCriteria();
		criteria.andEqualTo("vMobileNo", mobileNo);
		criteria.andEqualTo("vType", type);
		criteria.andEqualTo("vStatus", "2");
		
		List<SVcode> vcodeList = vcodeMapper.selectByCondition(condition);
		if (vcodeList != null && vcodeList.size() > 0) {
			return vcodeList.get(0);
		} else {
			return null;
		}
	}

}
