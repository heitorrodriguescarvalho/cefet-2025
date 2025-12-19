package models;

import java.time.LocalDate;
import java.util.Objects;

public class Refeicao {
  private String idCartaoEstudante;
  private LocalDate dataRefeicao;

  public Refeicao(String idCartaoEstudante, LocalDate dataRefeicao) {
    this.idCartaoEstudante = idCartaoEstudante;
    this.dataRefeicao = dataRefeicao;
  }

  public String getIdCartaoEstudante() {
    return idCartaoEstudante;
  }

  public void setIdCartaoEstudante(String idCartaoEstudante) {
    this.idCartaoEstudante = idCartaoEstudante;
  }

  public LocalDate getDataRefeicao() {
    return dataRefeicao;
  }

  public void setDataRefeicao(LocalDate dataRefeicao) {
    this.dataRefeicao = dataRefeicao;
  }

  @Override
  public String toString() {
    return "Refeicao{" +
        "idCartaoEstudante='" + idCartaoEstudante + '\'' +
        ", dataRefeicao=" + dataRefeicao +
        '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Refeicao refeicao = (Refeicao) obj;
    return Objects.equals(idCartaoEstudante, refeicao.idCartaoEstudante) &&
        Objects.equals(dataRefeicao, refeicao.dataRefeicao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCartaoEstudante, dataRefeicao);
  }
}