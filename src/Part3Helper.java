import Jama.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Created by JoelAnderson on 11/20/14.
 */
public class Part3Helper {

    private Matrix letterF = new Matrix(11, 3);
    private Matrix letterI = new Matrix(12, 3);
    private Matrix letterT = new Matrix(8, 3);
    private MatrixTransformations transformer
            = new MatrixTransformations();
    public static Timer timer;
    private static int counter = 0;

    public static void main(String[] args) {
        final Part3Helper helper = new Part3Helper();
        helper.setUpLetters();
        final MyPanel grid = new MyPanel();
        drawMatrices(grid, helper);
        grid.setLayout(new GridLayout(25, 25));
        JFrame frame = new JFrame("Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(grid);
        frame.pack();
        frame.setVisible(true);
        timer = new Timer(41 + 2/3, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (counter <= 121) {
                    grid.clearLines();
                    helper.letterF = rotate(helper.letterF, 3, 121, "Z");
                    helper.letterI = rotate(helper.letterI, 2, 121, "Y");
                    helper.letterT = rotate(helper.letterT, 5, 121, "X");
                    drawMatrices(grid, helper);
                    grid.repaint();
                    ++counter;
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    /**
     * Draws the matrices on the jFrame
     * @param grid panel on which they are drawn
     * @param helper helper method containing the matrices
     */
    private static void drawMatrices(MyPanel grid, Part3Helper helper) {
        for (int i = 0; i < 10; ++i) {
            int X1 = (int) helper.letterF.get(0, i) * 10 + 300;
            int Y1 = (int) helper.letterF.get(1, i) * 10 + 100;
            int X2 = (int) helper.letterF.get(0, i + 1) * 10 + 300;
            int Y2 = (int) helper.letterF.get(1, i + 1) * 10 + 100;
            grid.addLine(X1, Y1, X2, Y2);
        }
        int a = (int) helper.letterF.get(0, 10) * 10 + 300;
        int b = (int) helper.letterF.get(1, 10) * 10 + 100;
        int c = (int) helper.letterF.get(0, 0) * 10 + 300;
        int d = (int) helper.letterF.get(1, 0) * 10 + 100;
        grid.addLine(a, b, c, d);
        for (int i = 0; i < 11; ++i) {
            int X1 = (int) helper.letterI.get(0, i) * 10 + 300;
            int Y1 = (int) helper.letterI.get(1, i) * 10 + 100;
            int X2 = (int) helper.letterI.get(0, i + 1) * 10 + 300;
            int Y2 = (int) helper.letterI.get(1, i + 1) * 10 + 100;
            grid.addLine(X1, Y1, X2, Y2);
        }
        a = (int) helper.letterI.get(0, 11) * 10 + 300;
        b = (int) helper.letterI.get(1, 11) * 10 + 100;
        c = (int) helper.letterI.get(0, 0) * 10 + 300;
        d = (int) helper.letterI.get(1, 0) * 10 + 100;
        grid.addLine(a, b, c, d);
        for (int i = 0; i < 7; ++i) {
            int X1 = (int) helper.letterT.get(0, i) * 10 + 300;
            int Y1 = (int) helper.letterT.get(1, i) * 10 + 100;
            int X2 = (int) helper.letterT.get(0, i + 1) * 10 + 300;
            int Y2 = (int) helper.letterT.get(1, i + 1) * 10 + 100;
            grid.addLine(X1, Y1, X2, Y2);
        }
        a = (int) helper.letterT.get(0, 7) * 10 + 300;
        b = (int) helper.letterT.get(1, 7) * 10 + 100;
        c = (int) helper.letterT.get(0, 0) * 10 + 300;
        d = (int) helper.letterT.get(1, 0) * 10 + 100;
        grid.addLine(a, b, c, d);
    }

    /**
     * Helper method that sets up letters, then
     * plays the movie by rotating the letters.
     */
    public Part3Helper() {
    }

    public void setUpLetters() {
        setUpLetterF();
        setUpLetterI();
        setUpLetterT();
    }

    /**
     * Sets up the letter F.
     */
    private void setUpLetterF() {
        letterF.set(0, 0, 8);
        letterF.set(0, 1, 0);
        letterF.set(0, 2, 0);
        letterF.set(1, 0, 10);
        letterF.set(1, 1, 0);
        letterF.set(1, 2, 0);
        letterF.set(2, 0, 10);
        letterF.set(2, 1, 6);
        letterF.set(2, 2, 0);
        letterF.set(3, 0, 11);
        letterF.set(3, 1, 6);
        letterF.set(3, 2, 0);
        letterF.set(4, 0, 11);
        letterF.set(4, 1, 8);
        letterF.set(4, 2, 0);
        letterF.set(5, 0, 10);
        letterF.set(5, 1, 8);
        letterF.set(5, 2, 0);
        letterF.set(6, 0, 10);
        letterF.set(6, 1, 10);
        letterF.set(6, 2, 0);
        letterF.set(7, 0, 12);
        letterF.set(7, 1, 10);
        letterF.set(7, 2, 0);
        letterF.set(8, 0, 12);
        letterF.set(8, 1, 12);
        letterF.set(8, 2, 0);
        letterF.set(9, 0, 10);
        letterF.set(9, 1, 12);
        letterF.set(9, 2, 0);
        letterF.set(10, 0, 8);
        letterF.set(10, 1, 12);
        letterF.set(10, 2, 0);
        letterF = letterF.transpose();
    }

    /**
     * Sets up the letter I.
     */
    private void setUpLetterI() {
        letterI.set(0, 0, 16);
        letterI.set(0, 1, 0);
        letterI.set(0, 2, 0);
        letterI.set(1, 0, 22);
        letterI.set(1, 1, 0);
        letterI.set(1, 2, 0);
        letterI.set(2, 0, 22);
        letterI.set(2, 1, 2);
        letterI.set(2, 2, 0);
        letterI.set(3, 0, 20);
        letterI.set(3, 1, 2);
        letterI.set(3, 2, 0);
        letterI.set(4, 0, 20);
        letterI.set(4, 1, 8);
        letterI.set(4, 2, 0);
        letterI.set(5, 0, 22);
        letterI.set(5, 1, 8);
        letterI.set(5, 2, 0);
        letterI.set(6, 0, 22);
        letterI.set(6, 1, 10);
        letterI.set(6, 2, 0);
        letterI.set(7, 0, 16);
        letterI.set(7, 1, 10);
        letterI.set(7, 2, 0);
        letterI.set(8, 0, 16);
        letterI.set(8, 1, 8);
        letterI.set(8, 2, 0);
        letterI.set(9, 0, 18);
        letterI.set(9, 1, 8);
        letterI.set(9, 2, 0);
        letterI.set(10, 0, 18);
        letterI.set(10, 1, 2);
        letterI.set(10, 2, 0);
        letterI.set(11, 0, 16);
        letterI.set(11, 1, 2);
        letterI.set(11, 2, 0);
        letterI = letterI.transpose();
    }

    /**
     * Sets up the letter T.
     */
    private void setUpLetterT() {
        letterT.set(0, 0, 2);
        letterT.set(0, 1, 0);
        letterT.set(0, 2, 0);
        letterT.set(1, 0, 4);
        letterT.set(1, 1, 0);
        letterT.set(1, 2, 0);
        letterT.set(2, 0, 4);
        letterT.set(2, 1, 4);
        letterT.set(2, 2, 0);
        letterT.set(3, 0, 6);
        letterT.set(3, 1, 4);
        letterT.set(3, 2, 0);
        letterT.set(4, 0, 6);
        letterT.set(4, 1, 6);
        letterT.set(4, 2, 0);
        letterT.set(5, 0, 0);
        letterT.set(5, 1, 6);
        letterT.set(5, 2, 0);
        letterT.set(6, 0, 0);
        letterT.set(6, 1, 4);
        letterT.set(6, 2, 0);
        letterT.set(7, 0, 2);
        letterT.set(7, 1, 4);
        letterT.set(7, 2, 0);
        letterT = letterT.transpose();
    }

    private static Matrix rotate(Matrix matrix, int rotation, int frames, String direction) {
        double angle = (rotation * Math.PI * 2) / (frames);
        Matrix rotationMatrix = new Matrix(3, 3);
        if (direction.equals("Z")) {
            rotationMatrix.set(0, 0, Math.cos(angle));
            rotationMatrix.set(0, 1, -Math.sin(angle));
            rotationMatrix.set(0, 2, 0);
            rotationMatrix.set(1, 0, Math.sin(angle));
            rotationMatrix.set(1, 1, Math.cos(angle));
            rotationMatrix.set(1, 2, 0);
            rotationMatrix.set(2, 0, 0);
            rotationMatrix.set(2, 1, 0);
            rotationMatrix.set(2, 2, 1);
        } else if (direction.equals("Y")) {
            rotationMatrix.set(0, 0, 1);
            rotationMatrix.set(0, 1, 0);
            rotationMatrix.set(0, 2, 0);
            rotationMatrix.set(1, 0, 0);
            rotationMatrix.set(1, 1, Math.cos(angle));
            rotationMatrix.set(1, 2, -Math.sin(angle));
            rotationMatrix.set(2, 0, 0);
            rotationMatrix.set(2, 1, Math.sin(angle));
            rotationMatrix.set(2, 2, Math.cos(angle));
        } else {
            rotationMatrix.set(0, 0, Math.cos(angle));
            rotationMatrix.set(0, 1, 0);
            rotationMatrix.set(0, 2, -Math.sin(angle));
            rotationMatrix.set(1, 0, 0);
            rotationMatrix.set(1, 1, 1);
            rotationMatrix.set(1, 2, 0);
            rotationMatrix.set(2, 0, Math.sin(angle));
            rotationMatrix.set(2, 1, 0);
            rotationMatrix.set(2, 2, Math.cos(angle));
        }
        return rotatedMatrix(matrix, rotationMatrix);
    }

    private static Matrix rotatedMatrix(Matrix matrix, Matrix rotationMatrix) {
        Matrix translationMatrix = new Matrix(matrix.getRowDimension(), matrix.getColumnDimension());
        for (int i = 0; i < translationMatrix.getColumnDimension(); ++i) {
            translationMatrix.set(0, i, 2.0);
            translationMatrix.set(1, i, 3.0);
            translationMatrix.set(2, i, 2.0);
        }
        return rotationMatrix.times(
                matrix.minus(translationMatrix)).plus(translationMatrix);
    }

    private static class MyPanel extends JPanel {

        private final ArrayList<Line> lines = new ArrayList<Line>();

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(800, 800);
        }

        public void clearLines() {
            this.lines.clear();
        }

        public void addLine(int x1, int y1, int x2, int y2) {
            this.lines.add(new Line(x1, y1, x2, y2));
        }

        public void paintComponent(Graphics g) {
            for(final Line r : lines) {
                r.paint2D((Graphics2D) g);
            }
        }
    }

    private static class Line {
        public final int x1;
        public final int x2;
        public final int y1;
        public final int y2;
        public Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
        public void paint2D(Graphics2D g2) {
            AffineTransform tform = AffineTransform.getTranslateInstance(0, 400);
            tform.scale( 1, -1);
            g2.setTransform( tform);
            g2.drawLine(this.x1, this.y1, this.x2, this.y2);
        }
    }
}
