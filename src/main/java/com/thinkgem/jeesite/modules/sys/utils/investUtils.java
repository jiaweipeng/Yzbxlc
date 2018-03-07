package com.thinkgem.jeesite.modules.sys.utils;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.mtinvest.dao.MtInvestDao;

public class investUtils {
	private static MtInvestDao  mtinvestDao = SpringContextHolder.getBean(MtInvestDao.class);
	public static final String USER_CACHE = "mtinvestCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";
	
	public static String getcountname(Integer productid){
		String mtinvestcache = (String)CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + productid);
		if(mtinvestcache==null){
			System.out.println("=========投标次数"+productid);
			mtinvestcache = mtinvestDao.getcountname(productid);
			if(mtinvestcache==null){
				return null;
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + mtinvestcache, mtinvestcache);
		}
		
		return mtinvestcache;
	}
}
