package br.com.cefetmg.batalhapokemon.model.normal;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Pidgeot extends Pidgeotto {
  public Pidgeot(String apelido) {
    super(apelido);
    this.nivelEvolucao = 3;
    this.vidaMaxima = 83.0;
    this.vida = this.vidaMaxima;
    this.ataque = 80.0;
    definirDefesa(75.0);
    this.velocidade = 101.0;

    adicionarAtaque(new Ataque("Hurricane", 110, Tipo.FOGO));
  }

  @Override
  public Pokemon evoluir() {
    return this;
  }

  @Override
  public void realizarSom() {
    System.out.println("Pidgeooot!");
  }

}
