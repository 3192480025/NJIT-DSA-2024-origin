package oy.tol.tra;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A class showing your daily schedule using a timer.
 */
public class DailyTasks {

   private QueueInterface<String> dailyTaskQueue = null;
   private Timer timer = null;
   private static final int TASK_DELAY_IN_SECONDS = 1 * 1000;

   private DailyTasks() throws QueueAllocationException {
       // Step 1: Create a queue for daily tasks
       dailyTaskQueue = QueueFactory.createStringQueue();
   }

   public static void main(String[] args) {
       try {
           DailyTasks tasks = new DailyTasks();
           tasks.run();
       } catch (QueueAllocationException e) {
           System.out.println("Failed to create daily tasks queue: " + e.getMessage());
       }
   }

   private void run() {
       try {
           readTasks(); // Step 2: Read tasks for today
           timer = new Timer(); // Step 3: Create a Java Timer object
           timer.scheduleAtFixedRate(new TimerTask() {
               @Override
               public void run() {
                   if (!dailyTaskQueue.isEmpty()) { // Step 4.1.1: Check if there are tasks in the queue
                       try {
                           String task = dailyTaskQueue.dequeue(); // Step 4.1.2: Dequeue and print the task
                           System.out.println("Task for today: " + task);
                       } catch (QueueIsEmptyException e) {
                           System.out.println("Error dequeueing task: " + e.getMessage());
                       }
                   } else {
                       timer.cancel(); // Step 4.1.3: Cancel the timer if no tasks are left
                   }
               }
           }, TASK_DELAY_IN_SECONDS, TASK_DELAY_IN_SECONDS);
       } catch (IOException e) {
           System.out.println("Something went wrong while reading tasks: " + e.getMessage());
       }
   }

   private void readTasks() throws IOException {
       String tasks = new String(getClass().getClassLoader().getResourceAsStream("DailyTasks.txt").readAllBytes());
       String[] allTasks = tasks.split("\\r?\\n");
       for (String task : allTasks) {
           try {
               dailyTaskQueue.enqueue(task); // Enqueue the task to the Queue
           } catch (QueueAllocationException | NullPointerException e) {
               System.out.println("Error enqueueing task: " + e.getMessage());
           }
       }
       System.out.println("Number of tasks in the queue: " + dailyTaskQueue.size()); // Print the number of tasks
   }
}