package br.com.cefetmg.batalhapokemon.model.fogo;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Charizard extends Charmeleon {
  public Charizard(String apelido) {
    super(apelido);
    this.nivelEvolucao = 3;
    this.vidaMaxima = 78.0;
    this.vida = this.vidaMaxima;
    this.ataque = 84.0;
    definirDefesa(78.0);
    this.velocidade = 100.0;

    adicionarAtaque(new Ataque("Wing Attack", 60, Tipo.FOGO));
  }

  @Override
  public Pokemon evoluir() {
    return this;
  }

  @Override
  public void realizarSom() {
    System.out.println("Chari-zaaard!");
  }

}
