package br.com.cefetmg.batalhapokemon.model.planta;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Venusaur extends Ivysaur {
  public Venusaur(String apelido) {
    super(apelido);
    this.nivelEvolucao = 3;
    this.vidaMaxima = 80.0;
    this.vida = this.vidaMaxima;
    this.ataque = 82.0;
    definirDefesa(83.0);
    this.velocidade = 80.0;

    adicionarAtaque(new Ataque("Razor Leaf", 55, Tipo.PLANTA));
  }

  @Override
  public Pokemon evoluir() {
    return this;
  }

  @Override
  public void realizarSom() {
    System.out.println("Ve–nu–saur!");
  }

}
