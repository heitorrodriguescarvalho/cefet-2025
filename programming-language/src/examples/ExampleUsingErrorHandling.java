package examples;

import java.util.Scanner;

public class ExampleUsingErrorHandling {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.print("Enter first number: ");
    int n1 = scan.nextInt();

    System.out.print("Enter second number: ");
    int n2 = scan.nextInt();

    scan.close();

    try {
      int result = n1 / n2;
      System.out.println("Result: " + result);
    } catch (ArithmeticException e) {
      System.out.println("Error: " + e.getLocalizedMessage());
    } finally {
      System.out.println("Thank you for using the program!");
    }
  }
}
