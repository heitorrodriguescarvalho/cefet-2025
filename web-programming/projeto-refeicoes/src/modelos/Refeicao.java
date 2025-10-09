package modelos;

import java.time.LocalDate;

public class Refeicao {
  private String id;
  private LocalDate data;

  public Refeicao(String id, LocalDate data) {
    this.id = id;
    this.data = data;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  @Override
  public boolean equals(Object r) {
    if (r instanceof Refeicao) {
      Refeicao refeicao = (Refeicao) r;

      if (this.id.equals(refeicao.getId()) && this.data.equals(refeicao.getData())) {
        return true;
      }
    }

    return false;
  }
}
