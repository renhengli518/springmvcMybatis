package commons.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScheduleDateUtils {
	
	//static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 获取周开始时间
	 * @param date
	 * @return
	 */
	public static Date getWeekStartDate(Date date){
		if(date == null){
			date = new Date();
		}
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		if(week == 1){
			cal.add(Calendar.DATE, -6);
		}else{
			cal.add(Calendar.DATE, (-week+2));
		}
		return cal.getTime();
	}
	
	/**
	 * 获取周结束时间
	 * @param date
	 * @return
	 */
	public static Date getWeekEndDate(Date date){
		if(date == null){
			date = new Date();
		}
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		if(week != 1){
			cal.add(Calendar.DATE, (7-week+1));
		}
		return cal.getTime();
	}
	
	/**
	 * 获取当前日期与参数日期所跨周数
	 * @param befDt
	 * @return
	 */
	public static Integer getCurrWeek(Date date){
	    long CONST_WEEK = 3600 * 1000 * 24 * 7;
		Calendar before = Calendar.getInstance();
		Calendar after = Calendar.getInstance();
		before.setTime(date);
		//设置当前日期
		after.setTime(new Date());			
		int week = before.get(Calendar.DAY_OF_WEEK);
		before.add(Calendar.DATE, week==1?(-7):(-week+1));
		week = after.get(Calendar.DAY_OF_WEEK);
		after.add(Calendar.DATE, 7 - (week==1?7:(week-1)));
		int interval = (int) ((after.getTimeInMillis() - before.getTimeInMillis()) / CONST_WEEK);
		//interval = interval-1;
		return (interval< 0 ? 1: interval);
	}
	
	
	/**
	 * 
	 * getMondayDate:获取指定日期所在周的周一日期
	 * @author chenjing
	 * @param date
	 * @return
	 */
	public static Date getMondayDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		day = (day+6)%7;
		day = day==0?7:day;
		calendar.add(Calendar.DAY_OF_YEAR, 1-day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 
	 * getSundayDate:获取指定日期所在周的周日日期
	 * @author chenjing
	 * @param date
	 * @return
	 */
	public static Date getSundayDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		day = (day+6)%7;
		day = day==0?7:day;
		calendar.add(Calendar.DAY_OF_YEAR, 7-day);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 
	 * getWeekDateRange:获取
	 * @author chenjing
	 * @param date
	 * @param weekSeq
	 * @return
	 */
	public static List<Date> getWeekDateRange(Date date, int weekSeq){
		List<Date> dateRange = new ArrayList<Date>();
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(date);
		startDate.add(Calendar.WEEK_OF_YEAR, weekSeq);
		Date newDate = startDate.getTime();
		dateRange.add(getMondayDate(newDate));
		dateRange.add(getSundayDate(newDate));
		return dateRange;
	}
	
	/**
	 * 由startDate开始, 计算countDate是属于第几周
	 * @param startDate
	 * @param countDate
	 * @return
	 */
	public static int getWeekCount(final Date startDate, final Date countDate){
		if (countDate.getTime() < startDate.getTime())
			return -1;
		final long CONST_DAY = 3600 * 1000 * 24;
		long s = startDate.getTime() - CONST_DAY;
		long c = countDate.getTime() - CONST_DAY;
		int weekCount = (int) ((c - s)/(7*CONST_DAY) + 1);
		long mod = (c - s) % (7*CONST_DAY);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(s);
		int d1 = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.setTimeInMillis(s + mod);
		int d2 = calendar.get(Calendar.DAY_OF_WEEK);
		if (d2 < d1)
			weekCount++;
		return weekCount;
	}
	
	
}
