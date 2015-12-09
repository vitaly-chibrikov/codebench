package generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v.chibrikov on 07.01.2015.
 */
public class TypeSafeArray<T> {
    private final Object[] objArray;

    public TypeSafeArray(int size) {
        this.objArray = new Object[size];
    }

    public void set(int index, T value) {
        objArray[index] = value;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) objArray[index];
    }

    public static void main(String[] args) {
        TypeSafeArray<List<Integer>> listIntArray = new TypeSafeArray<>(1);
        listIntArray.set(0, new ArrayList<>());
        List<Integer> list = listIntArray.get(0);

        //listIntArray.set(0, new ArrayList<String>());
        //List<String> listS = listIntArray.get(0);

    }
}
