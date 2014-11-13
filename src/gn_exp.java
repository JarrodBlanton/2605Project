import Jama.Matrix;

/**
 * Created by JarrodBlanton on 11/11/14.
 */
public class gn_exp {

    private Matrix arrValsMatrix;
    private Matrix matrixB;
    public Matrix jacobianMatrix;

    public gn_exp(double[] arr, double a, double b, double c, int iters) {
        double[][] abc = new double[3][1];
        abc[0][0] = a;
        abc[1][0] = b;
        abc[2][0] = c;
        matrixB = new Matrix(abc);
        double[][] arrValsArray = new double[arr.length / 2][1];

        int count = 0;
        for (int i = 0; i < arr.length - 1; i += 2) {
            double x = arr[i];
            double y = arr[i + 1];
            double solution = y - (a * Math.exp(b * x) + c);
            arrValsArray[count][0] = solution;
            count++;
        }
        arrValsMatrix = new Matrix(arrValsArray);

        double[][] jValsArray = new double[arr.length / 2][3];
        count = 0;
        for (int i = 0; i < arr.length - 1; i += 2) {
            for (int j = 0; j < 3; ++j) {
                if (j==0) {
                    jValsArray[count][j] = -1 * Math.exp(b * arr[i]);
                } else if (j==1) {
                    jValsArray[count][j] = -1 * arr[i] * Math.exp(b * arr[i]);
                } else {
                    jValsArray[count][j] = -1;
                }
            }
        }
    }
}
