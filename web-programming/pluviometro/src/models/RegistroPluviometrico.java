package models;

import java.time.LocalDate;

public class RegistroPluviometrico {
  private int id;
  private static int idCounter = 0;
  private LocalDate date;
  private double value;
  private Pluviometro pluviometro;

  public RegistroPluviometrico(LocalDate date, double value, Pluviometro pluviometro) {
    this.id = ++idCounter;
    this.date = date;
    this.value = value;
    this.pluviometro = pluviometro;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public Pluviometro getPluviometro() {
    return pluviometro;
  }

  public void setPluviometro(Pluviometro pluviometro) {
    this.pluviometro = pluviometro;
  }

  @Override
  public boolean equals(Object registro) {
    if (registro instanceof RegistroPluviometrico) {
      RegistroPluviometrico r = (RegistroPluviometrico) registro;

      return this.date.equals(r.date) && this.value == r.value && this.pluviometro.equals(r.pluviometro);
    }

    return false;
  }
}
