/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.repayment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.repayment.entity.MtRepayment;
import com.thinkgem.jeesite.modules.product.dao.MtProductDao;
import com.thinkgem.jeesite.modules.repayment.dao.MtRepaymentDao;

/**
 * 还款列表Service
 * @author jiaweipeng
 * @version 2017-06-16
 */
@Service
@Transactional(readOnly = true)
public class MtRepaymentService extends CrudService<MtRepaymentDao, MtRepayment> {
	
	private MtRepaymentDao mtRepaymentDao = null;

	public MtRepayment get(String id) {
		return super.get(id);
	}
	
	public List<MtRepayment> findList(MtRepayment mtRepayment) {
		return super.findList(mtRepayment);
	}
	
	public Page<MtRepayment> findPage(Page<MtRepayment> page, MtRepayment mtRepayment) {
		return super.findPage(page, mtRepayment);
	}
	
	@Transactional(readOnly = false)
	public void save(MtRepayment mtRepayment) {
		super.save(mtRepayment);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtRepayment mtRepayment) {
		super.delete(mtRepayment);
	}
	
	public List<MtRepayment> findPidList(String grade) {
		mtRepaymentDao = SpringContextHolder.getBean(MtRepaymentDao.class);
		return mtRepaymentDao.findPidList(grade);
	}
	
}