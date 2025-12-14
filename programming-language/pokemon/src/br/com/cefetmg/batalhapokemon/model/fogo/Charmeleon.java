package br.com.cefetmg.batalhapokemon.model.fogo;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Charmeleon extends Charmander {
  public Charmeleon(String apelido) {
    super(apelido);
    this.nivelEvolucao = 2;
    this.vidaMaxima = 58.0;
    this.vida = this.vidaMaxima;
    this.ataque = 64.0;
    definirDefesa(58.0);
    this.velocidade = 80.0;

    adicionarAtaque(new Ataque("Ember", 40, Tipo.FOGO));
  }

  @Override
  public Pokemon evoluir() {
    return new Charizard(this.getApelido());
  }

  @Override
  public void realizarSom() {
    System.out.println("Char-me-leooon!");
  }

}
