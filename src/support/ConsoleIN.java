package src.support;

import java.util.Scanner;
import java.util.stream.Stream;

public class ConsoleIN {
    
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString() {
        return readInput();
    }

    public static Integer readInt() {
        try {
            return Integer.parseInt(readAnyInput());
        } catch (NumberFormatException e) {
            return null;
        }
    }


    // tries to get input 3 times
    private static String readInput() {
        for (int i = 0; i < 2; i++) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (!input.isBlank()) return input;
        }
        System.out.print("> ");
        return scanner.nextLine();
    }

    public static String readAnyInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    public static String readInputOptions(String[] options) {
        String result = "";
        while(!Stream.of(options).toList().contains(result)) {
            result = readAnyInput();
        }
        return result;
    }
}
