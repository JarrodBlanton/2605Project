import Jama.Matrix;

/**
 * Created by JoelAnderson on 11/18/14.
 */
public class Inverse {

    public static Matrix inverse(Matrix matrix) {
        return (cofactor(matrix).transpose()).times(1.0/determinant(matrix));
    }

    public static Matrix cofactor(Matrix matrix) {
        int rows = matrix.getRowDimension();
        int columns = matrix.getColumnDimension();
        Matrix cofactor = new Matrix(rows, columns);
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                cofactor.set(i, j,
                        changeSign(i) * changeSign(j) * determinant(createSubMatrix(matrix, i, j)));
            }
        }
        return cofactor;
    }

    public static Matrix createSubMatrix(Matrix matrix, int excluding_row, int excluding_column) {
        int rows = matrix.getRowDimension();
        int columns = matrix.getColumnDimension();
        Matrix subMatrix = new Matrix(rows - 1, columns - 1);
        int r = -1;
        for (int i = 0; i < rows; ++i) {
            if (i == excluding_row)
                continue;
            ++r;
            int c = -1;
            for (int j = 0; j < columns; ++j) {
                if (j == excluding_column)
                    continue;
                subMatrix.set(r, ++c, matrix.get(i, j));
            }
        }
        return subMatrix;
    }

    public static int changeSign(int i) {
        if (i % 2 == 0) {
            return 1;
        } else {
            return  -1;
        }
    }

    public static double determinant(Matrix matrix) {
        double sum = 0.0;
        int columns = matrix.getColumnDimension();
        int rows = matrix.getRowDimension();
        if (columns == 1) {
            return matrix.get(0, 0);
        }
        for (int i = 0; i < columns; ++i) {
            Matrix smaller = new Matrix(rows - 1, columns - 1);
            for (int a = 1; a < rows; ++a) {
                for (int b = 0; b < columns; ++b) {
                    if (b < i) {
                        smaller.set(a - 1, b, matrix.get(a, b));
                    } else if (b > i) {
                        smaller.set(a - 1, b - 1, matrix.get(a, b));
                    }
                }
            }
            sum += changeSign(i) * matrix.get(0, i) * (determinant(smaller));
        }
        return sum;
    }
}
