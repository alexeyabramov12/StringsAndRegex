package practice.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TotalEarningsCalculatorRegex {

    public static void main(String[] args) {

    }

    public static int calculateSalarySum(String text) {
        String regex = "[1-9][0-9]{2,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int sum = 0;
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String result = text.substring(start, end);
            int money = Integer.parseInt(result);
            sum += money;

        }
        return sum;
    }

}