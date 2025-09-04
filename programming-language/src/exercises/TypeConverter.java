package exercises;

import java.util.Scanner;

public class TypeConverter {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.print("Enter an integer: ");
    String text = scan.nextLine();

    try {
      int number = Integer.parseInt(text);
      System.out.println("Entered number: " + number);
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Please enter a valid integer.");
    }

    scan.close();
  }
}
