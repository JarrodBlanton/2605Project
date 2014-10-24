import Jama.Matrix;


/**
 * Created by JarrodBlanton on 10/16/14.
 */
public class test {
    public static void main(String[] piratenoises) {
        System.out.println("jarrod i hate you, also tell me if you see this change.");
        Matrix matrix = new Matrix(3, 3, 1.0);
        matrix = matrix.plus(matrix);
        matrix.print(0,0);

    }

    public static int sum(Matrix matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.getColumnDimension(); i++) {
            for (int j = 0; i < matrix.getRowDimension(); j++) {
                sum += matrix.get(i, j);
            }
        }
        return sum;
    }
}
