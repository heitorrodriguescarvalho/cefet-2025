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

  void deposit(double valor) {
    this.amount += valor;
  }

  boolean withdraw(double valor) {
    if (this.amount < valor)
      return false;

    this.amount -= valor;

    return true;
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
