package com.hello.java.test;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

public class JavaTypeUtil {
    private static final Set<String> recognizableTypes = new HashSet<>(19);

    static {
        recognizableTypes.add(Byte.class.getCanonicalName());
        recognizableTypes.add(Short.class.getCanonicalName());
        recognizableTypes.add(Integer.class.getCanonicalName());
        recognizableTypes.add(Long.class.getCanonicalName());
        recognizableTypes.add(Float.class.getCanonicalName());
        recognizableTypes.add(Double.class.getCanonicalName());
        recognizableTypes.add(Character.class.getCanonicalName());
        recognizableTypes.add(Boolean.class.getCanonicalName());

        recognizableTypes.add(String.class.getCanonicalName());
        recognizableTypes.add(StringBuilder.class.getCanonicalName());
        recognizableTypes.add(StringBuffer.class.getCanonicalName());
        recognizableTypes.add(BigDecimal.class.getCanonicalName());
        recognizableTypes.add(BigInteger.class.getCanonicalName());

        recognizableTypes.add(Locale.class.getCanonicalName());
        recognizableTypes.add(Date.class.getCanonicalName());
        recognizableTypes.add(java.sql.Date.class.getCanonicalName());
        recognizableTypes.add(LocalDate.class.getCanonicalName());
        recognizableTypes.add(LocalDateTime.class.getCanonicalName());
        recognizableTypes.add(LocalTime.class.getCanonicalName());
    }


    public static boolean isJavaPrimitiveType(Field field) {
        Class<?> type = field.getType();
        if(type.isPrimitive() || recognizableTypes.contains(type.getCanonicalName()) || type.isEnum()) {
            return true;
        }
        return false;
    }

    public static boolean isJavaCollection(Object value){
        return Objects.nonNull(value) && (value instanceof  Collection);
    }

    public static boolean isSerializableJavaCollection(Field field, Object value){
        if(isJavaCollection(value)){
           Type[] types =  getActualTypeArguments(field);
           if(ArrayUtils.isNotEmpty(types)){
               return recognizableTypes.contains(types[0].getTypeName());
           }
        }
        return false;
    }

    public static boolean isJavaMap(Object value){
        return Objects.nonNull(value) && (value instanceof  Map);
    }

    public static boolean isSerializableJavaMap(Field field, Object value){
        if(isJavaMap(value)){
            Type[] types =  getActualTypeArguments(field);
            if(ArrayUtils.isNotEmpty(types)){
                return recognizableTypes.contains(types[1].getTypeName());
            }
        }
        return false;
    }

    private static Type[] getActualTypeArguments(Field field){
        Type type = field.getGenericType();
        if(type instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return parameterizedType.getActualTypeArguments();
        }
        return null;
    }
}