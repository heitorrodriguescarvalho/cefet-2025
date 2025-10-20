package exercises.bank_account;

public class Client {
  private int id;
  private String name;
  private String cpf;
  public AccountsManager accounts;
  private static int idCount = 0;

  public Client(String name, String cpf) {
    this.name = name;
    this.cpf = cpf;
    this.accounts = new AccountsManager(this);
    this.id = idCount++;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCpf() {
    return cpf;
  }
}
