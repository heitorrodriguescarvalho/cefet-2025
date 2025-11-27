package exercises.stack;

import java.util.Scanner;

public class VerifyPalindrome {
  public static void main(String[] args) {
    Stack<Character> stack = new Stack<>();
    Scanner scan = new Scanner(System.in);

    String expression = scan.nextLine().replaceAll(" ", "");

    scan.close();

    for (Character character : expression.toCharArray()) {
      if (character != ' ') {
        stack.push(character);
      }
    }

    String reversed = "";

    while (!stack.isEmpty()) {
      reversed += stack.pop();
    }

    if (expression.equalsIgnoreCase(reversed)) {
      System.out.println("A expressão é um palíndromo.");
    } else {
      System.out.println("A expressão não é um palíndromo.");
    }
  }
}
