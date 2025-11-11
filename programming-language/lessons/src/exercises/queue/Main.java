package exercises.queue;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Queue<String> passagers = new Queue<>();
    Scanner scan = new Scanner(System.in);

    while (true) {
      clearScreen();

      System.out
          .print("Aeroporto\n\n1. Chamar próximo passageiro\n2. Adicionar passageiro\n3. Sair\n\nEscolha uma opção: ");

      int option;

      try {
        option = scan.nextInt();
      } catch (NoSuchElementException e) {
        System.out.println("\nEntrada inválida. Tente novamente.");
        scan.nextLine();
        continue;
      }
      scan.nextLine();

      switch (option) {
        case 1:
          String passager = passagers.get();

          if (passager == null) {
            System.out.println("\nNenhum passageiro na fila.");
          } else {
            System.out.println("\nPassageiro " + passager + ", por favor dirija-se ao portão de embarque.");
          }

          scan.nextLine();
          break;

        case 2:
          System.out.print("\nNome do passageiro: ");
          String name = scan.nextLine();
          passagers.add(name);

          System.out.println("\nPassageiro " + name + " adicionado à fila.");

          System.out.flush();
          scan.nextLine();
          break;

        case 3:
          System.out.println("\nEncerrando o programa.");
          scan.close();
          return;

        default:
          System.out.println("\nOpção inválida.");
      }
    }
  }

  public static void clearScreen() {
    // \033[H move para (1,1), \033[2J limpa a tela
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
