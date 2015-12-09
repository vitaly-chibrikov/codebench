package generics;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by v.chibrikov on 03.01.2015.
 */
public class AdvancedGeneric<E> {
    private E eField;
    private int a = 0;
    private List<String> sList = new ArrayList<>();

    public AdvancedGeneric(E eField) {
        this.eField = eField;
    }

    public E geteField() {
        return eField;
    }

    static public void main(String[] args) {
        AdvancedGeneric<Integer> iTyped = new AdvancedGeneric<>(1);
        System.out.print(getGenericClassName(iTyped) + '\n'); //java.lang.Integer

        AdvancedGeneric<String> sTyped = new AdvancedGeneric<>("hello");
        System.out.print(getGenericClassName(sTyped) + '\n'); //java.lang.String
    }

    static String getGenericClassName(AdvancedGeneric<?> advancedGeneric) {
         //return advancedGeneric.geteField().getClass().getCanonicalName();
        Field[] fields = advancedGeneric.getClass().getDeclaredFields();
        fields[0].setAccessible(true);
        try {
            Object fieldObject = fields[0].get(advancedGeneric);
            return fieldObject.getClass().getCanonicalName();
        } catch (IllegalAccessException e) {
            return e.getMessage();
        }
    }
}
