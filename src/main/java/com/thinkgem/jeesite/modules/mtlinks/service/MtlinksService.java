/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtlinks.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mtlinks.entity.Mtlinks;
import com.thinkgem.jeesite.modules.mtlinks.dao.MtlinksDao;

/**
 * 友情连接Service
 * @author wuhao
 * @version 2017-06-12
 */
@Service
@Transactional(readOnly = true)
public class MtlinksService extends CrudService<MtlinksDao, Mtlinks> {

	public Mtlinks get(String id) {
		return super.get(id);
	}
	
	public List<Mtlinks> findList(Mtlinks mtlinks) {
		return super.findList(mtlinks);
	}
	
	public Page<Mtlinks> findPage(Page<Mtlinks> page, Mtlinks mtlinks) {
		return super.findPage(page, mtlinks);
	}
	
	@Transactional(readOnly = false)
	public void save(Mtlinks mtlinks) {
		super.save(mtlinks);
	}
	
	@Transactional(readOnly = false)
	public void delete(Mtlinks mtlinks) {
		super.delete(mtlinks);
	}
	
}