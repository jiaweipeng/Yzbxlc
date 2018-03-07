/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.webinfo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.webinfo.entity.MtWebinfo;

/**
 * 网站信息DAO接口
 * @author wuhao
 * @version 2017-06-29
 */
@MyBatisDao
public interface MtWebinfoDao extends CrudDao<MtWebinfo> {
	public MtWebinfo findVarname(String varname);
}