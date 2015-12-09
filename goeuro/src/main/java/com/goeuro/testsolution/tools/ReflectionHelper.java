package com.goeuro.testsolution.tools;

import com.goeuro.testsolution.exceptions.TestException;

import java.lang.reflect.Field;

/**
 * @author v.chibrikov
 */
public class ReflectionHelper {
    public static Object getValue(Object object, String fieldName) {
        if (fieldName.contains(".")) {
            final int dotIndex = fieldName.indexOf(".");
            final String parentName = fieldName.substring(0, dotIndex);
            final Object parent = getObject(object, parentName);
            String childName = fieldName.substring(dotIndex + ".".length());
            return getObject(parent, childName);
        } else {
            return getObject(object, fieldName);
        }
    }

    private static Object getObject(Object object, String fieldName) {
        Field field = null;
        try {
            field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(object);
            return value;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new TestException("Can't get field: '" + fieldName + "' from: " + object + " " + e.getMessage());
        } finally {
            if (field != null)
                field.setAccessible(false);
        }
    }
}
