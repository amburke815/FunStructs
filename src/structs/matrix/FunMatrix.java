package structs.matrix;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import structs.FunStruct;
import structs.utils.NullArgumentException;

public interface FunMatrix<X> extends FunStruct<X> {

  <Y> Y foldNorthWest(BiFunction<X, Y, Y> folder, Y base)
      throws NullArgumentException;

  <Y> Y foldSouthEast(BiFunction<X, Y, Y> folder, Y base)
      throws NullArgumentException;

  X getElementAt(int row, int col)
      throws IllegalArgumentException;

  List<X> asList();

  FunMatrix<X> subMatrix(int firstRowIncl, int lastRowIncl, int firstColIncl, int lastColIncl)
      throws IllegalArgumentException;

  FunMatrix<X> subMatrix(int lastRowIncl, int lastColIncl)
      throws IllegalArgumentException;

  FunMatrix<X> reflectAcross(EReflectionAxis reflectionAxis)
      throws NullArgumentException;

  FunMatrix<X> diagonalize();

  FunMatrix<X> reducedRowEchelonForm(Comparator<X> comparator)
      throws NullArgumentException;

  int width();

  int height();

  List<X> extractRow(int rowNum)
      throws IllegalArgumentException;

  List<X> extractCol(int colNum)
      throws IllegalArgumentException;

  <Y, Z> FunMatrix<Z> elementWiseCombine(BiFunction<X, Y, Z> combiner, FunMatrix<Y> combineWith)
      throws NullArgumentException;

  <Y, Z, A> FunMatrix<A> pseudoMultiply(BiFunction<X, Y, Z> vectorWiseOperation,
      BiFunction<Z, Z, A> vectorSumOperation, FunMatrix<Y> toMultiplyWith)
    throws NullArgumentException;

  <Y> FunMatrix<Y> mapIndex(BiFunction<Integer, Integer, Y> indexMapper)
    throws NullArgumentException;

}