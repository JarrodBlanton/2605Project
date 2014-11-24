import Jama.Matrix;
import java.util.Scanner;

/**
 * Created by JarrodBlanton on 11/24/14.
 */
public class GivensRotationRunner {
    public static qr_fact_givens givens;

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Please enter the number of rows in the matrix");
        int rows = Integer.parseInt(kb.nextLine());
        System.out.println("Please enter the number of columns in the matrix");
        int cols = Integer.parseInt(kb.nextLine());
        double[][] doubs = new double[rows][cols];
        System.out.println("Enter the entries of the matrix across the rows, row by row.");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                doubs[i][j] = Double.parseDouble(kb.nextLine());
            }
        }
        givens = new qr_fact_givens(new Matrix(doubs));
        System.out.println("Q: ");
        givens.getQMatrix().print(1,3);
        System.out.println("R: ");
        givens.getRMatrix().print(1,3);
    }
}
