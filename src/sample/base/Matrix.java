package sample.base;

public class Matrix {

    private int[][] adjazenz;

    public Matrix(int[][] adjazenz) {
        this.adjazenz = adjazenz;
    }

    public Matrix(int size) {
        adjazenz = new int[size][size];
        init();
    }

    public int[][] getMatrix() {
        return adjazenz;
    }

    public void setMatrix(int[][] adjazenz) {
        this.adjazenz = adjazenz;
    }

    public int getValue(int r, int c) {
        return adjazenz[r][c];
    }

    public boolean ensureNotNull(Object o) {
        return o != null;
    }

    public int[][] reduce(int col) {
        int[][] temp = new int[adjazenz.length - 1][adjazenz.length - 1];
        for (int i = 0; i < adjazenz.length; i++) {
            for (int j = 0; j < adjazenz.length; j++) {
                if (j != col || i != col) {
                    temp[i][j] = adjazenz[i][j];
                }
            }
        }
        return temp;
    }

    public void init() {
        for (int r = 0; r < adjazenz.length; r++) {
            for (int c = 0; c < adjazenz.length; c++) {
                adjazenz[r][c] = 0;
            }
        }
    }

    public int[][] invert() {
        int[][] temp = this.getMatrix();
        for (int r = 0; r < adjazenz.length; r++) {
            for (int c = 0; c < adjazenz.length; c++) {
                temp[r][c] *= -1;
            }
        }
        return temp;
    }

    public void insertInto(int row, int col, int value) {
        if (adjazenz.length >= row && adjazenz.length >= col) {
            if (0 <= value) {
                adjazenz[row][col] = value;
            }
        }
    }

    public boolean contains(Matrix mat, int num) {
        int size = mat.getMatrix().length;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (mat.getValue(r, c) == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean rowHasEdge(int row) {
        for (int c = 0; c < adjazenz.length; c++) {
            if (adjazenz[row][c] > 0) {
                return true;
            }
        }
        return false;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < adjazenz.length; i++) {
            for (int j = 0; j < adjazenz.length; j++) {
                sb.append(adjazenz[i][j] + ", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String print(int[][] temp) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                sb.append(temp[i][j] + ", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
