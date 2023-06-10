package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputReader {

    private static final Scanner scanner = new Scanner(System.in);

    public static String getString(String message) {
        System.out.print(message);
        final String result = scanner.nextLine().trim();
        if(result.isEmpty()) {
            System.out.println("Please enter a text.");
            return getString(message);
        }
        return result;
    }

    public static int getInt(String message) {
        System.out.print(message);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer.");
            return getInt(message);
        }
    }

    public static LocalDate getDate(String message) {
        System.out.print(message);
        final String result = scanner.nextLine().trim();
        if (result.isEmpty()) {
            System.out.println("Please enter a date.");
            return getDate(message);
        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(result, formatter);
        }
    }

}
