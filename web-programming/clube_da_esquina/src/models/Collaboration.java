package models;

import java.time.LocalDate;
import java.util.Objects;

public class Collaboration {
  private int artistId;
  private LocalDate date;
  private int level;

  public Collaboration(int artistId, LocalDate date, int level) {
    this.artistId = artistId;
    this.date = date;
    this.level = level;
  }

  public int getArtistId() {
    return this.artistId;
  }

  public void setArtistId(int artistId) {
    this.artistId = artistId;
  }

  public LocalDate getDate() {
    return this.date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public int getLevel() {
    return this.level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;

    if (obj == null || this.getClass() != obj.getClass())
      return false;

    Collaboration collaboration = (Collaboration) obj;

    return Objects.equals(this.getArtistId(), collaboration.getArtistId()) &&
        Objects.equals(this.getDate(), collaboration.getDate());
  }
}
