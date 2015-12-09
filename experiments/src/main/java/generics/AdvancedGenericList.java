package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by v.chibrikov on 03.01.2015.
 */
public class AdvancedGenericList<E> {
    private List<E> eList;
    private Class<E> clazz;

    public AdvancedGenericList(List<E> eList, Class<E> clazz) {
        this.eList = eList;
        this.clazz = clazz;
    }

    public E get(int i) {
        return eList.get(i);
    }

    public void add(E e){
        eList.add(e);
    }

    static public void main(String[] args) {
        AdvancedGenericList<Integer> iTyped = new AdvancedGenericList<>(new ArrayList<>(), Integer.class);
        System.out.print(getGenericClassName(iTyped) + '\n'); //java.lang.Integer

        AdvancedGenericList<String> sTyped = new AdvancedGenericList<>(new ArrayList<>(), String.class);
        System.out.print(getGenericClassName(sTyped) + '\n'); //java.lang.String
    }

    static String getGenericClassName(AdvancedGenericList<?> advancedGenericList) {
        return advancedGenericList.clazz.getCanonicalName();
    }
}
