/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtrecharge.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mtrecharge.entity.MtRecharge;
import com.thinkgem.jeesite.modules.mtrecharge.dao.MtRechargeDao;

/**
 * 充值记录Service
 * @author wuhao
 * @version 2017-06-21
 */
@Service
@Transactional(readOnly = true)
public class MtRechargeService extends CrudService<MtRechargeDao, MtRecharge> {

	public MtRecharge get(String id) {
		return super.get(id);
	}
	
	public List<MtRecharge> findList(MtRecharge mtRecharge) {
		return super.findList(mtRecharge);
	}
	
	public Page<MtRecharge> findPage(Page<MtRecharge> page, MtRecharge mtRecharge) {
		return super.findPage(page, mtRecharge);
	}
	
	@Transactional(readOnly = false)
	public void save(MtRecharge mtRecharge) {
		super.save(mtRecharge);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtRecharge mtRecharge) {
		super.delete(mtRecharge);
	}
	
}