import Jama.Matrix;

/**
 * Created by JarrodBlanton on 11/11/14.
 */
public class gn_rat {

    Matrix B;
    Matrix rVals;
    Matrix Jacobian;

    public gn_rat(double[] arr, double a, double b, double c, int iters) {
        double[][] abc = new double[3][1];
        abc[0][0] = a;
        abc[1][0] = b;
        abc[2][0] = c;
        B = new Matrix(abc);
        double[][] rValsArr = new double[arr.length/2][1];
        int count = 0;
        for (int i = 0; i < arr.length - 1; i+=2) {
            double y = arr[i+1];
            double x = arr[i];
            double solution = y - ((a*x)/(x+b) + c);
            rValsArr[0][1] = solution;
            count++;
        }
        rVals = new Matrix(rValsArr);
        double[][] jValsArr = new double[arr.length/2][3];
        for (int i = 0; i < arr.length - 1; i+=2) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {

                } else if (j == 1) {

                } else {

                }
            }
        }
    }

}
