package exercises.bank_account;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Contas accounts = new Contas();

    int input;
    int accountId;
    double amount;

    do {
      System.out.print(
          "\n1. Criar conta\n2. Ver saldo\n3. Depositar\n4. Sacar\n\nOutro número encerrará o programa\n\nDigite um número: ");

      input = scan.nextInt();
      scan.nextLine();

      switch (input) {
        case 1:
          System.out.println("Insira os dados da conta:");

          System.out.print("Nome: ");
          String name = scan.nextLine();

          System.out.print("Saldo: ");
          amount = scan.nextDouble();

          accounts.createAccount(name, amount);

          System.out.println("Conta criada!");

          break;

        case 2:
          accountId = accounts.selectAccount();

          if (accountId == -1) {
            System.out.println("Nº da conta inválido!");
            break;
          }

          System.out.println("Saldo: R$" + Double.toString(accounts.getAccount(accountId).getAmount()));

          break;

        case 3:
          accountId = accounts.selectAccount();

          if (accountId == -1) {
            System.out.println("Nº da conta inválido!");
            break;
          }

          System.out.print("Valor do depósito: R$");
          amount = scan.nextDouble();

          accounts.getAccount(accountId).deposit(amount);

          System.out.println("Depósito efetuado!");

          break;

        case 4:
          accountId = accounts.selectAccount();

          if (accountId == -1) {
            System.out.println("Nº da conta inválido!");
            break;
          }

          System.out.print("Valor do saque: R$");
          amount = scan.nextDouble();

          boolean success = accounts.getAccount(accountId).withdraw(amount);

          if (success)
            System.out.println("Saque efetuado!");
          else
            System.out.println("Saldo insuficiente!");

          break;
      }
    } while (input == 1 || input == 2 || input == 3 || input == 4);

    System.out.println("\nTerminando a execução...");
    scan.close();
  }
}
