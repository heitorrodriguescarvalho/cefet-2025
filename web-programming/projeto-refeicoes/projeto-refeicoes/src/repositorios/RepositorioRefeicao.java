package repositorios;

import java.time.LocalDate;
import java.util.List;

import modelos.Refeicao;

public interface RepositorioRefeicao {
  public void create(Refeicao refeicao);

  public Refeicao retrieve(String idCartaoEstudante, LocalDate dataRefeicao);

  public List<Refeicao> retrieveAll();

  public void update(Refeicao oldRefeicao, Refeicao newRefeicao);

  public void delete(Refeicao refeicao);
}