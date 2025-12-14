package br.com.cefetmg.batalhapokemon.model;

import java.util.ArrayList;
import java.util.List;

import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public abstract class Pokemon {
  // Atributos fixos
  private String apelido;
  protected String especie;
  protected Tipo tipo;
  protected int nivelEvolucao; // 1 (Base), 2, 3 (Max)

  // Status dinâmicos
  protected double vida;
  protected double vidaMaxima;
  protected double ataque;
  protected double defesa;
  protected double defesaAtual;
  protected double velocidade;

  // Lógica do Trabalho
  protected int experiencia = 0;
  protected int pocoes = 0; // Começa com 0
  protected int contadorAtaquesBemSucedidos = 0;

  private List<Ataque> ataques = new ArrayList<>();

  public Pokemon(String apelido, String especie, Tipo tipo, int nivelEvolucao, double vidaMaxima, double ataque,
      double defesa, double velocidade) {
    this.apelido = apelido;
    this.especie = especie;
    this.tipo = tipo;
    this.nivelEvolucao = nivelEvolucao;
    this.vidaMaxima = vidaMaxima;
    this.vida = vidaMaxima;
    this.ataque = ataque;
    definirDefesa(defesa);
    this.velocidade = velocidade;
  }

  // --- Lógica de Batalha ---

  public void atacar(Pokemon oponente, Ataque golpe) {
    System.out.printf("⚔️ %s usou %s!%n", this.apelido, golpe.nome());

    double multiplicador = Tipo.obterMultiplicador(golpe.tipo(), oponente.getTipo());

    // Feedback visual e Cálculo de XP
    int xpGanho = 15; // XP Neutro padrão
    if (multiplicador > 1.0) {
      System.out.println("🔥 Foi super efetivo!");
      xpGanho = 20;
    } else if (multiplicador < 1.0 && multiplicador > 0) {
      System.out.println("🛡️ Não foi muito efetivo...");
      xpGanho = 10;
    } else {
      System.out.println("😐 Dano normal.");
    }

    // Dano: (Atk * Poder / 20) - (Def / 3) * Multiplicador
    double danoBase = ((this.ataque / 5) + (double) golpe.poder() / 2);
    double danoReal = Math.max(1, (danoBase - (oponente.getDefesa() / 3)) * multiplicador);

    oponente.receberDano(danoReal);

    // Lógica de Recompensa (Poção e XP)
    this.contadorAtaquesBemSucedidos++;
    if (this.contadorAtaquesBemSucedidos % 2 == 0) {
      this.pocoes++;
      System.out.println("💊 " + this.apelido + " ganhou uma Poção de Cura por bons ataques!");
    }

    ganharExperiencia(xpGanho);
  }

  public void receberDano(double dano) {
    double danoRestante = dano;

    if (this.defesaAtual > 0) {
      double danoAbsorvido = Math.min(this.defesaAtual, danoRestante);
      this.defesaAtual -= danoAbsorvido;
      danoRestante -= danoAbsorvido;
      System.out.printf("🛡️ %s absorveu %.1f de dano. (DEF: %.1f/%.1f)%n", this.apelido, danoAbsorvido,
          this.defesaAtual, this.defesa);
    }

    if (danoRestante > 0) {
      this.vida -= danoRestante;
      System.out.printf("💥 %s recebeu %.1f de dano. (VIDA: %.1f/%.1f)%n", this.apelido, danoRestante,
          Math.max(0, this.vida), this.vidaMaxima);
    } else {
      System.out.printf("✨ O dano foi totalmente bloqueado pela defesa de %s!%n", this.apelido);
    }
  }

  public boolean usarPocao() {
    if (this.pocoes > 0) {
      this.pocoes--;
      double cura = 20.0;
      this.vida += cura;
      if (this.vida > this.vidaMaxima)
        this.vida = this.vidaMaxima;
      System.out.printf("✨ %s usou uma poção! Recuperou %.1f VIDA. (Restam %d poções)%n", this.apelido, cura,
          this.pocoes);
      return true;
    } else {
      System.out.println("❌ Você não tem poções!");
      return false;
    }
  }

  // --- Lógica de Evolução ---

  private void ganharExperiencia(int xp) {
    this.experiencia += xp;
    System.out.printf("🎓 %s ganhou %d XP. (Total: %d/100)%n", this.apelido, xp, this.experiencia);
  }

  /**
   * Verifica se deve evoluir. Se sim, retorna a NOVA instância.
   * Se não, retorna 'this' (a própria instância atual).
   */
  public Pokemon tentarEvoluir() {
    if (this.experiencia >= 100 && this.nivelEvolucao < 3) {
      System.out.println("\n✨✨ O QUE? " + this.apelido + " ESTÁ EVOLUINDO! ✨✨");
      Pokemon evoluido = evoluir(); // Método abstrato implementado pelos filhos

      // Transfere o estado importante
      evoluido.pocoes = this.pocoes;
      // Reseta XP ou transfere excedente (opcional, aqui reseta para o novo nível)
      evoluido.experiencia = 0;

      // Mantém a porcentagem de vida atual ou cura? Vamos curar na evolução (bônus)
      System.out.println("🎉 " + this.apelido + " se tornou um " + evoluido.especie + "!\n");
      return evoluido;
    }
    return this;
  }

  // Métodos abstratos e auxiliares
  public abstract Pokemon evoluir();

  public abstract void realizarSom();

  public void adicionarAtaque(Ataque a) {
    ataques.add(a);
  }

  // Getters
  public boolean estaVivo() {
    return vida > 0;
  }

  public double getVelocidade() {
    return velocidade;
  }

  public String getApelido() {
    return apelido;
  }

  public String getEspecie() {
    return especie;
  }

  public List<Ataque> getAtaques() {
    return ataques;
  }

  public double getDefesa() {
    return defesa;
  }

  public double getDefesaAtual() {
    return defesaAtual;
  }

  public Tipo getTipo() {
    return tipo;
  }

  public int getNivelEvolucao() {
    return nivelEvolucao;
  }

  public int getPocoes() {
    return pocoes;
  }

  public double getVida() {
    return vida;
  }

  public double getVidaMaxima() {
    return this.vidaMaxima;
  }

  // Construtor protegido para cópia de dados na evolução
  protected void copiarDados(Pokemon antigo) {
    this.pocoes = antigo.pocoes;
    // Vida enche na evolução
  }

  @Override
  public String toString() {
    return "Pokemon: " + apelido + " (" + especie + ") | VIDA: " + vida + "/" + vidaMaxima + " | DEF: "
        + defesaAtual + "/" + defesa;
  }

  protected void definirDefesa(double valorBase) {
    this.defesa = valorBase;
    this.defesaAtual = Math.max(0, valorBase);
  }

}