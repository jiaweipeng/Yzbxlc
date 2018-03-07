/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.stat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.stat.entity.MtStat;
import com.thinkgem.jeesite.modules.stat.dao.MtStatDao;

/**
 * statService
 * @author 张琨鹏
 * @version 2017-09-23
 */
@Service
@Transactional(readOnly = true)
public class MtStatService extends CrudService<MtStatDao, MtStat> {

	public MtStat get(String id) {
		return super.get(id);
	}
	
	public List<MtStat> findList(MtStat mtStat) {
		return super.findList(mtStat);
	}
	
	public Page<MtStat> findPage(Page<MtStat> page, MtStat mtStat) {
		return super.findPage(page, mtStat);
	}
	
	@Transactional(readOnly = false)
	public void save(MtStat mtStat) {
		super.save(mtStat);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtStat mtStat) {
		super.delete(mtStat);
	}
	
}