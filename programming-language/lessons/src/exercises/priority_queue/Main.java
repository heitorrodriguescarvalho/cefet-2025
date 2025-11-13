package exercises.priority_queue;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    PlaneQueue planeQueue = new PlaneQueue();
    Scanner scanner = new Scanner(System.in);
    int option;

    do {
      clearScreen();
      displayMenu();
      option = scanner.nextInt();
      scanner.nextLine();

      clearScreen();
      switch (option) {
        case 1:
          addPlane(planeQueue, scanner);
          break;
        case 2:
          removePlane(planeQueue);
          break;
        case 3:
          displayQueue(planeQueue);
          break;
        case 4:
          System.out.println("\nExiting program. Goodbye!");
          break;
        default:
          System.out.println("\nInvalid option! Please try again.");
      }

      if (option != 4) {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
      }
    } while (option != 4);

    scanner.close();
  }

  private static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  private static void displayMenu() {
    System.out.println("\n========== PLANE QUEUE MENU ==========");
    System.out.println("1. Add plane to queue");
    System.out.println("2. Remove plane from queue (by priority)");
    System.out.println("3. Display queue status");
    System.out.println("4. Exit");
    System.out.print("Choose an option: ");
  }

  private static void addPlane(PlaneQueue planeQueue, Scanner scanner) {
    System.out.print("\nEnter plane name: ");
    String name = scanner.nextLine();

    System.out.print("Enter priority (lower number = higher priority): ");
    int priority = scanner.nextInt();
    scanner.nextLine();

    Plane plane = new Plane(name, priority);
    planeQueue.add(plane);
    System.out.println("\n✓ Plane '" + name + "' added to queue with priority " + priority);
  }

  private static void removePlane(PlaneQueue planeQueue) {
    if (planeQueue.isEmpty()) {
      System.out.println("\n✗ Queue is empty! No planes to remove.");
      return;
    }

    Plane plane = planeQueue.get();
    System.out.println("\n✓ Plane removed: " + plane.getName() +
        " (Priority: " + plane.getPriority() + ")");
  }

  private static void displayQueue(PlaneQueue planeQueue) {
    if (planeQueue.isEmpty()) {
      System.out.println("\n✗ Queue is empty! No planes waiting.");
    } else {
      System.out.println("\n✓ Queue has planes waiting!");
    }
  }
}
