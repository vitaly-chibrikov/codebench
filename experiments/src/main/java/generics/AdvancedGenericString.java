package generics;

import java.lang.reflect.ParameterizedType;

/**
 * Created by v.chibrikov on 03.01.2015.
 */
public class AdvancedGenericString extends AdvancedGeneric<String> {
    public AdvancedGenericString(String eField) {
        super(eField);
    }

    static public void main(String[] args) {
        AdvancedGeneric<?> sTyped = new AdvancedGenericString("test");
        System.out.print(getGenericClassName(sTyped) + '\n'); //java.lang.String
    }

    static String getGenericClassName(AdvancedGeneric<?> advancedGeneric) {
        Class<?> clazz = (Class<?>) ((ParameterizedType) advancedGeneric.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz.getCanonicalName();
    }
}
