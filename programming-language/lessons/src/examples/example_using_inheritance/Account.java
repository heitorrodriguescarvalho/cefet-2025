package examples.example_using_inheritance;

public class Account {
  private int id;
  private String name;
  private double amount;

  public Account(String name, int id, double amount) {
    this.id = id;
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
