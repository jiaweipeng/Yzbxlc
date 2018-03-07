/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtcash.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mtcash.entity.MtCash;

/**
 * 提现列表DAO接口
 * @author wuhao
 * @version 2017-06-22
 */
@MyBatisDao
public interface MtCashDao extends CrudDao<MtCash> {
	//提款总金额
	public String getCountlipsTrdAmt(Integer mtuserid);
	//用户每天提现金额
	public String sumUserCash(@Param("userid")Integer userid,
			@Param("starttime")String starttime, @Param("aborttime")String aborttime);
}