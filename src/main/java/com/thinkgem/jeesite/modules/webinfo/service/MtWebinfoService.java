/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.webinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.webinfo.entity.MtWebinfo;
import com.thinkgem.jeesite.modules.webinfo.dao.MtWebinfoDao;

/**
 * 网站信息Service
 * @author wuhao
 * @version 2017-06-29
 */
@Service
@Transactional(readOnly = true)
public class MtWebinfoService extends CrudService<MtWebinfoDao, MtWebinfo> {

	public MtWebinfo get(String id) {
		return super.get(id);
	}
	
	public List<MtWebinfo> findList(MtWebinfo mtWebinfo) {
		return super.findList(mtWebinfo);
	}
	
	public Page<MtWebinfo> findPage(Page<MtWebinfo> page, MtWebinfo mtWebinfo) {
		return super.findPage(page, mtWebinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(MtWebinfo mtWebinfo) {
		super.save(mtWebinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtWebinfo mtWebinfo) {
		super.delete(mtWebinfo);
	}
	
}