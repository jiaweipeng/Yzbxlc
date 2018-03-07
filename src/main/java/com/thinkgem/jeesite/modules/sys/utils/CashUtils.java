package com.thinkgem.jeesite.modules.sys.utils;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.mtcash.dao.MtCashDao;

/**
 * 提现列表总金额
 * @author Administrator
 *
 */
public class CashUtils {
	private static MtCashDao  mtCashDao = SpringContextHolder.getBean(MtCashDao.class);
	public static final String USER_CACHE = "mtcashCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_USERID_ = "cash_uid_";
	
	public static String getCountlipsTrdAmt(Integer mtuserid){
		String mtcashstr = (String)CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + mtuserid);
		if (mtcashstr ==  null){
			System.out.println("------------------"+mtuserid);
			mtcashstr = mtCashDao.getCountlipsTrdAmt(mtuserid);
			if (mtcashstr == null){
				return null;
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + mtcashstr, mtcashstr);
		}
		return mtcashstr;
	}
	
	public static String sumUserCash(Integer userid, String starttime, String aborttime){
		String sumCash = (String)CacheUtils.get(USER_CACHE, USER_CACHE_USERID_ + userid);
		if (sumCash ==  null){
			System.out.println("------------------"+userid);
			sumCash = mtCashDao.sumUserCash(userid, starttime, aborttime);
			if (sumCash == null){
				return "0.00";
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_USERID_ + sumCash, sumCash);
		}
		return sumCash;
	}
	
}
