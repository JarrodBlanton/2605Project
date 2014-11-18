import Jama.Matrix;

/**
 * Created by JoelAnderson on 11/18/14.
 */
public class inverse {

    public Matrix inverse(Matrix matrix) {
        return (cofactor(matrix).transpose()).times(1.0/matrix.det());
    }

    public Matrix cofactor(Matrix matrix) {
        int rows = matrix.getRowDimension();
        int columns = matrix.getColumnDimension();
        Matrix cofactor = new Matrix(rows, columns);
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                cofactor.set(i, j,
                        changeSign(i) * changeSign(j) * createSubMatrix(matrix, i, j).det());
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

    public int changeSign(int i) {
        if (i % 2 == 0) {
            return 1;
        } else {
            return  -1;
        }
    }
}
