package repos.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.Collaboration;
import repos.interfaces.CollaborationRepo;
import utils.Database;

public class CollaborationRepoDB implements CollaborationRepo {
  private final Connection connection;

  public CollaborationRepoDB() {
    this.connection = Database.getConnection();

    this.initializeTable();
  }

  private void initializeTable() {
    PreparedStatement ps = null;

    String sql = "CREATE TABLE IF NOT EXISTS collaboration (" +
        "artist_id INT NOT NULL," +
        "date DATE NOT NULL," +
        "level INT NOT NULL," +
        "PRIMARY KEY (artist_id, date)," +
        "FOREIGN KEY (artist_id) REFERENCES artist(id) ON DELETE CASCADE" +
        ");";

    try {
      ps = connection.prepareStatement(sql);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Error initializing collaboration table", e);
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException ignored) {
      }
    }
  }

  @Override
  public void create(Collaboration collaboration) {
    PreparedStatement ps = null;

    String sql = "INSERT INTO collaboration (artist_id, date, level) VALUES (?, ?, ?);";

    try {
      ps = connection.prepareStatement(sql);

      ps.setInt(1, collaboration.getArtistId());
      ps.setDate(2, Date.valueOf(collaboration.getDate()));
      ps.setInt(3, collaboration.getLevel());

      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Error creating collaboration", e);
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException ignored) {
      }
    }
  }

  @Override
  public Collaboration retrieve(int artistId, int musicId) {
    PreparedStatement ps = null;

    String sql = "SELECT * FROM collaboration WHERE artist_id = ? AND music_id = ?;";

    try {
      ps = connection.prepareStatement(sql);

      ps.setInt(1, artistId);
      ps.setInt(2, musicId);

      var rs = ps.executeQuery();

      if (rs.next()) {
        var collaboration = new Collaboration(
            rs.getInt("artist_id"),
            rs.getDate("date").toLocalDate(),
            rs.getInt("level"));

        return collaboration;
      }

      return null;
    } catch (SQLException e) {
      throw new RuntimeException("Error retrieving collaboration", e);
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException ignored) {
      }
    }
  }

  @Override
  public List<Collaboration> retrieveAll() {
    PreparedStatement ps = null;

    String sql = "SELECT * FROM collaboration;";

    try {
      ps = connection.prepareStatement(sql);

      var rs = ps.executeQuery();

      var collaborations = new ArrayList<Collaboration>();

      while (rs.next()) {
        var collaboration = new Collaboration(
            rs.getInt("artist_id"),
            rs.getDate("date").toLocalDate(),
            rs.getInt("level"));

        collaborations.add(collaboration);
      }

      return collaborations;
    } catch (SQLException e) {
      throw new RuntimeException("Error retrieving all collaborations", e);
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException ignored) {
      }
    }
  }

  @Override
  public void update(Collaboration currentCollaboration, Collaboration newCollaboration) {
    PreparedStatement ps = null;

    String sql = "UPDATE collaboration SET date = ?, level = ? WHERE artist_id = ? AND date = ?;";

    try {
      ps = connection.prepareStatement(sql);

      ps.setDate(1, Date.valueOf(newCollaboration.getDate()));
      ps.setInt(2, newCollaboration.getLevel());
      ps.setInt(3, currentCollaboration.getArtistId());
      ps.setDate(4, Date.valueOf(currentCollaboration.getDate()));

      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Error updating collaboration", e);
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException ignored) {
      }
    }
  }

  @Override
  public void delete(int id, LocalDate date) {
    PreparedStatement ps = null;

    String sql = "DELETE FROM collaboration WHERE artist_id = ? AND date = ?;";

    try {
      ps = connection.prepareStatement(sql);

      ps.setInt(1, id);
      ps.setDate(2, Date.valueOf(date));

      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Error deleting collaboration", e);
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException ignored) {
      }
    }
  }
}