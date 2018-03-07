/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtegsrord.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mtegsrord.entity.MtEgsrord;

/**
 * 收益列表DAO接口
 * @author wuhao
 * @version 2017-06-16
 */
@MyBatisDao
public interface MtEgsrordDao extends CrudDao<MtEgsrord> {
	public String sumUserReceivable(@Param("userid")Integer userid,
			@Param("starttime")String starttime, @Param("aborttime")String aborttime);
	public String sumUserInterest(@Param("userid")Integer userid,
			@Param("starttime")String starttime, @Param("aborttime")String aborttime);
}