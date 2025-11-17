package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import modelos.Refeicao;
import repositorios.RepositorioRefeicao;
import repositorios.RepositorioRefeicaoSQL;

public class Main {
  private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public static void main(String[] args) {
    RepositorioRefeicao repo = new RepositorioRefeicaoSQL("jdbc:mariadb://localhost:3306/refeicoes", "root", "");
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("\n=== Controle de Refeições - Restaurante Estudantil ===");
      System.out.println("1. Registrar refeição");
      System.out.println("2. Consultar refeição(s)");
      System.out.println("3. Modificar refeição");
      System.out.println("4. Excluir refeição(s)");
      System.out.println("0. Sair");
      System.out.print("Escolha uma opção: ");

      String opcao = scanner.nextLine().trim();
      switch (opcao) {
        case "1":
          registrar(scanner, repo);
          break;
        case "2":
          consultar(scanner, repo);
          break;
        case "3":
          modificar(scanner, repo);
          break;
        case "4":
          excluir(scanner, repo);
          break;
        case "0":
          System.out.println("Saindo... até logo.");
          scanner.close();
          return;
        default:
          System.out.println("Opção inválida. Tente novamente.");
      }
    }
  }

  private static void registrar(Scanner scanner, RepositorioRefeicao repo) {
    try {
      System.out.print("ID do cartão do estudante: ");
      String id = scanner.nextLine().trim();
      System.out.print("Data da refeição (dd/MM/yyyy): ");
      String dataStr = scanner.nextLine().trim();
      LocalDate data = parseData(dataStr);
      Refeicao r = new Refeicao(id, data);
      repo.create(r);
      System.out.println("Refeição registrada com sucesso.");
    } catch (DateTimeParseException e) {
      System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
    } catch (IllegalArgumentException e) {
      System.out.println("Erro: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("Erro inesperado: " + e.getMessage());
    }
  }

  private static void consultar(Scanner scanner, RepositorioRefeicao repo) {
    System.out.println("1. Listar todas as refeições");
    System.out.println("2. Buscar por ID do cartão do estudante (retorna a primeira encontrada)");
    System.out.print("Escolha: ");
    String sub = scanner.nextLine().trim();
    if ("1".equals(sub)) {
      List<Refeicao> todas = repo.retrieveAll();
      if (todas.isEmpty()) {
        System.out.println("Nenhuma refeição registrada.");
      } else {
        System.out.println("Refeições registradas:");
        for (int i = 0; i < todas.size(); i++) {
          System.out.println("[" + i + "] " + todas.get(i));
        }
      }
    } else if ("2".equals(sub)) {
      System.out.print("ID do cartão do estudante: ");
      String id = scanner.nextLine().trim();
      System.out.print("Data da refeição (dd/MM/yyyy): ");
      String dataStr = scanner.nextLine().trim();
      LocalDate data = parseData(dataStr);
      Refeicao r = repo.retrieve(id, data);
      if (r == null) {
        System.out.println("Nenhuma refeição encontrada para o ID informado.");
      } else {
        System.out.println("Encontrado: " + r);
      }
    } else {
      System.out.println("Opção inválida.");
    }
  }

  private static void modificar(Scanner scanner, RepositorioRefeicao repo) {
    List<Refeicao> todas = repo.retrieveAll();
    if (todas.isEmpty()) {
      System.out.println("Nenhuma refeição registrada.");
      return;
    }
    System.out.println("Refeições registradas:");
    for (int i = 0; i < todas.size(); i++) {
      System.out.println("[" + i + "] " + todas.get(i));
    }
    System.out.print("Informe o índice da refeição a modificar: ");
    String idxStr = scanner.nextLine().trim();
    int idx;
    try {
      idx = Integer.parseInt(idxStr);
    } catch (NumberFormatException e) {
      System.out.println("Índice inválido.");
      return;
    }
    if (idx < 0 || idx >= todas.size()) {
      System.out.println("Índice fora do intervalo.");
      return;
    }
    Refeicao antiga = todas.get(idx);
    System.out.println("Refeição selecionada: " + antiga);
    try {
      System.out.print("Novo ID do cartão (enter para manter): ");
      String novoId = scanner.nextLine().trim();
      if (novoId.isEmpty())
        novoId = antiga.getIdCartaoEstudante();

      System.out.print("Nova data (dd/MM/yyyy) (enter para manter): ");
      String novaDataStr = scanner.nextLine().trim();
      LocalDate novaData = antiga.getDataRefeicao();
      if (!novaDataStr.isEmpty()) {
        novaData = parseData(novaDataStr);
      }

      Refeicao nova = new Refeicao(novoId, novaData);
      repo.update(antiga, nova);
      System.out.println("Refeição atualizada com sucesso.");
    } catch (DateTimeParseException e) {
      System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
    } catch (IllegalArgumentException e) {
      System.out.println("Erro ao atualizar: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("Erro inesperado: " + e.getMessage());
    }
  }

  private static void excluir(Scanner scanner, RepositorioRefeicao repo) {
    System.out.println("Excluir por:");
    System.out.println("1. ID do cartão (remove todas as refeições deste estudante)");
    System.out.println("2. Índice específico (remover apenas uma ocorrência)");
    System.out.print("Escolha: ");
    String sub = scanner.nextLine().trim();
    if ("1".equals(sub)) {
      System.out.print("Informe o ID do cartão do estudante: ");
      String id = scanner.nextLine().trim();
      List<Refeicao> antes = repo.retrieveAll();
      long countAntes = antes.stream().filter(r -> r.getIdCartaoEstudante().equals(id)).count();
      if (countAntes == 0) {
        System.out.println("Nenhuma refeição encontrada para este ID.");
        return;
      }
      System.out.print("Data da refeição (dd/MM/yyyy): ");
      String dataStr = scanner.nextLine().trim();
      LocalDate data = parseData(dataStr);
      repo.delete(id, data);
      System.out.println("Excluídas " + countAntes + " refeição(ões) para o ID " + id + ".");
    } else if ("2".equals(sub)) {
      List<Refeicao> todas = repo.retrieveAll();
      if (todas.isEmpty()) {
        System.out.println("Nenhuma refeição registrada.");
        return;
      }
      for (int i = 0; i < todas.size(); i++) {
        System.out.println("[" + i + "] " + todas.get(i));
      }
      System.out.print("Informe o índice a remover: ");
      String idxStr = scanner.nextLine().trim();
      int idx;
      try {
        idx = Integer.parseInt(idxStr);
      } catch (NumberFormatException e) {
        System.out.println("Índice inválido.");
        return;
      }
      if (idx < 0 || idx >= todas.size()) {
        System.out.println("Índice fora do intervalo.");
        return;
      }
      Refeicao alvo = todas.get(idx);
      repo.delete(alvo.getIdCartaoEstudante(), alvo.getDataRefeicao());
      System.out.println("Refeição removida: " + alvo);
    } else {
      System.out.println("Opção inválida.");
    }
  }

  private static LocalDate parseData(String str) {
    return LocalDate.parse(str, DTF);
  }
}
