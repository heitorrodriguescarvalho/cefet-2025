package models;

import java.util.Objects;

public class Composition {
  private int musicId;
  private int artistId;

  public Composition(int musicId, int artistId) {
    this.musicId = musicId;
    this.artistId = artistId;
  }

  public int getMusicId() {
    return this.musicId;
  }

  public void setMusicId(int musicId) {
    this.musicId = musicId;
  }

  public int getArtistId() {
    return this.artistId;
  }

  public void setArtistId(int artistId) {
    this.artistId = artistId;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;

    if (obj == null || this.getClass() != obj.getClass())
      return false;

    Composition composition = (Composition) obj;

    return Objects.equals(this.getMusicId(), composition.getMusicId()) &&
        Objects.equals(this.getArtistId(), composition.getArtistId());
  }
}