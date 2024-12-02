package com.develogical;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryProcessor {

    public String process(String query) {

        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        }

        if (query.contains("your name")) {
            return "QA_survival";
        }

        if (query.contains("numbers is the largest")) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(query);
            
            // Parse numbers and find the largest
            int max = Integer.MIN_VALUE;
            while (matcher.find()) {
                int number = Integer.parseInt(matcher.group());
                if (number > max) {
                    max = number;
                }
            }
            return String.valueOf(max);
        }   

        if (query.toLowerCase().contains("plus")) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(query);

            int sum = 0;
            while (matcher.find()) {
                sum += Integer.parseInt(matcher.group());
            }

            return String.valueOf(sum);
        }

        if (query.toLowerCase().contains("multiplied")) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(query);

            int sum = 1;
            while (matcher.find()) {
                sum *= Integer.parseInt(matcher.group());
            }

            return String.valueOf(sum);
        }

        if (query.toLowerCase().contains("which of the following numbers is both a square and a cube")) {
            String[] numbers = query.replaceAll("[^\\d ]", "").trim().split(" ");
            for (String numberStr : numbers) {
                if (!numberStr.isEmpty()) {
                    int number = Integer.parseInt(numberStr);
                    double sqrt = Math.sqrt(number);
                    double cbrt = Math.cbrt(number);
                    if (sqrt == Math.floor(sqrt) && cbrt == Math.floor(cbrt)) {
                        return String.valueOf(number);
                    }
                }
            }
        }

        return "";
    }

}