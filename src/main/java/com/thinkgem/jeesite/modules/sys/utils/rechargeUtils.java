package com.thinkgem.jeesite.modules.sys.utils;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.mtrecharge.dao.MtRechargeDao;

/**
 * 充值列表总金额
 * @author Administrator
 *
 */
public class rechargeUtils {
	
	private static MtRechargeDao  rechargeDao = SpringContextHolder.getBean(MtRechargeDao.class);
	public static final String USER_CACHE = "mtrechargCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_USERID_ = "userid_";
	
	public static String getTotalipsTrdAmt(Integer mtuserid){
		String mtrechargestr = (String)CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + mtuserid);
		if (mtrechargestr ==  null){
			//System.out.println("------------------"+mtuserid);
			mtrechargestr = rechargeDao.getTotalipsTrdAmt(mtuserid);
			if (mtrechargestr == null){
				return null;
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + mtrechargestr, mtrechargestr);
		}
		return mtrechargestr;
	}
	
	public static String sumUserRecharge(Integer userid, String starttime, String aborttime) {
		String sumRecharge = (String)CacheUtils.get(USER_CACHE, USER_CACHE_USERID_ + userid);
		if (sumRecharge ==  null){
			//System.out.println("------------------"+userid);
			sumRecharge = rechargeDao.sumUserRecharge(userid, starttime, aborttime);
			if (sumRecharge == null){
				return "0.00";
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_USERID_ + sumRecharge, sumRecharge);
		}
		return sumRecharge;
	}
	
}
