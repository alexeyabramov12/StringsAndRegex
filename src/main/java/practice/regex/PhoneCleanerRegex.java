package practice.regex;

import java.util.Scanner;

public class PhoneCleanerRegex {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        scanner.close();
        break;
      }
      //TODO:напишите ваш код тут, результат вывести в консоль.
      String regex1 = "[^0-9]";
      String phoneNumber = input.replaceAll(regex1, "");
      String regex2 = "[0-9]{10}";
      String regex3 = "[7,8][0-9]{10}";
      String regex4 = "[8]";
      if (phoneNumber.matches(regex2)) {
        System.out.println(7 + phoneNumber);
        break;
      }
      if (!phoneNumber.matches(regex3)) {
        System.out.println("Неверный формат номера");
        break;
      }
      if (phoneNumber.charAt(0) == '8') {
        String phoneNumber2 = phoneNumber.replaceFirst(regex4, "7");
        System.out.println(phoneNumber2);
        break;
      }
      System.out.println(phoneNumber);
    }
  }

}
