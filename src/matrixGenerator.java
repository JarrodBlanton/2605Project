import Jama.Matrix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

/**
 * Created by chris on 11/21/14.
 */
public class matrixGenerator {

    static double[] traceA = new double[1000];
    static double[] detA = new double[1000];
    static double[] itersA = new double[1000];
    static double[] itersAIn = new double[1000];
    static int count =  0;
    static Inverse inverter = new Inverse();
    static Trace Trace = new Trace();

    public static void main(String[] args) {
        runner();
    }

    public static void runner() {
        for (int i = 0; i < 1000; i ++) {
            double[][] currDoubs = generateMatrix();
            powerMethod pow = new powerMethod();
            powerMethod powIn = new powerMethod();
            pow.power_method(currDoubs, 100);
            traceA[i] = Trace.trace(new Matrix(currDoubs));
            detA[i] = inverter.determinant(new Matrix(currDoubs));
            itersA[i] = pow.getIters();
            Matrix toInvert = new Matrix(currDoubs);
            toInvert = inverter.inverse(toInvert);
            powIn.power_method(toInvert.getArrayCopy(), 100);
            itersAIn[i] = powIn.getIters();
        }
        fileWrite();
    }

    public static double[][] generateMatrix() {
        Random rand = new Random();
        double min = -2.0;
        double max = 2.0;
        double[][] doubs = new double[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                doubs[i][j] = min + (max - min) * rand.nextDouble();
            }
        }
        return doubs;
    }

    public static void fileWrite() {
        BufferedWriter bw1;
        BufferedWriter bw2;
        BufferedWriter bw3;
        BufferedWriter bw4;

        try {
            bw1 = new BufferedWriter(new FileWriter("traceA.txt"));
            bw2 = new BufferedWriter(new FileWriter("detA.txt"));
            bw3 = new BufferedWriter(new FileWriter("itersA.txt"));
            bw4 = new BufferedWriter(new FileWriter("itersAIn.txt"));
            for (int i = 0; i < traceA.length; i++) {
                bw1.write(Double.toString(traceA[i]));
                bw1.newLine();
                bw2.write(Double.toString(detA[i]));
                bw2.newLine();
                bw3.write(Double.toString(itersA[i]));
                bw3.newLine();
                bw4.write(Double.toString(itersAIn[i]));
                bw4.newLine();
            }
            bw1.close();
            bw2.close();
            bw3.close();
            bw4.close();
        } catch (Exception e) {
            runner();
        }
    }

}
