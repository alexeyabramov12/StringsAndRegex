package practice.regex;

import java.util.Scanner;

public class FullNameFormatterRegex {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                scanner.close();
                break;
            }
            String name = input.trim();
            String regex = "([ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz -]{2,}){3}";
            if (!name.matches(regex)) {
                System.out.println("The string entered is not a full name.");
                break;
            }
            String[] array = name.split("\\s");
            System.out.println("Surname: " + array[0]);
            System.out.println("Name: " + array[1]);
            System.out.println("Patronymic: " + array[2]);
        }
    }

}