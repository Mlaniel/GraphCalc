package sample.control;

import sample.base.WegDistanz;
import sample.view.MainPane;

public class MainController {

    private WegDistanz wd;
    public static MainPane mp;

    public MainController() {
        initMain();
    }

    private void initMain() {
        mp = new MainPane(this);
    }

    public void calculate() {
        wd = new WegDistanz(mp.getMatrixPane().getButtonMatrix());

        wd.berrechneMatrizen();
    }

    public int[][] getDistance() {

        return wd.dMatrix.getMatrix();
    }

    public int[][] getWay() {
        return wd.wMatrix.getMatrix();
    }

    public int[] getGrade() {
        return wd.getGrade();
    }

    public static MainPane getMainPane() {
        return mp;
    }

    public String[] getBridge() {
        String[] str = new String[wd.getMatrix().length];
        for (int r = 0; r < wd.getMatrix().length; r++) {
            int val = 0;
            for (int c = 0; c < wd.getMatrix().length; c++) {
                val += wd.getValue(r, c);
            }
            if (val == 1) {
                str[r] = "Brücke";
            }
        }
        return str;
    }
}
