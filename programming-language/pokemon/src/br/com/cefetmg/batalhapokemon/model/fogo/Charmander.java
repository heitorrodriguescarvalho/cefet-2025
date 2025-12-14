package br.com.cefetmg.batalhapokemon.model.fogo;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Charmander extends Pokemon {
  public Charmander(String apelido) {
    super(
        apelido,
        "Charmander",
        Tipo.FOGO,
        1,
        39.0,
        52.0,
        43.0,
        65.0);

    adicionarAtaque(new Ataque("Scratch", 40, Tipo.NORMAL));
  }

  @Override
  public Pokemon evoluir() {
    return new Charmeleon(this.getApelido());
  }

  @Override
  public void realizarSom() {
    System.out.println("Char! Char-man-der!");
  }

}
