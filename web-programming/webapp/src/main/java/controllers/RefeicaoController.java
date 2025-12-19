package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Refeicao;
import repositories.RefeicaoRepo;
import repositories.RefeicaoRepoSQL;

public class RefeicaoController extends HttpServlet {
  private RefeicaoRepo repo;

  @Override
  public void init() throws ServletException {
    // Configuração hardcoded para MariaDB local
    // Não é a melhor prática para produção, pois, expõe credenciais
    // credenciais no código fonte, mas, é suficiente para nosso exemplo
    // didático.
    String dbUrl = "jdbc:mariadb://localhost:3306/refeicoes";
    String dbUser = "root"; // trocar conforme seu setup
    String dbPass = "secret"; // trocar conforme seu setup
    this.repo = new RefeicaoRepoSQL(dbUrl, dbUser, dbPass);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String idCartaoEstudante = request.getParameter("idCartaoEstudante");
    String dataRefeicaoStr = request.getParameter("dataRefeicao");

    if (idCartaoEstudante == null || dataRefeicaoStr == null) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST,
          "Parâmetros idCartaoEstudante e dataRefeicao são obrigatórios.");
      return;
    }

    try {
      LocalDate dataRefeicao = LocalDate.parse(dataRefeicaoStr);
      Refeicao novaRefeicao = new Refeicao(idCartaoEstudante, dataRefeicao);
      repo.create(novaRefeicao);
      response.setStatus(HttpServletResponse.SC_CREATED);
      response.getWriter().write("Refeição adicionada com sucesso.");
    } catch (Exception e) {
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao adicionar refeição: " + e.getMessage());
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String idCartaoEstudante = request.getParameter("idCartaoEstudante");
    String dataRefeicaoStr = request.getParameter("dataRefeicao");

    response.setContentType("application/json");
    List<Refeicao> refeicoes = new ArrayList<>();

    try {
      if (idCartaoEstudante == null && dataRefeicaoStr == null) {
        // Caso 1: Nenhum parâmetro fornecido
        refeicoes = repo.retrieveAll();
      } else if (idCartaoEstudante != null && dataRefeicaoStr == null) {
        // Caso 2: Apenas idCartaoEstudante fornecido
        refeicoes = repo.retrieveByCartao(idCartaoEstudante);
      } else if (idCartaoEstudante == null && dataRefeicaoStr != null) {
        // Caso 3: Apenas dataRefeicao fornecido
        LocalDate dataRefeicao = LocalDate.parse(dataRefeicaoStr);
        refeicoes = repo.retrieveByData(dataRefeicao);
      } else {
        // Caso 4: Ambos os parâmetros fornecidos
        LocalDate dataRefeicao = LocalDate.parse(dataRefeicaoStr);
        Refeicao refeicao = repo.retrieve(idCartaoEstudante, dataRefeicao);
        if (refeicao != null) {
          refeicoes.add(refeicao);
        }
      }

      // Converter a lista de refeições para JSON, usando StringBuilder json
      response.setContentType("application/json");
      // I want to show status as success only if there are refeicoes found
      if (refeicoes.isEmpty()) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.getWriter().write("{\"status\":\"not found\",\"refeicoes\":[]}");
      } else {
        response.setStatus(HttpServletResponse.SC_OK);
        StringBuilder json = new StringBuilder();
        json.append("{\"status\":\"success\",\"refeicoes\":[");
        for (int i = 0; i < refeicoes.size(); i++) {
          Refeicao r = refeicoes.get(i);
          json.append("{");
          json.append("\"idCartaoEstudante\":\"").append(r.getIdCartaoEstudante()).append("\",");
          json.append("\"dataRefeicao\":\"").append(r.getDataRefeicao().toString()).append("\"");
          json.append("}");
          if (i < refeicoes.size() - 1) {
            json.append(",");
          }
        }
        json.append("]}");
        response.getWriter().write(json.toString());
      }
      response.getWriter().flush();
      return;
    } catch (Exception e) {
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
          "Erro ao recuperar refeições: " + e.getMessage());
    }
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String oldIdCartaoEstudante = request.getParameter("oldIdCartaoEstudante");
    String oldDataRefeicaoStr = request.getParameter("oldDataRefeicao");
    String newIdCartaoEstudante = request.getParameter("newIdCartaoEstudante");
    String newDataRefeicaoStr = request.getParameter("newDataRefeicao");

    if (oldIdCartaoEstudante == null || oldDataRefeicaoStr == null ||
        newIdCartaoEstudante == null || newDataRefeicaoStr == null) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST,
          "Parâmetros oldIdCartaoEstudante, oldDataRefeicao, newIdCartaoEstudante e newDataRefeicao são obrigatórios.");
      return;
    }

    try {
      LocalDate oldDataRefeicao = LocalDate.parse(oldDataRefeicaoStr);
      LocalDate newDataRefeicao = LocalDate.parse(newDataRefeicaoStr);

      Refeicao oldRefeicao = new Refeicao(oldIdCartaoEstudante, oldDataRefeicao);
      Refeicao newRefeicao = new Refeicao(newIdCartaoEstudante, newDataRefeicao);

      repo.update(oldRefeicao, newRefeicao);
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().write("Refeição atualizada com sucesso.");
    } catch (Exception e) {
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar refeição: " + e.getMessage());
    }
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String idCartaoEstudante = request.getParameter("idCartaoEstudante");
    String dataRefeicaoStr = request.getParameter("dataRefeicao");

    if (idCartaoEstudante == null || dataRefeicaoStr == null) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST,
          "Parâmetros idCartaoEstudante e dataRefeicao são obrigatórios.");
      return;
    }

    try {
      LocalDate dataRefeicao = LocalDate.parse(dataRefeicaoStr);
      Refeicao refeicaoToDelete = new Refeicao(idCartaoEstudante, dataRefeicao);
      repo.delete(refeicaoToDelete);
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().write("Refeição deletada com sucesso se ela existia no banco de dados.");
    } catch (Exception e) {
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao deletar refeição: " + e.getMessage());
    }
  }
}