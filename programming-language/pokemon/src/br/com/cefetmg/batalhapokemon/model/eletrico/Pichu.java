package br.com.cefetmg.batalhapokemon.model.eletrico;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Pichu extends Pokemon {
  public Pichu(String apelido) {
    super(
        apelido,
        "Pichu",
        Tipo.ELETRICO,
        1,
        35.0,
        35.0,
        40.0,
        60.0);

    adicionarAtaque(new Ataque("Thunder Shock", 40, Tipo.ELETRICO));
  }

  @Override
  public Pokemon evoluir() {
    return new Pikachu(this.getApelido());
  }

  @Override
  public void realizarSom() {
    System.out.println("Pii-chuuu!");
  }

}
