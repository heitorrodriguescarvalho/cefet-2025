package repos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import models.Composition;
import repos.interfaces.CompositionRepo;
import utils.Database;

public class CompositionRepoDB implements CompositionRepo {
  private Connection connection;

  public CompositionRepoDB() {
    this.connection = Database.getConnection();

    this.initializeTable();
  }

  private void initializeTable() {
    PreparedStatement ps = null;

    String sql = "CREATE TABLE IF NOT EXISTS composition (" +
        "music_id INT NOT NULL," +
        "artist_id INT NOT NULL," +
        "PRIMARY KEY (music_id, artist_id)," +
        "FOREIGN KEY (music_id) REFERENCES music(id) ON DELETE CASCADE," +
        "FOREIGN KEY (artist_id) REFERENCES artist(id) ON DELETE CASCADE" +
        ");";

    try {
      ps = connection.prepareStatement(sql);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Error initializing composition table", e);
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
  public void create(Composition composition) {
    PreparedStatement ps = null;

    String sql = "INSERT INTO composition (music_id, artist_id) VALUES (?, ?);";

    try {
      ps = connection.prepareStatement(sql);

      ps.setInt(1, composition.getMusicId());
      ps.setInt(2, composition.getArtistId());

      ps.executeUpdate();
    } catch (SQLIntegrityConstraintViolationException e) {
      throw new RuntimeException("Composition already exists", e);
    } catch (SQLException e) {
      throw new RuntimeException("Error creating composition", e);
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
  public Composition retrieve(int musicId, int artistId) {
    PreparedStatement ps = null;

    String sql = "SELECT * FROM composition WHERE music_id = ? AND artist_id = ?;";

    try {
      ps = connection.prepareStatement(sql);

      ps.setInt(1, musicId);
      ps.setInt(2, artistId);

      var rs = ps.executeQuery();

      if (rs.next()) {
        return new Composition(
            rs.getInt("music_id"),
            rs.getInt("artist_id"));
      } else {
        return null;
      }
    } catch (SQLException e) {
      throw new RuntimeException("Error retrieving composition", e);
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
  public List<Composition> retrieveByArtist(int artistId) {
    PreparedStatement ps = null;

    String sql = "SELECT * FROM composition WHERE artist_id = ?;";

    try {
      ps = connection.prepareStatement(sql);

      ps.setInt(1, artistId);

      var rs = ps.executeQuery();

      List<Composition> compositions = new ArrayList<>();

      while (rs.next()) {
        int musicId = rs.getInt("music_id");

        compositions.add(new Composition(musicId, artistId));
      }

      return compositions;
    } catch (SQLException e) {
      throw new RuntimeException("Error retrieving compositions by artist", e);
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
  public List<Composition> retrieveByMusic(int musicId) {
    PreparedStatement ps = null;

    String sql = "SELECT * FROM composition WHERE music_id = ?;";
    try {
      ps = connection.prepareStatement(sql);

      ps.setInt(1, musicId);

      var rs = ps.executeQuery();

      List<Composition> compositions = new ArrayList<>();

      while (rs.next()) {
        int artistId = rs.getInt("artist_id");

        compositions.add(new Composition(musicId, artistId));
      }

      return compositions;
    } catch (SQLException e) {
      throw new RuntimeException("Error retrieving compositions by music", e);
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
  public List<Composition> retrieveAll() {
    PreparedStatement ps = null;

    String sql = "SELECT * FROM composition;";

    try {
      ps = connection.prepareStatement(sql);

      var rs = ps.executeQuery();

      List<Composition> compositions = new ArrayList<>();

      while (rs.next()) {
        int musicId = rs.getInt("music_id");
        int artistId = rs.getInt("artist_id");

        compositions.add(new Composition(musicId, artistId));
      }

      return compositions;
    } catch (SQLException e) {
      throw new RuntimeException("Error retrieving all compositions", e);
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
  public void update(Composition currentComposition, Composition updatedComposition) {
    PreparedStatement ps = null;

    String sql = "UPDATE composition SET music_id = ?, artist_id = ? WHERE music_id = ? AND artist_id = ?;";

    try {
      ps = connection.prepareStatement(sql);

      ps.setInt(1, updatedComposition.getMusicId());
      ps.setInt(2, updatedComposition.getArtistId());
      ps.setInt(3, currentComposition.getMusicId());
      ps.setInt(4, currentComposition.getArtistId());

      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Error updating composition", e);
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
  public void delete(int artistId, int musicId) {
    PreparedStatement ps = null;

    String sql = "DELETE FROM composition WHERE music_id = ? AND artist_id = ?;";

    try {
      ps = connection.prepareStatement(sql);

      ps.setInt(1, musicId);
      ps.setInt(2, artistId);

      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Error deleting composition", e);
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
