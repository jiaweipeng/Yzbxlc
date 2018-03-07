/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtegsrord.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mtegsrord.dao.MtEgsrordDao;
import com.thinkgem.jeesite.modules.mtegsrord.entity.MtEgsrord;

/**
 * 收益列表Service
 * @author wuhao
 * @version 2017-06-16
 */
//@Lazy(false)
@Service
@Transactional(readOnly = true)
public class MtEgsrordService extends CrudService<MtEgsrordDao, MtEgsrord> {

	public MtEgsrord get(String id) {
		return super.get(id);
	}
	
	public List<MtEgsrord> findList(MtEgsrord mtEgsrord) {
		return super.findList(mtEgsrord);
	}
	
	public Page<MtEgsrord> findPage(Page<MtEgsrord> page, MtEgsrord mtEgsrord) {
		return super.findPage(page, mtEgsrord);
	}
	
	@Transactional(readOnly = false)
	public void save(MtEgsrord mtEgsrord) {
		super.save(mtEgsrord);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtEgsrord mtEgsrord) {
		super.delete(mtEgsrord);
	}
	
}