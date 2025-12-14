package br.com.cefetmg.batalhapokemon.model.agua;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Poliwrath extends Poliwhirl {
  public Poliwrath(String apelido) {
    super(apelido);
    this.nivelEvolucao = 3;
    this.vidaMaxima = 90.0;
    this.vida = this.vidaMaxima;
    this.ataque = 95.0;
    definirDefesa(95.0);
    this.velocidade = 70.0;

    adicionarAtaque(new Ataque("Submission", 80, Tipo.FOGO));
  }

  @Override
  public Pokemon evoluir() {
    return this;
  }

  @Override
  public void realizarSom() {
    System.out.println("Poli–wraaaath!");
  }

}
