package com.thinkgem.jeesite.modules.mtloanrd.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;


public class Loanrd {

	/**
	 * 资金流动类型
	 * @param num
	 * @return
	 */
	public static String getType(Integer num) {
		if(num==null) {
			return ("");
		}
		Map<String, String> transType = getMap();
		JSONObject jsonobject = new JSONObject(transType);
		return jsonobject.getString(num.toString());
	}

	/**
	 * 获取全部类型
	 * @return
	 */
	public static List<DataType> getAllType() {
		Map<String, String> transType = getMap();
		List<DataType> list = new ArrayList<DataType>();
		for(int i=0; i<transType.size(); i++) {
			DataType dataType = new DataType();
			dataType.setKey(String.valueOf(i+1));
			dataType.setValue(transType.get(String.valueOf(i+1)));
			list.add(dataType);
		}
		return list;
	}

	private static Map<String, String> getMap() {
		Map<String, String> transType;
		transType = new HashMap<String, String>();
		transType.put("1", "充值");
		transType.put("2", "提现");
		transType.put("3", "投资");
		transType.put("4", "借款");
		transType.put("5", "收益");
		transType.put("6", "还款");
		transType.put("7", "罚息");
		transType.put("8", "放款");
		transType.put("9", "流标");
		return transType;
	}
	
	public static class DataType {
		
		private String key;
		private String value;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
