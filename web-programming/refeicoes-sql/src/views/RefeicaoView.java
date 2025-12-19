package views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import modelos.Refeicao;

public class RefeicaoView {
  private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final String CLEAR_SCREEN = "\033[H\033[2J";
  private final Scanner scanner;

  public RefeicaoView() {
    this.scanner = new Scanner(System.in);
  }

  public void limparTela() {
    System.out.print(CLEAR_SCREEN);
    System.out.flush();
  }

  public int exibirMenuPrincipal() {
    limparTela();
    System.out.println("=== Controle de Refeições - Restaurante Estudantil ===");
    System.out.println("1. Registrar refeição");
    System.out.println("2. Consultar refeição(s)");
    System.out.println("3. Modificar refeição");
    System.out.println("4. Excluir refeição(s)");
    System.out.println("0. Sair");
    System.out.print("Escolha uma opção: ");

    String opcao = scanner.nextLine().trim();
    try {
      return Integer.parseInt(opcao);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  public int exibirMenuConsulta() {
    System.out.println("1. Listar todas as refeições");
    System.out.println("2. Buscar por ID do cartão do estudante");
    System.out.print("Escolha: ");

    String opcao = scanner.nextLine().trim();
    try {
      return Integer.parseInt(opcao);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  public int exibirMenuExclusao() {
    System.out.println("Excluir por:");
    System.out.println("1. ID do cartão e data");
    System.out.println("2. Índice específico (remover apenas uma ocorrência)");
    System.out.print("Escolha: ");

    String opcao = scanner.nextLine().trim();
    try {
      return Integer.parseInt(opcao);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  public String solicitarIdCartao() {
    System.out.print("ID do cartão do estudante: ");
    return scanner.nextLine().trim();
  }

  public String solicitarNovoIdCartao(String idAtual) {
    System.out.print("Novo ID do cartão (enter para manter '" + idAtual + "'): ");
    String novoId = scanner.nextLine().trim();
    return novoId.isEmpty() ? idAtual : novoId;
  }

  public LocalDate solicitarData() throws DateTimeParseException {
    System.out.print("Data da refeição (dd/MM/yyyy): ");
    String dataStr = scanner.nextLine().trim();
    return parseData(dataStr);
  }

  public LocalDate solicitarNovaData(LocalDate dataAtual) throws DateTimeParseException {
    System.out.print("Nova data (dd/MM/yyyy) (enter para manter '" + formatarData(dataAtual) + "'): ");
    String dataStr = scanner.nextLine().trim();
    return dataStr.isEmpty() ? dataAtual : parseData(dataStr);
  }

  public int solicitarIndice(String mensagem) {
    System.out.print(mensagem);
    String idxStr = scanner.nextLine().trim();
    try {
      return Integer.parseInt(idxStr);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  public void exibirListaRefeicoes(List<Refeicao> refeicoes, boolean aguardar) {
    if (refeicoes.isEmpty()) {
      exibirMensagem("Nenhuma refeição registrada.");
    } else {
      System.out.println("Refeições registradas:");
      for (int i = 0; i < refeicoes.size(); i++) {
        System.out.println("[" + i + "] " + formatarRefeicao(refeicoes.get(i)));
      }
    }
    if (aguardar) {
      aguardarEnter();
    }
  }

  public void exibirRefeicao(Refeicao refeicao, boolean aguardar) {
    if (refeicao == null) {
      exibirMensagem("Nenhuma refeição encontrada.");
    } else {
      System.out.println("Encontrado: " + formatarRefeicao(refeicao));
    }
    if (aguardar) {
      aguardarEnter();
    }
  }

  public void exibirRefeicaoSelecionada(Refeicao refeicao) {
    System.out.println("Refeição selecionada: " + formatarRefeicao(refeicao));
  }

  public void exibirMensagem(String mensagem) {
    System.out.println(mensagem);
  }

  public void exibirSucesso(String mensagem) {
    System.out.println("✓ " + mensagem);
    aguardarEnter();
  }

  public void exibirErro(String mensagem) {
    System.out.println("✗ Erro: " + mensagem);
    aguardarEnter();
  }

  public void exibirDespedida() {
    System.out.println("Saindo... até logo.");
  }

  public void aguardarEnter() {
    System.out.print("\nPressione Enter para continuar...");
    scanner.nextLine();
  }

  public void fechar() {
    scanner.close();
  }

  private LocalDate parseData(String str) throws DateTimeParseException {
    return LocalDate.parse(str, DTF);
  }

  private String formatarData(LocalDate data) {
    return data.format(DTF);
  }

  private String formatarRefeicao(Refeicao r) {
    return "Cartão: " + r.getIdCartaoEstudante() + " | Data: " + formatarData(r.getDataRefeicao());
  }
}
