package examples;

public class ExampleUsingObject {
  private static class Account {
    public String name;
    private double balance = 0.0;

    private void deposit(double amount) {
      balance += amount;
    }

    private double getBalance() {
      return balance;
    }
  }

  public static void main(String[] args) {
    Account account = new Account();

    System.out.println(account.name);
    System.out.println(account.getBalance());

    account.name = "Heitor";
    account.deposit(1000.0);

    System.out.println(account.name);
    System.out.println(account.getBalance());
  }
}
