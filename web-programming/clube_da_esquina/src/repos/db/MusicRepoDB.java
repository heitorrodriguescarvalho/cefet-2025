package repos.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

import models.Music;
import repos.interfaces.MusicRepo;
import utils.Database;

public class MusicRepoDB implements MusicRepo {
  private Connection connection;

  public MusicRepoDB() {
    this.connection = Database.getConnection();

    this.initializeTable();
  }

  private void initializeTable() {
    PreparedStatement ps = null;

    String sql = "CREATE TABLE IF NOT EXISTS music (" +
        "id INT PRIMARY KEY," +
        "name VARCHAR(255) NOT NULL," +
        "duration INT NOT NULL," +
        "release_date DATE NOT NULL" +
        ");";

    try {
      ps = connection.prepareStatement(sql);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Error initializing music table", e);
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
  public void create(Music music) {
    PreparedStatement ps = null;

    String sql = "INSERT INTO music (id, name, duration, release_date) VALUES (?, ?, ?, ?);";

    try {
      ps = connection.prepareStatement(sql);

      ps.setInt(1, music.getId());
      ps.setString(2, music.getName());
      ps.setInt(3, music.getDuration());
      ps.setDate(4, Date.valueOf(music.getReleaseDate()));

      ps.executeUpdate();
    } catch (SQLIntegrityConstraintViolationException e) {
      throw new IllegalArgumentException("Music with id " + music.getId() + " already exists", e);
    } catch (SQLException e) {
      throw new RuntimeException("Error creating music", e);
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
  public Music retrieve(int id) {
    PreparedStatement ps = null;

    String sql = "SELECT * FROM music WHERE id = ?;";

    try {
      ps = connection.prepareStatement(sql);
      ps.setInt(1, id);

      var rs = ps.executeQuery();

      if (rs.next()) {
        String name = rs.getString("name");
        int duration = rs.getInt("duration");
        LocalDate releaseDate = rs.getDate("release_date").toLocalDate();

        return new Music(id, name, duration, releaseDate);
      }

      return null;
    } catch (SQLException e) {
      throw new RuntimeException("Error retrieving music with id " + id, e);
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
  public List<Music> retrieveAll() {
    PreparedStatement ps = null;

    String sql = "SELECT * FROM music;";

    try {
      ps = connection.prepareStatement(sql);

      var rs = ps.executeQuery();

      List<Music> musics = new java.util.ArrayList<>();

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int duration = rs.getInt("duration");
        LocalDate releaseDate = rs.getDate("release_date").toLocalDate();

        musics.add(new Music(id, name, duration, releaseDate));
      }

      return musics;
    } catch (SQLException e) {
      throw new RuntimeException("Error retrieving all musics", e);
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
  public void update(Music currentMusic, Music updatedMusic) {
    PreparedStatement ps = null;

    String sql = "UPDATE music SET name = ?, duration = ?, release_date = ? WHERE id = ?;";

    try {
      ps = connection.prepareStatement(sql);

      ps.setString(1, updatedMusic.getName());
      ps.setInt(2, updatedMusic.getDuration());
      ps.setDate(3, Date.valueOf(updatedMusic.getReleaseDate()));
      ps.setInt(4, currentMusic.getId());

      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Error updating music with id " + currentMusic.getId(), e);
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
  public void delete(int id) {
    PreparedStatement ps = null;

    String sql = "DELETE FROM music WHERE id = ?;";

    try {
      ps = connection.prepareStatement(sql);

      ps.setInt(1, id);

      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Error deleting music with id " + id, e);
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
