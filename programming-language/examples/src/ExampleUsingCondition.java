import java.util.Scanner;

public class ExampleUsingCondition {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.print("Enter a grade: ");
    float grade = scan.nextFloat();

    scan.close();

    if (grade < 0 || grade > 100) {
      System.out.println("Invalid grade. Please enter a number between 0 and 100.");
    } else {
      if (grade >= 60) {
        System.out.println("Congratulations, you passed!");
      } else {
        System.out.println("You don't pass. Better luck next time!");
      }
    }
  }
}
