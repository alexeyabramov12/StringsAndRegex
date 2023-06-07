package practice.strings;

import java.util.Scanner;

public class FullNameFormatter {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                scanner.close();
                break;
            }
            String name = input.trim();
            String validChar = "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя -";
            boolean validName = true;
            for (int i = 0; i < name.length(); i++) {
                char symbol = name.charAt(i);
                String string = Character.toString(symbol);
                validName = validChar.contains(string);
            }
            int space1 = name.indexOf(' ');
            int space2 = name.indexOf(' ', space1 + 1);
            int space3 = name.indexOf(' ', space2 + 1);
            if (space1 < 0 || space2 < 0 || space3 > 0 || !validName) {
                System.out.println("Введенная строка не является ФИО");
                break;
            }
            String name1 = name.substring(0, space1);
            String name2 = name.substring(space1, space2);
            String name3 = name.substring(space2);

            System.out.println("Фамилия: " + name1);
            System.out.println("Имя: " + name2.trim());
            System.out.println("Отчество: " + name3.trim());
        }
    }

}