package exercises.stack;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Stack<Character> stack = new Stack<>();
    Scanner scan = new Scanner(System.in);

    String expression = scan.nextLine();

    scan.close();

    boolean isValid = true;

    for (Character digit : expression.toCharArray()) {
      if (digit.equals('(') || digit.equals('{') || digit.equals('[')) {
        stack.push(digit);
        continue;
      }

      if (digit.equals(')') && (stack.isEmpty() || !stack.pop().equals('('))) {
        isValid = false;
        break;
      }

      if (digit.equals('}') && (stack.isEmpty() || !stack.pop().equals('{'))) {
        isValid = false;
        break;
      }

      if (digit.equals(']') && (stack.isEmpty() || !stack.pop().equals('['))) {
        isValid = false;
        break;
      }
    }

    if (!stack.isEmpty()) {
      isValid = false;
    }

    if (isValid)
      System.out.println("A expressão é válida.");
    else
      System.out.println("A expressão não é válida.");
  }
}
