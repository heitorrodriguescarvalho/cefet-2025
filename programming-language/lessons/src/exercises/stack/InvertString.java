package exercises.stack;

import java.util.Scanner;

public class InvertString {
  public static void main(String[] args) {
    Stack<Character> stack = new Stack<>();
    Scanner scan = new Scanner(System.in);

    String expression = scan.nextLine();

    scan.close();

    for (Character character : expression.toCharArray()) {
      stack.push(character);
    }

    while (!stack.isEmpty()) {
      System.out.print(stack.pop());
    }
  }
}
