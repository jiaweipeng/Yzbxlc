/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.stat.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.stat.entity.MtStat;
import com.thinkgem.jeesite.modules.stat.entity.MtStatDetail;
/**
 * statDAO接口
 * @author 张琨鹏
 * @version 2017-09-23
 */
@MyBatisDao
public interface MtStatDao extends CrudDao<MtStat> {
	public Integer getpv(String start,String end);
	public Integer getuv(String start,String end);
	public Integer getiv(String start,String end);
	public List<MtStatDetail> getDetail(String start,String end);
	public List<MtStatDetail> getTitleDetail(String start,String end);
	
}