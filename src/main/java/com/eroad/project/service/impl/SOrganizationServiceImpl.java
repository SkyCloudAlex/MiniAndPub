package com.eroad.project.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eroad.project.core.AbstractService;
import com.eroad.project.dao.SOrganizationMapper;
import com.eroad.project.model.SOrganization;
import com.eroad.project.service.SOrganizationService;


/**
 * Created by cyt on 2018/12/11.
 */
@Service
@Transactional
public class SOrganizationServiceImpl extends AbstractService<SOrganization> implements SOrganizationService {
	@Resource
    private SOrganizationMapper organizationMapper;

	@Override
	public String getNewOId(String oParentId) {
		int maxOId = organizationMapper.getMaxOId(oParentId);
		
		if (maxOId == 0) {
			return oParentId + "001";
		} else {
			return String.valueOf(maxOId + 1);
		}
	}
	
	@Override
	public void move(String oId, String pos) {
		SOrganization currOrganization = organizationMapper.selectByPrimaryKey(oId);
		if (currOrganization == null) {
			return;
		} else {
			String oParentId = currOrganization.getoParentId();
			String oSort = currOrganization.getoSort();
			
			// 取交换排序菜单
			SOrganization exchangeOrganization = null;
			if ("up".equals(pos)) {
				exchangeOrganization = organizationMapper.getPreviousOrganization(oParentId, oSort);
			} else if ("down".equals(pos)) {
				exchangeOrganization = organizationMapper.getNextOrganization(oParentId, oSort);
			}
			
			if (exchangeOrganization == null) {
				return;
			} else {
				String eOSort = exchangeOrganization.getoSort();
				
				// 交换排序
				currOrganization.setoSort(eOSort);
				exchangeOrganization.setoSort(oSort);
				organizationMapper.updateByPrimaryKeySelective(currOrganization);
				organizationMapper.updateByPrimaryKeySelective(exchangeOrganization);
			}
		}
	}

	@Override
	public List<Map<String, Object>> getCoList() {
		return organizationMapper.getCoList();
	}

	@Override
	public List<Map<String, Object>> getDeptList(String oParentId) {
		return organizationMapper.getDeptList(oParentId);
	}

}
