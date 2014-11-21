import Jama.Matrix;

/**
 * Created by JarrodBlanton on 11/14/14.
 */
public class qr_fact_givens {
    int n;
    double[][] G;
    double[][] R;
    double[][] Q;
    Matrix rMatrix, gMatrix, qMatrix;
    public qr_fact_givens(Matrix M) {
        n = M.getRowDimension();
        double[][] m = M.getArrayCopy();
        G = new double[n][n];
        R = new double[n][n];
        R = m;
        Q = new double[n][n];
        // Create Givens Rotations:
        // G Matrix and Q matrix will be identity.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i==j) {
                    G[i][j] = 1.0;
                    Q[i][j] = 1.0;
                } else {
                    G[i][j] = 0.0;
                    Q[i][j] = 0.0;
                }
            }
        }

        double a = R[0][n-2];
        double b = R[0][n-1];
        double cosX, sinX;

        // For loop begins rotations
        for (int k = 0; k < n; k++) {
            for (int l = n-1; l > k; l--) {
                a = R[l-1][k];
                b = R[l][k];
                cosX = a/(Math.sqrt(a+b));
                sinX = -b/(Math.sqrt(a+b));

                G[l][l] = cosX;
                G[l][l-1] = sinX;
                G[l-1][l] = -sinX;
                G[l-1][l-1] = cosX;

                gMatrix = new Matrix(G);
                rMatrix = new Matrix(R);
                rMatrix = rMatrix.times(gMatrix);

                qMatrix = new Matrix(Q);
                qMatrix = qMatrix.times(gMatrix);

                R = rMatrix.getArrayCopy();
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++) {
                        if (x == y) {
                            G[x][y] = 1.0;
                        } else {
                            G[x][y] = 0.0;
                        }
                    }
                }

            }
        }




    }

    public Matrix getQMatrix() {
        return qMatrix;
    }

    public double[][] getQ() {
        return qMatrix.getArrayCopy();
    }

    public Matrix getRMatrix() {
        return rMatrix;
    }

    public double[][] getR() {
        return rMatrix.getArrayCopy();
    }
//    public double matrixError() {
//        return 0.0;
//    }
}
