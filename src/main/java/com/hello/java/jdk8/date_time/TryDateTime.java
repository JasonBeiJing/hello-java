package com.hello.java.jdk8.date_time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class TryDateTime {

	public static void tryLocalDate() {
		System.err.println("LocalDate.now() ====> " + LocalDate.now());
		System.err.println("LocalDate.now(ZoneId.of(ZoneId.SHORT_IDS.get(\"CTT\"))) ====> " + LocalDate.now(ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))));
		
		System.err.println("LocalDate.of(2012, Month.JULY, 12) ====> " + LocalDate.of(2012, Month.JULY, 12));
		
		//��1970-01-01��ʼ������
		System.err.println("LocalDate.ofEpochDay(15) ====> " + LocalDate.ofEpochDay(15));
		//�Ӹ�������ݿ�ʼ������
		System.err.println("LocalDate.ofYearDay(2018, 10) ====> " + LocalDate.ofYearDay(2018, 10));
	}
	
	public static void tryLocalTime() {
		System.err.println("LocalTime.now() ====> " + LocalTime.now());
		System.err.println("LocalTime.now(ZoneId.of(ZoneId.SHORT_IDS.get(\"CTT\"))) ====> " + LocalTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))));
	
		System.err.println("LocalTime.of(5, 10, 20, 30) ====> " + LocalTime.of(5, 10, 20, 30));
		
		//��00:00:00 ��ʼ������
		System.err.println("LocalTime.ofNanoOfDay(10) ====> " + LocalTime.ofNanoOfDay(10));
		//��00:00:00 ��ʼ����
		System.err.println("LocalTime.ofSecondOfDay(10) ====> " + LocalTime.ofSecondOfDay(10));
	}
	
	public static void tryLocalDateTime() {
		System.err.println("LocalDateTime.now() ====> " + LocalDateTime.now());
		System.err.println("LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get(\"CTT\"))) ====> " + LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))));
		
		System.err.println("LocalDateTime.of(2012, Month.MARCH, 16, 17, 18, 19, 20) ====> " + LocalDateTime.of(2012, Month.MARCH, 16, 17, 18, 19, 20));
		System.err.println("LocalDateTime.of(LocalDate.now(), LocalTime.now()) ====> " + LocalDateTime.of(LocalDate.now(), LocalTime.now()));
	
		//��1970-01-01 00:00:00 0000Ϊ�����������ƶ�����õ�һ����־
		System.err.println("LocalDateTime.ofEpochSecond(3600, 20, ZoneOffset.UTC) ====> " + LocalDateTime.ofEpochSecond(3600, 20, ZoneOffset.UTC));
	
	}
	
	public static void tryInstant() {
		
	}
	
	public static void tryDateTimeUtils() {
		
	}
	
	public static void tryParseDateTime() {
		
	}
	
	public static void main(String[] args) {
		//tryLocalDate();
		//tryLocalTime();
		tryLocalDateTime();
		tryParseDateTime();
		tryParseDateTime();
	}
}
