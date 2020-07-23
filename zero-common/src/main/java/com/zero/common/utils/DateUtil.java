package com.zero.common.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public final class DateUtil
{

	public static final String FORMATDATE="yyyy-MM-dd HH:mm:ss";
	public static final String FORMATDATETWO="yyyy-MM-dd";
	public static final String FORMATMONTHTWO="yyyy-MM";
	public static final String FORMATDATE_SIMPLE="yyyyMMdd";
	public static final String FORMATDATE_MORE="yyyy-MM-dd HH:mm:ss.SSS";

	private DateUtil()
	{
	}

	public static Date getSQLDate(java.util.Date date)
	{
		if (date == null)
			return null;
		else
			return new Date(date.getTime());
	}
	
	/**
	 * Return current datetime string.
	 *
	 * @param pattern
	 *            format pattern
	 * @return current datetime
	 */
	public static String getDateTime(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dt = sdf.format(new java.util.Date());
		return dt;
	}
	
	public static Date getSQLDate(String dateStr){
		if(dateStr==null || dateStr.trim().length()==0){
			return null;
		}else{
			return new Date(toDate(dateStr).getTime());
		}
	}

	public static Date getSQLToday()
	{
		Calendar cal = Calendar.getInstance();
		return new Date(cal.getTimeInMillis());
	}

	public static java.util.Date getToday()
	{
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public static Timestamp getTimestamp(java.util.Date date)
	{
		if (date == null)
			return null;
		else
			return new Timestamp(date.getTime());
	}

	public static java.util.Date toDate(String sDD, String sMM, String sYYYY, String sHHmm, int iFromTZOffset, int iToTZOffset)
	{
		GregorianCalendar gc = new GregorianCalendar();
		int iDD;
		int iMM;
		int iYYYY;
		try {
			gc.setTime((new SimpleDateFormat("ddMMyyyyHHmm", Locale.US)).parse((new StringBuilder()).append(sDD).append(sMM).append(sYYYY).append(sHHmm).toString()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		iDD = Integer.parseInt(sDD);
		iMM = Integer.parseInt(sMM);
		iYYYY = Integer.parseInt(sYYYY);
		if (iDD != gc.get(5) || iMM != gc.get(2) + 1 || iYYYY != gc.get(1))
			return null;
		try
		{
			gc.add(10, -iFromTZOffset + iToTZOffset);
		}
		catch (Exception e)
		{
			return null;
		}
		return gc.getTime();
	}

	public static java.util.Date toDate(String sDD, String sMMM, String sYYYY)
	{
		return toDate(sDD, sMMM, sYYYY, "0000");
	}

	public static java.util.Date toDate(String sDD, String sMMM, String sYYYY, String sHHmm)
	{
		return toDate(sDD, sMMM, sYYYY, sHHmm, 0, 0);
	}

	public static int getDateDiff(Calendar d1, Calendar d2)
	{
		if (d1.after(d2))
		{
			Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(6) - d1.get(6);
		int y2 = d2.get(1);
		if (d1.get(1) != y2)
		{
			d1 = (Calendar)d1.clone();
			do
			{
				days += d1.getActualMaximum(6);
				d1.add(1, 1);
			} while (d1.get(1) != y2);
		}
		return days;
	}

	public static int getDateDiff(java.util.Date firstDate, java.util.Date secondDate)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(secondDate);
		long differenceInMillis = firstDate.getTime() - secondDate.getTime();
		double differenceInDays = (double)differenceInMillis / 86400000D;
		int intDay = (int)Math.round(differenceInDays);
		return intDay;
	}

	public int getDateDiff(String startDate, String endDate)
	{
		DateFormat df = DateFormat.getDateInstance(3, Locale.US);
		java.util.Date sDate = new java.util.Date();
		java.util.Date eDate = new java.util.Date();
		try
		{
			sDate = df.parse(startDate);
			eDate = df.parse(endDate);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Integer diff = new Integer((int)((eDate.getTime() - sDate.getTime()) / 0x5265c00L) + 1);
		return diff.intValue();
	}

	public static java.util.Date addDate(java.util.Date d, int dayOffset)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.add(5, dayOffset);
		return gc.getTime();
	}

	public static java.util.Date addDate(String strDate, String strfmt, int numDay)
	{
		java.util.Date bdate = null;
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat(strfmt);
			java.util.Date sd = formatter.parse(strDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sd);
			cal.add(5, numDay);
			bdate = cal.getTime();
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return bdate;
	}

	public static java.util.Date parseDate(String strdate, String format)
	{
		java.util.Date bdate = null;
		if (null == strdate)
			return bdate;
		try
		{
			SimpleDateFormat dFormat = new SimpleDateFormat(format);
			dFormat.setLenient(false);
			bdate = new java.util.Date(dFormat.parse(strdate).getTime());
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return bdate;
	}

	public static String formatDate(java.util.Date date, String format)
	{
		if (date == null || format == null)
		{
			return "";
		} else
		{
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.format(date);
		}
	}

	public static String formatDate(Date date, String format)
	{
		if (date == null || format == null)
		{
			return "";
		} else
		{
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.format(date);
		}
	}

	public static java.util.Date toDate(String date)
	{
		if (date == null || date.equals(""))
			return null;
		DateFormat df = SimpleDateFormat.getDateInstance(3, Locale.CHINA);
		java.util.Date d = null;
		try
		{
			d = df.parse(date.trim());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return d;
	}

	public static Calendar getCalendar(java.util.Date date)
	{
		if (date == null)
		{
			return null;
		} else
		{
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c;
		}
	}

	public static java.util.Date getDate(Calendar c)
	{
		if (c == null)
			return null;
		else
			return c.getTime();
	}
	/*
	 * 时间格式转换默认格式"yyyyMMdd"
	 * @author yangg  2014-5-27
	 * @param date
	 * @define "yyyyMMdd"
	 * @return String
	 */
	public static String getDateFormat(Date date){
		if(date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);		
	}
	public static String DateFormat(Date date){
		if(date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);		
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param begin 较小的时间
     * @param end  较大的时间
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date begin,Date end) throws ParseException {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
        Calendar cal = Calendar.getInstance();    
        cal.setTime(begin);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(end);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time1-time2)/(1000*3600*24);  
    	return Integer.parseInt(String.valueOf(between_days)); 
    }
    
    /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param days  较大的时间
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static Date addSqlDate(Date smdate,Integer days) throws ParseException {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
        Calendar cal = Calendar.getInstance();  
        cal.setTime(smdate);
        cal.add(Calendar.DAY_OF_MONTH, days);
    	return new Date(cal.getTimeInMillis()); 
    }	
    
    /**
     * 生成时间戳
     * @return
     */
    public static String lastDateTime(){
    	SimpleDateFormat df = new SimpleDateFormat(FORMATDATE_MORE);
    	return df.format(System.currentTimeMillis());
    }

	/**
	 * 获取当前期间的上下几个月的信息  iMonth 为正时将来月   为负时 之前月份
	 * @param today
	 * @param iMonth
	 * @return
	 */
    public static String nextMonth(java.util.Date today, Integer iMonth){
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.MONTH, iMonth);
		return DateUtil.formatDate(new Date(cal.getTimeInMillis()), FORMATMONTHTWO);
	}

	/**
	 * 计算两个日期之间相差的月份
	 * @param 	begin 较小的时间
	 * @param 	end  较大的时间
	 * @return  相差天数
	 */
	public static int monthBetween(java.util.Date begin, java.util.Date end) {
		Calendar calBegin = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		calBegin.setTime(begin);
		calEnd.setTime(end);
		int result = calEnd.get(Calendar.MONTH) - calBegin.get(Calendar.MONTH);
		int month = (calEnd.get(Calendar.YEAR) - calBegin.get(Calendar.YEAR)) * 12;
		return Math.abs(month + result);
	}

	/**
	 * 获取指定年月的最后一天
	 * @param period
	 * @return
	 */
	public static java.util.Date getLastDayOfMonth(String period) {
		Integer year = Integer.valueOf(period.substring(0,4));
		Integer month = Integer.valueOf(period.substring(5,7));
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR, year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return cal.getTime();
	}
}
