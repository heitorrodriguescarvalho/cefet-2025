package br.com.cefetmg.batalhapokemon.model.planta;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Bulbasaur extends Pokemon {
  public Bulbasaur(String apelido) {
    super(
        apelido,
        "Bulbasaur",
        Tipo.PLANTA,
        1,
        45.0,
        49.0,
        49.0,
        45.0);

    adicionarAtaque(new Ataque("Tackle", 40, Tipo.NORMAL));
  }

  @Override
  public Pokemon evoluir() {
    return new Ivysaur(this.getApelido());
  }

  @Override
  public void realizarSom() {
    System.out.println("Bulba! Bulbasaur!");
  }

}
