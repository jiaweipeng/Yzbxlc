/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pact.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.pact.entity.MtPact;
import com.thinkgem.jeesite.modules.pact.dao.MtPactDao;

/**
 * 合同管理Service
 * @author shouming
 * @version 2017-06-20
 */
@Service
@Transactional(readOnly = true)
public class MtPactService extends CrudService<MtPactDao, MtPact> {

	private MtPactDao mtPactDao = null;
	
	public MtPact get(String id) {
		return super.get(id);
	}
	
	public List<MtPact> findList(MtPact mtPact) {
		return super.findList(mtPact);
	}
	
	public Page<MtPact> findPage(Page<MtPact> page, MtPact mtPact) {
		return super.findPage(page, mtPact);
	}
	
	@Transactional(readOnly = false)
	public void save(MtPact mtPact) {
		super.save(mtPact);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtPact mtPact) {
		super.delete(mtPact);
	}
	
}