package models;

import java.time.LocalDate;
import java.util.Objects;

public class Music {
  private static int idCount = 0;
  private int id;
  private String name;
  private int duration;
  private LocalDate releaseDate;

  public Music(String name, int duration, LocalDate releaseDate) {
    this.id = ++idCount;
    this.name = name;
    this.duration = duration;
    this.releaseDate = releaseDate;
  }

  public Music(int id, String name, int duration, LocalDate releaseDate) {
    this.id = id;
    this.name = name;
    this.duration = duration;
    this.releaseDate = releaseDate;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getDuration() {
    return this.duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public LocalDate getReleaseDate() {
    return this.releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;

    if (obj == null || this.getClass() != obj.getClass())
      return false;

    Music music = (Music) obj;

    return Objects.equals(this.getId(), music.getId());
  }
}