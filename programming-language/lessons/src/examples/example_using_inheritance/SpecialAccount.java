package examples.example_using_inheritance;

class SpecialAccount extends Account {
  private double limit;

  public SpecialAccount(String name, int id, double amount, double limit) {
    super(name, id, amount);

    this.limit = limit;
  }

  double getLimit() {
    return this.limit;
  }

  double calculateLimit() {
    this.limit = this.getAmount() * 2.5;

    return this.limit;
  }
}