package repositorios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelos.Refeicao;

public class RepositorioRefeicaoSQL implements RepositorioRefeicao {
  private final String url;
  private final String user;
  private final String pass;
  private Connection con; // mantida enquanto o repositório estiver em uso

  // Construtor chama o processo explícito de abertura
  public RepositorioRefeicaoSQL(String url, String user, String pass) {
    this.url = url;
    this.user = user;
    this.pass = pass;
    openConnection(); // padrão do projeto
  }

  // 6.1 Iniciar conexão (explícito)
  public final void openConnection() {
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

  // 6.2 Fechar conexão (explícito) — liberar recursos
  public final void closeConnection() {
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

  @Override
  public void create(Refeicao r) {
    PreparedStatement ps = null;
    try {
      String sql = "INSERT INTO refeicao (id_cartao, data_refeicao) VALUES (?, ?)";
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
  public Refeicao retrieve(String idCartao, LocalDate data) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      String sql = "SELECT id_cartao, data_refeicao FROM refeicao WHERE id_cartao=? AND data_refeicao=?";
      ps = con.prepareStatement(sql);
      ps.setString(1, idCartao);
      ps.setDate(2, java.sql.Date.valueOf(data));
      rs = ps.executeQuery();
      if (rs.next()) {
        return new Refeicao(
            rs.getString("id_cartao"),
            rs.getDate("data_refeicao").toLocalDate());
      }
      return null; // <<-- agora retorna null quando não encontra
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
  public List<Refeicao> retrieveAll() {
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Refeicao> out = new ArrayList<>();
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
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException ignored) {
        }
      }
    }
  }

  @Override
  public void update(Refeicao r, Refeicao newR) {
    PreparedStatement ps = null;
    try {
      String sql = "UPDATE refeicao SET id_cartao=?, data_refeicao=? WHERE id_cartao=? AND data_refeicao=?";
      ps = con.prepareStatement(sql);
      ps.setString(1, newR.getIdCartaoEstudante());
      ps.setDate(2, Date.valueOf(newR.getDataRefeicao()));
      ps.setString(3, r.getIdCartaoEstudante());
      ps.setDate(4, Date.valueOf(r.getDataRefeicao()));
      int affected = ps.executeUpdate();
      if (affected == 0) {
        throw new IllegalArgumentException("Refeição original não encontrada para atualização.");
      }
    } catch (SQLIntegrityConstraintViolationException dup) {
      throw new IllegalArgumentException("Refeição já registrada para este estudante nesta data.", dup);
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao atualizar refeição", e);
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
  public void delete(String id, LocalDate data) {
    PreparedStatement ps = null;
    try {
      String sql = "DELETE FROM refeicao WHERE id_cartao=? AND data_refeicao=?";
      ps = con.prepareStatement(sql);
      ps.setString(1, id);
      ps.setDate(2, Date.valueOf(data));
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao excluir refeições", e);
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException ignored) {
        }
      }
    }
  }
}