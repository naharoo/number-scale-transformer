package assignment;

public enum NumberShortNotation {
  K(3),
  M(6),
  B(9),
  T(12),
  q(15),
  Q(18),
  S(21);

  private final int decimalExponent;

  NumberShortNotation(final int decimalExponent) {
    this.decimalExponent = decimalExponent;
  }

  public int getDecimalExponent() {
    return decimalExponent;
  }
}
