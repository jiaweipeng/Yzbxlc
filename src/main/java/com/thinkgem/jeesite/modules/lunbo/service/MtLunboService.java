/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lunbo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.lunbo.entity.MtLunbo;
import com.thinkgem.jeesite.modules.lunbo.dao.MtLunboDao;

/**
 * 轮播图Service
 * @author wuhao
 * @version 2017-06-12
 */
@Service
@Transactional(readOnly = true)
public class MtLunboService extends CrudService<MtLunboDao, MtLunbo> {

	public MtLunbo get(String id) {
		return super.get(id);
	}
	
	public List<MtLunbo> findList(MtLunbo mtLunbo) {
		return super.findList(mtLunbo);
	}
	
	public Page<MtLunbo> findPage(Page<MtLunbo> page, MtLunbo mtLunbo) {
		return super.findPage(page, mtLunbo);
	}
	
	@Transactional(readOnly = false)
	public void save(MtLunbo mtLunbo) {
		super.save(mtLunbo);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtLunbo mtLunbo) {
		super.delete(mtLunbo);
	}
	
}