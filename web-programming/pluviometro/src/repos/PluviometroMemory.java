package repos;

import java.util.ArrayList;

import models.Pluviometro;

public class PluviometroMemory implements PluviometroRepo {
  private ArrayList<Pluviometro> pluviometros = new ArrayList<>();

  @Override
  public void create(Pluviometro pluviometro) {
    this.pluviometros.add(pluviometro);
  }

  @Override
  public void update(Pluviometro oldPluviometro, Pluviometro newPluviometro) {
    for (int i = 0; i < this.pluviometros.size(); i++) {
      if (this.pluviometros.get(i).equals(oldPluviometro)) {
        this.pluviometros.set(i, newPluviometro);

        return;
      }
    }
  }

  @Override
  public Pluviometro retrieve(int id) {
    for (Pluviometro p : this.pluviometros) {
      if (p.getId() == id) {
        return p;
      }
    }

    return null;
  }

  @Override
  public ArrayList<Pluviometro> retrieveAll() {
    return new ArrayList<>(this.pluviometros);
  }

  @Override
  public void delete(Pluviometro pluviometro) {
    this.pluviometros.removeIf(p -> p.equals(pluviometro));
  }
}
