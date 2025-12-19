package repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

import models.Refeicao;

public class RefeicaoRepoSQL implements RefeicaoRepo {

  private Connection con; // mantida enquanto o repositório estiver em usoprivate Connection con; //
                          // mantida enquanto o repositório estiver em uso
  private final String url;
  private final String user;
  private final String pass;

  public RefeicaoRepoSQL() { // mariadb hardcoded
    this.url = "jdbc:mariadb://localhost:3306/refeicoes";
    this.user = "aluno";
    this.pass = "123456";
    this.openConnection();
  }

  public RefeicaoRepoSQL(String url) { // sqlite
    this.url = url;
    this.user = null;
    this.pass = null;
    this.openConnection();
  }

  // generico (mariadb, postgres, sqlserver), exceto sqlite
  public RefeicaoRepoSQL(String url, String user, String pass) {
    this.url = url;
    this.user = user;
    this.pass = pass;
    this.openConnection();
  }

  private void openConnection() {
    // Força registro do driver no DriverManager (muito útil em webapp/Tomcat)
    try {
      if (url.startsWith("jdbc:mariadb:")) {
        Class.forName("org.mariadb.jdbc.Driver");
      } else if (url.startsWith("jdbc:postgresql:")) {
        Class.forName("org.postgresql.Driver");
      } else if (url.startsWith("jdbc:sqlserver:")) {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      } else if (url.startsWith("jdbc:sqlite:")) {
        Class.forName("org.sqlite.JDBC");
      } else {
        throw new RuntimeException("URL JDBC não suportada: " + url);
      }
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Driver JDBC não encontrado para a URL: " + url, e);
    }
    if (this.con != null)
      return; // já aberta
    try {
      if (user == null && pass == null) {
        this.con = DriverManager.getConnection(url); // ex.: SQLite
      } else {
        this.con = DriverManager.getConnection(url, user, pass);
      }
      // this.con.setAutoCommit(true); // default
    } catch (SQLException e) {
      throw new RuntimeException("Falha ao abrir conexão JDBC", e);
    }
  }

  private void closeConnection() {
    if (this.con != null) {
      try {
        if (!this.con.isClosed())
          this.con.close();
      } catch (SQLException ignored) {
      } finally {
        this.con = null;
      }
    }
  }

  // Método auxiliar para fechar recursos (para Update e Delete)
  private void closePreparedStatement(PreparedStatement ps) {
    if (ps != null) {
      try {
        ps.close();
      } catch (SQLException ignored) {
      }
    }
  }

  @Override
  public void finalize() {
    // 1. encerrar a conexao
    this.closeConnection();
  }

  @Override
  public void create(Refeicao r) {
    PreparedStatement ps = null;
    try {
      String sql = "INSERT INTO refeicao (id_cartao, data_refeicao) values (?, ?)";
      ps = con.prepareStatement(sql);
      ps.setString(1, r.getIdCartaoEstudante());
      ps.setDate(2, Date.valueOf(r.getDataRefeicao()));
      ps.executeUpdate();
    } catch (SQLIntegrityConstraintViolationException dup) {
      throw new IllegalArgumentException("Refeição já registrada para este estudante nesta data.", dup);
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao inserir refeição", e);
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException ignored) {
        }
      }
    }
  }

  @Override
  public void delete(Refeicao refeicao) {
    PreparedStatement ps = null;
    try {
      // A interface pede para remover TODAS as refeições com este ID
      String sql = "DELETE FROM refeicao WHERE id_cartao = ? AND data_refeicao = ?";
      ps = con.prepareStatement(sql);
      ps.setString(1, refeicao.getIdCartaoEstudante());
      ps.setDate(2, Date.valueOf(refeicao.getDataRefeicao()));
      int rowsAffected = ps.executeUpdate();
      if (rowsAffected == 0) {
        // Pode ser tratado como sucesso ou aviso, dependendo da regra de negócio.
        // Aqui, vamos apenas registrar que nenhuma refeição foi encontrada para
        // deleção.
        System.out.println("Aviso: Nenhuma refeição encontrada para o ID " + refeicao.getIdCartaoEstudante() + ".");
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao deletar refeições por ID", e);
    } finally {
      closePreparedStatement(ps);
    }
  }

  @Override
  public List<Refeicao> retrieveAll() {
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Refeicao> out = new java.util.ArrayList<>();
    try {
      String sql = "SELECT id_cartao, data_refeicao FROM refeicao ORDER BY data_refeicao DESC, id_cartao";
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        out.add(new Refeicao(
            rs.getString("id_cartao"),
            rs.getDate("data_refeicao").toLocalDate()));
      }
      return out;
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao listar refeições", e);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException ignored) {
        }
      }
      closePreparedStatement(ps);
    }
  }

  @Override
  public void update(Refeicao oldRefeicao, Refeicao newRefeicao) {
    PreparedStatement ps = null;
    try {
      // Atualiza o ID e a Data da refeição. A chave composta anterior é a
      // "oldRefeicao".
      String sql = "UPDATE refeicao SET id_cartao = ?, data_refeicao = ? WHERE id_cartao = ? AND data_refeicao = ?";
      ps = con.prepareStatement(sql);

      // Novos valores
      ps.setString(1, newRefeicao.getIdCartaoEstudante());
      ps.setDate(2, Date.valueOf(newRefeicao.getDataRefeicao()));

      // Chave da refeição a ser atualizada (Old Refeicao)
      ps.setString(3, oldRefeicao.getIdCartaoEstudante());
      ps.setDate(4, Date.valueOf(oldRefeicao.getDataRefeicao()));

      int rowsAffected = ps.executeUpdate();

      if (rowsAffected == 0) {
        throw new IllegalArgumentException("Refeição a ser atualizada não encontrada (ID ou Data incorretos).");
      }
      // Se tentar mudar a chave para uma já existente, o BD lança
      // SQLIntegrityConstraintViolationException

    } catch (SQLIntegrityConstraintViolationException dup) {
      throw new IllegalArgumentException("Nova refeição já existe (duplicidade de chave).", dup);
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao atualizar refeição", e);
    } finally {
      closePreparedStatement(ps);
    }
  }

  @Override
  public Refeicao retrieve(String idCartaoEstudante, LocalDate data) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      String sql = "SELECT id_cartao, data_refeicao FROM refeicao WHERE id_cartao = ? AND data_refeicao = ?";
      ps = con.prepareStatement(sql);
      ps.setString(1, idCartaoEstudante);
      ps.setDate(2, Date.valueOf(data));
      rs = ps.executeQuery();
      if (rs.next()) {
        return new Refeicao(
            rs.getString("id_cartao"),
            rs.getDate("data_refeicao").toLocalDate());
      }
      return null;
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao recuperar refeição", e);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException ignored) {
        }
      }
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException ignored) {
        }
      }
    }
  }

  @Override
  public List<Refeicao> retrieveByCartao(String cartao) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Refeicao> out = new java.util.ArrayList<>();
    try {
      String sql = "SELECT id_cartao, data_refeicao FROM refeicao WHERE id_cartao = ? ORDER BY data_refeicao DESC";
      ps = con.prepareStatement(sql);
      ps.setString(1, cartao);
      rs = ps.executeQuery();
      while (rs.next()) {
        out.add(new Refeicao(
            rs.getString("id_cartao"),
            rs.getDate("data_refeicao").toLocalDate()));
      }
      return out;
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao recuperar refeições por cartão", e);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException ignored) {
        }
      }
      closePreparedStatement(ps);
    }
  }

  @Override
  public List<Refeicao> retrieveByData(LocalDate data) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Refeicao> out = new java.util.ArrayList<>();
    try {
      String sql = "SELECT id_cartao, data_refeicao FROM refeicao WHERE data_refeicao = ? ORDER BY id_cartao";
      ps = con.prepareStatement(sql);
      ps.setDate(1, Date.valueOf(data));
      rs = ps.executeQuery();
      while (rs.next()) {
        out.add(new Refeicao(
            rs.getString("id_cartao"),
            rs.getDate("data_refeicao").toLocalDate()));
      }
      return out;
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao recuperar refeições por data", e);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException ignored) {
        }
      }
      closePreparedStatement(ps);
    }
  }

}