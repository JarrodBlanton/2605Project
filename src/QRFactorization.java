import Jama.Matrix;
/**
 * Created by JarrodBlanton on 10/24/14.
 */
public class QRFactorization {
    Matrix orig;
    int rows;
    int cols;
    /**
        Instantiates the QRFactorization class
     */
    public QRFactorization(Matrix matrix) {
        orig = matrix.copy();
        cols = matrix.getColumnDimension();
        rows = matrix.getRowDimension();
    }

    public Matrix getQ() {
        Matrix Q = new Matrix(rows, cols);
        double[][] X = Q.getArray();
        // Makes X matrix an Identity Matrix
        for (int i = cols-1; i >= 0; i--) {
            X[i][i]= 1.0;
            for (int j = 0; j < rows; j++) {

            }
        }
        return Q;
    }
}
