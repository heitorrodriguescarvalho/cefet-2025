package modelos;

import java.time.LocalDate;

public class Refeicao {
  private String id;
  private LocalDate dataRefeicao;

  public Refeicao(String idCartaoEstudante, LocalDate dataRefeicao) {
    this.id = idCartaoEstudante;
    this.dataRefeicao = dataRefeicao;
  }

  public String getId() {
    return id;
  }

  public void setId(String idCartaoEstudante) {
    this.id = idCartaoEstudante;
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
        "id='" + id + '\'' +
        ", dataRefeicao=" + dataRefeicao +
        '}';
  }
}