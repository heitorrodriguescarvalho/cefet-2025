package exercises.bank_account;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {
  private Scanner scan;
  private ArrayList<Client> clients;

  public Menu() {
    this.clients = new ArrayList<>();
    this.scan = new Scanner(System.in);
  }

  public boolean showMenu() {
    this.clearTerminal();

    System.out.print(
        "\n1. Cadastrar cliente\n2. Selecionar cliente\n\nOutro número encerrará o programa...\n\nDigite um número: ");

    int input = this.scan.nextInt();
    this.scan.nextLine();

    Client client = null;

    switch (input) {
      case 1:
        client = this.createClient();

      case 2:
        if (client == null)
          client = this.selectClient();

        if (client == null)
          break;

        boolean exit;
        do {
          exit = this.accountOptions(client);
        } while (!exit);

        break;

      default:
        return true;
    }

    return false;
  }

  public Client selectClient() {
    this.clearTerminal();

    if (this.clients.isEmpty()) {
      System.out.println("Nenhum cliente cadastrado.");
      this.awaitEnter();
      return null;
    }

    for (Client client : this.clients) {
      System.out.println(Integer.toString(client.getId()) + ". " + client.getName());
    }

    System.out.print("\nOutro número voltará ao menu...\n\nSelecione um cliente: ");
    int clientId = this.scan.nextInt();

    if (clientId < 0 || clientId >= this.clients.size()) {
      this.clearTerminal();
      System.out.println("Nº do cliente inválido! Voltando ao menu...");
      this.scan.nextLine();
      this.awaitEnter();
      return null;
    }

    return this.clients.get(clientId);
  }

  public Client createClient() {
    this.clearTerminal();

    System.out.print("Nome do cliente: ");
    String name = this.scan.nextLine();

    if (name.isEmpty()) {
      this.clearTerminal();
      System.out.println("Nome inválido! Voltando ao menu...");
      this.awaitEnter();
      return null;
    }

    System.out.print("CPF do cliente: ");
    String cpf = this.scan.nextLine();

    if (Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}").matcher(cpf).matches() == false) {
      this.clearTerminal();
      System.out.println("CPF inválido! Formato esperado: xxx.xxx.xxx-xx. Voltando ao menu...");
      this.awaitEnter();
      return null;
    }

    Client newClient = new Client(name, cpf);
    this.clients.add(newClient);

    this.clearTerminal();
    System.out.println("Cliente cadastrado com sucesso!");
    this.awaitEnter();

    return newClient;
  }

  public Account selectAccount(Client client, String accountType) {
    this.clearTerminal();

    boolean hasAccounts = false;

    for (int i = 0; i < client.accounts.getAccountsCount(); i++) {
      Account account = client.accounts.getAccount(i);

      if (accountType == null || account.getAccountType().equals(accountType)) {
        System.out.println(Integer.toString(i) + ". " + client.getName() + " ("
            + account.getAccountType() + ")");

        hasAccounts = true;
      }
    }

    if (!hasAccounts) {
      if (accountType != null)
        System.out.println("Nenhuma " + accountType + " disponível para " + client.getName() + ".");
      else
        System.out.println("Nenhuma conta disponível para " + client.getName() + ".");

      this.awaitEnter();

      return null;
    }

    System.out.print("\nOutro número voltará ao menu...\n\nSelecione uma conta: ");
    int accountId = this.scan.nextInt();

    if (accountId < 0 || accountId >= client.accounts.getAccountsCount()) {
      this.clearTerminal();
      System.out.println("Nº da conta inválido! Voltando ao menu...");
      this.scan.nextLine();

      this.awaitEnter();

      return null;
    }

    return client.accounts.getAccount(accountId);
  }

  public boolean accountOptions(Client client) {
    this.clearTerminal();

    System.out.print(
        "\n1. Criar conta\n2. Ver saldo\n3. Depositar\n4. Sacar\n5. Ver limite\n6. Ver crédito\n7. Reajustar\n\nOutro número sairá da área do cliente...\n\nDigite um número: ");

    int input = this.scan.nextInt();
    this.scan.nextLine();

    switch (input) {
      case 1:
        this.createAccount(client);
        break;

      case 2:
        this.checkAmount(client);
        break;

      case 3:
        this.deposit(client);
        break;

      case 4:
        this.withdraw(client);
        break;

      case 5:
        this.checkLimit(client);
        break;

      case 6:
        this.checkCredit(client);
        break;

      case 7:
        this.readjust(client);
        break;

      default:
        return true;
    }

    return false;
  }

  public void createAccount(Client client) {
    this.clearTerminal();

    System.out.print(
        "1. Conta Especial\n2. Conta Poupança\n\nOutro número voltará ao menu...\n\nDigite um número: ");

    int input = this.scan.nextInt();

    this.clearTerminal();
    this.scan.nextLine();

    Account newAccount;

    switch (input) {
      case 1:
        newAccount = client.accounts.createSpecialAccount();
        break;

      case 2:
        newAccount = client.accounts.createSavingsAccount();
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

  public void checkAmount(Client client) {
    Account account = this.selectAccount(client, null);

    if (account == null)
      return;

    this.clearTerminal();

    System.out.println("Saldo: R$" + Double.toString(account.getAmount()));

    this.scan.nextLine();
    this.awaitEnter();
  }

  public void deposit(Client client) {
    Account account = this.selectAccount(client, null);

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

  public void withdraw(Client client) {
    Account account = this.selectAccount(client, null);

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

  public void readjust(Client client) {
    SavingsAccount account = (SavingsAccount) selectAccount(client, "Conta Poupança");

    if (account == null)
      return;

    this.clearTerminal();

    System.out.print("Taxa de reajuste (%): ");
    double rate = this.scan.nextDouble();

    if (rate <= 0) {
      this.clearTerminal();
      System.out.println("Taxa inválida! Voltando ao menu...");
      this.scan.nextLine();
      this.awaitEnter();

      return;
    }

    account.readjust(rate);

    this.clearTerminal();

    System.out.println("Reajuste efetuado!");
    System.out.println("Saldo atual: R$" + Double.toString(account.getAmount()));

    this.scan.nextLine();
    this.awaitEnter();
  }

  public void checkLimit(Client client) {
    SpecialAccount account = (SpecialAccount) selectAccount(client, "Conta Especial");

    if (account == null)
      return;

    this.clearTerminal();

    System.out.println("Limite: R$" + Double.toString(account.getLimit()));

    this.scan.nextLine();
    this.awaitEnter();
  }

  public void checkCredit(Client client) {
    SpecialAccount account = (SpecialAccount) selectAccount(client, "Conta Especial");

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
