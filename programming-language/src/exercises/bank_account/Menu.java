package exercises.bank_account;

import java.util.Scanner;

public class Menu {
  private AccountsManager accountsManager;
  private Scanner scan;

  public Menu() {
    this.accountsManager = new AccountsManager();
    this.scan = new Scanner(System.in);
  }

  public boolean showMenu() {
    this.clearTerminal();

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

  public Account selectAccount(String accountType) {
    this.clearTerminal();

    boolean hasAccounts = false;

    for (int i = 0; i < this.accountsManager.getAccountsCount(); i++) {
      Account account = this.accountsManager.getAccount(i);

      if (accountType == null || account.getAccountType().equals(accountType)) {
        System.out.println(Integer.toString(account.getId()) + ". " + account.getName() + " ("
            + account.getAccountType() + ")");

        hasAccounts = true;
      }
    }

    if (!hasAccounts) {
      System.out.println("Nenhuma " + accountType + " disponível.");

      this.awaitEnter();

      return null;
    }

    System.out.print("\nOutro número voltará ao menu...\n\nSelecione uma conta: ");
    int accountId = this.scan.nextInt();

    if (accountId < 0 || accountId >= this.accountsManager.getAccountsCount()) {
      this.clearTerminal();
      System.out.println("Nº da conta inválido! Voltando ao menu...");
      this.scan.nextLine();

      this.awaitEnter();

      return null;
    }

    return this.accountsManager.getAccount(accountId);
  }

  public void createAccount() {
    this.clearTerminal();

    System.out.print(
        "\n1. Conta\n2. Conta Especial\n3. Conta Poupança\n\nOutro número voltará ao menu...\n\nDigite um número: ");

    int input = this.scan.nextInt();

    this.clearTerminal();
    this.scan.nextLine();

    Account newAccount;

    switch (input) {
      case 1:
        newAccount = this.accountsManager.createAccount();
        break;

      case 2:
        newAccount = this.accountsManager.createSpecialAccount();
        break;

      case 3:
        newAccount = this.accountsManager.createSavingsAccount();
        break;

      default:
        this.clearTerminal();
        System.out.println("Nº Inválido! Voltando ao menu...");
        this.awaitEnter();

        return;
    }

    if (newAccount != null)
      System.out.println(newAccount.getAccountType() + " criada!");
    else
      System.out.println("Falha na criação da conta!");

    this.awaitEnter();
  }

  public void checkAmount() {
    Account account = this.selectAccount(null);

    if (account == null)
      return;

    this.clearTerminal();

    System.out.println("Saldo: R$" + Double.toString(account.getAmount()));

    this.scan.nextLine();
    this.awaitEnter();
  }

  public void deposit() {
    Account account = this.selectAccount(null);

    if (account == null)
      return;

    this.clearTerminal();

    System.out.print("Valor do depósito: R$");
    double amount = this.scan.nextDouble();

    if (amount <= 0) {
      this.clearTerminal();
      System.out.println("Valor inválido! Voltando ao menu...");
      this.scan.nextLine();
      this.awaitEnter();

      return;
    }

    account.deposit(amount);

    this.clearTerminal();

    System.out.println("Depósito efetuado!");
    System.out.println("Saldo atual: R$" + Double.toString(account.getAmount()));

    this.scan.nextLine();
    this.awaitEnter();
  }

  public void withdraw() {
    Account account = this.selectAccount(null);

    if (account == null)
      return;

    this.clearTerminal();

    System.out.print("Valor do saque: R$");
    double amount = this.scan.nextDouble();

    if (amount <= 0) {
      this.clearTerminal();
      System.out.println("Valor inválido! Voltando ao menu...");
      this.scan.nextLine();
      this.awaitEnter();

      return;
    }

    boolean success = account.withdraw(amount);

    this.clearTerminal();

    if (success)
      System.out.println("Saque efetuado!");
    else
      System.out.println("Saldo Insuficiente!");

    System.out.println("Saldo atual: R$" + Double.toString(account.getAmount()));
    if (account instanceof SpecialAccount) {
      System.out.println("Crédito disponível: R$" + Double.toString(((SpecialAccount) account).getCredit()));
    }

    this.scan.nextLine();
    this.awaitEnter();
  }

  public void checkLimit() {
    SpecialAccount account = (SpecialAccount) selectAccount("Conta Especial");

    if (account == null)
      return;

    this.clearTerminal();

    System.out.println("Limite: R$" + Double.toString(account.getLimit()));

    this.scan.nextLine();
    this.awaitEnter();
  }

  public void checkCredit() {
    SpecialAccount account = (SpecialAccount) selectAccount("Conta Especial");

    if (account == null)
      return;

    this.clearTerminal();

    System.out.println("Crédito disponível: R$" + Double.toString(account.getCredit()));

    this.scan.nextLine();
    this.awaitEnter();
  }

  public void clearTerminal() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public void awaitEnter() {
    System.out.println("\nPressione Enter ↵ para continuar...");
    this.scan.nextLine();
  }
}
