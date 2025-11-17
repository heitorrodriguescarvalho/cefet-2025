package repositorios;

import java.time.LocalDate;
import java.util.List;

import modelos.Refeicao;

public interface RepositorioRefeicao {
  void create(Refeicao refeicao); // Create

  Refeicao retrieve(String idCartaoEstudante, LocalDate data); // Read / Retrieve (retorna a primeira encontrada)

  List<Refeicao> retrieveAll(); // Read / Retrieve all

  void update(Refeicao oldRefeicao, Refeicao newRefeicao); // Update substituindo a refeição especificada

  void delete(String idCartaoEstudante, LocalDate data); // Delete (remove a refeição com este id e data)
}
