/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtinvest.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.mtinvest.entity.MtInvest;
import com.thinkgem.jeesite.modules.mtinvest.dao.MtInvestDao;

/**
 * 投资记录Service
 * @author wuhao
 * @version 2017-06-15
 */
@Service
@Transactional(readOnly = true)
public class MtInvestService extends CrudService<MtInvestDao, MtInvest> {
	
	private MtInvestDao mtInvestDao = null;

	public MtInvest get(String id) {
		return super.get(id);
	}
	
	public List<MtInvest> findList(MtInvest mtInvest) {
		return super.findList(mtInvest);
	}
	
	public Page<MtInvest> findPage(Page<MtInvest> page, MtInvest mtInvest) {
		return super.findPage(page, mtInvest);
	}
	
	@Transactional(readOnly = false)
	public void save(MtInvest mtInvest) {
		super.save(mtInvest);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtInvest mtInvest) {
		super.delete(mtInvest);
	}
	
	@Transactional(readOnly = false)
	public Integer updateInvment(String invment, String pid) {
		mtInvestDao = SpringContextHolder.getBean(MtInvestDao.class);
		return mtInvestDao.updateInvment(invment, pid);
	}
	
	public List<MtInvest> findIdList(String id) {
		mtInvestDao = SpringContextHolder.getBean(MtInvestDao.class);
		return mtInvestDao.findIdList(id);
	}
	
	public MtInvest findInvest(String freezeId) {
		mtInvestDao = SpringContextHolder.getBean(MtInvestDao.class);
		return mtInvestDao.findInvest(freezeId);
	}
	
}