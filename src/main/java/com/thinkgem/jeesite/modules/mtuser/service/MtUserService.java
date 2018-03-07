/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtuser.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;
import com.thinkgem.jeesite.modules.mtuser.dao.MtUserDao;

/**
 * 客户管理Service
 * @author fengtao
 * @version 2017-06-14
 */
@Service
@Transactional(readOnly = true)
public class MtUserService extends CrudService<MtUserDao, MtUser> {

	private MtUserDao mtUserDao = null;
	
	public MtUser get(String id) {
		return super.get(id);
	}
	
	public List<MtUser> findList(MtUser mtUser) {
		return super.findList(mtUser);
	}
	
	public Page<MtUser> findPage(Page<MtUser> page, MtUser mtUser) {
		return super.findPage(page, mtUser);
	}
	
	@Transactional(readOnly = false)
	public void save(MtUser mtUser) {
		super.save(mtUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtUser mtUser) {
		super.delete(mtUser);
	}
	
	public MtUser findUser(String ipsAcctNo) {
		mtUserDao = SpringContextHolder.getBean(MtUserDao.class);
		return mtUserDao.findUser(ipsAcctNo);
	}
	
}