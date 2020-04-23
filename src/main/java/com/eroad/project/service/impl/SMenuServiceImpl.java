package com.eroad.project.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eroad.project.core.AbstractService;
import com.eroad.project.dao.SMenuMapper;
import com.eroad.project.model.SMenu;
import com.eroad.project.service.SMenuService;


/**
 * Created by cyt on 2018/12/11.
 */
@Service
@Transactional
public class SMenuServiceImpl extends AbstractService<SMenu> implements SMenuService {
	@Resource
    private SMenuMapper menuMapper;

	@Override
	public List<SMenu> getUserMenu(String uId) {
		return menuMapper.getUserMenu(uId);
	}

	@Override
	public void move(String mId, String pos) {
		SMenu currMenu = menuMapper.selectByPrimaryKey(mId);
		if (currMenu == null) {
			return;
		} else {
			String mParentId = currMenu.getmParentId();
			String mSort = currMenu.getmSort();
			
			// 取交换排序菜单
			SMenu exchangeMenu = null;
			if ("up".equals(pos)) {
				exchangeMenu = menuMapper.getPreviousMenu(mParentId, mSort);
			} else if ("down".equals(pos)) {
				exchangeMenu = menuMapper.getNextMenu(mParentId, mSort);
			}
			
			if (exchangeMenu == null) {
				return;
			} else {
				String eMSort = exchangeMenu.getmSort();
				
				// 交换排序
				currMenu.setmSort(eMSort);
				exchangeMenu.setmSort(mSort);
				menuMapper.updateByPrimaryKeySelective(currMenu);
				menuMapper.updateByPrimaryKeySelective(exchangeMenu);
			}
		}
	}

	@Override
	public String getNewMId(String mParentId) {
		int maxMId = menuMapper.getMaxMId(mParentId);
		
		if (maxMId == 0) {
			return mParentId + "001";
		} else {
			return String.valueOf(maxMId + 1);
		}
	}

	@Override
	public List<Map<String, Object>> getRoleMenu(String rId) {
		return menuMapper.getRoleMenu(rId);
	}

}
