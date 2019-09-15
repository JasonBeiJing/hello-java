package com.hello.java.jdk8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.hello.java.jdk8.lambda.util.Employee;

public class Lambda4FunctionalInterfaces {

	//java.util.function.Consumer t -> void ：消费型 -> java.util.function.BiConsumer
	//java.util.function.Supplier () -> t ：供应型
	//java.util.function.Predicate  t -> boolean ：断言型 -> java.util.function.BiPredicate
	//java.util.function.Function t -> r ：功能转换型 -> java.util.function.BiFunction
	
	@Test
	public void testConsumer() {
		Employee employee = new Employee("x", 18);
		consumeEmployee(employee, t -> System.out.println(t.getName() + "_" + t.getAge()));
		consumeEmployee2(employee);
	}
	
	private void consumeEmployee(Employee employee, Consumer<Employee> consumer) {
		consumer.accept(employee);
	}
	
	private void consumeEmployee2(Employee employee) {
		System.out.println(employee.getName() + "_" + employee.getAge());
	}
	
	@Test
	public void testSupplier() {
		System.out.println(supplyIntegers(10, () -> (int)(Math.random() * 100)));
		System.out.println(supplyIntegers2(10));
	}
	
	private List<Integer> supplyIntegers(int size, Supplier<Integer> supplier) {
		List<Integer> re = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			re.add(supplier.get());
		}
		return re;
	}
	
	private List<Integer> supplyIntegers2(int size) {
		List<Integer> re = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			re.add((int)(Math.random() * 100));
		}
		return re;
	}
	
	@Test
	public void testFunction() {
		Employee employee = new Employee("x", 18);
		
		System.out.println(getEmployeeAttribute(employee, t -> t.getName()));
		System.out.println(getEmployeeAttribute(employee, t -> t.getAge()+""));
		
		System.err.println(getEmployeeName(employee));
		System.err.println(getEmployeeAge(employee));
		
	}
	
	private String getEmployeeAttribute(Employee employee, Function<Employee, String> function) {
		return function.apply(employee);
	}
	
	private String getEmployeeName(Employee employee) {
		return employee.getName();
	}
	
	private String getEmployeeAge(Employee employee) {
		return employee.getAge()+"";
	}
	
	@Test
	public void testPredicate() {
		Employee employee = new Employee("x", 18);
		System.out.println(predicateEmployee(employee, t -> t.getAge() > 18));
		System.out.println(predicateEmployee(employee, t -> StringUtils.equals(t.getName(), "x")));
		
		System.err.println(predicateEmployee2(employee));
		System.err.println(predicateEmployee3(employee));
	}
	
	private boolean predicateEmployee(Employee employee, Predicate<Employee> predicate) {
		return predicate.test(employee);
	}
	
	private boolean predicateEmployee2(Employee employee) {
		return employee.getAge() > 18;
	}
	
	private boolean predicateEmployee3(Employee employee) {
		return StringUtils.equals(employee.getName(), "x");
	}
}
