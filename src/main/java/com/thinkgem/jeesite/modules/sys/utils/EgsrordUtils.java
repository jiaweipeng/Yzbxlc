package com.thinkgem.jeesite.modules.sys.utils;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.mtegsrord.dao.MtEgsrordDao;

/**
 * 统计收益金额
 * @author Administrator
 *
 */
public class EgsrordUtils {
	
	private static MtEgsrordDao  mtEgsrordDao = SpringContextHolder.getBean(MtEgsrordDao.class);
	public static final String USER_CACHE = "mtEgsrordCache";
	public static final String USER_CACHE_USERID_ = "userid_";
	
	/**
	 * 统计用户本金
	 * @param userid
	 * @return
	 */
	public static String sumUserReceivable(Integer userid, String starttime, String aborttime) {
		String sumRecharge = (String)CacheUtils.get(USER_CACHE, USER_CACHE_USERID_ + userid);
		if (sumRecharge ==  null){
			System.out.println("------------------"+userid);
			sumRecharge = mtEgsrordDao.sumUserReceivable(userid, starttime, aborttime);
			if (sumRecharge == null){
				return "0.00";
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_USERID_ + sumRecharge, sumRecharge);
		}
		return sumRecharge;
	}
	
	/**
	 * 统计用户收益
	 * @param time
	 * @param userid
	 * @return
	 */
	public static String sumUserInterest(Integer userid, String starttime, String aborttime) {
		String sumRecharge = (String)CacheUtils.get(USER_CACHE, USER_CACHE_USERID_ + userid);
		if (sumRecharge ==  null){
			System.out.println("------------------"+userid);
			sumRecharge = mtEgsrordDao.sumUserInterest(userid, starttime, aborttime);
			if (sumRecharge == null){
				return "0.00";
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_USERID_ + sumRecharge, sumRecharge);
		}
		return sumRecharge;
	}
	
}
