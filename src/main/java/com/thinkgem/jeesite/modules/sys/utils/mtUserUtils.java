package com.thinkgem.jeesite.modules.sys.utils;

import org.json.JSONObject;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.xstream.DES;
import com.thinkgem.jeesite.modules.mtuser.dao.MtUserDao;
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;
import com.thinkgem.jeesite.modules.product.web.HuanXun;

public class mtUserUtils {
	private static MtUserDao  userDao = SpringContextHolder.getBean(MtUserDao.class);
	public static final String USER_CACHE = "mtuserCache";
	public static final String USER_CACHE_ID_ = "id_";
	private static final String USER_ACCTNO_CACHE = "ipsAcctNoCache";
	private static final String USER_ACCTNO_CACHE_ID_ = "id_";
	
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */
	public static MtUser get(String id){
		MtUser user = (MtUser)CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user ==  null){
			System.out.println("------------------"+id);
			user = userDao.get(id);
			if (user == null){
				return null;
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
		}
		return user;
	}
	
	/**
	 * 根据ipsAcctNo获取用户
	 * @param ipsAcctNo
	 * @return
	 */
	public static MtUser findUser(String ipsAcctNo) {
		MtUser user = (MtUser)CacheUtils.get(USER_ACCTNO_CACHE, USER_ACCTNO_CACHE_ID_+ipsAcctNo);
		if (user ==  null){			
			user = userDao.findUser(ipsAcctNo);
			if (user == null){
				return null;
			}
			CacheUtils.put(USER_ACCTNO_CACHE, USER_ACCTNO_CACHE_ID_+user.getIpsAcctNo(), user);
		}
		return user;
	}
	
	public static String findFreeze(String ipsAcctNo) {
		String freeze = "";
		if(ipsAcctNo!=null && !ipsAcctNo.equals("")) {
			HuanXun huanXun = new HuanXun();
			String result = huanXun.findInterface(ipsAcctNo, "03", "");
			JSONObject object = new JSONObject(result);
			String leanStatus = object.getString("resultCode");
			if(leanStatus.equals("000000")) {
				String response = object.getString("response");
		    	response = DES.decrypt3DES(response, huanXun.getDesKey(), huanXun.getDesIv());
				JSONObject jsonobject = new JSONObject(response).getJSONObject("balance");
				freeze = jsonobject.optString("freezeBal");
			}
		}
		return freeze;
	}
	
}
