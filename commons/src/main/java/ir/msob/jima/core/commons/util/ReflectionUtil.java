package ir.msob.jima.core.commons.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ReflectionUtil {


    public static Collection<Field> getFields(Class<?> clazz, Class<?> parentClass) {
        List<Field> fields = new ArrayList<>();
        if (clazz != null) {
            while (clazz != null && clazz != parentClass) {
                Field[] classFields = clazz.getDeclaredFields();
                if (classFields.length > 0)
                    fields.addAll(Arrays.asList(classFields));
                clazz = clazz.getSuperclass();
            }
        }
        return fields;
    }

    public static Collection<Field> getFields(Class<?> clazz) {
        return getFields(clazz, Object.class);
    }

    public static Method getGetter(Class<?> clazz, Field field) {
        // Construct the getter method name based on the field name
        String getterName = "get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);

        // Retrieve the getter method from the DTO class
        Method getterMethod;
        try {
            getterMethod = clazz.getMethod(getterName);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Getter method not found: " + getterName, e);
        }
        return getterMethod;
    }

    public static Method getSetter(Class<?> clazz, Field field) {
        // Construct the getter method name based on the field name
        String setterName = "set" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);

        // Retrieve the getter method from the DTO class
        Method getterMethod;
        try {
            getterMethod = clazz.getMethod(setterName);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Setter method not found: " + setterName, e);
        }
        return getterMethod;
    }
}
