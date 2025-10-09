package app;

import modelos.Refeicao;
import repositorios.RepositorioRefeicao;
import repositorios.RepositorioRefeicaoMemoria;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner SC = new Scanner(System.in);
    private static final RepositorioRefeicao repo = new RepositorioRefeicaoMemoria();

    public static void main(String[] args) {
        System.out.println("=== CRUD de Refeicoes (CLI) ===");
        boolean running = true;
        while (running) {
            switch (menu()) {
                case 1 -> criar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> atualizar();
                case 5 -> excluir();
                case 0 -> running = false;
                default -> System.out.println("Opcao invalida!");
            }
        }
        System.out.println("Encerrando. Ate logo!");
    }

    private static int menu() {
        System.out.println();
        System.out.println("1) Criar refeicao");
        System.out.println("2) Listar refeicoes");
        System.out.println("3) Buscar refeicao (por id+data)");
        System.out.println("4) Atualizar refeicao");
        System.out.println("5) Excluir refeicao");
        System.out.println("0) Sair");
        System.out.print("Escolha: ");
        String in = SC.nextLine().trim();
        try {
            return Integer.parseInt(in);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // --- Operações ---

    private static void criar() {
        System.out.println("\n[CRIAR]");
        String id = lerNaoVazio("ID: ");
        LocalDate data = lerDataISO("Data (yyyy-MM-dd): ");

        // (opcional) impedir duplicidade
        Refeicao existente = repo.retrieveRefeicao(id, data);
        if (existente != null) {
            System.out.println("Ja existe refeicao com esse ID e data:");
            imprimir(existente);
            return;
        }

        Refeicao r = new Refeicao(id, data);
        repo.createRefeicao(r);
        System.out.println("Refeicao criada com sucesso!");
        imprimir(r);
    }

    private static void listar() {
        System.out.println("\n[LISTAR]");
        List<Refeicao> todas = repo.retrieveRefeicoes();
        if (todas == null || todas.isEmpty()) {
            System.out.println("(Nenhuma refeicao cadastrada)");
            return;
        }
        int i = 1;
        for (Refeicao r : todas) {
            System.out.printf("%d) ", i++);
            imprimir(r);
        }
    }

    private static void buscar() {
        System.out.println("\n[BUSCAR]");
        String id = lerNaoVazio("ID: ");
        LocalDate data = lerDataISO("Data (yyyy-MM-dd): ");
        Refeicao r = repo.retrieveRefeicao(id, data);
        if (r == null) {
            System.out.println("Nao encontrado.");
        } else {
            imprimir(r);
        }
    }

    private static void atualizar() {
        System.out.println("\n[ATUALIZAR]");
        // chave antiga
        String idOld = lerNaoVazio("ID atual: ");
        LocalDate dataOld = lerDataISO("Data atual (yyyy-MM-dd): ");
        Refeicao antigo = repo.retrieveRefeicao(idOld, dataOld);
        if (antigo == null) {
            System.out.println("Nao encontrado. Nada a atualizar.");
            return;
        }
        System.out.println("Encontrado:");
        imprimir(antigo);

        // novos valores
        String idNew = lerNaoVazio("Novo ID: ");
        LocalDate dataNew = lerDataISO("Nova data (yyyy-MM-dd): ");

        // (opcional) evitar colidir em outro registro
        Refeicao colisao = repo.retrieveRefeicao(idNew, dataNew);
        if (colisao != null && !(idOld.equals(idNew) && dataOld.equals(dataNew))) {
            System.out.println("Ja existe refeicao com o novo ID e data. Atualizacao cancelada.");
            imprimir(colisao);
            return;
        }

        Refeicao novo = new Refeicao(idNew, dataNew);
        repo.updateRefeicao(antigo, novo);
        System.out.println("Atualizado com sucesso!");
        imprimir(novo);
    }

    private static void excluir() {
        System.out.println("\n[EXCLUIR]");
        String id = lerNaoVazio("ID: ");
        LocalDate data = lerDataISO("Data (yyyy-MM-dd): ");

        Refeicao r = repo.retrieveRefeicao(id, data);
        if (r == null) {
            System.out.println("Nao encontrado. Nada a excluir.");
            return;
        }
        repo.deleteRefeicao(r);
        System.out.println("Excluido com sucesso.");
    }

    // --- utilitários ---

    private static String lerNaoVazio(String prompt) {
        while (true) {
            System.out.print(prompt);
            String v = SC.nextLine().trim();
            if (!v.isEmpty()) return v;
            System.out.println("Valor nao pode ser vazio.");
        }
    }

    /**
     * Lê uma data no formato ISO yyyy-MM-dd (compatível com LocalDate.parse padrão).
     */
    private static LocalDate lerDataISO(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = SC.nextLine().trim();
            try {
                return LocalDate.parse(s);
            } catch (DateTimeParseException e) {
                System.out.println("Data invalida. Use o formato yyyy-MM-dd (ex.: 2025-10-09).");
            }
        }
    }

    private static void imprimir(Refeicao r) {
        // Como Refeicao nao tem toString, formatamos aqui de forma amigavel:
        System.out.printf("Refeicao{id='%s', data=%s}%n", r.getId(), String.valueOf(r.getData()));
    }
}
