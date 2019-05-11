package com.hello.java.test;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

public class TagParserUtil {


	/**
	 *
	 * @param response API response value
	 * @param responseSignature the tag response key defined in TAG module
	 * @param <T> a basic Java pojo, or collection
	 * @return
	 */
	public static <T> String getTagValue(T response, String responseSignature){
		if(response instanceof Collection){
			return getTagValue((Collection)response, responseSignature);
		}else{
			return getTagValue(Arrays.asList(response), responseSignature);
		}
	}

	/**
	 *
	 * @param response API response value
	 * @param responseSignature the tag response key defined in TAG module
	 * @param <T> cannot be collection or map, just a basic Java POJO
	 * @return
	 */
	public static <T> String getTagValue(Collection<T> response, String responseSignature){
		Set<Object> results = new HashSet<>();
		String[] signatures = StringUtils.split(responseSignature, ".");
		try {
			for(T re:response){
				String lastKey = null;
				Field field = null;
				Object result = re;
				for (String key:signatures){
					lastKey = key;
					field = findField(result, lastKey);
					Objects.requireNonNull(field, "Field not found with signature: " + lastKey);
					field.setAccessible(true);
					result = field.get(result);
				}
				if(Objects.nonNull(result)){
					fillUpResults(results, result, field, lastKey);
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return StringUtils.join(results, ",");
	}

	private static void fillUpResults(Set<Object> results, Object result, Field field, String lastKey){
		if(JavaTypeUtil.isRecognizableType(field)){
			results.add(result);
		}else if(JavaTypeUtil.isCollection(result)){
			Collection<Object> collection = (Collection)result;
			if(JavaTypeUtil.isRecognizableCollection(field, result)){
				results.addAll(collection);
			}else{
				String subKey = lastKey.substring(lastKey.indexOf("[") + 1, lastKey.indexOf("]"));
				results.add(getTagValue(collection, subKey));
			}
		}else if(JavaTypeUtil.isMap(result)){
			Map<Object, Object> map = (Map)result;
			Object value = map.get(lastKey.substring(lastKey.indexOf("{") + 1, lastKey.indexOf("}")));
			if(JavaTypeUtil.isRecognizableMap(field, result)){
				results.add(value);
			}else{
				results.add(getTagValue(value, lastKey.substring(lastKey.indexOf("[") + 1, lastKey.indexOf("]"))));
			}
		}
	}

	private static Field findField(Object object, String signature){
		if(Objects.nonNull(object)){
			Field[] fields = object.getClass().getDeclaredFields();
			if(ArrayUtils.isNotEmpty(fields)){
				String realSignature = getRealSignature(signature.replaceAll("\\s*", ""));
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
}
