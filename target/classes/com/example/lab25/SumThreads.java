package com.example.lab25;

public class SumThreads{ 
  public SumThreads(double startPower, double endPower){
    this.startPower = startPower; 
    this.endPower = endPower;
    this.sum = 0;
  }
  public double getSum(){ //Do you even map reduce?
    
    //A place to store the threads:
    Thread[] threads = new Thread[10];
    SummingThread[] myThreads = new SummingThread[10];

    //Calculate step size in powers of 2:
    double step = Math.floor(endPower / 10);
    double translate = endPower % 10;

    //Divide work among threads - there is some room for optimaztions here.
    for(int i = 0; i < 10 ; i ++) {
      //TODO: Remove not needed.
      String threadNum = Integer.toString(i);
      
      //Adjust to just work with the values
      //Calculate the starting and ending power for the thread created. 
      // double startPower = i * step + translate;
      // double endPower = (i+1) * step + translate;

      //Calculate the actual values for the thread. 
      // double startVal = Math.pow(2,startPower) + 1;
      // double endVal= Math.pow(2,endPower);
      double startVal = i * endPower / 10 + 1;
      double endVal= (i+1) * endPower / 10 ;
      

      //Let the first thread handle some more work as its working on small numbers. 
      if(i == 0){
        startVal = 1;
      }

      //TODO: Comment out used for debugging. 
      // System.out.println("from: " + startPower +" to: " + endPower);

      //Create a new thread from our class. Thank god for java garabge collector. 
      myThreads[i] = new SummingThread(startVal, endVal);
      threads[i] = new Thread(myThreads[i]);
    }

    //Start all the threads. 
    for (Thread thread : threads) {
      thread.start(); // start the threads
    }

    //wait for the threads to finish. 
    for (Thread thread : threads) {
      try {
        thread.join(); // wait for the threads to terminate
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    //Sum thread results:
    for(SummingThread t : myThreads){
      sum += t.getSum();
    }

    //Print out to console the sum. 
  
    return sum;
  }
  private

  //Not needed as all questions summed from 1. But good for future use. 
  double startPower;
  double endPower;
  double sum;

}
