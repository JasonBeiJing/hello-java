package com.hello.java.jdk8.optional;

import java.io.Serializable;
import java.util.Optional;

/**
 * why optional ��
 * String isocode = user.getAddress().getCountry().getIsocode().toUpperCase();
 * if (user != null) {
	    Address address = user.getAddress();
	    if (address != null) {
	        Country country = address.getCountry();
	        if (country != null) {
	            String isocode = country.getIsocode();
	            if (isocode != null) {
	                isocode = isocode.toUpperCase();
	            }
	        }
	    }
	}
 */

public class TryOptional {

	public static void getUserName(User user) {
		//stupid way
//		Optional<User> x = Optional.ofNullable(u);
//	    if (!x.isPresent())
//	        return "Unknown";
//	    return x.get().name;
		
		String x = Optional.ofNullable(user).map(n -> n.getName()).orElse("Unknown");
		System.err.println("====>>>> " + x);
		String y = Optional.ofNullable(user).flatMap(n -> n.getPosition()).orElse("Unknown");
		System.err.println("====>>>> " + y);
	}
	
	public static void main(String[] args) throws Exception {
		//Optional.empty(); 
		//Optional.of(value) -> value����Ϊnull�������ָ�룬Ӧ����ȷ����Ϊnull��ʱ��ʹ�� of()
		//Optional.ofNullable(value); -> ��������� null Ҳ�����Ƿ� null,�����null���˻�Ϊ Optional.empty();
/************************************************************************************************************/
		System.err.println(Optional.ofNullable("x"));
		System.err.println(Optional.ofNullable(null));
		System.err.println(Optional.ofNullable("x").orElse("z"));
		System.err.println(Optional.ofNullable(null).orElse("z"));
/************************************************************************************************************/
		//��xΪnullʱ����������else��������
		User x = null;
		User y = Optional.ofNullable(x).orElse(new User("sss", 18));
		User z = Optional.ofNullable(x).orElseGet(() -> new User("ttt", 19));
		System.err.println(y.getName() + "===>" + z.getName());
		
		//��x��Ϊnullʱ��orElse������������ִ�У���orElseGet�򲻻�
		x = new User("niubi", 66);
		Optional.ofNullable(x).orElse(new User("hello"));
		Optional.ofNullable(x).orElseGet(() -> new User("hi"));
		
		//Optional.ofNullable(x).orElseThrow(() -> new Exception());
/************************************************************************************************************/
		//ת��ֵ
		getUserName(null);
/************************************************************************************************************/
		//����ֵ
		Optional<User> a = Optional.ofNullable(x).filter(n -> n.getName().equals("niubi"));
		Optional<User> b = Optional.ofNullable(x).filter(n -> n.getName().equals("&&&&&"));
		System.out.println(a.isPresent() + " < ===== >" +b.isPresent());
/************************************************************************************************************/
		//Optional �����ʽ����
		x.setAddress(Optional.ofNullable(new Address(Optional.ofNullable(new Country("DE")))));
		String code1 = Optional.ofNullable(x).flatMap(n -> n.getAddress()).flatMap(m -> m.getC()).map(l -> l.getCode()).orElse("ZH");
		
		y.setAddress(Optional.ofNullable(new Address(Optional.ofNullable(null))));
		String code2 = Optional.ofNullable(y).flatMap(n -> n.getAddress()).flatMap(m -> m.getC()).map(l -> l.getCode()).orElse("ZH");
		
		System.err.println(code1 + "------>" + code2);
	}
	
	static class User implements Serializable{
		private static final long serialVersionUID = -5505406831337567146L;
		private String name;
		private int age;
		
		private Optional<String> position;
		
		private Optional<Address> address;
		
		
		public User(String name) {
			super();
			this.name = name;
			System.out.println("---------> " + name);
		}
		public User(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public Optional<String> getPosition() {
			return position;
		}
		public void setPosition(Optional<String> position) {
			this.position = position;
		}
		public Optional<Address> getAddress() {
			return address;
		}
		public void setAddress(Optional<Address> address) {
			this.address = address;
		}
	}
	
	static class Address implements Serializable{
		private static final long serialVersionUID = -3731419726578784629L;
		private Optional<Country> c;
		
		
		public Address(Optional<Country> c) {
			super();
			this.c = c;
		}
		public Optional<Country> getC() {
			return c;
		}
		public void setC(Optional<Country> c) {
			this.c = c;
		}
		
	}
	
	static class Country implements Serializable{
		private static final long serialVersionUID = -3942874785393351284L;
		private String code;
		
		public Country(String code) {
			super();
			this.code = code;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
	}
}
