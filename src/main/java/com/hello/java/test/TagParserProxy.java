package com.huawei.it.acc.sign.template.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.jws.Oneway;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class TagParserProxy {

	public static <T> String getTagValues(T apiResponse,String tagResponseKey){
		return getTagValues(Arrays.asList(apiResponse), tagResponseKey);
	}

	public static <T> String getTagValues(Collection<T> apiResponse,String tagResponseKey){
		Set<Object> results = new HashSet<>();
		//A.B.C
		String[] keys = StringUtils.split(tagResponseKey, ".");
		for(T re:apiResponse){
			Object result = re;
			Field field = null;
			String lastKey = null;
			for (String key:keys){
				field = findField(result, key);
				Objects.requireNonNull(field, "Field not found with signature: " + key);
				field.setAccessible(true);
				lastKey = key;
				try {
					result = field.get(result);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					//整个tag解析结束
				}
			}
			if(Objects.nonNull(result)){
				if(JavaTypeUtil.isJavaPrimitiveType(field)){
					results.add(result);
				}else if(JavaTypeUtil.isSerializableJavaCollection(field, result)){
					results.addAll((Collection)result);
				}else if(JavaTypeUtil.isSerializableJavaMap(field, result)){
					Map<Object, Object> map = (Map)result;
					Object value = map.get(lastKey);
					results.add(value);
				}
			}
		}
		return StringUtils.join(results, ",");
	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//System.err.println(JSONObject.toJSONString(getContracts()));

		//findContractByNumbers(null);

		//name
		//address.country
		//sources
		//maps{B}
		//lines[number]
		//mapLines{HAHA}.number
//		String key = "maps{B}";
//		System.err.println("===> " + JSONObject.toJSONString(getTagValues(getContracts(), key)));

		System.err.println(getRealSignature("mapLines{HAHA}[number]"));
	}

	private static Field findField(Object object, String signature){
		if(Objects.nonNull(object)){
			Field[] fields = object.getClass().getDeclaredFields();
			if(ArrayUtils.isNotEmpty(fields)){
				String realSignature = getRealSignature(signature);
				for(Field field:fields){
					if(field.getName().equals(realSignature)){
						return field;
					}
				}
			}
		}
		return null;
	}

	private static String getRealSignature(String signature){
		if(StringUtils.isNotBlank(signature)){
			int leftBrace = signature.indexOf("{");
			int rightBrace = signature.indexOf("}");
			int leftBracket = signature.indexOf("[");
			int rightBracket = signature.indexOf("]");
			int lastLeftBrace = signature.lastIndexOf("{");
			int lastRightBrace = signature.lastIndexOf("}");
			int lastLeftBracket = signature.lastIndexOf("[");
			int latRightBracket = signature.lastIndexOf("]");
			if(leftBrace == lastLeftBrace && rightBrace == lastRightBrace && leftBracket == lastLeftBracket && rightBracket == latRightBracket){
				if(leftBrace == -1 && rightBrace == -1 && leftBracket == -1 && rightBracket == -1){
					return signature;
				}else if(leftBrace > 0 && rightBrace > 2){
					if(leftBracket > 0 && leftBracket - rightBrace != 1){
						return null;
					}
					return signature.substring(0, leftBrace);
				}else if(leftBracket > 0 && rightBracket > 2){
					return signature.substring(0, leftBracket);
				}
			}
		}
		return null;
	}


	public static Map<String, String> findContractByNumbers(List<String> numbers) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		List<Contract> contracts = getContracts();
		System.err.println(JSONObject.toJSONString(contracts));
		Map<String, Set<String>> flattenedValue = new HashMap<>();
		for(Contract contract:contracts){
			Class<? extends Contract> clazz = contract.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for(Field field:fields){
				field.setAccessible(true);
				Object value = field.get(contract);
				if(Objects.nonNull(value)){
					if(JavaTypeUtil.isJavaPrimitiveType(field)){
						String key = field.getName();
						Set<String> existedValue = flattenedValue.get(key);
						if(existedValue == null){
							existedValue = new LinkedHashSet<>();
							flattenedValue.put(key, existedValue);
						}
						existedValue.add(value.toString());
					}else if(JavaTypeUtil.isSerializableJavaCollection(field, value)){
						String key = field.getName();
						Set<String> existedValue = flattenedValue.get(key);
						if(existedValue == null){
							existedValue = new LinkedHashSet<>();
							flattenedValue.put(key, existedValue);
						}
						existedValue.add(StringUtils.join((Collection)value, ","));
					}else if (JavaTypeUtil.isSerializableJavaMap(field, value)){
						String key = field.getName();
						Map<String, Object> maps = (Map)value;
						for(Map.Entry<String, Object> map:maps.entrySet()){
							StringBuilder mapKey = new StringBuilder(key);
							mapKey.append(".");
							mapKey.append(map.getKey());
							Set<String> existedValue = flattenedValue.get(mapKey.toString());
							if(existedValue == null){
								existedValue = new LinkedHashSet<>();
								flattenedValue.put(mapKey.toString(), existedValue);
							}
							existedValue.add(map.getValue().toString());
						}
					}else if(value instanceof Address){
						String key = field.getName();
						Field[] inFields = value.getClass().getDeclaredFields();
						for(Field inField:inFields){
							inField.setAccessible(true);
							Object newValue = inField.get(value);
							StringBuilder mapKey = new StringBuilder(key);
							mapKey.append(".");
							mapKey.append(inField.getName());
							if(JavaTypeUtil.isJavaPrimitiveType(inField)){
								Set<String> ss = flattenedValue.get(mapKey.toString());
								if(ss == null){
									ss = new LinkedHashSet<>();
									flattenedValue.put(mapKey.toString(), ss);
								}
								ss.add(newValue.toString());
							}
						}
					}else if(JavaTypeUtil.isJavaCollection(value)){
						Collection<Object> cs =	(Collection)value;
						for(Object c:cs){
							String key = field.getName();
							Field[] inFields = c.getClass().getDeclaredFields();
							for(Field inField:inFields){
								inField.setAccessible(true);
								Object newValue = inField.get(c);
								StringBuilder mapKey = new StringBuilder(key);
								mapKey.append(".");
								mapKey.append(inField.getName());
								if(JavaTypeUtil.isJavaPrimitiveType(inField)){
									Set<String> ss = flattenedValue.get(mapKey.toString());
									if(ss == null){
										ss = new LinkedHashSet<>();
										flattenedValue.put(mapKey.toString(), ss);
									}
									ss.add(newValue.toString());
								}
							}
						}
					}else if(JavaTypeUtil.isJavaMap(value)){
						String key = field.getName();
						Map<String, Object> maps = (Map)value;
						for(Map.Entry<String, Object> map:maps.entrySet()){
							StringBuilder mapKey = new StringBuilder(key);
							mapKey.append(".");
							mapKey.append(map.getKey());
							Set<String> existedValue = flattenedValue.get(mapKey.toString());
							if(existedValue == null){
								existedValue = new LinkedHashSet<>();
								flattenedValue.put(mapKey.toString(), existedValue);
							}

							Object value2 = map.getValue();

							Field[] inFields = value2.getClass().getDeclaredFields();
							for(Field inField:inFields){
								inField.setAccessible(true);
								Object newValue = inField.get(value2);
								StringBuilder mapKey2 = new StringBuilder(mapKey);
								mapKey2.append(".");
								mapKey2.append(inField.getName());
								if(JavaTypeUtil.isJavaPrimitiveType(inField)){
									Set<String> ss = flattenedValue.get(mapKey2.toString());
									if(ss == null){
										ss = new LinkedHashSet<>();
										flattenedValue.put(mapKey2.toString(), ss);
									}
									ss.add(newValue.toString());
								}
							}

						}
					}
				}
			}
		}
		Map<String, String> out = new HashMap<>(flattenedValue.size());
		for(Map.Entry<String, Set<String>> entry : flattenedValue.entrySet()){
			Set<String> values = entry.getValue();
			if(!values.isEmpty()){
				out.put(entry.getKey(), StringUtils.join(values, ","));
			}
		}


		System.err.println(JSONObject.toJSONString(out));
		return out;
	}

	private static List<Contract> getContracts() {
		List<Contract> contracts = new ArrayList<>();
		Contract e = new Contract();
		e.setName("X");
		e.setSources(Arrays.asList("a", "b"));
		e.setLines(Arrays.asList(new Line(1), new Line(2)));
		e.setAddress(new Address("china", "shandong"));
		Map<String, Line> mapLines1 = new HashMap<>();
		mapLines1.put("HAHA", new Line(5));
		mapLines1.put("HEIHEI", new Line(6));
		e.setMapLines(mapLines1);
		Map<String, String> maps = new HashMap<>();
		maps.put("A", "111");
		maps.put("B", "222");
		e.setMaps(maps);
		contracts.add(e);
		Contract s = new Contract();
		s.setName("Y");
		s.setSources(Arrays.asList("c", "d"));
		s.setLines(Arrays.asList(new Line(3), new Line(4)));
		s.setAddress(new Address("德国", "巴伐利亚"));
		Map<String, Line> mapLines2 = new HashMap<>();
		mapLines2.put("HAHA", new Line(6));
		mapLines2.put("HEHE", new Line(7));
		s.setMapLines(mapLines2);
		Map<String, String> maps2 = new HashMap<>();
		maps2.put("A", "333");
		maps2.put("B", "444");
		s.setMaps(maps2);
		contracts.add(s);
		return contracts;
	}

	static class Contract implements Serializable{
		private String id = UUID.randomUUID().toString();
		private String name;
		private List<String> sources;
		private  Map<String, String> maps;
		private Address address;
		private List<Line> lines;
		private Map<String, Line> mapLines;

		public Map<String, String> getMaps() {
			return maps;
		}

		public void setMaps(Map<String, String> maps) {
			this.maps = maps;
		}

		public Map<String, Line> getMapLines() {
			return mapLines;
		}

		public void setMapLines(Map<String, Line> mapLines) {
			this.mapLines = mapLines;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<String> getSources() {
			return sources;
		}

		public void setSources(List<String> sources) {
			this.sources = sources;
		}

		public List<Line> getLines() {
			return lines;
		}

		public void setLines(List<Line> lines) {
			this.lines = lines;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}

	static class Line implements Serializable{
		private String id = UUID.randomUUID().toString();;
		private Integer number;

		public Line() {
		}

		public Line(Integer number) {
			this.number = number;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Integer getNumber() {
			return number;
		}

		public void setNumber(Integer number) {
			this.number = number;
		}
	}

	static class Address implements Serializable{
		private String country;
		private String state;

		public Address() {
		}

		public Address(String country, String state) {
			this.country = country;
			this.state = state;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}
	}
}
