package Reflection;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<Reflection> clazz = Reflection.class;
        System.out.println(clazz);
        System.out.println(clazz.getSuperclass());
        Class<?>[] interfaces = clazz.getInterfaces();

        for (Class<?> aClass : interfaces) {
            System.out.println(aClass);
        }

        Reflection instance = clazz.getDeclaredConstructor().newInstance();

        System.out.println(instance.toString());

    }
}
