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

        if (query.toLowerCase().matches("plus")) {
            System.out.println("plus Query: " + query);
            Pattern pattern = Pattern.compile("(\\d+)\\s+plus\\s+(\\d+)");
            Matcher matcher = pattern.matcher(query);
            
            int sum = 0;
            if (matcher.find()) {
                // Extract the two numbers
                int num1 = Integer.parseInt(matcher.group(1));
                int num2 = Integer.parseInt(matcher.group(2));
                
                // Calculate the sum
                sum = num1 + num2;
            }
            return String.valueOf(sum);
        }

        
        return "";
    }

}