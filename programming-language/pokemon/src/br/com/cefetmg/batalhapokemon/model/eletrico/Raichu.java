package br.com.cefetmg.batalhapokemon.model.eletrico;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Raichu extends Pikachu {
  public Raichu(String apelido) {
    super(apelido);
    this.nivelEvolucao = 3;
    this.vidaMaxima = 60.0;
    this.vida = this.vidaMaxima;
    this.ataque = 90.0;
    definirDefesa(55.0);
    this.velocidade = 110.0;

    adicionarAtaque(new Ataque("Thunder Punch", 75, Tipo.ELETRICO));
  }

  @Override
  public Pokemon evoluir() {
    return this;
  }

  @Override
  public void realizarSom() {
    System.out.println("Raii–chuuu!");
  }

}
