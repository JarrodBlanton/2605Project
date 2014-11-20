import Jama.Matrix;

/**
 * Created by JoelAnderson on 11/20/14.
 */
public class Part3Helper {

    private Matrix letterF = new Matrix(11, 2);
    private Matrix letterI = new Matrix(12, 2);
    private Matrix letterT = new Matrix(8, 2);

    /**
     * Helper method that sets up letters, then
     * plays the movie by rotating the letters.
     */
    public Part3Helper() {
        setUpLetterF();
        setUpLetterI();
        setUpLetterT();
        rotateF();
        rotateI();
        rotateT();
    }

    /**
     * Sets up the letter F.
     */
    private void setUpLetterF() {
        letterF.set(0, 0, 8);
        letterF.set(0, 1, 0);
        letterF.set(1, 0, 10);
        letterF.set(1, 1, 0);
        letterF.set(2, 0, 10);
        letterF.set(2, 1, 6);
        letterF.set(3, 0, 11);
        letterF.set(3, 1, 6);
        letterF.set(4, 0, 11);
        letterF.set(4, 1, 8);
        letterF.set(5, 0, 10);
        letterF.set(5, 1, 8);
        letterF.set(6, 0, 10);
        letterF.set(6, 1, 10);
        letterF.set(7, 0, 12);
        letterF.set(7, 1, 10);
        letterF.set(8, 0, 12);
        letterF.set(8, 1, 12);
        letterF.set(9, 0, 10);
        letterF.set(9, 1, 12);
        letterF.set(10, 0, 8);
        letterF.set(10, 1, 12);
    }

    /**
     * Sets up the letter I.
     */
    private void setUpLetterI() {
        letterI.set(0, 0, 16);
        letterI.set(0, 1, 0);
        letterI.set(1, 0, 22);
        letterI.set(1, 1, 0);
        letterI.set(2, 0, 22);
        letterI.set(2, 1, 2);
        letterI.set(3, 0, 20);
        letterI.set(3, 1, 2);
        letterI.set(4, 0, 20);
        letterI.set(4, 1, 8);
        letterI.set(5, 0, 22);
        letterI.set(5, 1, 8);
        letterI.set(6, 0, 22);
        letterI.set(6, 1, 10);
        letterI.set(7, 0, 16);
        letterI.set(7, 1, 10);
        letterI.set(8, 0, 16);
        letterI.set(8, 1, 8);
        letterI.set(9, 0, 18);
        letterI.set(9, 1, 8);
        letterI.set(10, 0, 18);
        letterI.set(10, 1, 2);
        letterI.set(11, 0, 16);
        letterI.set(11, 1, 2);
    }

    /**
     * Sets up the letter T.
     */
    private void setUpLetterT() {
        letterT.set(0, 0, 2);
        letterT.set(0, 1, 0);
        letterT.set(1, 0, 4);
        letterT.set(1, 1, 0);
        letterT.set(2, 0, 4);
        letterT.set(2, 1, 4);
        letterT.set(3, 0, 6);
        letterT.set(3, 1, 4);
        letterT.set(4, 0, 6);
        letterT.set(4, 1, 6);
        letterT.set(5, 0, 0);
        letterT.set(5, 1, 6);
        letterT.set(6, 0, 0);
        letterT.set(6, 1, 4);
        letterT.set(7, 0, 2);
        letterT.set(7, 1, 4);
    }

    /**
     * Rotates the first letter three times about the line
     * in the z direction through the center of the letter.
     */
    private void rotateF() {
        for (int i = 0; i < 3; ++i) {

        }
    }

    /**
     * Rotates the second letter two times about the line
     * in the y direction through the center of the letter.
     */
    private void rotateI() {
        for (int i = 0; i < 2; ++i) {

        }
    }

    /**
     * Rotates the third letter five times about the line
     * in the x direction through the center of the letter.
     */
    private void rotateT() {
        for (int i = 0; i < 5; ++i) {

        }
    }
}
