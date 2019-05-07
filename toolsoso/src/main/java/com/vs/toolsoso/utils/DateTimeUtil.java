package com.vs.toolsoso.utils;

import com.blankj.utilcode.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	/**
	 * 将时间格式字符串转换为时间format: yyyy-MM-dd yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate, String format) {
		if (StringUtils.isEmpty(strDate) || StringUtils.isEmpty(format)) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 判断 time1  是否在 time2 之前    
	 * @param time1
	 * @param time2
	 */
	public static boolean Timebefore(String time1,String time2){
		if (StringUtils.isEmpty(time1) || StringUtils.isEmpty(time2)) {
			return false;
		}
		Date t1=strToDate(time1,"yyyy-MM-dd HH:mm:ss");
		Date t2=strToDate(time2,"yyyy-MM-dd HH:mm:ss");
		
		boolean b=t1.before(t2);
		return b;
	}

	/**
	 * 判断 time1  是否在 time2 之前
	 * @param time1
	 * @param time2
	 */
	public static boolean Timebefore2(String time1,String time2){
		if (StringUtils.isEmpty(time1) || StringUtils.isEmpty(time2)) {
			return false;
		}
		Date t1=strToDate(time1,"yyyy-MM-dd");
		Date t2=strToDate(time2,"yyyy-MM-dd");

		boolean b=t1.before(t2);
		return b;
	}

	/**
	 * 判断 time1  是否在 time2 之后
	 * @param time1
	 * @param time2
	 */
	public static boolean Timeafter(String time1,String time2){
		if (StringUtils.isEmpty(time1) || StringUtils.isEmpty(time2)) {
			return false;
		}
		Date t1=strToDate(time1,"yyyy-MM-dd HH:mm:ss");
		Date t2=strToDate(time2,"yyyy-MM-dd HH:mm:ss");
		
		boolean b=t1.after(t2);
		return b;
	}

	/**
	 * yyyyMMddHHmmss 转为 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static String strToDate2(String strDate) {
		if (StringUtils.isEmpty(strDate)) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		String str = formatter2.format(strtodate);
		
		return str;
	}
	
	
	/**
	 * yyyy-MM-dd 转为yyyy-MM
	 * 
	 * @param strDate
	 * @return
	 */
	public static String strToDate3(String strDate) {
		if (StringUtils.isEmpty(strDate)) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		String str = formatter2.format(strtodate);
		
		return str;
	}
	
	/**
	 * strDate 时间字符串
	 * gs1 原来格式
	 * gs2 目标格式
	 *  如  yyyy-MM-dd 转为yyyy-MM
	 * 
	 * @param strDate
	 * @return
	 */
	public static String strToDate4(String strDate,String gs1,String gs2) {
		if (StringUtils.isEmpty(strDate)) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(gs1);
		SimpleDateFormat formatter2 = new SimpleDateFormat(gs2);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		String str = formatter2.format(strtodate);
		
		return str;
	}

	/**
	 *
	 * @param strDate 要转换的日期
	 * @param formatBefor 转换前的日期格式 例如 yyyyMMdd
	 * @param formatAfter 转换后的日期格式 例如 yyyy-MM-dd
     * @return
     */
	public static String ArbitraryFormat(String strDate,String formatBefor,String formatAfter) {
		if (StringUtils.isEmpty(strDate)) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(formatBefor);
		SimpleDateFormat formatter2 = new SimpleDateFormat(formatAfter);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		String str = formatter2.format(strtodate);

		return str;
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 *
	 * @return
	 */
	public static String getWeekStr(Date date) {
		if (date == null) {
			return "";
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int hour = c.get(Calendar.DAY_OF_WEEK);
		switch (hour) {
		case 1:
			return "星期日";
		case 2:
			return "星期一";
		case 3:
			return "星期二";
		case 4:
			return "星期三";
		case 5:
			return "星期四";
		case 6:
			return "星期五";
		case 7:
			return "星期六";
		}
		return "";
	}

	/**
	 * 年月日是否相同
	 */
	public static boolean isSameDate(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}
		if (date1.getYear() != date2.getYear()) {
			return false;
		}
		if (date1.getMonth() != date2.getMonth()) {
			return false;
		}
		if (date1.getDate() != date2.getDate()) {
			return false;
		}
		return true;
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	public static String GetToday() {
		return GetNowTime("yyyy-MM-dd");
	}

	public static String GetNowTime() {
		return GetNowTime("yyyy-MM-dd HH:mm:ss");
	}
	
	
	/**
	 * 获取任意格式 时间
	 * @param format 格式
	 * @return
	 */
	public static String GetFormattime(String format){
		return GetNowTime(format);
	}

	public static String GetNowTime(String formatStr) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	public static String GetDateStr(Date date, String formatStr) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		String str = formatter.format(date);
		return str;
	}

	public static int Gethour() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}

	public static String GetNowTime_sf() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	public static int GetMinute() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MINUTE);
	}

	public static int GetYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	public static int GetMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH)+1;
	}

	public static int GetDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DATE);
	}

	/**
	 * 时间对比差 与过去的时间对比
	 * 
	 * @param nowstr
	 *            现在时间
	 * @param time
	 *            要对比的时间
	 * @return
	 */
	public static String gettime(String nowstr, String time) {
		if (time.equals("") || time.equals("null")) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date now = df.parse(nowstr);
			Date date = df.parse(time);
			long l = now.getTime() - date.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			if (day <= 0) {
				if (hour <= 0) {
					if (min <= 0) {
						if (s < 0) {
							s = 1;
						}
						return s + "秒前";
					} else {
						return min + "分钟前";
					}
				} else {
					return hour + "小时前";
				}
			} else if (day == 1) {
				return "昨天";
			} else if (day == 2) {
				return "前天";
			} else if (day <= 7) {
				return day + "天前";
			} else {
				String[] timearr = time.split(" ");
				return timearr[0];
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 时间对比差
	 * 
	 * @param nowstr
	 *            现在时间
	 * @param time
	 *            要对比的时间
	 * @return 返回相差数值
	 */
	public static long DifferenceTime(String nowstr, String time) {
		if (time.equals("") || time.equals("null")) {
			return 0;
		} else {
			try {
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date now = df.parse(nowstr);
				Date date = df.parse(time);
				long l = date.getTime() - now.getTime();
				return l;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}

		}
	}

	/**
	 * 得到几天前的时间
	 * 
	 * @param day
	 * @return
	 */
	public static String getDateBefore(int day,String timeformat) {
		SimpleDateFormat formatter;
		if(timeformat!=null&& !timeformat.equals("")){
			formatter = new SimpleDateFormat(timeformat);
		}else{
			 formatter = new SimpleDateFormat("yyyy-MM-dd");
		}
		
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		Calendar now = Calendar.getInstance();
		now.setTime(curDate);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);

		String str = formatter.format(now.getTime());
		return str;
	}

	/**
	 * 得到几天后的时间
	 * 
	 * @param day
	 * @return
	 */
	public static String getDateAfter(int day) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		Calendar now = Calendar.getInstance();
		now.setTime(curDate);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		String str = formatter.format(now.getTime());
		return str;
	}

	
	/**
	 * 获取某年某月 有多少天
	 * @param y
	 * @param m
	 * @return
	 */
	public static int GetMonthDay(int y,int m){	
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, y);
		cal.set(Calendar.MONTH, m);
		return cal.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 日期字符串转Calendar
	 * @param dateString yyyy-MM-dd
	 * @return Calendar
     */
	public static Calendar String2Calendar(String dateString){

		Calendar calendar = null;
		try {
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			Date date =sdf.parse(dateString);
			calendar = Calendar.getInstance();
			calendar.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar;
	}

	/**
	 * 两个时间之间相差距离多少天
	 * @param str1 时间参数 1：
	 * @param str2 时间参数 2：
	 * @return 相差天数
	 */
	public static long getDistanceDays(String str1, String str2,String format){
		DateFormat df = new SimpleDateFormat(format);
		Date one;
		Date two;
		long days=0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff ;
			if(time1<time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			days = diff / (1000 * 60 * 60 * 24);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}

}
