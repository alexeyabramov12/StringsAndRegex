package practice.strings;

public class TotalEarningsCalculator {

  public static void main(String[] args) {

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
    //TODO: напишите ваш код, результат вывести в консоль
    System.out.println(getVasyaMoney(text) + getPetyaMoney(text) + getMashaMoney(text));

  }

  public static int getVasyaMoney(String text) {
    String str = "Вася заработал ";
    int start = text.indexOf(str) + str.length();
    int end = text.indexOf(' ', start);
    String result = text.substring(start, end);
    int vasyaMoney = Integer.parseInt(result);
    return vasyaMoney;
  }

  public static int getPetyaMoney(String text) {
    String str = "Петя - ";
    int start = text.indexOf(str) + str.length();
    int end = text.indexOf(' ', start);
    String result = text.substring(start, end);
    int petyaMoney = Integer.parseInt(result);
    return petyaMoney;
  }

  public static int getMashaMoney(String text) {
    String str = "Маша - ";
    int start = text.indexOf(str) + str.length();
    int end = text.indexOf(' ', start);
    String result = text.substring(start, end);
    int mashaMoney = Integer.parseInt(result);
    return mashaMoney;
  }
}