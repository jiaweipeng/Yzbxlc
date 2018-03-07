package com.thinkgem.jeesite.modules.sys.utils;

import java.util.List;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.mtguatee.dao.MtGuateeDao;
import com.thinkgem.jeesite.modules.mtguatee.entity.MtGuatee;

public class GuateeUtils {
	private static MtGuateeDao  mtGuateeDao = SpringContextHolder.getBean(MtGuateeDao.class);
	public static final String GUATEE_CACHE = "mtGuateeCache";
	public static final String GUATEE_CACHE_ID = "mt_guatee_id";
	
	/**
	 * 获取担保机构
	 * @return
	 */
	public static List<MtGuatee> getGuateeAllList(){
		@SuppressWarnings("unchecked")
		List<MtGuatee> mtGuatees = (List<MtGuatee>) CacheUtils.get(GUATEE_CACHE, GUATEE_CACHE_ID);
		if (mtGuatees ==  null){
			MtGuatee findGuatee = new MtGuatee();
			mtGuatees = mtGuateeDao.findAllList(findGuatee);
			if (mtGuatees == null){
				return null;
			}
			CacheUtils.put(GUATEE_CACHE, GUATEE_CACHE_ID, mtGuatees);
		}
		return mtGuatees;
	}
}
