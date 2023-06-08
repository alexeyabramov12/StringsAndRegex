package practice.regex;

import java.util.Scanner;

public class PhoneCleanerRegex {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    String regex1 = "[^0-9]";
    String regex2 = "[0-9]{10}";
    String regex3 = "[7,8][0-9]{10}";
    String regex4 = "[8]";

    while (true) {
      String input = scanner.nextLine();
      String phoneNumber = input.replaceAll(regex1, "");
      if (input.equals("0")) {
        scanner.close();
        break;
      }
      if (phoneNumber.matches(regex2)) {
        System.out.println(7 + phoneNumber);
        continue;
      }
      if (!phoneNumber.matches(regex3)) {
        System.out.println("Invalid number format.");
        break;
      }
      if (phoneNumber.charAt(0) == '8') {
        String phoneNumber2 = phoneNumber.replaceFirst(regex4, "7");
        System.out.println(phoneNumber2);
        continue;
      }
      System.out.println(phoneNumber);
    }
  }

}
