package br.com.cefetmg.batalhapokemon.model.agua;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Poliwhirl extends Poliwag {
  public Poliwhirl(String apelido) {
    super(apelido);
    this.nivelEvolucao = 2;
    this.vidaMaxima = 65.0;
    this.vida = this.vidaMaxima;
    this.ataque = 65.0;
    definirDefesa(65.0);
    this.velocidade = 90.0;

    adicionarAtaque(new Ataque("Water Gun", 40, Tipo.AGUA));
  }

  @Override
  public Pokemon evoluir() {
    return new Poliwrath(this.getApelido());
  }

  @Override
  public void realizarSom() {
    System.out.println("Poli-whiirl!");
  }

}
