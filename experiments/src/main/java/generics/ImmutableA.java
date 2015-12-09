package generics;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by v.chibrikov on 04.01.2015.
 */
public final class ImmutableA<T> {
    private ArrayList<T> myList;
    private Class<T> clazz;

    public static <E> ImmutableA<E> createEmpty(Class<E> clazz) {
        return new ImmutableA<>(clazz);
    }

    private ImmutableA(Class<T> clazz) {
        this.myList = new ArrayList<T>();
        this.clazz = clazz;
    }

    public ImmutableA<T> addData(T t) {
        ImmutableA<T> newOb = new ImmutableA<>(clazz);
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(clazz);
            T newT = constructor.newInstance(t);
            newOb.myList.add(newT);
            return newOb;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<T> getMyList() {
        return myList;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        ImmutableA<String> immutableA = createEmpty(String.class);
        System.out.print(immutableA.getMyList().toString());
        immutableA = immutableA.addData("a");
        System.out.print(immutableA.getMyList().toString());
        immutableA = immutableA.addData("b");
        System.out.print(immutableA.getMyList().toString());
        immutableA = immutableA.addData("c");
        System.out.print(immutableA.getMyList().toString());
    }
}
