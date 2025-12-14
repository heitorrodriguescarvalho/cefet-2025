package br.com.cefetmg.batalhapokemon.model.normal;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Pidgeotto extends Pidgey {
  public Pidgeotto(String apelido) {
    super(apelido);
    this.nivelEvolucao = 2;
    this.vidaMaxima = 63.0;
    this.vida = this.vidaMaxima;
    this.ataque = 60.0;
    definirDefesa(55.0);
    this.velocidade = 71.0;

    adicionarAtaque(new Ataque("Wing Attack", 60, Tipo.FOGO));
  }

  @Override
  public Pokemon evoluir() {
    return new Pidgeot(this.getApelido());
  }

  @Override
  public void realizarSom() {
    System.out.println("Pidge-oooot!");
  }

}
