# List of answers for Lab2
## By Paz Jaldety

### Question 1:
The difference is that the method start actually creates a new thread and runs the method run().
Calling just the method run will not prompt the creation of a new thread. 


### Question 2:
1. Here are 2 programs run worth of output.
```cmd
D:\Programing\VSC-Workspaces\Java Stand Alone Files\Software Design\HW2>javac HelloWorld.java 

D:\Programing\VSC-Workspaces\Java Stand Alone Files\Software Design\HW2>java HelloWorld
Hello world from thread number 7
Hello world from thread number 0
Hello world from thread number 4
Hello world from thread number 5
Hello world from thread number 3
Hello world from thread number 2
Hello world from thread number 1
Hello world from thread number 6
Hello world from thread number 8
Hello world from thread number 9
That's all, folks

D:\Programing\VSC-Workspaces\Java Stand Alone Files\Software Design\HW2>java HelloWorld
Hello world from thread number 6
Hello world from thread number 9
Hello world from thread number 2
Hello world from thread number 4
Hello world from thread number 5
Hello world from thread number 3
Hello world from thread number 0
Hello world from thread number 8
Hello world from thread number 1
Hello world from thread number 7
That's all, folks

D:\Programing\VSC-Workspaces\Java Stand Alone Files\Software Design\HW2>
```

2. After removing the loop as suggested we got this run output:
```cmd
That's all, folks
Hello world from thread number 6
Hello world from thread number 5
Hello world from thread number 2
Hello world from thread number 8
Hello world from thread number 7
Hello world from thread number 4
Hello world from thread number 1
Hello world from thread number 3
Hello world from thread number 9
Hello world from thread number 0
```

What we are seeing here is that now that join was removed the main thread no longer waits for the other threads to finish before printing "That's all, folks".

3. As expected, we are now getting the threads in order is the main program waits for each thread to finish before creating the next one.

```cmd
D:\Programing\VSC-Workspaces\Java Stand Alone Files\Software Design\HW2>java HelloWorld        
Hello world from thread number 0
Hello world from thread number 1
Hello world from thread number 2
Hello world from thread number 3
Hello world from thread number 4
Hello world from thread number 5
Hello world from thread number 6
Hello world from thread number 7
Hello world from thread number 8
Hello world from thread number 9
That's all, folks
```
4. The following code:
```java
Thread.currentThread().join()
```

will cause the current thread to wait for itself to end, which will never happen while its waiting. (deadlock?)


### Question 3

1. There is an advantage on any multi - core system. These systems are capable of executing simulatesouly. 
2. On my tests it was suprisingly, but usuaully it is not as it dpendes on system CPU utilizaiton,
other proccesses that are running and the amount of cores on the machine. 
3. Code implementation of SumThreads and the assisting SummingThreard. 

SumThreads - manages the creation and termination of calculating threads.
```java
package com.example.pj;

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

    //TODO: Move 10 out into a configurable varirable - will allow to choose the number of threads to create.  
    //Divide work among threads - there is some room for optimaztions here.
    for(int i = 0; i < 10 ; i ++) {
      //TODO: Remove not needed.
      String threadNum = Integer.toString(i);
      
      //Calculate the starting and ending power for the thread created. 
      double startPower = i * step + translate;
      double endPower = (i+1) * step + translate;

      //Calculate the actual values for the thread. 
      double startVal = Math.pow(2,startPower) + 1;
      double endVal= Math.pow(2,endPower);

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

```

SummingThread implements runnable to give us an easy to create summing thread. 
```java
package com.example.pj;

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

```

### Question 4
We add a check before entering a new value and put thread on wait if there is no room.
We must also check now on consuming to wake sleeping produce thread. 
```java
public class ProducerConsumer2 {
  Queue<Integer> workingQueue = new LinkedList<Integer>();
  public synchronized void produce(int num) {
    //Cause thread to wait if there are more than 10 elements in queu. 
    while(workingQueue.size() > 10){

    }
    workingQueue.add(num);
    notifyAll();
  }
  public synchronized Integer consume() throws InterruptedException {
    while (workingQueue.isEmpty()) {
      wait();
    }
    //Must check and notify if you can now insert new elements into queu. 
    int result = workingQueue.poll();
    if(workingQueue.size() < 10>){
      notifyAll();
    }
    return result;
  }
}
```


### Question 5 - Lab assignment:
3. Time on my system shows 0 seconds on both cases, if there is a difference its in nanosecs and untociable.
