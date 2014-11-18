import Jama.Matrix;

/**
 * Created by JoelAnderson on 11/18/14.
 */
public class trace {

    public double trace(Matrix matrix) {
        double trace = 0;
        int columns = matrix.getColumnDimension();
        int rows = matrix.getRowDimension();
        for (int i = 0; i < Math.min(columns, rows); ++i) {
            trace += matrix.get(i, i);
        }
        return trace;
    }
}
