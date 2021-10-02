package structs.tree;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import structs.utils.EFoldMode;
import structs.FunStruct;
import structs.utils.NullArgumentException;

public class FunLeaf<X> implements FunTree<X> {

  @Override
  public FunStruct<X> copy() {
    return null;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public <Y> FunStruct<Y> map(Function<X, Y> mapper) throws NullArgumentException {
    return null;
  }

  @Override
  public FunStruct<X> replaceMap(Predicate<X> replaceIf, X replaceWith)
      throws NullArgumentException {
    return null;
  }

  @Override
  public FunStruct<X> findAndReplace(X toFind, X replaceWith) throws NullArgumentException {
    return null;
  }

  @Override
  public boolean andMap(Predicate<X> predicate) throws NullArgumentException {
    return false;
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
    return null;
  }

  @Override
  public <Y> Y foldPreorder(BiFunction<X, Y, Y> folder, Y base) throws NullArgumentException {
    return null;
  }

  @Override
  public <Y> Y foldPostorder(BiFunction<X, Y, Y> folder, Y base) throws NullArgumentException {
    return null;
  }

  @Override
  public <Y> Y foldInorder(BiFunction<X, Y, Y> folder, Y base) throws NullArgumentException {
    return null;
  }

  @Override
  public FunTree<X> invert() {
    return null;
  }

  @Override
  public boolean isBalanced() {
    return false;
  }

  @Override
  public List<X> flatten() {
    return null;
  }

  @Override
  public int depth() {
    return 0;
  }
}
