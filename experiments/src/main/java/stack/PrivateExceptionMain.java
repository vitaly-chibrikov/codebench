package stack;

import java.lang.reflect.Constructor;

public class PrivateExceptionMain {
    public static void main(String[] args) {
        try {
            runTest();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void runTest() throws Exception {
        try {
            Class<?> clazz = Class.forName("stack.PrivateExceptionMain.OuterClass.PrivateException");
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Exception e = (Exception) constructor.newInstance("Hello!");
            throw e;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public class OuterClass {
        private class PrivateException extends Exception {
            private PrivateException(String s) {
                super(s);
            }
        }
    }
}
