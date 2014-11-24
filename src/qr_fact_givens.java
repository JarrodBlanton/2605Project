import Jama.Matrix;

/**
 * Created by JarrodBlanton on 11/14/14.
 */
public class qr_fact_givens {
    private int n, m;
    private double x, y;
    private double[][] G, Q, R;

    public qr_fact_givens(Matrix orig) {
        // Instantiate R matrix
        R = orig.getArrayCopy();

        // Instantiate Row and Column sizes
        m = R.length;
        n = R[0].length;

        // Obtain starting x and y values
        x = R[0][n-2];
        y = R[0][n-1];

        // Initialize cos and sin values for Givens Rotations formulas
        double cos;
        double sin;

        // Instantiate G and Q matrices
        G = new double[m][m];
        Q = new double[m][m];

        // Make G and Q matrices identity
        G = new Matrix(G).identity(G[0].length, G[1].length).getArrayCopy();
        Q = new Matrix(Q).identity(G[0].length, G[1].length).getArrayCopy();

        // Iterate for Givens Rotations
        for (int i = 0; i < n; i++) {
            for (int j = (m-1); j > i; j--) {
                x = R[j-1][i];
                y = R[j][i];
                cos = x/(Math.sqrt(x*x + y*y));
                sin = -y/(Math.sqrt(x*x + y*y));

                G[j][j] = cos;
                G[j-1][j-1]= cos;
                G[j][j-1] = sin;
                G[j-1][j] = -sin;

                R = multiplyMatrices(G, R);
                Q = multiplyMatrices(G, Q);

                // Resets G to identity matrix
                G = new Matrix(G).identity(G[0].length, G[1].length).getArrayCopy();
            }
        }

        // Transpose Q matrix
        Q = new Matrix(Q).transpose().getArrayCopy();
    }

    public double[][] getQ() {
        // Attempts to remove -0 from Q matrix if there are 0s
        for (int i = 0; i < Q[0].length; i++) {
            for (int j = 0; j < Q[1].length; j++) {
                if (Q[i][j] == -0.000) {
                    Q[i][j] = 0.0;
                }
            }
        }
        return Q;
    }

    public Matrix getQMatrix() {
        return new Matrix(getQ());
    }

    public double[][] getR() {
        // This makes sure that there are no -0.0s in the lower triangular portion
        // of the R matrix
        for (int i = 0; i < R[0].length; i++) {
            for (int j = 0; j < R[1].length; j++) {
                if (i > j) {
                    R[i][j] = 0.0;
                }
            }
        }
        return R;
    }

    public Matrix getRMatrix() {
        return new Matrix(getR());
    }

    // Helper method to multiply two matrices together,
    // making sure to check their dimensions prior to computing.
    private double[][] multiplyMatrices(double[][] L, double[][] R) {
        int m = L[0].length;
        int n = R[1].length;

        // Matrices must have agreeing inner dimensions
        if (L[0].length != R.length) {
            throw new IllegalArgumentException();
        }

        // Initialize return matrix
        double[][] ret = new double[m][n];

        // Matrix multiplication algorithm
        for (int i = 0; i < m; i++) {
            for (int j = 0; j< n; j++) {
                for (int k = 0; k < R.length; k++) {
                    ret[i][j] += L[i][k] * R[k][j];
                }
            }
        }
        return ret;
    }
}