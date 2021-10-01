import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunTreeNode<X> implements FunTree<X> {

  private final X data;
  private final FunTree<X> left;
  private final FunTree<X> right;

  FunTreeNode(X data, FunTree<X> left, FunTree<X> right) {
    this.data = U.notNull(data);
    this.left = U.notNull(left);
    this.right = U.notNull(right);
  }

  FunTreeNode(X data, FunTree<X> oneSubtree, boolean isLeftSubtree) {
    this.data = U.notNull(data);
    if (isLeftSubtree) {
      this.left = oneSubtree;
      this.right = new FunLeaf<>();
    } else {
      this.left = new FunLeaf<>();
      this.right = oneSubtree;
    }
  }

  FunTreeNode(X justData) {
    this(justData, new FunLeaf<>(), new FunLeaf<>());
  }


  @Override
  public FunStruct<X> copy() {
    return new FunTreeNode<>(data, left, right);
  }

  @Override
  public int size() {
    return 1 + this.left.size() + this.right.size();
  }

  @Override
  public <Y> FunStruct<Y> map(Function<X, Y> mapper) throws NullArgumentException {
    return new FunTreeNode<>(mapper.apply(data), (FunTree<Y>) left.map(mapper),
        (FunTree<Y>) right.map(mapper));
  }

  @Override
  public FunStruct<X> replaceMap(Predicate<X> replaceIf, X replaceWith)
      throws NullArgumentException {
    return replaceIf.test(data) ?
        new FunTreeNode<>(replaceWith, (FunTree<X>) left.replaceMap(replaceIf, replaceWith),
            (FunTree<X>) right.replaceMap(replaceIf, replaceWith)) :
        new FunTreeNode<>(data, (FunTree<X>) left.replaceMap(replaceIf, replaceWith),
            (FunTree<X>) right.replaceMap(replaceIf, replaceWith));
  }

  @Override
  public FunStruct<X> findAndReplace(X toFind, X replaceWith) throws NullArgumentException {
    return replaceMap(x -> x.equals(toFind), replaceWith);
  }

  @Override
  public boolean andMap(Predicate<X> predicate) throws NullArgumentException {
    return predicate.test(data) && left.andMap(predicate) && right.andMap(predicate);
  }

  @Override
  public boolean contains(X amIContained) throws NullArgumentException {
    return orMap(x -> x.equals(amIContained));
  }

  @Override
  public int countOccurrenceOf(X toCount) throws NullArgumentException {
    return ((FunTree<Integer>) this.map(x -> (x.equals(toCount) ? 1 : 0)))
        .foldPreorder((x1, x2) -> (x1 + x2), 0);
  }

  @Override
  public boolean orMap(Predicate<X> predicate) throws NullArgumentException {
    return predicate.test(data) || left.orMap(predicate) || right.orMap(predicate);
  }

  @Override
  public <Y> Y fold(BiFunction<X, Y, Y> folder, Y base, EFoldMode biFoldMode)
      throws NullArgumentException {
    return null; // TODO
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
