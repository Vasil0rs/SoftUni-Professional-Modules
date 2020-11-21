package BlackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);

        BlackBoxInt boxInt;
        Class<BlackBoxInt> aClass = BlackBoxInt.class;
        try {
            Constructor<BlackBoxInt> constructor = aClass.getDeclaredConstructor(int.class);
            constructor.setAccessible(true);
            boxInt = constructor.newInstance(0);
        } catch (NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }

        String input = scanner.nextLine();
        Field field = aClass.getDeclaredField("innerValue");

        while (!"END".equals(input)) {

            String[] tokens = input.split("_");
            String methodName = tokens[0];
            int param = Integer.parseInt(tokens[1]);

            try {
                Method method = aClass.getDeclaredMethod(methodName, int.class);
                method.setAccessible(true);
                method.invoke(boxInt,param);

               field.setAccessible(true);
                System.out.println(field.get(boxInt));

            } catch (NoSuchMethodException e) {
                throw new IllegalStateException(e);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }


            input = scanner.nextLine();
        }
    }
}
