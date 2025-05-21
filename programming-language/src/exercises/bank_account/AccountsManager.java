package exercises.bank_account;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountsManager {
  private ArrayList<Account> accounts = new ArrayList<>();

  public void createAccount(String name, double initialAmount) {
    Account account = new Account(name, initialAmount);

    accounts.add(account);
  }

  public void createSavingsAccount(String name, double initialAmount) {
    SavingsAccount account = new SavingsAccount(name, initialAmount);

    accounts.add(account);
  }

  public int selectAccount() {
    Scanner scan = new Scanner(System.in);

    for (int i = 0; i < this.accounts.size(); i++) {
      System.out.println(Integer.toString(this.accounts.get(i).getId()) + ". " + this.accounts.get(i).getName());
    }

    System.out.println("\nSelecione uma conta: ");
    int accountId = scan.nextInt();

    if (accountId < 0 || accountId >= this.accounts.size())
      return -1;

    return accountId;
  }

  Account getAccount(int id) {
    return this.accounts.get(id);
  }
}
