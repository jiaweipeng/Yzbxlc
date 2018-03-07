package com.thinkgem.jeesite.modules.sys.utils;

import java.util.List;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.mtloanrd.dao.MtLoanrdDao;
import com.thinkgem.jeesite.modules.mtloanrd.web.Loanrd;
import com.thinkgem.jeesite.modules.mtloanrd.web.Loanrd.DataType;

public class LoanrdUtils {
	
	private static MtLoanrdDao  mtLoanrdDao = SpringContextHolder.getBean(MtLoanrdDao.class);
	public static final String USER_CACHE = "mtLoanrdCache";
	public static final String USER_CACHE_USERID_ = "userid_";
	
	/**
	 * 资金流动类型
	 * @param type
	 * @return
	 */
	public static String fundType(Integer type) {
		return Loanrd.getType(type);
	}
	
	/**
	 * 获取全部类型
	 * @return
	 */
	public static List<DataType> fundAllType() {
		return Loanrd.getAllType();
	}
	
	public static String sumUserLoanrd(Integer userid, Integer type, String starttime, String aborttime){
		String sumLoanrd = (String)CacheUtils.get(USER_CACHE, USER_CACHE_USERID_ + userid);
		if (sumLoanrd ==  null){
			System.out.println("------------------"+userid);
			sumLoanrd = mtLoanrdDao.sumUserLoanrd(userid, type, starttime, aborttime);
			if (sumLoanrd == null){
				return "0.00";
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_USERID_ + sumLoanrd, sumLoanrd);
		}
		return sumLoanrd;
	}
	
}
