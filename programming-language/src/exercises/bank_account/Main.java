package exercises.bank_account;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Conta account = new Conta();

    System.out.print("Digite o nome do titular da conta: ");
    account.nome_titular = scan.nextLine();

    System.out.print("Digite o número da conta: ");
    account.numero = scan.nextInt();

    System.out.print("Digite o saldo inicial da conta: R$");
    account.saldo = scan.nextDouble();

    int input;

    do {
      System.out.print("\n1. Depositar\n2. Sacar\n\nOutro número encerrará o programa\n\nDigite um número: ");
      input = scan.nextInt();

      switch (input) {
        case 1:
          System.out.print("\nValor do depósito: R$");
          account.depositar(scan.nextDouble());
          System.out.println("Valor atualizado: R$" + String.format("%.2f", account.saldo));
          break;

        case 2:
          System.out.print("\nValor da saque: R$");
          boolean success = account.sacar(scan.nextDouble());

          if (success)
            System.out.println("Saque efetuado com sucesso");
          else
            System.out.println("Saque não efetuado");
      }
    } while (input == 1 || input == 2);

    System.out.println("\nTerminando a execução...");
    scan.close();
  }
}
