package exercises;

import java.text.MessageFormat;
import java.util.Scanner;

public class IntegersSum {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.println("Digite números inteiros (n < 0 para parar):");

    int count = 0, sum = 0, input = scan.nextInt();

    while (input >= 0) {
      count++;
      sum += input;

      input = scan.nextInt();
    }

    scan.close();

    System.out.println(
        MessageFormat.format(
            "\nQuantidade de números digitados: {0}\nMédia: {1}",
            count,
            (float) sum / count));
  }
}
