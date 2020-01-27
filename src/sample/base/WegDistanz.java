package sample.base;

import java.util.ArrayList;

public class WegDistanz extends Matrix {

    private int recCounter;
    public Matrix dMatrix;
    public Matrix wMatrix;
    private int[][] potenz;
    private int[] grade;
    private ArrayList<ArrayList<Integer>> komponenten;

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

    private ArrayList<Integer> getKnotenArray() {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < getMatrix().length; i++) {
            temp.add(i);
        }
        return temp;
    }

    public int[] getGrade() {
        return grade;
    }

    public ArrayList<int[]> getBridges(int[][] matrix) {
        ArrayList bruecken = new ArrayList();
        int anz = komponenten.size();

        if (matrix != null && this.contains(this, 1)) {
            for (int r = 0; r < getMatrix().length; r++) {
                for (int c = 0; c < getMatrix().length; c++) {
                    int tempVal = matrix[r][c];
                    if (tempVal == 1) {
                        matrix[r][c] = 0;
                        matrix[c][r] = 0;
                        getKomponent();
                        if (anz < komponenten.size()) {
                            int[] kante = new int[2];
                            kante[0] = c;
                            kante[1] = r;
                            bruecken.add(kante);
                        }
                        matrix[r][c] = 1;
                        matrix[c][r] = 1;
                    }
                }
            }
        }
        getKomponent();
        return bruecken;
    }

    public String getKanten() {
        StringBuilder tmp = new StringBuilder();
        int[] count = new int[getMatrix().length];

        for (int r = 0; r < getMatrix().length; r++) {
            for (int c = r; c < getMatrix().length; c++) {
                if (getValue(r, c) == 1) {
                    count[r]++;
                    if (count[r] > 1 || count[c] > 1) {
                        tmp.append("{" + (r + 1) + "-" + (c + 1) + "},");
                    } else {
                        tmp.append("-{" + (r + 1) + "-" + (c + 1) + "}-,");
                    }
                }
            }
        }
        return tmp.toString();
    }

    public ArrayList<ArrayList<Integer>> getKomponent() {
        komponenten = new ArrayList<>();
        ArrayList<Integer> knoten = getKnotenArray();
        String komp = "";

        if (this.contains(wMatrix, 1)) {
            for (int r = 0; r < getMatrix().length; r++) {
                ArrayList temp = new ArrayList();

                for (int c = getMatrix().length - 1; c >= r; c--) {
                    if (wMatrix.getValue(r, c) == 1 && knoten.contains(c)) {
                        temp.add(0, c + 1);
                        knoten.remove(new Integer(c));
                    }
                }

                if (temp.size() > 0) {
                    komponenten.add(temp);
                }
            }
        } else {
            for (int i = 0; i < getMatrix().length; i++) {
                ArrayList temp = new ArrayList();
                temp.add(knoten.get(i));
                komponenten.add(temp);
            }
        }
        return komponenten;
    }
}
