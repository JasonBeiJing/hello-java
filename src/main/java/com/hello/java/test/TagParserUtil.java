package com.hello.java.test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class TagParserUtil {


	public static <T> String getTagValue(T apiResponse,String tagResponseKey){
		if(apiResponse instanceof Collection){
			return getTagValues((Collection)apiResponse, tagResponseKey);
		}else{
			return getTagValues(Arrays.asList(apiResponse), tagResponseKey);
		}
	}

	public static <T> String getTagValues(Collection<T> apiResponse,String tagResponseKey){
		Set<Object> results = new HashSet<>();
		//A.B.C
		String[] keys = StringUtils.split(tagResponseKey, ".");
		try {
			for(T re:apiResponse){
				Object result = re;
				Field field = null;
				String lastKey = null;
				for (String key:keys){
					field = findField(result, key);
					Objects.requireNonNull(field, "Field not found with signature: " + key);
					field.setAccessible(true);
					lastKey = key;
					result = field.get(result);
				}
				if(Objects.nonNull(result)){
					if(JavaTypeUtil.isJavaPrimitiveType(field)){
						results.add(result);
					}else if(JavaTypeUtil.isJavaCollection(result)){
						Collection<Object> collection = (Collection)result;
						if(JavaTypeUtil.isSerializableJavaCollection(field, result)){
							results.addAll(collection);
						}else{
							String subKey = lastKey.substring(lastKey.indexOf("[") + 1, lastKey.indexOf("]"));
							results.add(getTagValues(collection, subKey));
						}
					}else if(JavaTypeUtil.isJavaMap(result)){
						Map<Object, Object> map = (Map)result;
						Object value = map.get(lastKey.substring(lastKey.indexOf("{") + 1, lastKey.indexOf("}")));
						if(JavaTypeUtil.isSerializableJavaMap(field, result)){
							results.add(value);
						}else{
							results.add(getTagValue(value, lastKey.substring(lastKey.indexOf("[") + 1, lastKey.indexOf("]"))));
						}
					}


				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return StringUtils.join(results, ",");
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
