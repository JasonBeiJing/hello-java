package com.hello.java.jdk8.date_time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class TryDateTime {

	public static void tryLocalDate() {
		System.err.println("LocalDate.now() ====> " + LocalDate.now());
		System.err.println("LocalDate.now(ZoneId.of(ZoneId.SHORT_IDS.get(\"CTT\"))) ====> "
				+ LocalDate.now(ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))));

		System.err.println("LocalDate.of(2012, Month.JULY, 12) ====> " + LocalDate.of(2012, Month.JULY, 12));

		// 从1970-01-01开始加天数
		System.err.println("LocalDate.ofEpochDay(15) ====> " + LocalDate.ofEpochDay(15));
		// 从给定的年份开始加天数
		System.err.println("LocalDate.ofYearDay(2018, 10) ====> " + LocalDate.ofYearDay(2018, 10));
	}

	public static void tryLocalTime() {
		System.err.println("LocalTime.now() ====> " + LocalTime.now());
		System.err.println("LocalTime.now(ZoneId.of(ZoneId.SHORT_IDS.get(\"CTT\"))) ====> "
				+ LocalTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))));

		System.err.println("LocalTime.of(5, 10, 20, 30) ====> " + LocalTime.of(5, 10, 20, 30));

		// 从00:00:00 开始加纳秒
		System.err.println("LocalTime.ofNanoOfDay(10) ====> " + LocalTime.ofNanoOfDay(10));
		// 从00:00:00 开始加秒
		System.err.println("LocalTime.ofSecondOfDay(10) ====> " + LocalTime.ofSecondOfDay(10));
	}

	public static void tryLocalDateTime() {
		System.err.println("LocalDateTime.now() ====> " + LocalDateTime.now());
		System.err.println("LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get(\"CTT\"))) ====> "
				+ LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))));

		System.err.println("LocalDateTime.of(2012, Month.MARCH, 16, 17, 18, 19, 20) ====> "
				+ LocalDateTime.of(2012, Month.MARCH, 16, 17, 18, 19, 20));
		System.err.println("LocalDateTime.of(LocalDate.now(), LocalTime.now()) ====> "
				+ LocalDateTime.of(LocalDate.now(), LocalTime.now()));

		// 以1970-01-01 00:00:00 0000为基础，往后推多少秒得到一个日志
		System.err.println("LocalDateTime.ofEpochSecond(3600, 20, ZoneOffset.UTC) ====> "
				+ LocalDateTime.ofEpochSecond(3600, 20, ZoneOffset.UTC));

	}

	public static void tryInstant() {

	}

	public static void tryDateTimeUtils() {
		LocalDate now = LocalDate.now();

		// Get the Year, check if it's leap year
		System.out.println("Year " + now.getYear() + " is Leap Year? " + now.isLeapYear());

		// Compare two LocalDate for before and after
		System.out.println("Today is before 01/01/2015? " + now.isBefore(LocalDate.of(2015, 1, 1)));

		// Create LocalDateTime from LocalDate
		System.out.println("Current Time=" + now.atTime(LocalTime.now()));

		// plus operations
		System.out.println("10 days after today will be " + now.plusDays(10));
		System.out.println("3 weeks after today will be " + now.plusWeeks(3));
		System.out.println("20 months after today will be " + now.plusMonths(20));

		// minus operations
		System.out.println("10 days before today will be " + now.minusDays(10));
		System.out.println("3 weeks before today will be " + now.minusWeeks(3));
		System.out.println("20 months before today will be " + now.minusMonths(20));

		// Temporal adjusters for adjusting the dates
		System.out.println("First date of this month= " + now.with(TemporalAdjusters.firstDayOfMonth()));
		System.out.println("Last date of this year= " + now.with(TemporalAdjusters.lastDayOfYear()));

		Period period = now.until(now.with(TemporalAdjusters.lastDayOfYear()));
		System.out.println("Period Format= " + period);
		System.out.println("Months remaining in the year= " + period.getMonths());
	}

	public static void tryParseDateTime() {
		// Format examples
		LocalDate date = LocalDate.now();
		
		// default format
		System.out.println("Default format of LocalDate=" + date);
		// specific format
		System.out.println(date.format(DateTimeFormatter.ofPattern("d::MMM::uuuu")));
		System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));

		LocalDateTime dateTime = LocalDateTime.now();
		// default format
		System.out.println("Default format of LocalDateTime=" + dateTime);
		// specific format
		System.out.println(dateTime.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss")));
		System.out.println(dateTime.format(DateTimeFormatter.BASIC_ISO_DATE));

		// Parse examples
		LocalDateTime dt = LocalDateTime.parse("27::八月::2014 21::39::48", DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss"));
		System.out.println("Default format after parsing = " + dt);
		
	}

	public static void main(String[] args) {
		// tryLocalDate();
		// tryLocalTime();
		// tryLocalDateTime();
		//tryInstant();
		//tryDateTimeUtils();
		tryParseDateTime();
	}
}
