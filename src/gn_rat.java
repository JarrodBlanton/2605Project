import Jama.Matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by JarrodBlanton on 11/11/14.
 */
public class gn_rat {

    static Matrix B;
    static Matrix rVals;
    static Matrix jacobian;

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Please provide path to file");
        String fileName = kb.nextLine();
        File file = new File(fileName);
        System.out.println("Provide initial guess for a");
        double a = Double.parseDouble(kb.nextLine());
        System.out.println("Provide initial guess for b");
        double b = Double.parseDouble(kb.nextLine());
        System.out.println("Provide initial guess for c");
        double c = Double.parseDouble(kb.nextLine());
        System.out.println("Provide number of iterations");
        int iter = Integer.parseInt(kb.nextLine());
        gn_rat(file, a, b, c, iter);
    }

    public static Matrix gn_rat(File file, double a, double b, double c, int iter) {
        double[][] abc = new double[3][1];
        double[] arr;
        abc[0][0] = a;
        abc[1][0] = b;
        abc[2][0] = c;
        B = new Matrix(abc);

        try {
            BufferedReader lineCount = new BufferedReader(new FileReader(file));
            int lines = 0;
            while (lineCount.readLine() != null) {
                lines++;
            }
            lineCount.close();
            arr = new double[lines * 2];
            BufferedReader br = new BufferedReader(new FileReader(file));
            int i = 0;
            boolean done = false;
            while (br.ready() && !done) {
                String read = br.readLine();
                if (read == null) {
                    done = true;
                } else {
                    String[] splits = read.split(",");
                    arr[i] = Double.parseDouble(splits[0]);
                    arr[i + 1] = Double.parseDouble(splits[1]);
                    i += 2;
                }
            }

        } catch (Exception e) {
            System.out.println("file not found");
            arr = new double[0];
        }

        double[][] rValsArr = new double[arr.length / 2][1];
        int count = 0;
        for (int i = 0; i < arr.length - 1; i += 2) {
            double y = arr[i + 1];
            double x = arr[i];
            double solution = y - ((a * x) / (x + b) + c);
            rValsArr[count][0] = solution;
            count++;
        }
        rVals = new Matrix(rValsArr);

        double[][] jValsArr = new double[arr.length / 2][3];
        count = 0;
        for (int i = 0; i < arr.length - 1; i += 2) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    jValsArr[count][j] = ((-1 * arr[i]) / (arr[i] + b));
                } else if (j == 1) {
                    jValsArr[count][j] = ((a * arr[i]) / Math.pow((arr[i] + b), 2));
                } else {
                    jValsArr[count][j] = -1;
                }
            }
            count++;
        }

        jacobian = new Matrix(jValsArr);

        for (int i = 0; i < iter; i++) {
            // Step a
            qr_fact_househ qrFact = new qr_fact_househ(jacobian);
            Matrix Q = qrFact.getQMatrix();
            Matrix R = qrFact.getRMatrix();
            Matrix qT = Q.transpose();
            Matrix qTR = qT.times(rVals);
            double[][] rMatrix = R.getArrayCopy();
            double[][] qtrVals = qTR.getArrayCopy();
            // initialize x Array for back substitution
            double[][] xVals = new double[3][1];
            xVals[2][0] = qtrVals[2][0] / rMatrix[2][2];
            xVals[1][0] = (qtrVals[1][0] - (rMatrix[1][2] * xVals[2][0])) / rMatrix[1][1];
            xVals[0][0] = (qtrVals[0][0] - (rMatrix[0][1] * xVals[1][0]) - (rMatrix[0][2] * xVals[2][0])) / rMatrix[0][0];
            Matrix xBack = new Matrix(xVals);
            B = B.minus(xBack);
            B.print(1, 3);

            // Step b
            abc = B.getArrayCopy();
            a = abc[0][0];
            b = abc[1][0];
            c = abc[2][0];
            count = 0;
            for (int j = 0; j < arr.length - 1; j += 2) {
                double y = arr[j + 1];
                double x = arr[j];
                double solution = y - ((a * x) / (x + b) + c);
                rValsArr[count][0] = solution;
                count++;
            }
            rVals = new Matrix(rValsArr);

            // Step c
            count = 0;
            for (int k = 0; k < arr.length - 1; k += 2) {
                for (int l = 0; l < 3; l++) {
                    if (l == 0) {
                        jValsArr[count][l] = ((-1 * arr[k]) / (arr[k] + b));
                    } else if (l == 1) {
                        jValsArr[count][l] = ((a * arr[k]) / Math.pow((arr[k] + b), 2));
                    } else {
                        jValsArr[count][l] = -1;
                    }
                }
                count++;
            }

            jacobian = new Matrix(jValsArr);
        }
        B.print(1,3);
        return B;

    }
}
