package HarvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Class<RichSoilLand> aClass = RichSoilLand.class;

        Scanner scanner = new Scanner(System.in);

        List<Field> fields = Arrays.asList(aClass.getDeclaredFields());
        String input = scanner.nextLine();

        while (!"HARVEST".equals(input)) {

            String modifier = input;
            fields.stream()
                    .filter(f -> Modifier.toString(f.getModifiers()).equals(modifier)
                    ||modifier.equals("all"))
                    .forEach(f-> System.out.printf("%s %s %s%n"
                            ,Modifier.toString(f.getModifiers()),f.getType().getSimpleName()
                    ,f.getName()));


            input = scanner.nextLine();
        }
    }
}
