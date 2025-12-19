package repositories;

import java.time.LocalDate;
import java.util.List;

import models.Refeicao;

public interface RefeicaoRepo {
  void create(Refeicao refeicao); // Create

  Refeicao retrieve(String idCartaoEstudante, LocalDate data); // Read / Retrieve (retorna a primeira encontrada)

  List<Refeicao> retrieveByCartao(String cartao); // Read / Retrieve pela presença de um cartão

  List<Refeicao> retrieveByData(LocalDate data); // Read / Retrieve pela presença de uma data

  List<Refeicao> retrieveAll(); // Read / Retrieve all

  void update(Refeicao oldRefeicao, Refeicao newRefeicao); // Update substituindo a refeição especificada

  void delete(Refeicao refeicao); // Delete (remove a refeição especificada)
}