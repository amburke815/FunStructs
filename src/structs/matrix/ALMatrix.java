package structs.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import structs.FunStruct;
import structs.utils.EFoldMode;
import structs.utils.NullArgumentException;
import structs.utils.Utils;

public class ALMatrix<X> implements FunMatrix<X> {

  private final List<List<X>> entries;

  ALMatrix(List<List<X>> entries) {
    this.entries = Utils.notNull(entries);
  }

  ALMatrix(List<X>... rows) {
    List<List<X>> rowsList = Arrays.asList(Utils.notNull(rows));
    List<List<X>> entriesToAdd = new ArrayList<>();
    if (rowsList.size()
        > 0) { // if there's at least one row, all rows must share that size to preserve matrix dimensions
      int targetSize = rowsList.get(0).size(); // the size to preserve
      for (List<X> aRow : rowsList) {
        if (aRow.size() != targetSize) {
          throw new IllegalArgumentException(
              "Row " + rowsList.indexOf(aRow) + " has size " + aRow.size() + ", which is too large"
                  + " for a matrix of width " + targetSize
                  + ". Please make sure that all of the rows supplied are of the same length");
        }
        entriesToAdd.add(aRow);
      }
    }
    this.entries = entriesToAdd;
  }

  ALMatrix(List<X> oneRow, int numCopies) {
    List<List<X>> entries = new ArrayList<>();

    for (int i = 0; i < numCopies; i++) {
      entries.add(Utils.notNull(oneRow));
    }

    this.entries = entries;
  }

  ALMatrix() {
    this.entries = new ArrayList<>();
  }

  ALMatrix(X uniformEntry, int numRows, int numCols) {
    Utils.notNull(uniformEntry);
    if (numRows < 0 || numCols < 0) {
      throw new IllegalArgumentException("Bad matrix dimensions. Must be greater than 0");
    }
    List<List<X>> entries = new ArrayList<>();

    for (int i = 0; i < numRows; i++) {
      List<X> thisRow = new ArrayList<>();
      for (int j = 0; j < numCols; j++) {
        thisRow.add(uniformEntry);
      }
      entries.add(thisRow);
    }

    this.entries = entries;
  }

  ALMatrix(X identityElement, X nonIdentityElement, int squareDimension) {
    Utils.notNull(identityElement);
    Utils.notNull(nonIdentityElement);
    if (squareDimension < 0) {
      throw new IllegalArgumentException("Square dimension must be non-negative");
    }

    List<List<X>> entries = new ArrayList<>();

    for (int i = 0; i < squareDimension; i++) {
      List<X> thisRow = new ArrayList<>();
      for (int j = 0; j < squareDimension; j++) {
        if (i == j) {
          thisRow.add(identityElement);
        } else {
          thisRow.add(nonIdentityElement);
        }
      }
      entries.add(thisRow);
    }
    this.entries = entries;
  }

  @Override
  public FunStruct<X> copy() {
    return new ALMatrix<>(entries);
  }

  @Override
  public int size() {
    return ((FunMatrix<Integer>) map(x -> 1)).foldNorthWest((x, y) -> (x + y), 0);
  }

  @Override
  public <Y> FunStruct<Y> map(Function<X, Y> mapper) throws NullArgumentException {
    Utils.notNull(mapper);
    List<List<Y>> mapped = new ArrayList<>();

    for (int i = 0; i < height(); i++) {
      List<Y> thisRow = new ArrayList<>();
      for (int j = 0; j < width(); j++) {
        thisRow.add(mapper.apply(getElementAt(i, j)));
      }
      mapped.add(thisRow);
    }

    return new ALMatrix<>(mapped);
  }

  @Override
  public FunStruct<X> replaceMap(Predicate<X> replaceIf, X replaceWith)
      throws NullArgumentException {
    return map(x -> replaceIf.test(x) ? replaceWith : x);
  }

  @Override
  public FunStruct<X> findAndReplace(X toFind, X replaceWith) throws NullArgumentException {
    return replaceMap(x -> x.equals(toFind), replaceWith);
  }

  @Override
  public boolean andMap(Predicate<X> predicate) throws NullArgumentException {
    return ((FunMatrix<Boolean>) map(x -> predicate.test(x)))
        .foldNorthWest((x1, x2) -> (x1 && x2), true);
  }

  @Override
  public boolean contains(X amIContained) throws NullArgumentException {
    return orMap(x -> x.equals(amIContained));
  }

  @Override
  public int countOccurrenceOf(X toCount) throws NullArgumentException {
    return ((FunMatrix<Integer>) map(x -> x.equals(toCount) ? 1 : 0))
        .foldNorthWest((x1, x2) -> (x1 + x2), 0);
  }

  @Override
  public boolean orMap(Predicate<X> predicate) throws NullArgumentException {
    return ((FunMatrix<Boolean>) map(x -> predicate.test(x)))
        .foldNorthWest((x1, x2) -> (x1 || x2), false);
  }

  @Override
  public <Y> Y fold(BiFunction<X, Y, Y> folder, Y base, EFoldMode biFoldMode)
      throws NullArgumentException {
    return null; // TODO
  }

  @Override
  public <Y> Y foldNorthWest(BiFunction<X, Y, Y> folder, Y base) throws NullArgumentException {
    Y folded = base;

    for (int i = 0; i < height(); i++) {
      for (int j = 0; j < width(); j++) {
        folded = folder.apply(getElementAt(i, j), folded);
      }
    }

    return folded;
  }

  @Override
  public <Y> Y foldSouthEast(BiFunction<X, Y, Y> folder, Y base) throws NullArgumentException {
    Y folded = base;

    for (int i = height() - 1; i >= 0; i--) {
      for (int j = width(); j >= 0; j--) {
        folded = folder.apply(getElementAt(i, j), folded);
      }
    }

    return folded;
  }

  @Override
  public X getElementAt(int row, int col) throws IllegalArgumentException {
    try {
      return entries.get(row).get(col);
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Row " + row + " annd/or column " + col + " out of bounds "
          + "for matrix with height " + height() + " and width " + width());
    }
  }

  @Override
  public List<X> asList() {
    List<X> asList = new ArrayList<>();
    map(x -> asList.add(x));
    return asList;
  }

  @Override
  public FunMatrix<X> subMatrix(int firstRowIncl, int lastRowIncl, int firstColIncl,
      int lastColIncl) throws IllegalArgumentException {
    return null;
  }

  @Override
  public FunMatrix<X> subMatrix(int lastRowIncl, int lastColIncl) throws IllegalArgumentException {
    return null;
  }

  @Override
  public FunMatrix<X> reflectAcross(EReflectionAxis reflectionAxis) throws NullArgumentException {
    return null;
  }

  @Override
  public FunMatrix<X> diagonalize() {
    return null;
  }

  @Override
  public FunMatrix<X> reducedRowEchelonForm(Comparator<X> comparator) throws NullArgumentException {
    return null;
  }

  @Override
  public int width() {
    return 0;
  }

  @Override
  public int height() {
    return 0;
  }

  @Override
  public List<X> extractRow(int rowNum) throws IllegalArgumentException {
    return null;
  }

  @Override
  public List<X> extractCol(int colNum) throws IllegalArgumentException {
    return null;
  }

  @Override
  public <Y, Z> FunMatrix<Z> elementWiseCombine(BiFunction<X, Y, Z> combiner,
      FunMatrix<Y> combineWith) throws NullArgumentException {
    return null;
  }

  @Override
  public <Y, Z, A> FunMatrix<A> pseudoMultiply(BiFunction<X, Y, Z> vectorWiseOperation,
      BiFunction<Z, Z, A> vectorSumOperation, FunMatrix<Y> toMultiplyWith)
      throws NullArgumentException {
    return null;
  }
}
