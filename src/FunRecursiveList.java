import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunRecursiveList<X> implements FunList<X> {

  //!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~members and ctors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!



  private final X first;
  private final FunList<X> rest;


  FunRecursiveList(X first, FunList<X> rest) {
    this.first = U.notNull(first);
    this.rest = U.notNull(rest);
  }

  FunRecursiveList(X first) {
    this(first, new FunBaseList<>());
  }

  //!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~overriden from FunList~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!
  @Override
  public FunList<X> appendToFront(X toAppend) throws IllegalArgumentException {
    return new FunRecursiveList<>(U.notNull(toAppend), (FunList<X>) this.copy());
  }

  @Override
  public FunList<X> appendToEnd(X toAppend) throws IllegalArgumentException {
    return new FunRecursiveList<>(first, rest.appendToEnd(toAppend));
  }

  @Override
  public <Y> FunList<Y> indexMap(Function<Integer, Y> indexMapper) throws IllegalArgumentException {
    return null; // TODO -> find way to use accumulator helper
  }

  @Override
  public X getElementAt(int targetIndex) throws IllegalArgumentException {
    return targetIndex == 0 ? first : rest.getElementAt(targetIndex - 1);
  }

  @Override
  public int indexOf(X indexMe) throws IllegalArgumentException {
    return 0; // todo
  }

  /*
  TODO: use indexOf to complete the rest -> has to track index recursively. Understand helper double-dispatch structure in order to complete
   */
  @Override
  public FunList<X> until(int index) throws IllegalArgumentException {
    return null; // TODO
  }

  @Override
  public FunList<X> after(int index) throws IllegalArgumentException {
    return null; // TODO
  }

  @Override
  public FunList<X> between(int startIndexIncl, int stopIndexIncl) throws IllegalArgumentException {
    return null; // TODO
  }
/*
END-TODO
 */

  @Override
  public FunList<X> filter(Predicate<X> keepIf) {
    return keepIf.test(first) ? new FunRecursiveList<>(first, rest.filter(keepIf)) : rest.filter(keepIf);
  }

  @Override
  public <Y> Y foldr(BiFunction<X, Y, Y> folder, Y base) {
    return folder.apply(first, rest.foldr(folder, base));
  }

  @Override
  public FunList<X> reverse() {
    return null; // TODO
  }

  @Override
  public FunList<X> sort(Comparator<X> biComparator) {
    return null; // TODO
  }

  //!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~overriden from FunStruct~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!

  @Override
  public FunStruct<X> copy() {
    return new FunRecursiveList<>(first, rest);
  }

  @Override
  public int size() {
    return 1 + rest.size();
  }

  @Override
  public FunStruct<X> replaceMap(Predicate<X> replaceIf, X replaceWith)
      throws NullArgumentException {
    return replaceIf.test(first) ?
        new FunRecursiveList<>(replaceWith,
            (FunRecursiveList<X>) rest.replaceMap(replaceIf, replaceWith)) :
        new FunRecursiveList<>(first,
            (FunRecursiveList<X>) rest.replaceMap(replaceIf, replaceWith));
  }

  @Override
  public FunStruct<X> findAndReplace(X toFind, X replaceWith) throws NullArgumentException {
    return replaceMap(x -> x.equals(toFind), replaceWith); // this one is satisfying :-)
  }

  @Override
  public boolean andMap(Predicate<X> predicate) throws NullArgumentException {
    return predicate.test(first) && rest.andMap(predicate);
  }

  @Override
  public boolean contains(X amIContained) throws NullArgumentException {
    return orMap(x -> x.equals(amIContained)); // this one is satisfying too
  }

  @Override
  public int countOccurrenceOf(X toCount) throws NullArgumentException {
    return filter(x -> x.equals(toCount)).size();
  }

  @Override
  public boolean orMap(Predicate<X> predicate) throws NullArgumentException {
    return predicate.test(first) || rest.orMap(predicate);
  }

  @Override
  public <Y> Y fold(BiFunction<X, Y, Y> folder, Y base, EFoldMode biFoldMode)
      throws NullArgumentException {
    return null; // TODO
  }

  //!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~private helpers~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!


}
