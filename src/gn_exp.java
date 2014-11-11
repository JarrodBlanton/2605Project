import Jama.Matrix;

/**
 * Created by JarrodBlanton on 11/11/14.
 */
public class gn_exp {

    public gn_exp(double[] arr, double a, double b, double c, int iters) {
        double[][] abc = new double[3][1];
        abc[1][1] = a;
        abc[2][1] = b;
        abc[3][1] = c;
        Matrix matrix = new Matrix(abc);

    }
}
