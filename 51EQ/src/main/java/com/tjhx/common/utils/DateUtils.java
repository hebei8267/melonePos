package com.tjhx.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

	/**
	 * 取得当前时间
	 * 
	 * @return 当前日期（Date）
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 取得当前时间
	 * 
	 * @return 当前日期（String yyyyMMdd）
	 */
	public static String getCurrentDateShortStr() {
		return getCurFormatDate("yyyyMMdd");
	}

	/**
	 * 取得当前时间
	 * 
	 * @return 当前日期（String yyyy-MM）
	 */
	public static String getCurrentMonth() {
		return getCurFormatDate("yyyy-MM");
	}

	/**
	 * 取得当前时间
	 * 
	 * @return 当前日期（String yyyy）
	 */
	public static String getCurrentYear() {
		return getCurFormatDate("yyyy");
	}

	/**
	 * 取得当前时间的特定表示格式的字符串
	 * 
	 * @param style 时间格式（如：yyyy/MM/dd hh:mm:ss）
	 * @return 当前时间
	 */
	public static synchronized String getCurFormatDate(String style) {
		Date date = getCurrentDate();
		simpleDateFormat.applyPattern(style);
		return simpleDateFormat.format(date);
	}

	/**
	 * 取得指定天数后的日期（以当前时间为准）
	 * 
	 * @param addDay
	 * @return
	 */
	public static synchronized Date getNextDateFormatDate(int addDay) {
		Calendar cal = Calendar.getInstance();
		Date _date = getCurrentDate();
		cal.setTime(_date);
		cal.add(Calendar.DATE, addDay);
		return cal.getTime();
	}

	/**
	 * 取得指定天数后的日期（以当前时间为准）
	 * 
	 * @param addDay
	 * @param style
	 * @return
	 */
	public static synchronized String getNextDateFormatDate(int addDay, String style) {
		Calendar cal = Calendar.getInstance();
		Date _date = getCurrentDate();
		cal.setTime(_date);
		cal.add(Calendar.DATE, addDay);
		simpleDateFormat.applyPattern(style);
		return simpleDateFormat.format(cal.getTime());
	}

	/**
	 * 取得指定天数后的日期（以指定时间为准）
	 * 
	 * @param addDay
	 * @param style
	 * @return
	 * @throws ParseException
	 */
	public static synchronized String getNextDateFormatDate(String date, int addDay, String style) throws ParseException {
		Calendar cal = Calendar.getInstance();
		Date _date = stringToDate(date, style);
		cal.setTime(_date);
		cal.add(Calendar.DATE, addDay);
		simpleDateFormat.applyPattern(style);
		return simpleDateFormat.format(cal.getTime());
	}

	/**
	 * 取得指定年数后的日期（以指定时间为准）
	 * 
	 * @param addDay
	 * @param style
	 * @return
	 * @throws ParseException
	 */
	public static synchronized String getNextYearFormatDate(String date, int addDay, String style) throws ParseException {
		Calendar cal = Calendar.getInstance();
		Date _date = stringToDate(date, style);
		cal.setTime(_date);
		cal.add(Calendar.YEAR, addDay);
		simpleDateFormat.applyPattern(style);
		return simpleDateFormat.format(cal.getTime());
	}

	/**
	 * 取得指定天数后的日期（以当前时间为准）
	 * 
	 * @param addMonth
	 * @param style
	 * @return
	 */
	public static synchronized String getNextMonthFormatDate(int addMonth, String style) {
		Calendar cal = Calendar.getInstance();
		Date _date = getCurrentDate();
		cal.setTime(_date);
		cal.add(Calendar.MONTH, addMonth);
		simpleDateFormat.applyPattern(style);
		return simpleDateFormat.format(cal.getTime());
	}

	/**
	 * 转换日历格式
	 * 
	 * @param date
	 * @param fromStyle
	 * @param toStyle
	 * @return
	 */
	public static String transDateFormat(String date, String fromStyle, String toStyle) {
		simpleDateFormat.applyPattern(fromStyle);
		try {
			Date _d = simpleDateFormat.parse(date);
			simpleDateFormat.applyPattern(toStyle);
			return simpleDateFormat.format(_d);
		} catch (ParseException e) {
			return "";
		}
	}

	/**
	 * 转换日历格式
	 * 
	 * @param date
	 * @param toStyle
	 * @return
	 */
	public static String transDateFormat(Date date, String toStyle) {
		if (null == date) {
			return null;
		}
		simpleDateFormat.applyPattern(toStyle);
		return simpleDateFormat.format(date);
	}

	/**
	 * String型 ---> Date型変換する
	 * 
	 * @param date 入力のString型
	 * @param style 入力のフォーマット
	 * @return Date Date型時間
	 * 
	 *         2007/01/26 新規作成 weifeng
	 * @throws ParseException 日期不满足格式要求时抛异常
	 */
	public static Date stringToDate(String date, String style) throws ParseException {

		Date da = null;
		simpleDateFormat.applyPattern(style);
		da = simpleDateFormat.parse(date);
		return da;
	}

	/**
	 * 取得dateTo与dateFrom的日期间隔
	 * 
	 * @param dateFrom dateFrom
	 * @param dateTo dateTo
	 * @param style yyyyMMdd
	 * @return 日期间隔
	 * @throws ParseException
	 */
	public static Long getDateSpanDay(String dateFrom, String dateTo) throws ParseException {
		return getDateSpanDay(dateFrom, dateTo, "yyyyMMdd");
	}

	/**
	 * 取得dateTo与dateFrom的日期间隔
	 * 
	 * @param dateFrom dateFrom
	 * @param dateTo dateTo
	 * @param style
	 * @return 日期间隔
	 * @throws ParseException
	 */
	public static long getDateSpanDay(String dateFrom, String dateTo, String style) throws ParseException {
		Date _dateFrom = stringToDate(dateFrom, style);
		Date _dateTo = stringToDate(dateTo, style);
		return (_dateTo.getTime() - _dateFrom.getTime()) / (1000 * 3600 * 24);
	}

	/**
	 * 取得dateTo与dateFrom的日期间隔
	 * 
	 * @param dateFrom dateFrom
	 * @param dateTo dateTo
	 * @return 日期间隔
	 */
	public static long getDateSpanDay(Date dateFrom, Date dateTo) {
		return (dateTo.getTime() - dateFrom.getTime()) / (1000 * 3600 * 24);
	}

	/**
	 * 取得dateTo与dateFrom的日期间隔(返回结果-分钟)
	 * 
	 * @param dateFrom dateFrom
	 * @param dateTo dateTo
	 * @return 日期间隔
	 */
	public static double getDateSpanHour(Date dateFrom, Date dateTo) {
		return (dateTo.getTime() - dateFrom.getTime()) / (1000 * 3600);
	}

	/**
	 * 取得星期数
	 * 
	 * @param dt
	 * @param style
	 * @return
	 * @throws ParseException
	 */
	public static String getWeekOfDate(String dt, String style) throws ParseException {

		String[] weekDays = { "日", "一", "二", "三", "四", "五", "六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(stringToDate(dt, style));
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekDays[w];
	}

	/**
	 * 取得星期数
	 * 
	 * @param dt
	 * @return
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "日", "一", "二", "三", "四", "五", "六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekDays[w];
	}

	/**
	 * 取得指定月份天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getMonthDays(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);

		return cal.get(Calendar.DATE);
	}

	/**
	 * 取得指定月份天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getMonthDays(String year, String month) {
		int _year = Integer.parseInt(year);
		int _month = Integer.parseInt(month);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, _year);
		cal.set(Calendar.MONTH, _month - 1);
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);

		return cal.get(Calendar.DATE);
	}

	/**
	 * 返回两个时间差的毫秒数
	 * 
	 * @param time1 早一点的时间
	 * @param style1
	 * @param time2 晚一点的时间
	 * @param style2
	 * @return
	 * @throws ParseException
	 */
	public static long timeBetween(String time1, String style1, String time2, String style2) throws ParseException {
		SimpleDateFormat dfs1 = new SimpleDateFormat(style1);
		SimpleDateFormat dfs2 = new SimpleDateFormat(style2);
		long between = 0;

		java.util.Date date1 = dfs1.parse(time1);
		java.util.Date date2 = dfs2.parse(time2);
		between = (date2.getTime() - date1.getTime());// 得到两者的毫秒数
		return between;
	}

	/**
	 * 返回两个时间差的毫秒数
	 * 
	 * @param time1 早一点的时间
	 * @param time2 晚一点的时间
	 * @param style
	 * @return
	 * @throws ParseException
	 */
	public static long timeBetween(String time1, String time2, String style) throws ParseException {
		SimpleDateFormat dfs = new SimpleDateFormat(style);
		long between = 0;

		java.util.Date date1 = dfs.parse(time1);
		java.util.Date date2 = dfs.parse(time2);
		between = (date2.getTime() - date1.getTime());// 得到两者的毫秒数
		return between;
	}

	public static void main(String[] args) throws ParseException {

	}
}
