import Jama.Matrix;

import java.util.Scanner;

/**
 * Created by chris on 11/23/14.
 */
public class HouseHolderRunner {

    public static qr_fact_househ house;

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
        house = new qr_fact_househ(new Matrix(doubs));
        System.out.println("Q: ");
        house.getQMatrix().print(1,3);
        System.out.println("R: ");
        house.getRMatrix().print(1,3);
    }

}
