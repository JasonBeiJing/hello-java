package com.hello.java.jdk8.date_time;

import java.time.Clock;
import java.time.Duration;
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
import java.util.concurrent.TimeUnit;

public class TryDateTime {

	public static void tryLocalDate() {
		System.err.println("LocalDate.now() ====> " + LocalDate.now());
		System.err.println("LocalDate.now(ZoneId.of(ZoneId.SHORT_IDS.get(\"CTT\"))) ====> "
				+ LocalDate.now(ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))));

		System.err.println("LocalDate.of(2012, Month.JULY, 12) ====> " + LocalDate.of(2012, Month.JULY, 12));

		System.err.println("LocalDate.ofEpochDay(15) ====> " + LocalDate.ofEpochDay(15));
		System.err.println("LocalDate.ofYearDay(2018, 10) ====> " + LocalDate.ofYearDay(2018, 10));
	}

	public static void tryLocalTime() {
		System.err.println("LocalTime.now() ====> " + LocalTime.now());
		System.err.println("LocalTime.now(ZoneId.of(ZoneId.SHORT_IDS.get(\"CTT\"))) ====> "
				+ LocalTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))));

		System.err.println("LocalTime.of(5, 10, 20, 30) ====> " + LocalTime.of(5, 10, 20, 30));

		System.err.println("LocalTime.ofNanoOfDay(10) ====> " + LocalTime.ofNanoOfDay(10));
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

		System.err.println("LocalDateTime.ofEpochSecond(3600, 20, ZoneOffset.UTC) ====> "
				+ LocalDateTime.ofEpochSecond(3600, 20, ZoneOffset.UTC));

	}

	//Instant类在Java日期与时间功能中，表示了时间线上一个确切的点，定义为距离初始时间的时间差（初始时间为GMT 1970年1月1日00:00） 
	//经测量一天有86400秒，从初始时间开始不断向前移动。
	/*
	 * plusSeconds()
	 * plusMillis()
	 * plusNanos()
	 * minusSeconds()
	 * minusMillis()
	 * minusNanos()
	 */
	public static void tryInstant() {
		Instant now = Instant.now();
		System.err.println(" ==== Instant.getEpochSecond() === " + now.getEpochSecond());
		System.err.println(" ==== Instant.getNano() === " + now.getNano());
		System.err.println(" ==== Instant.toEpochMilli() === " + now.toEpochMilli());
		System.err.println(" ==== System.currentTimeMillis() === " + System.currentTimeMillis());
		
	}
	
	//一个Duration对象表示两个Instant间的一段时间
	/*
	  	plusNanos()
		plusMillis()
		plusSeconds()
		plusMinutes()
		plusHours()
		plusDays()
		minusNanos()
		minusMillis()
		minusSeconds()
		minusMinutes()
		minusHours()
		minusDays()
	 */
	public static void tryDuration() throws InterruptedException {
		Instant first = Instant.now();
		TimeUnit.SECONDS.sleep(2);
		Instant second = Instant.now();
		Duration duration = Duration.between(first, second);
		System.err.println(" == Duration == " + duration.getSeconds());
	}
	
	public static void tryPeriod () {
		LocalDate startDateInclusive = LocalDate.of(2018, Month.AUGUST, 15);
		LocalDate endDateExclusive = LocalDate.of(2018, Month.AUGUST, 17);
		Period p = Period.between(startDateInclusive, endDateExclusive);
		System.err.println(" == Period == " + p.getDays());
	}
	
	//Clock类提供了访问当前日期和时间的方法
	public static void tryClock () {
		Clock c = Clock.systemUTC();
		System.err.println(" ==> " + c.millis());
		Instant i = c.instant();
		System.err.println(" --> " + i.toEpochMilli());
		
		Clock c1 = Clock.systemDefaultZone();
		System.out.println(" ==> " + c1.millis());
		Instant i1 = c.instant();
		System.out.println(" --> " + i1.toEpochMilli());
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

	public static void main(String[] args) throws InterruptedException {
		// tryLocalDate();
		// tryLocalTime();
		// tryLocalDateTime();
		//tryInstant();
		//tryDuration();
		//tryPeriod();
		//tryDateTimeUtils();
		//tryParseDateTime();
		tryClock();
	}
}
