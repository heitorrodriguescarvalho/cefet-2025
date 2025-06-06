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

  void deposit(double amount) {
    this.amount += amount;
  }

  boolean withdraw(double amount) {
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
