package exercises.bank_account;

import java.util.Scanner;

public class Menu {
  AccountsManager accountsManager;
  Scanner scan;

  public Menu() {
    this.accountsManager = new AccountsManager();
    this.scan = new Scanner(System.in);
  }

  public boolean showMenu() {
    System.out.print(
        "\n1. Criar conta\n2. Ver saldo\n3. Depositar\n4. Sacar\n5. Ver limite\n6. Ver crédito\n\nOutro número encerrará o programa\n\nDigite um número: ");

    int input = this.scan.nextInt();
    this.scan.nextLine();

    switch (input) {
      case 1:
        this.createAccount();
        break;

      case 2:
        this.checkAmount();
        break;

      case 3:
        this.deposit();
        break;

      case 4:
        this.withdraw();
        break;

      case 5:
        this.checkLimit();
        break;

      case 6:
        this.checkCredit();
        break;

      default:
        return true;
    }

    return false;
  }

  public Account selectAccount() {
    for (int i = 0; i < this.accountsManager.getAccountsCount(); i++) {
      Account account = this.accountsManager.getAccount(i);

      System.out.println(Integer.toString(account.getId()) + ". " + account.getName() + " ("
          + account.getAccountType() + ")");
    }

    System.out.print("\nSelecione uma conta: ");
    int accountId = this.scan.nextInt();

    if (accountId < 0 || accountId >= this.accountsManager.getAccountsCount())
      return null;

    return this.accountsManager.getAccount(accountId);
  }

  public SpecialAccount selectSpecialAccount() {
    for (int i = 0; i < this.accountsManager.getAccountsCount(); i++) {
      Account account = this.accountsManager.getAccount(i);

      if (account instanceof SpecialAccount) {
        System.out.println(Integer.toString(account.getId()) + ". " + account.getName() + " ("
            + account.getAccountType() + ")");
      }
    }

    System.out.print("\nSelecione uma conta: ");
    int accountId = this.scan.nextInt();

    if (accountId < 0 || accountId >= this.accountsManager.getAccountsCount())
      return null;

    Account selectedAccount = this.accountsManager.getAccount(accountId);

    if (selectedAccount instanceof SpecialAccount)
      return (SpecialAccount) selectedAccount;

    return null;
  }

  public void createAccount() {
    System.out.print(
        "\n1. Conta\n2. Conta Especial\n3. Conta Poupança\n\nOutro número voltará ao menu\n\nDigite um número: ");

    int input = this.scan.nextInt();

    System.out.println();
    this.scan.nextLine();

    String name;
    double amount;
    double limit;

    switch (input) {
      case 1:
        System.out.print("Nome: ");
        name = this.scan.nextLine();

        System.out.print("Saldo: R$");
        amount = this.scan.nextDouble();

        this.accountsManager.createAccount(name, amount);

        System.out.println("Conta criada!");

        break;

      case 2:
        System.out.print("Nome: ");
        name = this.scan.nextLine();

        System.out.print("Saldo: R$");
        amount = this.scan.nextDouble();

        System.out.print("Limite: R$");
        limit = this.scan.nextDouble();

        this.accountsManager.createSpecialAccount(name, amount, limit);

        System.out.println("Conta Especial criada!");

        break;

      case 3:
        System.out.print("Nome: ");
        name = this.scan.nextLine();

        System.out.print("Saldo: R$");
        amount = this.scan.nextDouble();

        this.accountsManager.createSavingsAccount(name, amount);

        System.out.println("Conta Poupança criada!");

        break;

      default:
        return;
    }

    this.scan.nextLine();
  }

  public void checkAmount() {
    Account account = this.selectAccount();

    if (account == null) {
      System.out.println("Nº da conta inválido!");

      return;
    }

    System.out.println("Saldo: R$" + Double.toString(account.getAmount()));
  }

  public void deposit() {
    Account account = this.selectAccount();

    if (account == null) {
      System.out.println("Nº da conta inválido!");

      return;
    }

    System.out.print("Valor do depósito: R$");
    double amount = this.scan.nextDouble();

    account.deposit(amount);

    System.out.println("Depósito efetuado!");
  }

  public void withdraw() {
    Account account = this.selectAccount();

    if (account == null) {
      System.out.println("Nº da conta inválido!");
      return;
    }

    System.out.print("Valor do saque: R$");
    double amount = this.scan.nextDouble();

    boolean success = account.withdraw(amount);

    if (success)
      System.out.println("Saque efetuado!");
    else
      System.out.println("Saldo insuficiente!");
  }

  public void checkLimit() {
    SpecialAccount account = selectSpecialAccount();

    if (account == null) {
      System.out.println("Nº da conta inválido!");

      return;
    }

    System.out.println("Limite: R$" + Double.toString(account.getLimit()));
  }

  public void checkCredit() {
    SpecialAccount account = selectSpecialAccount();

    if (account == null) {
      System.out.println("Nº da conta inválido!");

      return;
    }

    System.out.println("Crédito disponível: R$" + Double.toString(account.getCredit()));
  }
}
