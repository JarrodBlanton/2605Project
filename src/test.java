import Jama.Matrix;
import Jama.QRDecomposition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


/**
 * Created by JarrodBlanton on 10/16/14.
 */
public class test {
    public static void main(String[] piratenoises) {
        double[][] qr = new double[3][3];
        qr[0][0] = 12;
        qr[0][1] = -51;
        qr[0][2] = 4;
        qr[1][0] = 6;
        qr[1][1] = 167;
        qr[1][2] = -68;
        qr[2][0] = -4;
        qr[2][1] = 24;
        qr[2][2] = -41;
        Matrix QR = new Matrix(qr);
        QRDecomposition QR2 = new QRDecomposition(QR);

        QR.print(1,3);
        qr_fact_househ hh= new qr_fact_househ(QR);
        Matrix Q = hh.getQMatrix();
        Matrix R = hh.getRMatrix();

        Q.print(1, 3);
        R.print(1, 3);

        System.out.println(-2.0*(-1.0*0.0));
    }
}
