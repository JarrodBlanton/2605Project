import Jama.Matrix;

/**
 * Created by JarrodBlanton on 11/14/14.
 */
public class qr_fact_givens {
    int n;
    double[][] H;
    double[][] G;
    double[][] R;
    double[][] Q;
    Matrix rMatrix, gMatrix, qMatrix;
    public qr_fact_givens(Matrix M) {

        n = M.getRowDimension();
        int c = M.getColumnDimension();
        H = new double[n][n];
        G = new double[n][n];
        R = new double[n][n];
        Q = new double[n][n];

        // If row dimension is equal to column dimension
        // Then we can find the givens rotation of a matrix
        if (n == c) {

            // This declares the Hilbert Matrix and the A matrix
            // hVal is the value found from (1/(i+j)-1)
            // because we start at 0 index, we add 1 to both i and j
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // Must cast i and j to double
                    double hVal = (1/(((double)i+1) + ((double)j+1)-1));
                    H[i][j] = hVal;
                    R[i][j] = hVal;
                }
            }

            // Init Givens (G) matrix and Q matrix to identity matrix
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        G[i][j] = 1.0;
                        Q[i][j] = 1.0;
                    }
                }
            }

            double a = R[0][n-2];
            double b = R[0][n-1];
            double cosX, sinX;

            // Continuously loops through and calculates
            // the Givens rotation matrices
            for (int i = 0; i < n; i++) {
                for (int j = (n-1); j > i; j--) {
                    a = R[j-1][i];
                    b = R[j][i];
                    cosX = a/(Math.sqrt(a*a+b*b));
                    sinX = -b/(Math.sqrt(a*a+b*b));

                    G[j][j] = cosX;
                    G[j-1][j-1] = cosX;
                    G[j][j-1] = sinX;
                    G[j-1][j] = -sinX;

                    // Converts A, G, and Q arrays into matrices
                    // Performs Matrix Multiplication for A and Q.
                    rMatrix = new Matrix(R);
                    gMatrix = new Matrix(G);

                    rMatrix = rMatrix.times(gMatrix);

                    qMatrix = new Matrix(Q);
                    qMatrix = qMatrix.times(gMatrix);

                    // Returns G back to identity Matrix
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if (x == y) {
                                G[x][y] = 1.0;
                            } else {
                                G[x][y] = 0.0;
                            }
                        }
                    }

                    // Converts new Q and A matrices into 2-D arrays
                    // This is so A and Q will continue to hold their new values.
                    R = rMatrix.getArrayCopy();
                    Q = qMatrix.getArrayCopy();
                } // end of column loop
            } // end of row loop
        } else {
            matrixError();
        }
    }

    public Matrix getQMatrix() {
        return qMatrix;
    }

    public double[][] getQ() {
        return Q;
    }

    public Matrix getRMatrix() {
        return rMatrix;
    }

    public double[][] getR() {
        return R;
    }
    public double matrixError() {
        return 0.0;
    }
}
