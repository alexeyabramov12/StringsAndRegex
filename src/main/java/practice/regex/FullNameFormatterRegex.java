package practice.regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullNameFormatterRegex {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                scanner.close();
                break;
            }
            //TODO:напишите ваш код тут, результат вывести в консоль.
            //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
            String name = input.trim();
            String regex = "([АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя -]{2,}){3}";
            if (!name.matches(regex)) {
                System.out.println("Введенная строка не является ФИО");
                break;
            }
            String[] name1 = name.split("\\s");
            System.out.println("Фамилия: " + name1[0]);
            System.out.println("Имя: " + name1[1]);
            System.out.println("Отчество: " + name1[2]);
        }
    }

}