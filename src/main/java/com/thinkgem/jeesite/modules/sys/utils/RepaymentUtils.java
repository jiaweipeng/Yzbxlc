package com.thinkgem.jeesite.modules.sys.utils;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.repayment.dao.MtRepaymentDao;

/**
 * 统计还款金额
 * @author Administrator
 *
 */
public class RepaymentUtils {
	
	private static MtRepaymentDao  mtRepaymentDao = SpringContextHolder.getBean(MtRepaymentDao.class);
	public static final String USER_CACHE = "mtRepaymentCache";
	public static final String USER_CACHE_USERID_ = "userid_";
	public static final String USER_CACHE_WZHRUID_ = "wzhruid_";
	
	/**
	 * 统计用户收益和本金
	 * @param time
	 * @param userid
	 * @param type 统计类型1为本金2为收益3为违约金4为罚息5为滞纳金
	 * @return
	 */
	public static String sumUserRepay(Integer userid, Integer type, String starttime, String aborttime) {
		String sumRepay = (String)CacheUtils.get(USER_CACHE, USER_CACHE_USERID_ + userid);
		if (sumRepay ==  null){
			System.out.println("------------------"+userid);
			sumRepay = mtRepaymentDao.sumUserRepay(userid, type, starttime, aborttime);
			if (sumRepay == null){
				return "0.00";
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_USERID_ + sumRepay, sumRepay);
		}
		return sumRepay;
	}
	
	/**
	 * 统计用户未还款金额
	 * @param userid
	 * @return
	 */
	public static String sumUserWzhr(Integer userid, String starttime, String aborttime) {
		String sumRepay = (String)CacheUtils.get(USER_CACHE, USER_CACHE_WZHRUID_ + userid);
		if (sumRepay ==  null){
			System.out.println("------------------"+userid);
			sumRepay = mtRepaymentDao.sumUserWzhr(userid, starttime, aborttime);
			if (sumRepay == null){
				return "0.00";
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_WZHRUID_ + sumRepay, sumRepay);
		}
		return sumRepay;
	}
	
}
