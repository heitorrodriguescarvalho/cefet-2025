package br.com.cefetmg.batalhapokemon.model.normal;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Pidgey extends Pokemon {
  public Pidgey(String apelido) {
    super(
        apelido,
        "Pidgey",
        Tipo.NORMAL,
        1,
        40.0,
        45.0,
        40.0,
        56.0);

    adicionarAtaque(new Ataque("Gust", 40, Tipo.FOGO));
  }

  @Override
  public Pokemon evoluir() {
    return new Pidgeotto(this.getApelido());
  }

  @Override
  public void realizarSom() {
    System.out.println("Pid-geyy!");
  }

}
