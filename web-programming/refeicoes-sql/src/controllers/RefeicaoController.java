package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import modelos.Refeicao;
import repositorios.RepositorioRefeicao;
import views.RefeicaoView;

public class RefeicaoController {
  private final RepositorioRefeicao repositorio;
  private final RefeicaoView view;
  private boolean executando;

  public RefeicaoController(RepositorioRefeicao repositorio, RefeicaoView view) {
    this.repositorio = repositorio;
    this.view = view;
    this.executando = true;
  }

  public void executar() {
    while (executando) {
      int opcao = view.exibirMenuPrincipal();
      processarOpcao(opcao);
    }
    view.fechar();
  }

  private void processarOpcao(int opcao) {
    switch (opcao) {
      case 1:
        registrar();
        break;
      case 2:
        consultar();
        break;
      case 3:
        modificar();
        break;
      case 4:
        excluir();
        break;
      case 0:
        sair();
        break;
      default:
        view.exibirErro("Opção inválida. Tente novamente.");
    }
  }

  private void registrar() {
    try {
      String id = view.solicitarIdCartao();
      LocalDate data = view.solicitarData();

      Refeicao refeicao = new Refeicao(id, data);
      repositorio.create(refeicao);

      view.exibirSucesso("Refeição registrada com sucesso.");
    } catch (DateTimeParseException e) {
      view.exibirErro("Formato de data inválido. Use dd/MM/yyyy.");
    } catch (IllegalArgumentException e) {
      view.exibirErro(e.getMessage());
    } catch (Exception e) {
      view.exibirErro("Erro inesperado: " + e.getMessage());
    }
  }

  private void consultar() {
    int subOpcao = view.exibirMenuConsulta();

    switch (subOpcao) {
      case 1:
        listarTodas();
        break;
      case 2:
        buscarPorIdEData();
        break;
      default:
        view.exibirErro("Opção inválida.");
    }
  }

  private void listarTodas() {
    List<Refeicao> todas = repositorio.retrieveAll();
    view.exibirListaRefeicoes(todas, true);
  }

  private void buscarPorIdEData() {
    try {
      String id = view.solicitarIdCartao();
      LocalDate data = view.solicitarData();

      Refeicao refeicao = repositorio.retrieve(id, data);
      view.exibirRefeicao(refeicao, true);
    } catch (DateTimeParseException e) {
      view.exibirErro("Formato de data inválido. Use dd/MM/yyyy.");
    }
  }

  private void modificar() {
    List<Refeicao> todas = repositorio.retrieveAll();

    if (todas.isEmpty()) {
      view.exibirMensagem("Nenhuma refeição registrada.");
      view.aguardarEnter();
      return;
    }

    view.exibirListaRefeicoes(todas, false);

    int idx = view.solicitarIndice("Informe o índice da refeição a modificar: ");

    if (idx < 0 || idx >= todas.size()) {
      view.exibirErro("Índice inválido ou fora do intervalo.");
      return;
    }

    Refeicao antiga = todas.get(idx);
    view.exibirRefeicaoSelecionada(antiga);

    try {
      String novoId = view.solicitarNovoIdCartao(antiga.getIdCartaoEstudante());
      LocalDate novaData = view.solicitarNovaData(antiga.getDataRefeicao());

      Refeicao nova = new Refeicao(novoId, novaData);
      repositorio.update(antiga, nova);

      view.exibirSucesso("Refeição atualizada com sucesso.");
    } catch (DateTimeParseException e) {
      view.exibirErro("Formato de data inválido. Use dd/MM/yyyy.");
    } catch (IllegalArgumentException e) {
      view.exibirErro("Erro ao atualizar: " + e.getMessage());
    } catch (Exception e) {
      view.exibirErro("Erro inesperado: " + e.getMessage());
    }
  }

  private void excluir() {
    int subOpcao = view.exibirMenuExclusao();

    switch (subOpcao) {
      case 1:
        excluirPorIdEData();
        break;
      case 2:
        excluirPorIndice();
        break;
      default:
        view.exibirErro("Opção inválida.");
    }
  }

  private void excluirPorIdEData() {
    try {
      String id = view.solicitarIdCartao();
      LocalDate data = view.solicitarData();

      Refeicao refeicao = repositorio.retrieve(id, data);
      if (refeicao == null) {
        view.exibirMensagem("Nenhuma refeição encontrada para este ID e data.");
        view.aguardarEnter();
        return;
      }

      repositorio.delete(id, data);
      view.exibirSucesso("Refeição excluída com sucesso.");
    } catch (DateTimeParseException e) {
      view.exibirErro("Formato de data inválido. Use dd/MM/yyyy.");
    }
  }

  private void excluirPorIndice() {
    List<Refeicao> todas = repositorio.retrieveAll();

    if (todas.isEmpty()) {
      view.exibirMensagem("Nenhuma refeição registrada.");
      view.aguardarEnter();
      return;
    }

    view.exibirListaRefeicoes(todas, false);

    int idx = view.solicitarIndice("Informe o índice a remover: ");

    if (idx < 0 || idx >= todas.size()) {
      view.exibirErro("Índice inválido ou fora do intervalo.");
      return;
    }

    Refeicao alvo = todas.get(idx);
    repositorio.delete(alvo.getIdCartaoEstudante(), alvo.getDataRefeicao());

    view.exibirSucesso("Refeição removida: " + alvo);
  }

  private void sair() {
    view.exibirDespedida();
    executando = false;
  }
}
