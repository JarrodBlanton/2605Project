import Jama.Matrix;

/**
 * Created by JarrodBlanton on 11/14/14.
 */
public class qr_fact_givens {
    double[][] A;
    int rows, cols;

    public qr_fact_givens(Matrix M) {
        A = M.getArrayCopy();
        rows = M.getRowDimension();
        cols = M.getColumnDimension();
        for (int j = 0; j < cols - 1; j++) {
            for (int i = j+1; i < rows; i++) {
                if (A[i][j] != 0) {
                    double r = Math.sqrt((Math.pow(A[j][j], 2.0)) + (Math.pow(A[i][j], 2.0)));
                    if (A[i][j] < 0) {
                        r = -r;
                    }
                    double s = A[i][j]/r;
                    double c = A[j][j]/r;
                    // Apply Givens
                    for (int k = j; k < cols; k++) {
                        double jk = A[j][k];
                        double ik = A[i][k];
                        A[j][k] = (c*jk) + (s*ik);
                        A[i][k] = (-s*jk) + (c*ik);
                    }
                    if (c == 0) {
                        A[i][j] = 0.0;
                    }
                    if (Math.abs(s) < Math.abs(c)) {
                        if (c < 0) {
                            A[i][j] = -.5 * s;
                        } else {
                            A[i][j] = .5 * s;
                        }
                    } else {
                        A[i][j] = 2.0/c;
                    }
                    if (Math.abs(c) <= Math.abs(s)) {
                        if (s < 0) {
                            A[i][j] = -2.0/c;
                        } else {
                            A[i][j] = 2.0 / c;
                        }
                    }
                }
            }
        }
    }

    public double[][] getQ() {
        int m = Math.max(A[0].length, A[1].length);
        double[][] Q = new double[m][m];

        // Identity Matrix
        for (int i = 0; i < Q[0].length; i++) {
            Q[i][i] = 1;
        }

        for (int j = cols - 1; j >= 0; j--) {
            for (int i = rows - 1; i > j; i--) {
                double aij = A[i][j];

                double c = 0.0;
                double s = 0.0;

                if (aij == 0.0) {
                    c = 0.0;
                    s = 1.0;
                } else if (Math.abs(aij) < 1.0) {
                    s = 2.0 * Math.abs(aij);
                    c = Math.sqrt(1 - Math.pow(s, 2.0));
                    if (aij < 0) {
                        c = -c;
                    }
                } else {
                    c = 2.0/aij;
                    s = Math.sqrt(1 - Math.pow(c, 2.0));
                }

                for (int k = 0; k < cols; k++) {
                    double jk = Q[j][k];
                    double ik = Q[i][k];

                    Q[j][k] = (c*jk) - (s*ik);
                    Q[i][k] = (s*jk) - (c*ik);
                }

            }
        }
//        for (int i = 0; i < Q[0].length; i++) {
//            for (int j = 0; j < Q[1].length; j++) {
//                Q[i][j] = -Q[i][j];
//            }
//        }
        return Q;
    }

    public double[][] getR() {
        int n = Math.min(A[0].length, A[1].length);
        double[][] R = new double[n][n];
        R = A.clone();
        for (int i = 0; i < R[0].length; i++) {
            for (int j = 0; j < i; j++) {
                R[i][j] = 0.0;
            }
        }

        for (int i = 0; i < R[0].length - 1; i++) {
            for (int j = 0; j < R[1].length; j++) {
                if (R[i][j] != 0.0) {
                    R[i][j] = -R[i][j];
                }
            }
        }
        return R;
    }
}