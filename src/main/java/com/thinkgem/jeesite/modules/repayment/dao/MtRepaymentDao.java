/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.repayment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.repayment.entity.MtRepayment;

/**
 * 还款列表DAO接口
 * @author jiaweipeng
 * @version 2017-06-16
 */
@MyBatisDao
public interface MtRepaymentDao extends CrudDao<MtRepayment> {
	public List<MtRepayment> findPidList(String grade);
	public List<MtRepayment> findListByOut(MtRepayment mtRepayment);
	public String sumUserRepay(@Param("userid")Integer userid, @Param("type")Integer type,
			@Param("starttime")String starttime, @Param("aborttime")String aborttime);
	public String sumUserWzhr(@Param("userid")Integer userid,
			@Param("starttime")String starttime, @Param("aborttime")String aborttime);
}