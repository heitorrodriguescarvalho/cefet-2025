package exercises.bank_account;

public class Main {
  public static void main(String[] args) {
    Menu menu = new Menu();

    boolean stop = false;

    while (!stop)
      stop = menu.showMenu();

    System.out.println("Finalizando o programa...");
  }
}
