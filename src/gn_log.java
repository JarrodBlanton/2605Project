import Jama.Matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Math;
import java.util.Scanner;

/**
 * Created by JarrodBlanton on 11/11/14.
 */
public class gn_log {

    //Option+Enter = Import closest package
    static Matrix matrixB;
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
        gn_log(file, a, b, c, iter);
    }

    public static void gn_log(File file, double a, double b, double c, int iter) {
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
            while (br.ready() && !done) {
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
                // partial b. -a/(b+x)
                // partial c. -1
                if (j == 0) {
                    jVals[count][j] = -Math.log(arr[i] + b);
                } else if (j == 1) {
                    jVals[count][j] = -a/(b+arr[i]);
                } else {
                    jVals[count][j] = -1;
                }
            }
            count++;
        }
        jacobian = new Matrix(jVals);
        qr_fact_househ qrFact = new qr_fact_househ(jacobian);
        Matrix Q = qrFact.getQ();
        Matrix R = qrFact.getR();


    }
}
