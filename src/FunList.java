import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public interface FunList<X> extends FunStruct<X> {

  FunList<X> appendToFront(X toAppend)
      throws IllegalArgumentException;

  /**
   * Appends the new element to the end of a non-empty list.
   * If an empty list calls this method then a list is returned with this added element, then the empty list.
   * @param toAppend
   * @return
   * @throws IllegalArgumentException
   */
  FunList<X> appendToEnd(X toAppend)
      throws IllegalArgumentException;

  <Y> FunList<Y> indexMap(Function<Integer, Y> indexMapper)
      throws IllegalArgumentException;

  X getElementAt(int index)
      throws IllegalArgumentException;

  int indexOf(X indexMe) // TODO keep track with accumulator
      throws IllegalArgumentException;

  FunList<X> until(int index)
    throws IllegalArgumentException;

  FunList<X> after(int index)
    throws IllegalArgumentException;

  FunList<X> between(int startIndexIncl, int stopIndexIncl)
    throws IllegalArgumentException;

  FunList<X> filter(Predicate<X> keepIf);

  <Y> Y foldr(BiFunction<X, Y, Y> folder, Y base);

  FunList<X> reverse();

  FunList<X> sort(Comparator<X> biComparator);




}
