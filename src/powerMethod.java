import Jama.Matrix;

import java.util.Scanner;

/**
 * Created by chris on 11/17/14.
 */
public class powerMethod {

    static double tolerance;
    static Matrix vector;
    static Matrix U;
    static double eigen;
    static int iters;
    static int finalIters;


    public powerMethod() {
        double[][] vecDoub = new double[2][1];
        vecDoub[0][0] = 1;
        vecDoub[1][0] = 1;
        vector = new Matrix(vecDoub);
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the number of rows for this matrix");
        int rows = Integer.parseInt(kb.nextLine());
        System.out.println("Enter the number of columns for this matrix");
        int cols = Integer.parseInt(kb.nextLine());
        double[][] vals = new double[rows][cols];
        System.out.println("Please enter your matrix entry by entry across the rows");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                vals[i][j] = Double.parseDouble(kb.nextLine());
            }
        }
        System.out.println("Give the tolerance value:");
        tolerance = Double.parseDouble(kb.nextLine());
        System.out.println("Give maximum number of iterations:");
        iters = Integer.parseInt(kb.nextLine());
        power_method(vals, iters, tolerance);
    }

    public Matrix getU() {
        return U;
    }

    public int getIters() {
        return finalIters;
    }

    public double getEigen() {
        return eigen;
    }

    public static void power_method(double[][] doubs, int iters, double tolerance) {
        Matrix A = new Matrix(doubs);
        U = vector;
        int i = 0;
        double currTol = Double.MAX_VALUE;
        eigen = Double.MAX_VALUE;
        while (i < iters && currTol > tolerance) {
            U = A.times(U);
            currTol = Math.abs(U.get(0,0) - eigen);
            eigen = U.get(0,0);
            U = U.times(1/U.get(0,0));
            i++;
        }
        if (i != iters) {
            finalIters = i;
            System.out.println("EigenValue: " + eigen);
            System.out.println("EigenVector: ");
            U.print(1,3);
            System.out.println("Iterations: " + i);
            System.out.println();
        } else {
            System.out.println("Reached max number of iterations.");
        }
    }

}
