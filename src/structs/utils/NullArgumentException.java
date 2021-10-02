package structs.utils;

public class NullArgumentException extends IllegalArgumentException {
  NullArgumentException(String msg) {
    super(msg);
  }
}
