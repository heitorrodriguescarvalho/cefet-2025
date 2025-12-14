package br.com.cefetmg.batalhapokemon.model.planta;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Ivysaur extends Bulbasaur {
  public Ivysaur(String apelido) {
    super(apelido);
    this.nivelEvolucao = 2;
    this.vidaMaxima = 60.0;
    this.vida = this.vidaMaxima;
    this.ataque = 62.0;
    definirDefesa(63.0);
    this.velocidade = 60.0;

    adicionarAtaque(new Ataque("Vine Whip", 45, Tipo.PLANTA));
  }

  @Override
  public Pokemon evoluir() {
    return new Venusaur(this.getApelido());
  }

  @Override
  public void realizarSom() {
    System.out.println("vy! Ivysaur!");
  }

}
