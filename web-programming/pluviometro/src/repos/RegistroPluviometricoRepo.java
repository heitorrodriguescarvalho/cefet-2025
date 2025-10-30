package repos;

import java.util.ArrayList;

import models.RegistroPluviometrico;

public interface RegistroPluviometricoRepo {
  void create(RegistroPluviometrico registro);

  RegistroPluviometrico retrieve(int id);

  void update(RegistroPluviometrico oldRegistro, RegistroPluviometrico newRegistro);

  void delete(RegistroPluviometrico registro);

  ArrayList<RegistroPluviometrico> retrieveAll();
}
