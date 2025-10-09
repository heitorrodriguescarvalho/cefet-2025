package repos;

import java.util.ArrayList;

import models.RegistroPluviometrico;

public class RegistroPluviometricoMemory implements RegistroPluviometricoRepo {
  private ArrayList<RegistroPluviometrico> registros = new ArrayList<>();

  @Override
  public void create(RegistroPluviometrico registro) {
    if (registro.getPluviometro().getMaxCapacity() < registro.getValue()) {
      throw new IllegalArgumentException(
          "O valor do registro não pode ser maior que a capacidade máxima do pluviômetro");
    }

    if (registro.getValue() < 0) {
      throw new IllegalArgumentException("O valor do registro não pode ser negativo");
    }

    for (RegistroPluviometrico r : this.registros) {
      if (r.getDate().equals(registro.getDate()) && r.getPluviometro().equals(registro.getPluviometro())) {
        throw new IllegalArgumentException("Já existe um registro nesse pluviômetro nessa data");
      }
    }

    this.registros.add(registro);
  }

  @Override
  public void update(RegistroPluviometrico oldRegistro, RegistroPluviometrico newRegistro) {
    for (int i = 0; i < this.registros.size(); i++) {
      if (this.registros.get(i).equals(oldRegistro)) {
        this.registros.set(i, newRegistro);

        return;
      }
    }
  }

  @Override
  public ArrayList<RegistroPluviometrico> retrieveAll() {
    return new ArrayList<>(this.registros);
  }

  @Override
  public void delete(RegistroPluviometrico registro) {
    this.registros.removeIf(r -> r.equals(registro));
  }
}