package com.thinkgem.jeesite.modules.sys.utils;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.pact.dao.MtPactDao;
import com.thinkgem.jeesite.modules.pact.entity.MtPact;

public class PactUtils {
	private static MtPactDao  mtPactDao = SpringContextHolder.getBean(MtPactDao.class);
	public static final String PACT_CACHE = "mtPactCache";
	public static final String PACT_CACHE_ID_ = "id_";
	
	/**
	 * 根据PID获取合同
	 * @param Pid
	 * @return 取不到返回null
	 */
	public static MtPact findPact(String grade){
		MtPact pact = (MtPact)CacheUtils.get(PACT_CACHE, PACT_CACHE_ID_ + grade);
		if (pact ==  null){
			pact = mtPactDao.findPact(grade);
			System.out.println(grade);
			if (pact == null){
				return null;
			}
			CacheUtils.put(PACT_CACHE, PACT_CACHE_ID_ + pact.getPid(), pact);
		}
		return pact;
	}
	
}
