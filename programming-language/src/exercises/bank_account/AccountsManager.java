package exercises.bank_account;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountsManager {
  private ArrayList<Account> accounts;
  private Client client;

  public AccountsManager(Client client) {
    this.accounts = new ArrayList<>();
    this.client = client;
  }

  public Account createSavingsAccount() {
    Scanner scan = new Scanner(System.in);
    Menu menu = new Menu();

    System.out.print("Saldo inicial: R$");
    Double initialAmount = scan.nextDouble();

    if (initialAmount < 0) {
      System.out.println("\nSaldo inválido!");

      return null;
    }

    SavingsAccount account = new SavingsAccount(initialAmount, this.client);

    accounts.add(account);

    return account;
  }

  public Account createSpecialAccount() {
    Scanner scan = new Scanner(System.in);
    Menu menu = new Menu();

    System.out.print("Saldo inicial: R$");
    Double initialAmount = scan.nextDouble();

    if (initialAmount < 0) {
      System.out.println("\nSaldo inválido!");

      return null;
    }

    System.out.print("Limite: R$");
    Double initialLimit = scan.nextDouble();

    if (initialLimit < 0) {
      System.out.println("\nLimite inválido!");

      return null;
    }

    SpecialAccount account = new SpecialAccount(initialAmount, initialLimit, this.client);

    accounts.add(account);

    return account;
  }

  public Account getAccount(int id) {
    return this.accounts.get(id);
  }

  public int getAccountsCount() {
    return accounts.size();
  }
}
