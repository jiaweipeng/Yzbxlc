/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.auth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.auth.entity.MtAuth;
import com.thinkgem.jeesite.modules.auth.dao.MtAuthDao;

/**
 * 认证管理Service
 * @author shouming
 * @version 2017-06-14
 */
@Service
@Transactional(readOnly = true)
public class MtAuthService extends CrudService<MtAuthDao, MtAuth> {

	public MtAuth get(String id) {
		return super.get(id);
	}
	
	public List<MtAuth> findList(MtAuth mtAuth) {
		return super.findList(mtAuth);
	}
	
	public Page<MtAuth> findPage(Page<MtAuth> page, MtAuth mtAuth) {
		return super.findPage(page, mtAuth);
	}
	
	@Transactional(readOnly = false)
	public void save(MtAuth mtAuth) {
		super.save(mtAuth);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtAuth mtAuth) {
		super.delete(mtAuth);
	}
	
}