package br.com.cefetmg.batalhapokemon.model.agua;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Wartortle extends Squirtle {
  public Wartortle(String apelido) {
    super(apelido);
    this.nivelEvolucao = 2;
    this.vidaMaxima = 59.0;
    this.vida = this.vidaMaxima;
    this.ataque = 63.0;
    definirDefesa(80.0);
    this.velocidade = 58.0;

    adicionarAtaque(new Ataque("Water Gun", 40, Tipo.AGUA));
  }

  @Override
  public Pokemon evoluir() {
    return new Blastoise(this.getApelido());
  }

  @Override
  public void realizarSom() {
    System.out.println("Wartoortle!");
  }

}
