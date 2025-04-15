package examples;

import java.util.Scanner;

public class ExampleUsingScanner {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.print("Enter a number: ");
    int number = scan.nextInt();

    scan.close();

    System.out.println("You entered: " + number);
  }
}
