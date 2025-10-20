package exercises.bank_account;

public class SpecialAccount extends Account {
  private double limit;
  private double credit;

  public SpecialAccount(double initialAmount, double initialLimit, Client client) {
    super(initialAmount, client);

    this.limit = initialLimit;
    this.credit = initialLimit;
  }

  @Override
  public boolean deposit(double amount) {
    if (amount <= 0)
      return false;

    if (this.credit == this.limit) {
      this.amount += amount;
      return true;
    }

    if (this.limit - this.credit >= amount) {
      this.credit += amount;
    } else {
      this.amount += (amount - (this.limit - this.credit));
      this.credit = this.limit;
    }

    return true;
  }

  @Override
  public boolean withdraw(double amount) {
    if (amount <= 0)
      return false;

    if (this.amount + this.credit < amount)
      return false;

    if (this.amount >= amount) {
      this.amount -= amount;

      return true;
    }

    this.credit -= amount - this.amount;
    this.amount = 0;

    return true;
  }

  @Override
  public String getAccountType() {
    return "Conta Especial";
  }

  public boolean setLimit(double limit) {
    if (this.limit - this.credit < limit)
      return false;

    this.credit += limit - this.limit;
    this.limit = limit;

    return true;
  }

  public double getLimit() {
    return this.limit;
  }

  public double getCredit() {
    return this.credit;
  }
}
