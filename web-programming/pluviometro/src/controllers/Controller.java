package controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import models.Pluviometro;
import models.RegistroPluviometrico;
import repos.PluviometroMemory;
import repos.RegistroPluviometricoMemory;

public class Controller {
  private PluviometroMemory pluviometerRepository;
  private RegistroPluviometricoMemory recordRepository;

  public Controller() {
    this.pluviometerRepository = new PluviometroMemory();
    this.recordRepository = new RegistroPluviometricoMemory();
  }

  // ===== Pluviometer Operations =====

  public void createPluviometer(double maxCapacity, String location) {
    Pluviometro pluviometer = new Pluviometro(maxCapacity, location);
    this.pluviometerRepository.create(pluviometer);
  }

  public Pluviometro getPluviometer(int id) {
    return this.pluviometerRepository.retrieve(id);
  }

  public ArrayList<Pluviometro> listAllPluviometers() {
    return this.pluviometerRepository.retrieveAll();
  }

  public void updatePluviometer(int id, double newCapacity, String newLocation) {
    Pluviometro oldPluviometer = this.pluviometerRepository.retrieve(id);

    if (oldPluviometer == null) {
      throw new IllegalArgumentException("Pluviometer with ID " + id + " not found");
    }

    Pluviometro newPluviometer = new Pluviometro(newCapacity, newLocation);
    this.pluviometerRepository.update(oldPluviometer, newPluviometer);
  }

  public void deletePluviometer(int id) {
    Pluviometro pluviometer = this.pluviometerRepository.retrieve(id);

    if (pluviometer == null) {
      throw new IllegalArgumentException("Pluviometer with ID " + id + " not found");
    }

    this.pluviometerRepository.delete(pluviometer);
  }

  // ===== Record Operations =====

  public void createRecord(LocalDate date, double value, int pluviometerId) {
    Pluviometro pluviometer = this.pluviometerRepository.retrieve(pluviometerId);

    if (pluviometer == null) {
      throw new IllegalArgumentException("Pluviometer with ID " + pluviometerId + " not found");
    }

    RegistroPluviometrico record = new RegistroPluviometrico(date, value, pluviometer);
    this.recordRepository.create(record);
  }

  public RegistroPluviometrico getRecord(int id) {
    return this.recordRepository.retrieve(id);
  }

  public ArrayList<RegistroPluviometrico> listAllRecords() {
    return this.recordRepository.retrieveAll();
  }

  public ArrayList<RegistroPluviometrico> listRecordsByPluviometer(int pluviometerId) {
    ArrayList<RegistroPluviometrico> records = this.recordRepository.retrieveAll();
    ArrayList<RegistroPluviometrico> result = new ArrayList<>();

    for (RegistroPluviometrico record : records) {
      if (record.getPluviometro().getId() == pluviometerId) {
        result.add(record);
      }
    }

    return result;
  }

  public void updateRecord(int id, LocalDate newDate, double newValue) {
    RegistroPluviometrico oldRecord = this.recordRepository.retrieve(id);

    if (oldRecord == null) {
      throw new IllegalArgumentException("Record with ID " + id + " not found");
    }

    RegistroPluviometrico newRecord = new RegistroPluviometrico(newDate, newValue,
        oldRecord.getPluviometro());
    this.recordRepository.update(oldRecord, newRecord);
  }

  public void deleteRecord(int id) {
    RegistroPluviometrico record = this.recordRepository.retrieve(id);

    if (record == null) {
      throw new IllegalArgumentException("Record with ID " + id + " not found");
    }

    this.recordRepository.delete(record);
  }
}
