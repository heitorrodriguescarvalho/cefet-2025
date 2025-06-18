package exercises.bank_account;

public class SavingsAccount extends Account {
  public SavingsAccount(double initialAmount, Client client) {
    super(initialAmount, client);
  }

  @Override
  public boolean deposit(double amount) {
    if (amount <= 0)
      return false;

    this.amount += amount;
    return true;
  }

  @Override
  public boolean withdraw(double amount) {
    if (amount <= 0)
      return false;

    if (this.amount < amount)
      return false;

    this.amount -= amount;

    return true;
  }

  @Override
  public String getAccountType() {
    return "Conta Poupança";
  }

  public double readjust(double percentage) {
    this.deposit(super.getAmount() * (percentage / 100));

    return super.getAmount();
  }
}