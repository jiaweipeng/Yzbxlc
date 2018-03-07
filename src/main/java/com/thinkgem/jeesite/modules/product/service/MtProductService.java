/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.product.service;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.product.dao.MtProductDao;
import com.thinkgem.jeesite.modules.product.entity.MtProduct;

/**
 * 产品管理Service
 * 
 * @author jiaweipeng
 * @version 2017-06-13
 */
@Service
@Lazy(false)
@Transactional(readOnly = true)
public class MtProductService extends CrudService<MtProductDao, MtProduct> {
	
	private MtProductDao  mtProductDao = null;

	public MtProduct get(String id) {
		return super.get(id);
		
	}

	public List<MtProduct> findList(MtProduct mtProduct) {
		return super.findList(mtProduct);
	}

	public Page<MtProduct> findPage(Page<MtProduct> page, MtProduct mtProduct) {
		return super.findPage(page, mtProduct);
	}

	@Transactional(readOnly = false)
	public void save(MtProduct mtProduct) {
		super.save(mtProduct);
	}

	@Transactional(readOnly = false)
	public void delete(MtProduct mtProduct) {
		super.delete(mtProduct);
	}

	public MtProduct findProduct(String grade) {
		mtProductDao = SpringContextHolder.getBean(MtProductDao.class);
		return mtProductDao.getProductByGrade(grade);
	}
	
	public List<MtProduct> findStatusList(Integer value_1, Integer value_2) {
		mtProductDao = SpringContextHolder.getBean(MtProductDao.class);
		return mtProductDao.findStatusList(value_1, value_2);
	}

	
}