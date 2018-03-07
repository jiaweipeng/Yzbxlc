/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pnuelist.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.pnuelist.entity.MtPnuelist;
import com.thinkgem.jeesite.modules.product.dao.MtProductDao;
import com.thinkgem.jeesite.modules.pnuelist.dao.MtPnuelistDao;

/**
 * 平台收益Service
 * @author wuhao
 * @version 2017-06-15
 */
@Service
@Transactional(readOnly = true)
public class MtPnuelistService extends CrudService<MtPnuelistDao, MtPnuelist> {

	private MtPnuelistDao mtPnuelistDao;
	
	public MtPnuelist get(String id) {
		return super.get(id);
	}
	
	public List<MtPnuelist> findList(MtPnuelist mtPnuelist) {
		return super.findList(mtPnuelist);
	}
	
	public Page<MtPnuelist> findPage(Page<MtPnuelist> page, MtPnuelist mtPnuelist) {
		return super.findPage(page, mtPnuelist);
	}
	
	@Transactional(readOnly = false)
	public void save(MtPnuelist mtPnuelist) {
		super.save(mtPnuelist);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtPnuelist mtPnuelist) {
		super.delete(mtPnuelist);
	}
	
	public List<MtPnuelist> findTotalList(MtPnuelist mtPnuelist) {
		mtPnuelistDao = SpringContextHolder.getBean(MtPnuelistDao.class);
		return mtPnuelistDao.findTotalList(mtPnuelist);
	}
	
}