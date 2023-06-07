package practice.regex;

import java.util.Scanner;

public class SplitText {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                scanner.close();
                break;
            }
            System.out.println(splitTextIntoWords(input));
        }
    }

    public static String splitTextIntoWords(String text) {
        if (text.isEmpty()) {
            return "";
        }
        String regex1 = "[-]";
        String text1 = text.replaceAll(regex1, " ");
        String regex2 = "[.,;:0-9]";
        String text3 = text1.replaceAll(regex2, "");
        String[] words = text3.split("\\s+");
        String result = "";

        for (int i = 0; i < words.length; i++) {
            result = result.concat(words[i]).concat(System.lineSeparator());
        }
        return result.trim();
    }

}