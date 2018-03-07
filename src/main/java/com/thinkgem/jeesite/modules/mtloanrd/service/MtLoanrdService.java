/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtloanrd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.mtloanrd.entity.MtLoanrd;
import com.thinkgem.jeesite.modules.mtloanrd.dao.MtLoanrdDao;

/**
 * 转账列表Service
 * @author wuhao
 * @version 2017-06-28
 */
@Service
@Transactional(readOnly = true)
public class MtLoanrdService extends CrudService<MtLoanrdDao, MtLoanrd> {
	 
	private MtLoanrdDao mtLoanrdDao = null;

	public MtLoanrd get(String id) {
		return super.get(id);
	}
	
	public List<MtLoanrd> findList(MtLoanrd mtLoanrd) {
		return super.findList(mtLoanrd);
	}
	
	public Page<MtLoanrd> findPage(Page<MtLoanrd> page, MtLoanrd mtLoanrd) {
		return super.findPage(page, mtLoanrd);
	}
	
	@Transactional(readOnly = false)
	public void save(MtLoanrd mtLoanrd) {
		super.save(mtLoanrd);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtLoanrd mtLoanrd) {
		super.delete(mtLoanrd);
	}
	
	public List<MtLoanrd> findProjectNO(MtLoanrd mtLoanrd) {
		mtLoanrdDao = SpringContextHolder.getBean(MtLoanrdDao.class);
		return mtLoanrdDao.findProjectNo(mtLoanrd);
	}
	
	public Integer findCount(String billon) {
		mtLoanrdDao = SpringContextHolder.getBean(MtLoanrdDao.class);
		return mtLoanrdDao.findCount(billon);
	}
	
}