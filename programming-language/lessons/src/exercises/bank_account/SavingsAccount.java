package exercises.bank_account;

public class SavingsAccount extends Account implements Investment {
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

  @Override
  public void readjust(double rate) {
    this.deposit(super.getAmount() * (rate / 100));
  }
}