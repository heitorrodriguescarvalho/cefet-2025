package repos;

import java.util.ArrayList;

import models.RegistroPluviometrico;

public interface RegistroPluviometricoRepo {
  void create(RegistroPluviometrico registro);

  void update(RegistroPluviometrico oldRegistro, RegistroPluviometrico newRegistro);

  void delete(RegistroPluviometrico registro);

  ArrayList<RegistroPluviometrico> retrieveAll();
}
