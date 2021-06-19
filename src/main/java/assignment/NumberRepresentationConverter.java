package assignment;

import static java.math.BigInteger.ZERO;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class NumberRepresentationConverter {

  private static final BigInteger TEN = new BigInteger("10");
  private static final String TEMPLATE = "%s.%s%s";
  private static final Map<Integer, Function<BigInteger, String>> CONVERTERS_MAP = new HashMap<>();

  static {
    for (final NumberShortNotation notation : NumberShortNotation.values()) {
      final int min = notation.getDecimalExponent();
      CONVERTERS_MAP.put(min, bigInteger -> doConvert(notation, min, bigInteger));
      CONVERTERS_MAP.put(min + 1, bigInteger -> doConvert(notation, min, bigInteger));
      CONVERTERS_MAP.put(min + 2, bigInteger -> doConvert(notation, min, bigInteger));
    }
  }

  private static String doConvert(
      final NumberShortNotation notation, final int min, final BigInteger bigInteger) {
    final BigInteger[] bigIntegers = bigInteger.divideAndRemainder(TEN.pow(min));

    final BigInteger whole = bigIntegers[0];
    final BigInteger remainder = bigIntegers[1];

    if (ZERO.equals(remainder)) {
      return 1 + notation.name();
    }

    return String.format(
        TEMPLATE, whole.toString(), remainder.toString().charAt(0), notation.name());
  }

  public String convertToShortString(final BigInteger number) {
    if (number == null) {
      return null;
    }

    final BigInteger absoluteNumber = number.abs();
    final String absoluteNumberAsString = absoluteNumber.toString();
    final int absoluteNumberLength = absoluteNumberAsString.length();

    // if its under |1000|, just return as is
    if (absoluteNumberLength < 4) {
      return number.toString();
    }

    final int decimalExponent = absoluteNumberLength - 1;
    final String result = CONVERTERS_MAP.get(decimalExponent).apply(absoluteNumber);
    return isPositive(number) ? result : "-" + result;
  }

  private boolean isPositive(final BigInteger number) {
    return number.signum() > 0;
  }
}
