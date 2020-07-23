package com.config;

import org.springframework.util.Assert;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 工具类
 * 
 * @author frank
 */

public class StringUtil {

	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;
		return obj.toString().trim().equals("");
	}

	public static boolean isNotEmpty(Object obj) {
		if (obj != null && !obj.toString().trim().equals(""))
			return true;
		return false;
	}

	public static boolean isEmptyArray(Object[] obj) {
		if (obj == null || obj.length == 0)
			return true;
		return false;
	}

	public static boolean isNotEmptyArray(Object[] obj) {
		if (obj != null && obj.length != 0)
			return true;
		return false;
	}

	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * 将Long类型的时间戳转换成String类型的时间格式"yyyy-MM-dd HH:mm:ss"
	 */
	public static String convertToDateTimeString(Long time){
		Assert.notNull(time, "the param time is null!");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
	}

	/**
	 * 将Long类型的时间戳转换成String类型的时间格式"yyyy-MM-dd"
	 */
	public static String convertToDateString(Long time){
		Assert.notNull(time, "the param time is null!");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return formatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
	}

	/**
	 * 将Long类型的时间戳转换成String类型的时间格式"HH:mm:ss"
	 */
	public static String convertToTimeString(Long time){
		Assert.notNull(time, "the param time is null!");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return formatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
	}

	/**
	 * 将Long类型的时间戳转换成String类型的时间格式"HH:mm"
	 */
	public static String convertToShortTimeString(Long time){
		Assert.notNull(time, "time is null!");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return formatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
	}

	/**
	 * Date转换成LocalDate
	 * @param date
	 * @return
	 */
	public static LocalDate dateToLocalDate(Date date) {
		Assert.notNull(date, "date is null");
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * LocalDate转换成Date
	 * @param localDate
	 * @return
	 */
	public static Date localDateToDate(LocalDate localDate) {
		Assert.notNull(localDate, "localDate is null");
		ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
		return Date.from(zonedDateTime.toInstant());
	}

	/**
	 * LocalDateTime转换成Date
	 * @param localDateTime
	 * @return
	 */
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		Assert.notNull(localDateTime, "localDateTime is null");
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * LocalDate格式化
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	/**
	 * 返回指定日期对应星期的简称
	 */
	public static String getShortWeekOfTheDate(LocalDate localDate) {
		String[][] weekArray = { { "MONDAY", "MON" }, { "TUESDAY", "TUE" }, { "WEDNESDAY", "WED" }, { "THURSDAY", "THUR" },
				{ "FRIDAY", "FRI" }, { "SATURDAY", "SAT" }, { "SUNDAY", "SUN" } };
		String weekNo = String.valueOf(localDate.getDayOfWeek());
		//获取行数
		for (int i = 0; i < weekArray.length; i++) {
			if (weekNo.equals(weekArray[i][0])) {
				weekNo = weekArray[i][1];
				break;
			}
		}
		return weekNo;
	}

	/**
	 * 将指定Date(yyyyMMdd HH:mm:ss)转为LocalDateTime
	 */
	public static LocalDateTime dateToLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

}
