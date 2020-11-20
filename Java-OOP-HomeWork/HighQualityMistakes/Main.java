package HighQualityMistakes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {
    public static class MethodComparator implements Comparator<Method> {

        @Override
        public int compare(Method o1, Method o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public static void main(String[] args) {

        Class<?> aClass = Reflection.class;

        Set<Field> fields = new TreeSet<>(Comparator.comparing(Field::getName));
        Set<Method> getters = new TreeSet<>(new MethodComparator());
        Set<Method> setters = new TreeSet<>(new MethodComparator());

        fields.addAll((Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .collect(Collectors.toList())));

        System.out.println(fields
                .stream()
                .map(f -> f.getName() + " must be private!")
                .collect(Collectors.joining(System.lineSeparator())));

        Method[] declaredMethods = aClass.getDeclaredMethods();

        for (Method method : declaredMethods) {
            if (method.getName().startsWith("get")
                    && !Modifier.isPublic(method.getModifiers())) {
                getters.add(method);
            } else if (method.getName().startsWith("set")
                    && !Modifier.isPrivate(method.getModifiers())) {
                setters.add(method);

            }
        }

        System.out.println(
                getters
                .stream()
                .map(g -> g.getName() + " have to be public!")
                .collect(Collectors.joining(System.lineSeparator())));

        System.out.println(
                setters
                .stream()
                .map(s -> s.getName() + " have to be private!")
                .collect(Collectors.joining(System.lineSeparator())));

    }
}
