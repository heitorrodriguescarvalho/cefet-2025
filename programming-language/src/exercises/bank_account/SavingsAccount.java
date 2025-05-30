package exercises.bank_account;

public class SavingsAccount extends Account {
  public SavingsAccount(String name, double initialAmount) {
    super(name, initialAmount);
  }

  public double readjust(double percentage) {
    super.deposit(super.getAmount() * (percentage / 100));

    return super.getAmount();
  }

  @Override
  String getAccountType() {
    return "Conta Poupança";
  }
}