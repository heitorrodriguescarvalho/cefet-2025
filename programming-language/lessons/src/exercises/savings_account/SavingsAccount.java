package exercises.savings_account;

public class SavingsAccount extends Account {
  public SavingsAccount(String name, int id, double initialAmount) {
    super(name, id, initialAmount);
  }

  public SavingsAccount(double initialAmount) {
    super("", 0, initialAmount);
  }

  public double readjust(double percentage) {
    super.deposit(super.getAmount() * (percentage / 100));

    return super.getAmount();
  }
}