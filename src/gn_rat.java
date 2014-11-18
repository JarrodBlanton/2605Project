import Jama.Matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by JarrodBlanton on 11/11/14.
 */
public class gn_rat {

    Matrix B;
    Matrix rVals;
    Matrix Jacobian;

    public gn_rat(File file, double a, double b, double c, int iter) {
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
            arr = new double[lines*2];
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
                    arr[i+1] = Double.parseDouble(splits[1]);
                    i+=2;
                }
            }

        } catch (Exception e) {
            System.out.println("file not found");
            arr = new double[0];
        }

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
        count = 0;
        for (int i = 0; i < arr.length - 1; i+=2) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    jValsArr[count][j] = ((-1 * arr[i])/(arr[i] = b));
                } else if (j == 1) {
                    jValsArr[count][j] = ((a * arr[i])/Math.pow((arr[i] + b), 2));
                } else {
                    jValsArr[count][j] = -1;
                }
            }
            count++;
        }
    }

}
