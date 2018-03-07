/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtrecharge.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mtrecharge.entity.MtRecharge;

/**
 * 充值记录DAO接口
 * @author wuhao
 * @version 2017-06-21
 */
@MyBatisDao
public interface MtRechargeDao extends CrudDao<MtRecharge> {
	public String getTotalipsTrdAmt(Integer mtuserid);
	public String sumUserRecharge(@Param("userid")Integer userid,
			@Param("starttime")String starttime, @Param("aborttime")String aborttime);
}