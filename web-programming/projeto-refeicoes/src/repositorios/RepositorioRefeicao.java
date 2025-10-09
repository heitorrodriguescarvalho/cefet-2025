package repositorios;

import java.time.LocalDate;
import java.util.List;

import modelos.Refeicao;

public interface RepositorioRefeicao {
  public void createRefeicao(Refeicao r);

  public Refeicao retrieveRefeicao(String id, LocalDate data);

  public List<Refeicao> retrieveRefeicoes();

  public void updateRefeicao(Refeicao rOld, Refeicao rNew);

  public void deleteRefeicao(Refeicao r);
}
