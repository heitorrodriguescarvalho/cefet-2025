package exercises.bank_account;

public class Conta {
  int numero;
  String nome_titular;
  double saldo;

  void depositar(double valor) {
    this.saldo += valor;
  }

  boolean sacar(double valor) {
    if (this.saldo < valor)
      return false;

    this.saldo -= valor;

    return true;
  }
}
