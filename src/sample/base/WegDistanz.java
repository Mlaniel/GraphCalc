package sample.base;

public class WegDistanz extends Matrix {

    private int recCounter;
    public Matrix dMatrix;
    public Matrix wMatrix;
    private int[][] potenz;
    private int[] grade;

    public WegDistanz(int[][] matrix) {
        super(matrix);
        recCounter = 2;
        dMatrix = new Matrix(matrix.length);
        wMatrix = new Matrix(matrix.length);
        grade = new int[matrix.length];
    }

    private void berechneWegDistanz(int[][] pMatrix) {
        int size = getMatrix().length;

        for (int r = 0; r < size; r++) {
            for (int c = r; c < size; c++) {
                if (r == c) {
                    wMatrix.insertInto(r, c, 1);
                    dMatrix.insertInto(r, c, 0);
                } else if (getValue(r, c) > 0) {
                    wMatrix.insertInto(r, c, 1);
                    wMatrix.insertInto(c, r, 1);
                    dMatrix.insertInto(r, c, 1);
                    dMatrix.insertInto(c, r, 1);
                } else if (getValue(r, c) == 0) {
                    dMatrix.insertInto(r, c, -1);
                    dMatrix.insertInto(c, r, -1);
                    if (pMatrix[r][c] > 0 && dMatrix.getValue(r, c) == 0) {
                        dMatrix.insertInto(r, c, recCounter);
                        dMatrix.insertInto(c, r, recCounter);
                    }
                }
            }
        }
    }

    private void potenz(int[][] tmp) {
        int size = getMatrix().length;
        potenz = new int[size][size];
//        potenz[][] = ergebnis     (Ergebnismatrix)
//        tmpPotenz[][] = storage   (Zwischenspeicher)
//        super matrix = adjazenz   (Adjazenz)

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                int val = 0;
                for (int i = 0; i < size; i++) {
                    potenz[r][c] += tmp[r][i] * getValue(i, c);
                    val += getValue(i, c);
                }
                grade[c] = val;
            }
        }
//        System.out.println(dMatrix);
    }

    public void berrechneMatrizen() {
        int size = getMatrix().length;
        Matrix temp = new Matrix(super.getMatrix());
        while (recCounter < size - 1) {
            if (recCounter == 2) {
                potenz(temp.getMatrix());
            } else {
                potenz(potenz);
            }
//            System.out.println("Potenzmatrix: " + recCounter + " \n" + super.print(potenz));
            berechneWegDistanz(potenz);
            recCounter++;
        }
    }

    public int[] getGrade() {
        return grade;
    }

}
