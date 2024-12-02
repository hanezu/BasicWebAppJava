package com.develogical;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class QueryProcessor {
    
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

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

        if (query.toLowerCase().contains("minus")) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(query);

            int i = 0;
            int sum = 0;
            while (matcher.find()) {
                if (i == 0) {
                    sum = Integer.parseInt(matcher.group());
                } else {
                    sum -= Integer.parseInt(matcher.group());
                }
                i++;
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
        
        if (query.contains("prime")) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(query);
            
            List<Integer> primes = new ArrayList<>();
            
            while (matcher.find()) {
                int number = Integer.parseInt(matcher.group());
                if (isPrime(number)) {
                    primes.add(number);
                }
            }
            
            String primesString = primes.stream()
                                        .map(String::valueOf)
                                        .collect(Collectors.joining(", "));
            
            return primesString;
        }


        // 	What is 63 to the power of 68?
        if (query.contains("to the power of")) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(query);
            int base = 0;
            int power = 0;
            int i = 0;
            while (matcher.find()) {
                if (i == 0) {
                    base = Integer.parseInt(matcher.group());
                } else {
                    power = Integer.parseInt(matcher.group());
                }
                i++;
            }
            return String.valueOf((int) Math.pow(base, power));
        }

        return "";
    }

}