/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtloanrd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mtloanrd.entity.MtLoanrd;

/**
 * 转账列表DAO接口
 * @author wuhao
 * @version 2017-06-28
 */
@MyBatisDao
public interface MtLoanrdDao extends CrudDao<MtLoanrd> {
	public Integer findCount(String billon);
	public List<MtLoanrd> findProjectNo(MtLoanrd mtLoanrd);
	public String sumUserLoanrd(@Param("userid")Integer userid,
			@Param("type")Integer type, @Param("starttime")String starttime, @Param("aborttime")String aborttime);
}