package com.hello.java.test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Test {
	
	private static List<Field> findFileds(Object obj){
		return Arrays.asList(obj.getClass().getDeclaredFields());
	}
	
	private static Object findValue(Object obj, String name) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Field f = obj.getClass().getDeclaredField(name);
		f.setAccessible(true);
		return f.get(obj);
	}
	
	private static OrderLine getOrderLine(List<OrderLine> lines) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException {
		OrderLine out = new OrderLine();
		
		List<Field> fs = findFileds(out);
		Map<String, Set<Object>> values = new HashMap<>(fs.size());
		for (OrderLine line : lines) {			
			for (Field f : fs) {
				Set<Object> vs = values.get(f.getName());
				if (Objects.isNull(vs)) {
					vs = new HashSet<>();
					values.put(f.getName(), vs);
				}
				vs.add(findValue(line, f.getName()));
			}
		}
		for (Field f : fs) {
			Object finalValue = null;
			Set<Object> fieldValues = values.get(f.getName());
			if (f.getType().equals(String.class)) {				
				finalValue = fieldValues.toString();
			} else if(Number.class.isAssignableFrom(f.getType())) {
				BigDecimal num = BigDecimal.ZERO;
				for(Object fieldValue:fieldValues) {
					BigDecimal bd = new BigDecimal(fieldValue.toString());
					num = num.add(bd);
				}
				finalValue = num;
			}
			f.setAccessible(true);
			f.set(out, finalValue);
		}
		
		return out;
	}

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException, InstantiationException {
		List<OrderLine> lines = new ArrayList<>();
		OrderLine l1 = new OrderLine();
		l1.setPoLineId(1);
		l1.setDesc("x");
		l1.setUnitPrice(new BigDecimal("3.3"));
		l1.setQuantity(new BigDecimal("2"));
		l1.setAmount(new BigDecimal("6.6"));
		l1.setId(1L);
		OrderLine l2 = new OrderLine();
		l2.setPoLineId(1);
		l2.setDesc("y");
		l2.setUnitPrice(new BigDecimal("3.3"));
		l2.setQuantity(new BigDecimal("3"));
		l2.setAmount(new BigDecimal("9.9"));
		l2.setId(2L);
		OrderLine l3 = new OrderLine();
		l3.setPoLineId(2);
		l3.setDesc("z");
		l3.setUnitPrice(new BigDecimal("4.4"));
		l3.setQuantity(new BigDecimal("2"));
		l3.setAmount(new BigDecimal("8.8"));
		l3.setId(3L);
		lines.add(l1);
		lines.add(l2);
		lines.add(l3);
		
		OrderLine re = getOrderLine(lines);
		System.err.println(re.toString());
	}
}
