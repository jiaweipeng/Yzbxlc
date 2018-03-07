/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtcash.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mtcash.entity.MtCash;
import com.thinkgem.jeesite.modules.mtcash.dao.MtCashDao;

/**
 * 提现列表Service
 * @author wuhao
 * @version 2017-06-22
 */
@Service
@Transactional(readOnly = true)
public class MtCashService extends CrudService<MtCashDao, MtCash> {

	public MtCash get(String id) {
		return super.get(id);
	}
	
	public List<MtCash> findList(MtCash mtCash) {
		return super.findList(mtCash);
	}
	
	public Page<MtCash> findPage(Page<MtCash> page, MtCash mtCash) {
		return super.findPage(page, mtCash);
	}
	
	@Transactional(readOnly = false)
	public void save(MtCash mtCash) {
		super.save(mtCash);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtCash mtCash) {
		super.delete(mtCash);
	}
	
}