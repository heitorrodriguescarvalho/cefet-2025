package exercises.bank_account;

public class Account {
  private int id;
  private String name;
  private double amount;
  private static int idCount = 0;

  public Account(String name, double amount) {
    this.id = Account.idCount++;
    this.name = name;
    this.amount = amount;
  }

  boolean deposit(double amount) {
    if (amount <= 0)
      return false;

    this.amount += amount;
    return true;
  }

  boolean withdraw(double amount) {
    if (amount <= 0)
      return false;

    if (this.amount < amount)
      return false;

    this.amount -= amount;

    return true;
  }

  String getAccountType() {
    return "Conta";
  }

  double getAmount() {
    return this.amount;
  }

  String getName() {
    return this.name;
  }

  int getId() {
    return this.id;
  }
}
