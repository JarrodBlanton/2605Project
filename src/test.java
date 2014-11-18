import Jama.Matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


/**
 * Created by JarrodBlanton on 10/16/14.
 */
public class test {
    public static void main(String[] piratenoises) {
        Scanner kb = new Scanner(System.in);
        System.out.println("file");
        String fileName = kb.nextLine();
        File file = new File(fileName);
        double[] arr;
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
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
