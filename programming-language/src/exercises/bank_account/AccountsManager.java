package exercises.bank_account;

import java.util.ArrayList;

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

  public void createSpecialAccount(String name, double initialAmount, double initialLimit) {
    SpecialAccount account = new SpecialAccount(name, initialAmount, initialLimit);

    accounts.add(account);
  }

  public Account getAccount(int id) {
    return this.accounts.get(id);
  }

  public int getAccountsCount() {
    return accounts.size();
  }
}
