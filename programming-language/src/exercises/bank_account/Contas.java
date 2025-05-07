package exercises.bank_account;

import java.util.ArrayList;
import java.util.Scanner;

public class Contas {
  private ArrayList<Conta> accounts = new ArrayList<>();
  private int idCount = 0;

  public void createAccount(String name, double initial_amount) {
    Conta account = new Conta(name, idCount, initial_amount);

    accounts.add(account);

    this.idCount++;
  }

  public int selectAccount() {
    Scanner scan = new Scanner(System.in);

    for (int i = 0; i < this.accounts.size(); i++) {
      System.out.println(Integer.toString(this.accounts.get(i).getId()) + ". " + this.accounts.get(i).getName());
    }

    System.out.println("\nSelecione uma conta: ");
    int accountId = scan.nextInt();

    if (accountId < 0 || accountId >= this.accounts.size())
      return -1;

    return accountId;
  }

  Conta getAccount(int id) {
    return this.accounts.get(id);
  }
}
