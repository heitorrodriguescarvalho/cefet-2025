package br.com.cefetmg.batalhapokemon.model.eletrico;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Pikachu extends Pichu {
  public Pikachu(String apelido) {
    super(apelido);
    this.nivelEvolucao = 2;
    this.vidaMaxima = 35.0;
    this.vida = this.vidaMaxima;
    this.ataque = 55.0;
    definirDefesa(40.0);
    this.velocidade = 90.0;

    adicionarAtaque(new Ataque("Thunder Shock", 40, Tipo.ELETRICO));
  }

  @Override
  public Pokemon evoluir() {
    return new Raichu(this.getApelido());
  }

  @Override
  public void realizarSom() {
    System.out.println("Pika! Pika-chuuu!");
  }

}
