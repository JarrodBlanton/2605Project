import Jama.Matrix;

/**
 * Created by JarrodBlanton on 11/11/14.
 */
public class Part1Helper {
    public Part1Helper(double arr[], double a, double b, double c, int iters) {
       // Initialize B - a vector of 3 coordinates
        double[][] abc = new double[1][3];
        abc[1][1] = a;
        abc[2][1] = b;
        abc[3][1] = c;
        Matrix vec = new Matrix(abc);
        // Initialize r, vector of residuals

    }
}
