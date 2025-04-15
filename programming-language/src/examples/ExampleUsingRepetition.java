package examples;

import java.util.Scanner;

public class ExampleUsingRepetition {
  public static void main() {
    Scanner scan = new Scanner(System.in);

    System.out.print("Enter a number: ");

    int num = scan.nextInt();

    scan.close();

    System.out.println("Numbers from 0 to " + num + ":");

    for (int i = 0; i <= num; i++) {
      System.out.print(i + " ");
    }
  }
}
