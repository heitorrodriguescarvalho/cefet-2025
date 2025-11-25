package repos.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.Artist;
import repos.interfaces.ArtistRepo;
import utils.Database;

public class ArtistRepoDB implements ArtistRepo {
  private Connection connection;

  public ArtistRepoDB() {
    this.connection = Database.getConnection();

    this.initializeTable();
  }

  private void initializeTable() {
    PreparedStatement ps = null;

    String sql = "CREATE TABLE IF NOT EXISTS artist (" +
        "id INT PRIMARY KEY," +
        "name VARCHAR(127) NOT NULL," +
        "citizenship VARCHAR(127) NOT NULL," +
        "birth_date DATE NOT NULL," +
        "biography VARCHAR(1023) NOT NULL" +
        ");";

    try {
      ps = connection.prepareStatement(sql);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Error initializing artist table", e);
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
  public void create(Artist artist) {
    PreparedStatement ps = null;

    String sql = "INSERT INTO artist (id, name, citizenship, birth_date, biography) VALUES (?, ?, ?, ?, ?);";

    try {
      ps = connection.prepareStatement(sql);

      ps.setInt(1, artist.getId());
      ps.setString(2, artist.getName());
      ps.setString(3, artist.getCitizenship());
      ps.setDate(4, Date.valueOf(artist.getBirthDate()));
      ps.setString(5, artist.getBiography());

      ps.executeUpdate();
    } catch (SQLIntegrityConstraintViolationException e) {
      throw new RuntimeException("Artist with id " + artist.getId() + " already exists", e);
    } catch (SQLException e) {
      throw new RuntimeException("Error creating artist", e);
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
  public Artist retrieve(int id) {
    PreparedStatement ps = null;

    String sql = "SELECT * FROM artist WHERE id = ?;";

    try {
      ps = connection.prepareStatement(sql);

      ps.setInt(1, id);

      var rs = ps.executeQuery();

      if (rs.next()) {
        String name = rs.getString("name");
        String citizenship = rs.getString("citizenship");
        LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
        String biography = rs.getString("biography");

        return new Artist(id, name, citizenship, birthDate, biography);
      }

      return null;
    } catch (SQLException e) {
      throw new RuntimeException("Error retrieving artist with id " + id, e);
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
  public List<Artist> retrieveAll() {
    PreparedStatement ps = null;

    String sql = "SELECT * FROM artist;";

    try {
      ps = connection.prepareStatement(sql);

      var rs = ps.executeQuery();

      List<Artist> artists = new ArrayList<>();

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String citizenship = rs.getString("citizenship");
        LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
        String biography = rs.getString("biography");

        artists.add(new Artist(id, name, citizenship, birthDate, biography));
      }

      return artists;
    } catch (SQLException e) {
      throw new RuntimeException("Error retrieving all artists", e);
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
  public void update(Artist currentArtist, Artist updatedArtist) {
    PreparedStatement ps = null;

    String sql = "UPDATE artist SET name = ?, citizenship = ?, birth_date = ?, biography = ? WHERE id = ?;";

    try {
      ps = connection.prepareStatement(sql);

      ps.setString(1, updatedArtist.getName());
      ps.setString(2, updatedArtist.getCitizenship());
      ps.setDate(3, Date.valueOf(updatedArtist.getBirthDate()));
      ps.setString(4, updatedArtist.getBiography());
      ps.setInt(5, currentArtist.getId());

      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Error updating artist with id " + currentArtist.getId(), e);
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

    String sql = "DELETE FROM artist WHERE id = ?;";

    try {
      ps = connection.prepareStatement(sql);

      ps.setInt(1, id);

      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Error deleting artist with id " + id, e);
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