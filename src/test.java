import Jama.Matrix;

/**
 * Created by JarrodBlanton on 10/16/14.
 */
public class test {
    public static void main(String[] piratenoises) {
        System.out.println("jarrod i hate you, also tell me if you see this change.");
        createMatrix();
    }

    public static void createMatrix() {
        double[][] arr = new double[2][2];
        for (int i = 0; i < 2; i++) {
            arr[i][i] = 1;
        }
        Matrix a = new Matrix(arr);
        a.print(0, 0);
    }
}
