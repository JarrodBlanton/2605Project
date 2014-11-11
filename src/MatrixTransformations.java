/**
 * Created by chris on 11/3/14.
 */
import Jama.Matrix;

public class MatrixTransformations {

    public Matrix rotateMatrix(Matrix matrix, double rads) {
        double[][] vals = {{Math.cos(rads), Math.sin(rads) * -1}, {Math.sin(rads), Math.cos(rads)}};
        Matrix rotationMatrix = new Matrix(vals);
        return matrix.times(rotationMatrix);
    }

    public Matrix reflectMatrix(Matrix orig) {

        return orig.times(//something here)
        return new Matrix();
    }

    public Matrix projectMatrix() {

    }

}
