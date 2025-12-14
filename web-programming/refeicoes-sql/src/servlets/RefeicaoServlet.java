package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Refeicao;
import repositorios.RepositorioRefeicaoSQL;

@WebServlet("/refeicoes")
public class RefeicaoServlet extends HttpServlet {

  // Formato de data esperado no JSON e Query Params
  private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  private RepositorioRefeicaoSQL getRepo() {
    // Cria uma nova instância (e conexão) para cada requisição para garantir
    // thread-safety
    // Em um ambiente real, seria ideal usar um Connection Pool (ex: HikariCP,
    // DataSource)
    return new RepositorioRefeicaoSQL("jdbc:mariadb://localhost:3306/refeicoes", "root", "");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    PrintWriter out = resp.getWriter();

    RepositorioRefeicaoSQL repo = getRepo();

    String id = req.getParameter("id");
    String dataStr = req.getParameter("data");

    try {
      if (id != null && dataStr != null) {
        // Buscar refeição específica por id e data
        LocalDate data = LocalDate.parse(dataStr, DTF);
        Refeicao r = repo.retrieve(id, data);
        if (r != null) {
          out.print(String.format("{\"idCartaoEstudante\": \"%s\", \"dataRefeicao\": \"%s\"}",
              r.getIdCartaoEstudante(), r.getDataRefeicao().format(DTF)));
        } else {
          resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
          out.print("{\"message\": \"Refeição não encontrada\"}");
        }
      } else if (id != null) {
        // Buscar todas as refeições de um estudante
        List<Refeicao> lista = repo.retrieveByStudentId(id);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < lista.size(); i++) {
          Refeicao r = lista.get(i);
          sb.append(String.format("{\"idCartaoEstudante\": \"%s\", \"dataRefeicao\": \"%s\"}",
              r.getIdCartaoEstudante(), r.getDataRefeicao().format(DTF)));
          if (i < lista.size() - 1) {
            sb.append(",");
          }
        }
        sb.append("]");
        out.print(sb.toString());
      } else if (dataStr != null) {
        // Buscar todas as refeições de uma data
        LocalDate data = LocalDate.parse(dataStr, DTF);
        List<Refeicao> lista = repo.retrieveByDate(data);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < lista.size(); i++) {
          Refeicao r = lista.get(i);
          sb.append(String.format("{\"idCartaoEstudante\": \"%s\", \"dataRefeicao\": \"%s\"}",
              r.getIdCartaoEstudante(), r.getDataRefeicao().format(DTF)));
          if (i < lista.size() - 1) {
            sb.append(",");
          }
        }
        sb.append("]");
        out.print(sb.toString());
      } else {
        // Listar todas as refeições
        List<Refeicao> lista = repo.retrieveAll();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < lista.size(); i++) {
          Refeicao r = lista.get(i);
          sb.append(String.format("{\"idCartaoEstudante\": \"%s\", \"dataRefeicao\": \"%s\"}",
              r.getIdCartaoEstudante(), r.getDataRefeicao().format(DTF)));
          if (i < lista.size() - 1) {
            sb.append(",");
          }
        }
        sb.append("]");
        out.print(sb.toString());
      }
    } catch (Exception e) {
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      out.print("{\"error\": \"" + e.getMessage() + "\"}");
    } finally {
      repo.closeConnection();
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    PrintWriter out = resp.getWriter();

    RepositorioRefeicaoSQL repo = getRepo();

    try {
      StringBuilder buffer = new StringBuilder();
      BufferedReader reader = req.getReader();
      String line;
      while ((line = reader.readLine()) != null) {
        buffer.append(line);
      }
      String body = buffer.toString();

      String id = null;
      String dataStr = null;

      String patternId = "\"idCartaoEstudante\"\\s*:\\s*\"([^\"]+)\"";
      java.util.regex.Pattern rId = java.util.regex.Pattern.compile(patternId);
      java.util.regex.Matcher mId = rId.matcher(body);
      if (mId.find()) {
        id = mId.group(1);
      }

      String patternData = "\"dataRefeicao\"\\s*:\\s*\"([^\"]+)\"";
      java.util.regex.Pattern rData = java.util.regex.Pattern.compile(patternData);
      java.util.regex.Matcher mData = rData.matcher(body);
      if (mData.find()) {
        dataStr = mData.group(1);
      }

      if (id == null || dataStr == null) {
        throw new IllegalArgumentException("JSON inválido. Campos esperados: idCartaoEstudante, dataRefeicao");
      }

      Refeicao r = new Refeicao(id, LocalDate.parse(dataStr, DTF));

      repo.create(r);

      resp.setStatus(HttpServletResponse.SC_CREATED);
      out.print("{\"message\": \"Refeição criada com sucesso\"}");
    } catch (IllegalArgumentException e) {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      out.print("{\"error\": \"" + e.getMessage() + "\"}");
    } catch (Exception e) {
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      out.print("{\"error\": \"" + e.getMessage() + "\"}");
    } finally {
      repo.closeConnection();
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    PrintWriter out = resp.getWriter();

    RepositorioRefeicaoSQL repo = getRepo();

    // Para atualizar, precisamos identificar a refeição original (via query params)
    // e os novos dados (via body)
    String idAntigo = req.getParameter("id");
    String dataAntigaStr = req.getParameter("data");

    if (idAntigo == null || dataAntigaStr == null) {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      out.print(
          "{\"error\": \"Parâmetros 'id' e 'data' são obrigatórios para identificar a refeição a ser atualizada.\"}");
      repo.closeConnection();
      return;
    }

    try {
      LocalDate dataAntiga = LocalDate.parse(dataAntigaStr, DTF);
      Refeicao refeicaoAntiga = new Refeicao(idAntigo, dataAntiga);

      StringBuilder buffer = new StringBuilder();
      BufferedReader reader = req.getReader();
      String line;
      while ((line = reader.readLine()) != null) {
        buffer.append(line);
      }
      String body = buffer.toString();

      String id = null;
      String dataStr = null;

      String patternId = "\"idCartaoEstudante\"\\s*:\\s*\"([^\"]+)\"";
      java.util.regex.Pattern rId = java.util.regex.Pattern.compile(patternId);
      java.util.regex.Matcher mId = rId.matcher(body);
      if (mId.find()) {
        id = mId.group(1);
      }

      String patternData = "\"dataRefeicao\"\\s*:\\s*\"([^\"]+)\"";
      java.util.regex.Pattern rData = java.util.regex.Pattern.compile(patternData);
      java.util.regex.Matcher mData = rData.matcher(body);
      if (mData.find()) {
        dataStr = mData.group(1);
      }

      if (id == null || dataStr == null) {
        throw new IllegalArgumentException("JSON inválido. Campos esperados: idCartaoEstudante, dataRefeicao");
      }

      Refeicao refeicaoNova = new Refeicao(id, LocalDate.parse(dataStr, DTF));

      repo.update(refeicaoAntiga, refeicaoNova);

      out.print("{\"message\": \"Refeição atualizada com sucesso\"}");
    } catch (IllegalArgumentException e) {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      out.print("{\"error\": \"" + e.getMessage() + "\"}");
    } catch (Exception e) {
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      out.print("{\"error\": \"" + e.getMessage() + "\"}");
    } finally {
      repo.closeConnection();
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    PrintWriter out = resp.getWriter();

    RepositorioRefeicaoSQL repo = getRepo();

    String id = req.getParameter("id");
    String dataStr = req.getParameter("data");

    if (id == null || dataStr == null) {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      out.print("{\"error\": \"Parâmetros 'id' e 'data' são obrigatórios\"}");
      repo.closeConnection();
      return;
    }

    try {
      LocalDate data = LocalDate.parse(dataStr, DTF);
      Refeicao r = new Refeicao(id, data);
      repo.delete(r.getIdCartaoEstudante(), r.getDataRefeicao());
      out.print("{\"message\": \"Refeição deletada com sucesso\"}");
    } catch (IllegalArgumentException e) {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      out.print("{\"error\": \"" + e.getMessage() + "\"}");
    } catch (Exception e) {
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      out.print("{\"error\": \"" + e.getMessage() + "\"}");
    } finally {
      repo.closeConnection();
    }
  }
}