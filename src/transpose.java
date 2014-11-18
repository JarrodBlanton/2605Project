import Jama.Matrix;

/**
 * Created by JoelAnderson on 11/18/14.
 */
public class transpose {

    public Matrix transpose(Matrix matrix) {
        int rows = matrix.getRowDimension();
        int columns = matrix.getColumnDimension();
        Matrix transpose = new Matrix(columns, rows);
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                transpose.set(j, i, matrix.get(i, j));
            }
        }
        return transpose;
    }
}
