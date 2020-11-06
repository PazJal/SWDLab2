package com.example.lab25;

import java.util.Calendar; // Importing the Calendar class.
import java.util.concurrent.TimeUnit;

import com.example.lab25.SumThreads;

public class App {
  public static void main(String[] args) {
    // Getting a reference to a Calendar instance
    Calendar cal = Calendar.getInstance();
    // Reading the actual system time - for start
    int startHour = cal.get(Calendar.HOUR_OF_DAY);
    int startMinute = cal.get(Calendar.MINUTE);
    int startSecond = cal.get(Calendar.SECOND);
    
    //Multi thrededad execution. 
    SumThreads t = new SumThreads(0, 1000);
    System.out.println(t.getSum());

    //Single Threaded execution:
    // double sum = 0;
    // for(double i = 0; i <1001 ; i ++) {
    //   sum += i;
    // }
    // System.out.println(sum);
    //Read the system time for finish. 
    Calendar cal1 = Calendar.getInstance();
    int endHour = cal1.get(Calendar.HOUR_OF_DAY);
    int endMinute = cal1.get(Calendar.MINUTE);
    int endSecond = cal1.get(Calendar.SECOND);

    int hour = endHour - startHour;
    int minute = endMinute - startMinute;
    int sec = endSecond - startSecond;

    //Time output to the screen. 
    System.out.println("Execution time: " + 
      hour + " Hours " + minute + " Minutes " + sec + " Seconds");

  }
}
