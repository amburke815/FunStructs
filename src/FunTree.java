import java.util.List;
import java.util.function.BiFunction;

public interface FunTree<X> extends FunStruct<X> {

  <Y> Y foldPreorder(BiFunction<X, Y, Y> folder, Y base)
      throws NullArgumentException;

  <Y> Y foldPostorder(BiFunction<X, Y, Y> folder, Y base)
      throws NullArgumentException;

  <Y> Y foldInorder(BiFunction<X, Y, Y> folder, Y base)
    throws NullArgumentException;

  FunTree<X> invert();

  boolean isBalanced();

  List<X> flatten();

  int depth();
}
