package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import controllers.Controller;
import models.Pluviometro;
import models.RegistroPluviometrico;

public class App {
  private Controller controller;
  private Scanner scanner;
  private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public App() {
    this.controller = new Controller();
    this.scanner = new Scanner(System.in);
  }

  public void run() {
    boolean running = true;

    while (running) {
      displayMainMenu();
      int option = readInteger();

      switch (option) {
        case 1:
          pluviometerMenu();
          break;
        case 2:
          recordMenu();
          break;
        case 0:
          running = false;
          System.out.println("Encerrando aplicação...");
          break;
        default:
          System.out.println("Opção inválida!");
      }
    }

    scanner.close();
  }

  private void displayMainMenu() {
    System.out.println("\n=== PLUVIÔMETRO - MENU PRINCIPAL ===");
    System.out.println("1. Gerenciar Pluviômetros");
    System.out.println("2. Gerenciar Registros");
    System.out.println("0. Sair");
    System.out.print("Escolha uma opção: ");
  }

  private void pluviometerMenu() {
    boolean backToMain = false;

    while (!backToMain) {
      System.out.println("\n=== GERENCIAR PLUVIÔMETROS ===");
      System.out.println("1. Criar novo pluviômetro");
      System.out.println("2. Listar todos os pluviômetros");
      System.out.println("3. Buscar pluviômetro por ID");
      System.out.println("4. Atualizar pluviômetro");
      System.out.println("5. Deletar pluviômetro");
      System.out.println("0. Voltar ao menu principal");
      System.out.print("Escolha uma opção: ");

      int option = readInteger();

      switch (option) {
        case 1:
          createPluviometer();
          break;
        case 2:
          listPluviometers();
          break;
        case 3:
          searchPluviometer();
          break;
        case 4:
          updatePluviometer();
          break;
        case 5:
          deletePluviometer();
          break;
        case 0:
          backToMain = true;
          break;
        default:
          System.out.println("Opção inválida!");
      }
    }
  }

  private void createPluviometer() {
    System.out.println("\n--- Criar Novo Pluviômetro ---");
    System.out.print("Digite a capacidade máxima (em mm): ");
    double capacity = readDouble();
    System.out.print("Digite a localização: ");
    String location = scanner.nextLine();

    try {
      controller.createPluviometer(capacity, location);
      System.out.println("✓ Pluviômetro criado com sucesso!");
    } catch (Exception e) {
      System.out.println("✗ Erro ao criar pluviômetro: " + e.getMessage());
    }
  }

  private void listPluviometers() {
    System.out.println("\n--- Lista de Pluviômetros ---");
    ArrayList<Pluviometro> pluviometers = controller.listAllPluviometers();

    if (pluviometers.isEmpty()) {
      System.out.println("Nenhum pluviômetro cadastrado.");
      return;
    }

    for (Pluviometro pluviometer : pluviometers) {
      System.out.println("ID: " + pluviometer.getId() + " | Localização: " + pluviometer.getLocation() +
          " | Capacidade Máxima: " + pluviometer.getMaxCapacity() + " mm");
    }
  }

  private void searchPluviometer() {
    System.out.println("\n--- Buscar Pluviômetro ---");
    System.out.print("Digite o ID do pluviômetro: ");
    int id = readInteger();

    try {
      Pluviometro pluviometer = controller.getPluviometer(id);
      if (pluviometer == null) {
        System.out.println("✗ Pluviômetro não encontrado.");
        return;
      }

      System.out.println("\nDados do Pluviômetro:");
      System.out.println("ID: " + pluviometer.getId());
      System.out.println("Localização: " + pluviometer.getLocation());
      System.out.println("Capacidade Máxima: " + pluviometer.getMaxCapacity() + " mm");
    } catch (Exception e) {
      System.out.println("✗ Erro ao buscar pluviômetro: " + e.getMessage());
    }
  }

  private void updatePluviometer() {
    System.out.println("\n--- Atualizar Pluviômetro ---");
    System.out.print("Digite o ID do pluviômetro a atualizar: ");
    int id = readInteger();

    Pluviometro pluviometer = controller.getPluviometer(id);
    if (pluviometer == null) {
      System.out.println("✗ Pluviômetro não encontrado.");
      return;
    }

    System.out.print("Digite a nova capacidade máxima (em mm): ");
    double newCapacity = readDouble();
    System.out.print("Digite a nova localização: ");
    String newLocation = scanner.nextLine();

    try {
      controller.updatePluviometer(id, newCapacity, newLocation);
      System.out.println("✓ Pluviômetro atualizado com sucesso!");
    } catch (Exception e) {
      System.out.println("✗ Erro ao atualizar pluviômetro: " + e.getMessage());
    }
  }

  private void deletePluviometer() {
    System.out.println("\n--- Deletar Pluviômetro ---");
    System.out.print("Digite o ID do pluviômetro a deletar: ");
    int id = readInteger();

    Pluviometro pluviometer = controller.getPluviometer(id);
    if (pluviometer == null) {
      System.out.println("✗ Pluviômetro não encontrado.");
      return;
    }

    System.out.print("Tem certeza que deseja deletar o pluviômetro '" + pluviometer.getLocation() + "'? (s/n): ");
    String confirmation = scanner.nextLine().toLowerCase();

    if (confirmation.equals("s")) {
      try {
        controller.deletePluviometer(id);
        System.out.println("✓ Pluviômetro deletado com sucesso!");
      } catch (Exception e) {
        System.out.println("✗ Erro ao deletar pluviômetro: " + e.getMessage());
      }
    } else {
      System.out.println("Operação cancelada.");
    }
  }

  private void recordMenu() {
    boolean backToMain = false;

    while (!backToMain) {
      System.out.println("\n=== GERENCIAR REGISTROS ===");
      System.out.println("1. Criar novo registro");
      System.out.println("2. Listar todos os registros");
      System.out.println("3. Listar registros por pluviômetro");
      System.out.println("4. Buscar registro por ID");
      System.out.println("5. Atualizar registro");
      System.out.println("6. Deletar registro");
      System.out.println("0. Voltar ao menu principal");
      System.out.print("Escolha uma opção: ");

      int option = readInteger();

      switch (option) {
        case 1:
          createRecord();
          break;
        case 2:
          listRecords();
          break;
        case 3:
          listRecordsByPluviometer();
          break;
        case 4:
          searchRecord();
          break;
        case 5:
          updateRecord();
          break;
        case 6:
          deleteRecord();
          break;
        case 0:
          backToMain = true;
          break;
        default:
          System.out.println("Opção inválida!");
      }
    }
  }

  private void createRecord() {
    System.out.println("\n--- Criar Novo Registro ---");

    listPluviometers();

    System.out.print("Digite o ID do pluviômetro: ");
    int pluviometerId = readInteger();

    if (controller.getPluviometer(pluviometerId) == null) {
      System.out.println("✗ Pluviômetro não encontrado.");
      return;
    }

    System.out.print("Digite a data (dd/MM/yyyy): ");
    LocalDate date = readDate();
    if (date == null)
      return;

    System.out.print("Digite o valor da precipitação (em mm): ");
    double value = readDouble();

    try {
      controller.createRecord(date, value, pluviometerId);
      System.out.println("✓ Registro criado com sucesso!");
    } catch (IllegalArgumentException e) {
      System.out.println("✗ Erro ao criar registro: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("✗ Erro inesperado: " + e.getMessage());
    }
  }

  private void listRecords() {
    System.out.println("\n--- Lista de Registros ---");
    ArrayList<RegistroPluviometrico> records = controller.listAllRecords();

    if (records.isEmpty()) {
      System.out.println("Nenhum registro cadastrado.");
      return;
    }

    for (RegistroPluviometrico record : records) {
      System.out.println("ID: " + record.getId() + " | Data: " + record.getDate().format(dateFormatter) +
          " | Valor: " + record.getValue() + " mm | Pluviômetro: " + record.getPluviometro().getLocation());
    }
  }

  private void listRecordsByPluviometer() {
    System.out.println("\n--- Registros por Pluviômetro ---");

    listPluviometers();

    System.out.print("Digite o ID do pluviômetro: ");
    int pluviometerId = readInteger();

    if (controller.getPluviometer(pluviometerId) == null) {
      System.out.println("✗ Pluviômetro não encontrado.");
      return;
    }

    ArrayList<RegistroPluviometrico> records = controller.listRecordsByPluviometer(pluviometerId);

    if (records.isEmpty()) {
      System.out.println("Nenhum registro para este pluviômetro.");
      return;
    }

    Pluviometro pluviometer = controller.getPluviometer(pluviometerId);
    System.out.println("\nRegistros do pluviômetro: " + pluviometer.getLocation());
    for (RegistroPluviometrico record : records) {
      System.out.println("ID: " + record.getId() + " | Data: " + record.getDate().format(dateFormatter) +
          " | Valor: " + record.getValue() + " mm");
    }
  }

  private void searchRecord() {
    System.out.println("\n--- Buscar Registro ---");
    System.out.print("Digite o ID do registro: ");
    int id = readInteger();

    try {
      RegistroPluviometrico record = controller.getRecord(id);
      if (record == null) {
        System.out.println("✗ Registro não encontrado.");
        return;
      }

      System.out.println("\nDados do Registro:");
      System.out.println("ID: " + record.getId());
      System.out.println("Data: " + record.getDate().format(dateFormatter));
      System.out.println("Valor: " + record.getValue() + " mm");
      System.out.println("Pluviômetro: " + record.getPluviometro().getLocation());
    } catch (Exception e) {
      System.out.println("✗ Erro ao buscar registro: " + e.getMessage());
    }
  }

  private void updateRecord() {
    System.out.println("\n--- Atualizar Registro ---");
    System.out.print("Digite o ID do registro a atualizar: ");
    int id = readInteger();

    RegistroPluviometrico record = controller.getRecord(id);
    if (record == null) {
      System.out.println("✗ Registro não encontrado.");
      return;
    }

    System.out.print("Digite a nova data (dd/MM/yyyy): ");
    LocalDate newDate = readDate();
    if (newDate == null)
      return;

    System.out.print("Digite o novo valor da precipitação (em mm): ");
    double newValue = readDouble();

    try {
      controller.updateRecord(id, newDate, newValue);
      System.out.println("✓ Registro atualizado com sucesso!");
    } catch (IllegalArgumentException e) {
      System.out.println("✗ Erro ao atualizar registro: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("✗ Erro inesperado: " + e.getMessage());
    }
  }

  private void deleteRecord() {
    System.out.println("\n--- Deletar Registro ---");
    System.out.print("Digite o ID do registro a deletar: ");
    int id = readInteger();

    RegistroPluviometrico record = controller.getRecord(id);
    if (record == null) {
      System.out.println("✗ Registro não encontrado.");
      return;
    }

    System.out.print("Tem certeza que deseja deletar o registro de " +
        record.getDate().format(dateFormatter) + "? (s/n): ");
    String confirmation = scanner.nextLine().toLowerCase();

    if (confirmation.equals("s")) {
      try {
        controller.deleteRecord(id);
        System.out.println("✓ Registro deletado com sucesso!");
      } catch (Exception e) {
        System.out.println("✗ Erro ao deletar registro: " + e.getMessage());
      }
    } else {
      System.out.println("Operação cancelada.");
    }
  }

  // Helper methods for reading input

  private int readInteger() {
    try {
      int value = Integer.parseInt(scanner.nextLine());
      return value;
    } catch (NumberFormatException e) {
      System.out.println("Entrada inválida. Digite um número inteiro.");
      return -1;
    }
  }

  private double readDouble() {
    try {
      double value = Double.parseDouble(scanner.nextLine());
      return value;
    } catch (NumberFormatException e) {
      System.out.println("Entrada inválida. Digite um número decimal.");
      return -1;
    }
  }

  private LocalDate readDate() {
    try {
      String dateString = scanner.nextLine();
      return LocalDate.parse(dateString, dateFormatter);
    } catch (DateTimeParseException e) {
      System.out.println("Formato de data inválido. Use dd/MM/yyyy");
      return null;
    }
  }

  public static void main(String[] args) {
    App app = new App();
    app.run();
  }
}
