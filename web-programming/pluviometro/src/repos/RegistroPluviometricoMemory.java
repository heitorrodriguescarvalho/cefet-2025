package repos;

import java.util.ArrayList;

import models.RegistroPluviometrico;

public class RegistroPluviometricoMemory implements RegistroPluviometricoRepo {
  private ArrayList<RegistroPluviometrico> records = new ArrayList<>();

  @Override
  public void create(RegistroPluviometrico registro) {
    if (registro.getPluviometro().getMaxCapacity() < registro.getValue()) {
      throw new IllegalArgumentException(
          "O valor do registro não pode ser maior que a capacidade máxima do pluviômetro");
    }

    if (registro.getValue() < 0) {
      throw new IllegalArgumentException("O valor do registro não pode ser negativo");
    }

    for (RegistroPluviometrico r : this.records) {
      if (r.getDate().equals(registro.getDate()) && r.getPluviometro().equals(registro.getPluviometro())) {
        throw new IllegalArgumentException("Já existe um registro nesse pluviômetro nessa data");
      }
    }

    this.records.add(registro);
  }

  @Override
  public RegistroPluviometrico retrieve(int id) {
    for (RegistroPluviometrico record : this.records) {
      if (record.getId() == id) {
        return record;
      }
    }

    return null;
  }

  @Override
  public void update(RegistroPluviometrico oldRegistro, RegistroPluviometrico newRegistro) {
    for (int i = 0; i < this.records.size(); i++) {
      if (this.records.get(i).equals(oldRegistro)) {
        this.records.set(i, newRegistro);

        return;
      }
    }
  }

  @Override
  public ArrayList<RegistroPluviometrico> retrieveAll() {
    return new ArrayList<>(this.records);
  }

  @Override
  public void delete(RegistroPluviometrico registro) {
    this.records.removeIf(r -> r.equals(registro));
  }
}