package exercises.savings_account;

public class Main {
  public static void main(String[] args) {
    SavingsAccount account = new SavingsAccount(1000.0);

    System.out.println("Saldo inicial: R$" + Double.toString(account.getAmount()));
    System.out.println("Depósito de R$500.00");

    account.deposit(500.0);

    System.out.println("Saldo atual: R$" + Double.toString(account.getAmount()));
    System.out.println("Saque de R$200.00");

    account.withdraw(200.0);

    System.out.println("Saldo atual: R$" + Double.toString(account.getAmount()));
    System.out.println("Reajuste de 5%");

    account.readjust(5.0);

    System.out.println("Saldo final: R$" + Double.toString(account.getAmount()));
  }
}
