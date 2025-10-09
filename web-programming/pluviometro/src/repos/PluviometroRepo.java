package repos;

import java.util.ArrayList;

import models.Pluviometro;

public interface PluviometroRepo {
  void create(Pluviometro pluviometro);

  void update(Pluviometro oldPluviometro, Pluviometro newPluviometro);

  Pluviometro retrieve(int id);

  void delete(Pluviometro pluviometro);

  ArrayList<Pluviometro> retrieveAll();
}
