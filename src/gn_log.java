import Jama.Matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Math;

/**
 * Created by JarrodBlanton on 11/11/14.
 */
public class gn_log {

    //Option+Enter = Import closest package
    Matrix matrixB;
    Matrix rVals;
    Matrix jacobian;
    public gn_log(File file, double a, double b, double c, int iter) {
        // Initialize B a vector of 3 coordinates
        double[][] abc = new double[3][1];
        double[] arr;
        abc[0][0] = a;
        abc[1][0] = b;
        abc[2][0] = c;
        matrixB = new Matrix(abc);

        //create arr
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
            while (br.ready() && done == false) {
                String read = br.readLine();
                if (read == null) {
                    done = true;
                } else {
                    String[] splits = read.split(",");
                    arr[i] = Integer.parseInt(splits[0]);
                    arr[i+1] = Integer.parseInt(splits[1]);
                    i+=2;
                }
            }

        } catch (Exception e) {
            System.out.println("file not found");
            arr = new double[0];
        }
        double[][] rValsArray = new double[arr.length/2][1];
        int count = 0;
        for (int i = 0; i < arr.length - 1; i+=2) {
            double x = arr[i];
            double y = arr[i + 1];
            double r = y - (a * (Math.log(x + b)) + c);
            rValsArray[count][0] = r;
            count++;
        }
        rVals = new Matrix(rValsArray);

        double[][] jVals = new double[arr.length/2][3];
        count = 0;
        for (int i = 0; i < arr.length - 1; i+=2) {
            for (int j = 0; j < 3; j++) {
                // 3 cases:
                // partial a. -log(x+b)
                // partial b. -1/(b+x)
                // partial c. -1
            }
        }

    }
}
