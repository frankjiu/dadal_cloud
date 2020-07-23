/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年7月2日
 * @version: V1.0
 */

package com.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年7月2日
 */

public class testmain {

	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Test
	public void get() throws ParseException {

		LocalDateTime localDateTime = LocalDateTime.now();
		Date date = new Date();

		System.out.println(localDateTime);
		System.out.println(date);

		//String timeStr = convertTimeToString(1594795195000L);
		String timeStr = convertTimeToString(1593671995000L);
		System.out.println(timeStr);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Date date15 = format.parse(timeStr);
		System.out.println(date15);

		String strDate = "2020-07-15";

		Date date14 = format.parse("2020-07-15 14:39:55");
		System.out.println(date);

		boolean flag = dateToLocalDate(date15).compareTo(LocalDate.now()) >= 0;

		LocalDate now = LocalDate.now();
		System.out.println(now);

		System.out.println(flag);

		System.out.println(getShortWeekOfTheDate(new Date()));

		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(strDate, formatter);
		System.out.println("====");
		System.out.println(localDate);

		String st = "aabb";
		boolean f = st.equals("aabb");
		System.out.println(".....");
		System.out.println(f);

	}

	// Monday(Mon.),Tuesday(Tues.),Wednesday(Wed.),Thursday(Thur.或者 Thurs.),Friday(Fri.),Saturday(Sat.),Sunday(Sun.)

	/**
	 * 返回指定日期对应星期的简称
	 */
	public static String getShortWeekOfTheDate(Date date) {
		String[][] weekArray = { { "MONDAY", "MON" }, { "TUESDAY", "TUE" }, { "WEDNESDAY", "WED" }, { "THURSDAY", "THUR" },
				{ "FRIDAY", "FRI" }, { "SATURDAY", "SAT" }, { "SUNDAY", "SUN" } };
		LocalDate localDate = dateToLocalDate(date);

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

	//yyyy-MM-dd HH:mm:ss
	public static String convertTimeToString(Long time) {
		Assert.notNull(time, "time is null");
		DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
	}

	//Date转换成LocalDate
	public static LocalDate dateToLocalDate(Date date) {
		Assert.notNull(date, "date is null");
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	//LocalDate转换成Date
	public static Date localDateToDate(LocalDate localDate) {
		Assert.notNull(localDate, "localDate is null");
		ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
		return Date.from(zonedDateTime.toInstant());
	}

	//LocalDateTime转换成Date
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		Assert.notNull(localDateTime, "localDateTime is null");
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	//LocalDate格式化
	public static String formatDate(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

}
