package assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.math.BigInteger;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NumberRepresentationConverterTest {

  private final NumberRepresentationConverter objectUnderTest = new NumberRepresentationConverter();

  private static Stream<Arguments> testArguments() {
    return Stream.of(
        arguments(new BigInteger("0"), "0"),
        arguments(new BigInteger("555"), "555"),
        arguments(new BigInteger("999"), "999"),
        arguments(new BigInteger("1000"), "1K"),
        arguments(new BigInteger("-555"), "-555"),
        arguments(new BigInteger("-999"), "-999"),
        arguments(new BigInteger("-1000"), "-1K"),
        arguments(new BigInteger("-1111"), "-1.1K"),
        arguments(new BigInteger("1111"), "1.1K"),
        arguments(new BigInteger("99999"), "99.9K"),
        arguments(new BigInteger("999999"), "999.9K"),
        arguments(new BigInteger("456789876543456787"), "456.7q"));
  }

  @ParameterizedTest
  @MethodSource("testArguments")
  void convertToShortString(final BigInteger provided, final String expected) {
    final String actual = objectUnderTest.convertToShortString(provided);
    assertEquals(expected, actual);
  }
}
