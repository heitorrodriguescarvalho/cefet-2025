package exercises.tasks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
  public static void main(String[] args) {
    List<Task> backlog = new ArrayList<>();
    Queue<Task> printQueue = new LinkedList<>();
    Queue<Task> scheduler = new PriorityQueue<>();

    for (int i = 0; i < 5; i++) {
      Task task = new Task("Task " + (i + 1), (int) (Math.random() * 3 + 1));
      System.out.println("Created: " + task.getDescription() + " with priority " + task.getPriority());
      backlog.add(task);
      printQueue.add(task);
      scheduler.add(task);
    }

    System.out.println("\n\n\nBacklog:");

    for (Task task : backlog) {
      System.out.println("- " + task.getDescription() + " (Priority: " + task.getPriority() + ")");
    }

    backlog.removeFirst();

    System.out.println("\n\n\nBacklog after Removal:");

    for (Task task : backlog) {
      System.out.println("- " + task.getDescription() + " (Priority: " + task.getPriority() + ")");
    }

    System.out.println("\n\n\nPrint Queue:");

    while (!printQueue.isEmpty()) {
      Task task = printQueue.poll();
      System.out.println("Printing: " + task.getDescription() + " (Priority: " + task.getPriority() + ")");
    }

    System.out.println("\n\n\nScheduler:");

    while (!scheduler.isEmpty()) {
      Task task = scheduler.poll();
      System.out.println("Printing: " + task.getDescription() + " (Priority: " + task.getPriority() + ")");
    }
  }
}
