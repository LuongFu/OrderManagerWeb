package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Utils {

    public static String getString(String command, Scanner input) {
        System.out.print(command + ": ");
        return input.nextLine().trim();
    }

    public static int getInt(String command, Scanner input) {
        int i = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(command + ": ");
                i = Integer.parseInt(input.nextLine().trim());
                validInput = true;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input [require: integer type]");
            }
        }
        return i;
    }

    public static double getDouble(String command, Scanner input) {
        double i = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(command + ": ");
                i = Double.parseDouble(input.nextLine().trim());
                validInput = true;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input [require: double type]");
            }
        }
        return i;
    }

    public static boolean getBoolean(String command, Scanner input) {
        System.out.print(command + ": ");
        String i = input.nextLine().trim();
        if (i.equalsIgnoreCase("true")) {
            return true;
        } else if (i.equalsIgnoreCase("false")) {
            return false;
        } else {
            System.err.println("Invalid input [require: boolean (true/false)]");
            return getBoolean(command, input); // Recursive call for retry
        }
    }

    public static boolean getBoolean_YN(String command, Scanner input) {
        System.out.print(command + ": ");
        String i = input.nextLine().trim();
        if (i.equalsIgnoreCase("Y")) {
            return true;
        } else if (i.equalsIgnoreCase("N")) {
            return false;
        } else {
            System.err.println("Invalid input [require: Y/N]");
            return getBoolean_YN(command, input); // Recursive call for retry
        }
    }

    // Get date with proper exception handling
    public static Date getDate(String command, Scanner input) {
        String returnValue = getString(command, input);
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(returnValue);
        } catch (ParseException e) {
            System.err.println("Invalid input - [require: dd/MM/yyyy]");
            return getDate(command, input); // Recursive call for retry
        }
    }

    // Normalize name with proper handling for empty strings
    public static String normalizeChar(String name) {
        // Remove non-alphabet characters, multiple spaces, and convert to lowercase
        String pName = name.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ").toLowerCase().trim();

        if (pName.isEmpty()) {
            return ""; // Return empty string if nothing valid is left
        }

        // Split and capitalize each word
        String[] parts = pName.split(" ");
        StringBuilder normalized = new StringBuilder();

        for (String part : parts) {
            if (!part.isEmpty()) {
                // Capitalize the first character and concatenate the rest
                normalized.append(Character.toUpperCase(part.charAt(0)))
                        .append(part.substring(1))
                        .append(" ");
            }
        }

        return normalized.toString().trim();
    }

    public boolean getEmail(String email) {
        String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

}
