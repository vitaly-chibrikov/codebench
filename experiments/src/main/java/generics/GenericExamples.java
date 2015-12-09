package generics;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by v.chibrikov on 03.01.2015.
 */
public class GenericExamples<E> {
    private E eField;

    public GenericExamples(E e) {
        this.eField = e;
    }

    public Class<?> getEClass(){
        return eField.getClass();
    }

    public static <T> T genericTypesRuntime(T t) throws IllegalAccessException {
        System.out.print(t.getClass().getCanonicalName());
        GenericExamples<Number> genericExamples = new GenericExamples<Number>(1);
        Method[] methods = genericExamples.getClass().getMethods();
        //ParameterizedType pType = (ParameterizedType) genericExamples.getClass();
        Field[] fields = genericExamples.getClass().getDeclaredFields();
        //Class<?> gType = genericExamples.getEClass();
        fields[0].setAccessible(true);
        Object eObject = fields[0].get(genericExamples);
        return t;
    }
}
