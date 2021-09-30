public class U { // TODO rename

  public static <X> X notNull(X toCheck)
      throws IllegalArgumentException {
    if (toCheck == null) {
      throw new NullArgumentException("null parameter");
    }
    return toCheck;
  }

  public static int intBetween(int lowerBoundIncl, int toCheck, int higherBoundIncl) {
    if (toCheck < lowerBoundIncl || toCheck > higherBoundIncl) {
      throw new IllegalArgumentException(
          "Invalid number: Integer " + toCheck + " out of bounds for range [" +
              lowerBoundIncl + "," + higherBoundIncl + "]");
    }

    return toCheck;
  }

}
