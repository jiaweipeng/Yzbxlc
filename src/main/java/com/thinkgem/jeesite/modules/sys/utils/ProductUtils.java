package com.thinkgem.jeesite.modules.sys.utils;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.product.dao.MtProductDao;
import com.thinkgem.jeesite.modules.product.entity.MtProduct;

public class ProductUtils {
	private static MtProductDao  productDao = SpringContextHolder.getBean(MtProductDao.class);
	public static final String USER_CACHE = "mtproductCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";
	/**
	 * 根据ID获取P
	 * @param id
	 * @return 取不到返回null
	 */
	public static MtProduct getProductByGrade(String grade){
		MtProduct product = (MtProduct)CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + grade);
		if (product ==  null){
			product = productDao.getProductByGrade(grade);
			System.out.println(grade);
			if (product == null){
				return null;
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + product.getPid(), product);
		}
		return product;
	}
}
