/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtguatee.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mtguatee.entity.MtGuatee;
import com.thinkgem.jeesite.modules.mtguatee.dao.MtGuateeDao;

/**
 * 担保机构列表Service
 * @author wuhao
 * @version 2017-06-22
 */
@Service
@Transactional(readOnly = true)
public class MtGuateeService extends CrudService<MtGuateeDao, MtGuatee> {

	public MtGuatee get(String id) {
		return super.get(id);
	}
	
	public List<MtGuatee> findList(MtGuatee mtGuatee) {
		return super.findList(mtGuatee);
	}
	
	public Page<MtGuatee> findPage(Page<MtGuatee> page, MtGuatee mtGuatee) {
		return super.findPage(page, mtGuatee);
	}
	
	@Transactional(readOnly = false)
	public void save(MtGuatee mtGuatee) {
		super.save(mtGuatee);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtGuatee mtGuatee) {
		super.delete(mtGuatee);
	}
	
}