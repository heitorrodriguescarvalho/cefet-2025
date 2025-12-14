import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import br.com.cefetmg.batalhapokemon.infra.PokedexRegistry;
import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    // 1. Inicializa e descobre Pokémons (apenas nível 1)
    PokedexRegistry.inicializar("br.com.cefetmg.batalhapokemon.model");
    List<String> especies = PokedexRegistry.getEspeciesDisponiveis();

    if (especies.isEmpty()) {
      System.err.println("Nenhum Pokémon encontrado no pacote. Verifique as classes.");
      return;
    }

    System.out.println("=== BATTLE POKÉMON JAVA 21 ===");

    // 2. Escolha do Jogador
    System.out.println("Escolha seu parceiro:");
    for (int i = 0; i < especies.size(); i++) {
      System.out.println((i + 1) + ". " + especies.get(i));
    }

    System.out.print("Digite o número do Pokémon: ");
    int escolha = scanner.nextInt();
    scanner.nextLine(); // Consumir quebra de linha
    String especieJog = especies.get(escolha - 1);

    System.out.print("Dê um apelido para ele: ");
    String apelidoJog = scanner.nextLine();

    Pokemon player = PokedexRegistry.criar(especieJog, apelidoJog);

    // --- 3. Escolha do Computador (LÓGICA ALTERADA) ---

    // Criamos uma lista de opções para o Bot, copiando a lista original
    List<String> opcoesBot = new ArrayList<>(especies);

    // Removemos a espécie que o jogador escolheu
    opcoesBot.remove(especieJog);

    String especieBot;

    // Verificação de segurança: Se só existe 1 tipo de pokémon no sistema (ex: Só
    // criou Charmander),
    // o Bot é obrigado a jogar com ele também, senão a lista fica vazia e dá erro.
    if (opcoesBot.isEmpty()) {
      System.out.println("⚠️ Aviso: Apenas uma espécie registrada no sistema. O Rival usará a mesma espécie.");
      especieBot = especieJog;
    } else {
      // Sorteia da lista filtrada
      especieBot = opcoesBot.get(random.nextInt(opcoesBot.size()));
    }

    Pokemon bot = PokedexRegistry.criar(especieBot, "Rival " + especieBot);

    System.out.println("\n-------------------------------------------");
    System.out.println("BATALHA INICIADA: " + player.getApelido() + " VS " + bot.getApelido());
    System.out.println("-------------------------------------------");

    // 4. Definição de quem começa (Velocidade)
    boolean turnoPlayer = player.getVelocidade() >= bot.getVelocidade();
    if (player.getVelocidade() == bot.getVelocidade())
      turnoPlayer = random.nextBoolean(); // Desempate aleatório

    // LOOP DE BATALHA
    while (player.estaVivo() && bot.estaVivo()) {
      System.out.println("\n>>> Status Atual <<<");
      System.out.printf("👤 %s (%s): %.0f/%.0f VIDA | DEF %.0f/%.0f | %d Poções\n",
          player.getApelido(),
          player.getEspecie(),
          player.getVida(),
          player.getVidaMaxima(),
          player.getDefesaAtual(),
          player.getDefesa(),
          player.getPocoes());
      System.out.printf("🤖 %s (%s): %.0f/%.0f VIDA | DEF %.0f/%.0f | %d Poções\n",
          bot.getApelido(),
          bot.getEspecie(),
          bot.getVida(),
          bot.getVidaMaxima(),
          bot.getDefesaAtual(),
          bot.getDefesa(),
          bot.getPocoes());
      System.out.println("-------------------------------------------");

      if (turnoPlayer) {
        realizarTurnoJogador(scanner, player, bot);
      } else {
        realizarTurnoBot(bot, player);
      }

      // Checagem de morte antes da evolução
      if (!bot.estaVivo() || !player.estaVivo())
        break;

      // Checagem de Evolução Automática
      // ATENÇÃO: Se evoluir, a variável player/bot passa a apontar para o novo objeto
      player = player.tentarEvoluir();
      bot = bot.tentarEvoluir();

      // Alterna o turno
      turnoPlayer = !turnoPlayer;

      try {
        Thread.sleep(1500);
      } catch (InterruptedException e) {
      } // Pausa dramática
    }

    // Fim de jogo
    System.out.println("\n===========================================");
    if (player.estaVivo()) {
      System.out.println("🏆 VITÓRIA! \n" + player.getApelido() + " venceu a batalha!");
      player.realizarSom();
    } else {
      System.out.println("💀 DERROTA... " + player.getApelido() + " desmaiou.");
    }
    System.out.println("===========================================");
  }

  private static void realizarTurnoJogador(Scanner scanner, Pokemon atacante, Pokemon alvo) {
    System.out.println("\n SUA VEZ! O que deseja fazer?");
    System.out.println("1. Atacar");
    System.out.println("2. Curar (Custo: 1 Poção)");

    int acao = 0;
    try {
      acao = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      acao = 1;
    }

    if (acao == 2) {
      boolean curou = atacante.usarPocao();
      if (!curou) {
        // Se não tinha poção, perdeu a vez de atacar? Ou volta o menu?
        // Para simplificar o código didático, se tentar curar sem poção, perde o turno
        // atacando automaticamente com golpe fraco ou apenas perde a vez.
        // Vamos forçar ataque se não tiver poção para não travar o jogo.
        System.out.println("Atacando por falta de poções...");
        selecionarAtaque(scanner, atacante, alvo);
      }
    } else {
      selecionarAtaque(scanner, atacante, alvo);
    }
  }

  private static void selecionarAtaque(Scanner scanner, Pokemon atacante, Pokemon alvo) {
    System.out.println("Escolha o ataque:");
    List<Ataque> ataques = atacante.getAtaques();
    for (int i = 0; i < ataques.size(); i++) {
      System.out.println((i + 1) + ". " + ataques.get(i).nome() + " (Poder: " + ataques.get(i).poder() + " | Tipo: "
          + ataques.get(i).tipo() + ")");
    }

    int index = 0;
    try {
      index = Integer.parseInt(scanner.nextLine()) - 1;
    } catch (NumberFormatException e) {
    }

    if (index < 0 || index >= ataques.size())
      index = 0; // Default attack

    atacante.atacar(alvo, ataques.get(index));
  }

  private static void realizarTurnoBot(Pokemon atacante, Pokemon alvo) {
    System.out.println("\n🤖 Vez do Computador...");

    // IA Simples: Se vida < 30% e tem poção, cura. Senão, ataca aleatoriamente.
    if (atacante.getVida() < (atacante.getVidaMaxima() * 0.3) && atacante.getPocoes() > 0) {
      atacante.usarPocao();
    } else {
      List<Ataque> ataques = atacante.getAtaques();
      Ataque ataqueEscolhido = ataques.get(new Random().nextInt(ataques.size()));
      atacante.atacar(alvo, ataqueEscolhido);
    }
  }
}