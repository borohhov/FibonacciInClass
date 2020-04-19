package com.ljuv.fibonacciapp.utils;

import java.util.ArrayList;

public class FizzBuzzList {
    public static ArrayList<String> getFizzBuzzList (int count) {
        ArrayList<String> fizzBuzzList = new ArrayList<>(count);

        for (int i=1; i<=count; i++)
        {
            if (i%15==0) {
                fizzBuzzList.add("FizzBuzz");
                //System.out.print("FizzBuzz" + " ");
            }

            else if (i%5==0) {
                fizzBuzzList.add("Buzz");
                //System.out.print("Buzz" + " ");
            }

            else if (i%3==0) {
                fizzBuzzList.add("Fizz");
                //System.out.print("Fizz" + " ");
            }

            else {
                fizzBuzzList.add(String.valueOf(i));
                //System.out.print(i + " ");
            }
        }
        return fizzBuzzList;

    }
}
