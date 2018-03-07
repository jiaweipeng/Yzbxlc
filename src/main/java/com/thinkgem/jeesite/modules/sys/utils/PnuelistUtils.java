package com.thinkgem.jeesite.modules.sys.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.pnuelist.dao.MtPnuelistDao;

public class PnuelistUtils {
	
	private static MtPnuelistDao  mtPnuelistDao = SpringContextHolder.getBean(MtPnuelistDao.class);
	
	/**
	 * 根据ID获取P
	 * @param id
	 * @return 取不到返回null
	 */
	public static String sumInmount(String datetime, Integer type) {
		String date = toDate(datetime);
		String result = mtPnuelistDao.sumInmount(date+"%", type);
		if (result == null){
			return "0.00";
		}
		return result;
	}
	
	public static String sumUserInmount(String userid, String starttime, String aborttime) {
		String result = mtPnuelistDao.sumUserInmount(userid, starttime, aborttime);
		if (result == null){
			return "0.00";
		}
		return result;
	}
	
	private static String toDate(String time) {
		String createDate = null;
		try {
			SimpleDateFormat simple = null;
			simple = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
			Date date = (Date) simple.parse(time.toString());
			createDate = DateUtils.formatDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createDate;
	}
	
}
