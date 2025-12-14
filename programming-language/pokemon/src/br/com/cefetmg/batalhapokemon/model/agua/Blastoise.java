package br.com.cefetmg.batalhapokemon.model.agua;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Blastoise extends Wartortle {
  public Blastoise(String apelido) {
    super(apelido);
    this.nivelEvolucao = 3;
    this.vidaMaxima = 79.0;
    this.vida = this.vidaMaxima;
    this.ataque = 83.0;
    definirDefesa(100.0);
    this.velocidade = 78.0;

    adicionarAtaque(new Ataque("Flash Cannon", 80, Tipo.FOGO));
  }

  @Override
  public Pokemon evoluir() {
    return this;
  }

  @Override
  public void realizarSom() {
    System.out.println("Blastoooiise!");
  }

}
