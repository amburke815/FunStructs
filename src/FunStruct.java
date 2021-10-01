import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public interface FunStruct<X> {

  FunStruct<X> copy();

  int size();

  <Y> FunStruct<Y> map(Function<X, Y> mapper)
    throws NullArgumentException;

  FunStruct<X> replaceMap(Predicate<X> replaceIf, X replaceWith)
      throws NullArgumentException;

  FunStruct<X> findAndReplace(X toFind, X replaceWith)
      throws NullArgumentException;

  boolean andMap(Predicate<X> predicate)
      throws NullArgumentException;

  boolean contains(X amIContained)
      throws NullArgumentException;

  int countOccurrenceOf(X toCount)
      throws NullArgumentException;

  boolean orMap(Predicate<X> predicate)
      throws NullArgumentException;

  // TODO: abstract away fold mode invocations using a hashmap between fold modes and function objects with signature
  //~TODO~ possibly with signature () -> Y. Intialize as static member and share between subclasses (all can fold).
  <Y> Y fold(BiFunction<X, Y, Y> folder, Y base, EFoldMode biFoldMode)
      throws NullArgumentException;

  @Override
  boolean equals(Object o);

  @Override
  int hashCode();

  @Override
  String toString();

}
