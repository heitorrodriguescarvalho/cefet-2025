package exercises.todo_list;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Vetor<String> todoList = new Vetor<>();

    Scanner scan = new Scanner(System.in);

    while (true) {
      System.out.print("\n\n\n");
      System.out.print(
          "\tOpções\n\n1. Adicionar tarefa\n2. Listar tarefas\n3. Excluir Tarefa\n4. Editar Tarefa\n5. Verificar Tarefa\n6. Remover Última Tarefa\n7. Remover Primeira Tarefa\n8. Sair\n\nEscolha uma opção: ");
      String option = scan.nextLine();

      if (option.equals("1")) {
        System.out.print("\n\n\nDigite a tarefa: ");
        String task = scan.nextLine();

        todoList.adicionar(task);
      } else if (option.equals("2")) {
        System.out.println("\n\n\n\tLista de tarefas:\n");

        for (int i = 0; i < todoList.getTamanho(); i++) {
          System.out.println("- " + todoList.obter(i));
        }
      } else if (option.equals("3")) {
        System.out.print("\n\n\nDigite a tarefa a ser removida: ");
        String taskToRemove = scan.nextLine();

        int index = -1;

        for (int i = 0; i < todoList.getTamanho(); i++) {
          if (taskToRemove.equals(todoList.obter(i))) {
            index = i;
            break;
          }
        }

        if (index != -1) {
          todoList.remover(index);
        } else {
          System.out.println("Tarefa não encontrada.");
        }
      } else if (option.equals("4")) {
        System.out.print("\n\n\nDigite o nome da tarefa a editar: ");
        String taskToEdit = scan.nextLine();

        int index = -1;

        for (int i = 0; i < todoList.getTamanho(); i++) {
          if (taskToEdit.equals(todoList.obter(i))) {
            index = i;
            break;
          }
        }

        if (index != -1) {
          System.out.print("Digite o novo nome da tarefa: ");
          String novaTarefa = scan.nextLine();
          todoList.definirPosicao(index, novaTarefa);
          System.out.println("Tarefa editada com sucesso!");
        } else {
          System.out.println("Tarefa não encontrada.");
        }
      } else if (option.equals("5")) {
        System.out.print("\n\n\nDigite a tarefa a verificar: ");
        String taskToCheck = scan.nextLine();

        if (todoList.contem(taskToCheck)) {
          System.out.println("A tarefa existe na lista.");
        } else {
          System.out.println("A tarefa não existe na lista.");
        }
      } else if (option.equals("6")) {
        try {
          String removedTask = todoList.removerUltimo();
          System.out.println("\n\n\nÚltima tarefa removida: " + removedTask);
        } catch (IndexOutOfBoundsException e) {
          System.out.println("Erro: " + e.getMessage());
        }
      } else if (option.equals("7")) {
        try {
          String removedTask = todoList.removerPrimeiro();
          System.out.println("\n\n\nPrimeira tarefa removida: " + removedTask);
        } catch (IndexOutOfBoundsException e) {
          System.out.println("Erro: " + e.getMessage());
        }
      } else if (option.equals("8")) {
        System.out.println("Saindo...");

        break;
      } else {
        System.out.println("Opção inválida. Tente novamente.");
      }
    }

    scan.close();
  }
}
