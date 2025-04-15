package exercises;

import java.util.Scanner;

public class VotingClass {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.print("Digite sua idade: ");
    int age = scan.nextInt();

    scan.close();

    if (age < 16)
      System.out.println("Você não pode votar.");
    else if (age < 17)
      System.out.println("Seu voto é facultativo.");
    else
      System.out.println("Você deve votar obrigatoriamente.");
  }
}
