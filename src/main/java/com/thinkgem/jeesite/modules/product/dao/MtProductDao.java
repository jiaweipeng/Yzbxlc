/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.product.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.product.entity.MtProduct;

/**
 * 产品管理DAO接口
 * @author jiaweipeng
 * @version 2017-06-13
 */
@MyBatisDao
public interface MtProductDao extends CrudDao<MtProduct> {
	public MtProduct getProductByGrade(String grade);
	public List<MtProduct> findListByagent(MtProduct mtProduct);
	public List<MtProduct> findStatusList(Integer value_1, Integer value_2);
}