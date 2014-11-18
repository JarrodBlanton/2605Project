import Jama.Matrix;

/**
 * Created by chris on 11/17/14.
 */
public class power_method {

    double tolerance;
    Matrix vector;
    int iters;


    public static void main(String[] args) {

    }

    public power_method(int iters, double tolerance) {
        this.iters = iters;
        this.tolerance = tolerance;
        double[][] doubs = new double[1][2];
        doubs[0][0] = 1;
        doubs[0][1] = 1;
        vector = new Matrix(doubs);
    }

    public void powerMethod(Matrix mat, Matrix vector) {
        int i = 0;
        double currTol = Double.MAX_VALUE;
        double eigen = Double.MAX_VALUE;
        while (i < iters && currTol > tolerance) {
            Matrix eVec = mat.times(vector);
            double[][] doubs = eVec.getArrayCopy();
            double max = Double.MIN_VALUE;
            for (int j = 0; j < eVec.getRowDimension(); j++) {
                for (int k = 0; k < eVec.getColumnDimension(); k++) {
                    if (doubs[j][k] > max) {
                        max = doubs[j][k];
                    }
                }
            }
            for (int j = 0; j < eVec.getRowDimension(); j++) {
                for (int k = 0; k < eVec.getColumnDimension(); k++) {
                    doubs[j][k] = doubs[j][k] * (1.0/max);
                }
            }
            Matrix eVecMin = new Matrix(doubs);
            double lambdaX = mat.times(eVecMin).times(eVecMin).get(0,0);
            double bottomX = eVecMin.times(eVecMin).get(0,0);
            double eigenNew = lambdaX/bottomX;
            currTol = Math.abs(eigenNew-eigen);
            eigen = eigenNew;
            vector = eVecMin;
            i++;
        }
        double finalEigen = eigen;
        Matrix finalVector = vector;
        System.out.println(finalEigen);
        System.out.println(finalVector);
    }

}
