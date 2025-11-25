package models;

import java.time.LocalDate;
import java.util.Objects;

public class Artist {
  private static int idCount = 0;
  private int id;
  private String name;
  private String citizenship;
  private LocalDate birthDate;
  private String biography;

  public Artist(String name, String citizenship, LocalDate birthDate, String biography) {
    this.id = ++idCount;
    this.name = name;
    this.citizenship = citizenship;
    this.birthDate = birthDate;
    this.biography = biography;
  }

  public Artist(int id, String name, String citizenship, LocalDate birthDate, String biography) {
    this.id = id;
    this.name = name;
    this.citizenship = citizenship;
    this.birthDate = birthDate;
    this.biography = biography;
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

  public String getCitizenship() {
    return this.citizenship;
  }

  public void setCitizenship(String citizenship) {
    this.citizenship = citizenship;
  }

  public LocalDate getBirthDate() {
    return this.birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getBiography() {
    return this.biography;
  }

  public void setBiography(String biography) {
    this.biography = biography;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;

    if (obj == null || this.getClass() != obj.getClass())
      return false;

    Artist artist = (Artist) obj;

    return Objects.equals(this.getId(), artist.getId());
  }
}
