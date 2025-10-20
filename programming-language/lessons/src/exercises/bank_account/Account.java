package exercises.bank_account;

public abstract class Account {
  private int id;
  protected double amount;
  private Client client;
  private static int idCount = 0;

  public Account(double amount, Client client) {
    this.id = Account.idCount++;
    this.amount = amount;
    this.client = client;
  }

  public abstract boolean deposit(double amount);

  public abstract boolean withdraw(double amount);

  public abstract String getAccountType();

  public Client getClient() {
    return this.client;
  }

  public double getAmount() {
    return this.amount;
  }

  public int getId() {
    return this.id;
  }
}
