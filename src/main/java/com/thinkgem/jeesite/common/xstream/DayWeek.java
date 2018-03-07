package com.thinkgem.jeesite.common.xstream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DayWeek {
	
	 /** 
     * 获取当月的 天数 
     * */  
    public static int getMonthDay() {
          
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
  
    /** 
     * 根据年 月 获取对应的月份 天数 
     * */  
    public static int getMonthDay(int year, int month) {
          
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
      
    /** 
     * 根据日期 找到对应日期的 星期 
     */  
    public static String getDayWeek(String date) {
        String dayOfweek = "-1";  
        try {  
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");  
            Date myDate = myFormatter.parse(date);  
            SimpleDateFormat formatter = new SimpleDateFormat("E");  
            String str = formatter.format(myDate);  
            dayOfweek = str;  
              
        } catch (Exception e) {  
            System.out.println("错误!");
        }  
        return dayOfweek;  
    }
    
    /**
      * 指定日期加上天数后的日期
      * @param num 为增加的天数
      * @param newDate 创建时间
      * @return
      * @throws ParseException 
      */
     public static String plusDay(int num,String newDate) throws ParseException {
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         Date  currdate = format.parse(newDate);
         System.out.println("现在的日期是：" + currdate);
         Calendar ca = Calendar.getInstance();
         ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
         currdate = ca.getTime();
         String enddate = format.format(currdate);
         System.out.println("增加天数以后的日期：" + enddate);
         return enddate;
     }
         
     /**
      * 当前日期加上天数后的日期
      * @param num 为增加的天数
      * @return
      */
     public static String plusDay2(int num) {
         Date d = new Date();
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         String currdate = format.format(d);
         System.out.println("现在的日期是：" + currdate);
 
         Calendar ca = Calendar.getInstance();
         ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
         d = ca.getTime();
         String enddate = format.format(d);
         System.out.println("增加天数以后的日期：" + enddate);
         return enddate;
     }
     
     /**
      * 当前日期减去天数后的日期
      * @param num 为减去的天数
      * @return
      */
     public static String minusDay(int num) {
         Date d = new Date();
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         String currdate = format.format(d);
         System.out.println("现在的日期是：" + currdate);
 
         Calendar ca = Calendar.getInstance();
         ca.setTime(d);
         ca.set(Calendar.DATE, ca.get(Calendar.DATE) - num);
         d = ca.getTime();
         String enddate = format.format(d);
         System.out.println("减去天数以后的日期：" + enddate);
         return enddate;
     }
     
     /**
      * 指定日期减去天数后的日期
      * @param num 为减去的天数
      * @return
     * @throws ParseException 
      */
     public static String minusDay(int num, String newDate) throws ParseException {
    	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	 Date currdate = format.parse(newDate);
    	 System.out.println("现在的日期是：" + currdate);
    	 
    	 Calendar ca = Calendar.getInstance();
    	 ca.setTime(currdate);
    	 ca.set(Calendar.DATE, ca.get(Calendar.DATE) - num);
    	 currdate = ca.getTime();
    	 String enddate = format.format(currdate);
    	 System.out.println("减去天数以后的日期：" + enddate);
    	 return enddate;
     }

	public static String plusMonth(int month) {
		//获得一个日历的实例
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		// 设置日历时间
		calendar.setTime(date);
		// 在日历的月份上增加月数
		calendar.add(Calendar.MONTH, month);		
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 获取两个时间差的天数
	 * @param date1 
	 * @param date2
	 * @return
	 */
	public static int makeDaynum(Date date1, Date date2) {
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
		return days;
	}
	
}
