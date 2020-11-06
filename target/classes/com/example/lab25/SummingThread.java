package com.example.lab25;

public class SummingThread implements Runnable{
  public SummingThread(double start, double end){
    this.start = start; 
    this.end = end;
    this.sum = 0;
  }
  public void run() {
    while(this.start <= this.end) {
      this.sum+=this.start;
      this.start++;
    }
  }
  public double getSum(){
    return sum;
  }
  private
  double start;
  double end;
  double sum;
  // static Object lock = new Object();
  // static double totalSum = 0;
}
