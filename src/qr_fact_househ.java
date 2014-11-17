import Jama.Matrix;

/**
 * Created by JarrodBlanton on 11/14/14.
 */

public class qr_fact_househ {
    private double[][] QR;
    private int m,n;
    private double[] diagR;

    /** Constructor also computes Householder Reflections
     *
     * @param M matrix that will be passed in from main method.
     */
    public qr_fact_househ(Matrix M) {
        // Declare global variables
        QR = M.getArrayCopy();
        m = M.getRowDimension();
        n = M.getColumnDimension();
        if (!(m >= n)) {
            entryError();
        }
        diagR = new double[n];

        // Outer loop
        for (int k = 0; k < n; k++) {
            //Compute 2-norm of outer loop
            double norm = 0;
            for (int i = k; i < m; i++) {
                norm = Math.hypot(norm, QR[i][k]);
            }

            if (norm != 0.0) {
                // Form k-th household vector
                if (QR[k][k] < 0) {
                    norm = -norm;
                }
                for (int i = k; i < m; i++) {
                    QR[i][k] /= norm;
                }
                QR[k][k] += 1.0;

                for (int j = k+1; j < n; j++) {
                    double x = 0.0;
                    for (int i = k; i < m; i++) {
                        x += QR[i][k]*QR[i][j];
                    }
                    x = -x/QR[k][k];
                    for (int i = k; i < m; i++) {
                        QR[i][j] += x*QR[i][k];
                    }
                }
            }
            diagR[k] = -norm;
        }

    }

    public Matrix getH() {
        Matrix hMatrix = new Matrix(m,n);
        double[][] H = hMatrix.getArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= j) {
                    H[i][j] = QR[i][j];
                } else {
                    H[i][j] = 0.0;
                }
            }
        }
        return hMatrix;
    }

    public Matrix getQ() {
        Matrix qMatrix = new Matrix(m,n);
        double[][] Q  = qMatrix.getArray();
        for (int k = n-1; k >= 0; k--) {
            for (int i = 0; i < m; i++) {
                Q[i][k] = 0.0;
            }
            Q[k][k] = 1.0;
            for (int j = k; j < n; j++) {
                if (QR[k][k] != 0.0) {
                    double x = 0.0;
                    for (int i = k; i < m; i++) {
                        x += QR[i][k]*Q[i][j];
                    }
                    x = -x/QR[k][k];
                    for (int i = k; i < m; i++) {
                        Q[i][j] += x*QR[i][k];
                    }
                }
            }
        }
        return qMatrix;
    }

    public Matrix getR() {
        Matrix rMatrix = new Matrix(m,n);
        double[][] R = rMatrix.getArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i < j) {
                    R[i][j] = QR[i][j];
                } else if (i > j) {
                    R[i][j] = 0.0;
                } else { // i == j
                    R[i][j] = diagR[i];
                }
            }
        }
        return rMatrix;
    }

    // If m !>= n the matrix can not be decomposed, return 0
    private double entryError() {
        return 0.0;
    }
}