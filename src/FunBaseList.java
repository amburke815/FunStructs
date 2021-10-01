import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;


public class FunBaseList<X> implements FunList<X> {

  FunBaseList() {
  }

  @Override
  public FunList<X> appendToFront(X toAppend) throws IllegalArgumentException {
    return new FunRecursiveList<>(toAppend, new FunBaseList<>());
  }

  @Override
  public FunList<X> appendToEnd(X toAppend) throws IllegalArgumentException {
    return appendToFront(toAppend); // TODO. May want to change this, reason for having it noted in FunList
  }

  @Override
  public <Y> FunList<Y> indexMap(Function<Integer, Y> indexMapper) throws IllegalArgumentException {
    return new FunBaseList<>();
  }

  @Override
  public X getElementAt(int index) throws IllegalArgumentException {
    throw new IllegalArgumentException("No such index");
  }

  @Override
  public int indexOf(X indexMe) throws IllegalArgumentException {
    throw new IllegalArgumentException("No such element " + indexMe.toString());
  }

  // TODO check indices for these next three and throw exceptions accordingly
  @Override
  public FunList<X> until(int index) throws IllegalArgumentException {
    return new FunBaseList<>();
  }

  @Override
  public FunList<X> after(int index) throws IllegalArgumentException {
    return new FunBaseList<>();
  }

  @Override
  public FunList<X> between(int startIndexIncl, int stopIndexIncl) throws IllegalArgumentException {
    return new FunBaseList<>();
  }

  @Override
  public FunList<X> filter(Predicate<X> keepIf) {
    return new FunBaseList<>();
  }

  @Override
  public <Y> Y foldr(BiFunction<X, Y, Y> folder, Y base) {
    return base;
  }

  @Override
  public FunList<X> reverse() {
    return new FunBaseList<>();
  }

  @Override
  public FunList<X> sort(Comparator<X> biComparator) {
    return new FunBaseList<>();
  }

  @Override
  public FunStruct<X> copy() {
    return new FunBaseList<>();
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public <Y> FunStruct<Y> map(Function<X, Y> mapper) throws NullArgumentException {
    return new FunBaseList<>();
  }

  @Override
  public FunStruct<X> replaceMap(Predicate<X> replaceIf, X replaceWith)
      throws NullArgumentException {
    return new FunBaseList<>();
  }

  @Override
  public FunStruct<X> findAndReplace(X toFind, X replaceWith) throws NullArgumentException {
    return new FunBaseList<>();
  }

  @Override
  public boolean andMap(Predicate<X> predicate) throws NullArgumentException {
    return true;
  }

  @Override
  public boolean contains(X amIContained) throws NullArgumentException {
    return false;
  }

  @Override
  public int countOccurrenceOf(X toCount) throws NullArgumentException {
    return 0;
  }

  @Override
  public boolean orMap(Predicate<X> predicate) throws NullArgumentException {
    return false;
  }

  @Override
  public <Y> Y fold(BiFunction<X, Y, Y> folder, Y base, EFoldMode biFoldMode)
      throws NullArgumentException {
    return base;
  }

}
