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

    public int[] getBridge() {
        int[] str = new int[wd.getMatrix().length];
        for (int r = 0; r < wd.getMatrix().length; r++) {
            int val = 0;
            for (int c = 0; c < wd.getMatrix().length; c++) {
                val += wd.getValue(r, c);
            }
            str[r] = (val == 1 ? 0 : 1);
        }
        return str;
    }

    public String[] getKomponenten() {
        String[] kompList = wd.getKanten();

        return kompList;
    }

    private int[] getArtikulationen() {
        int[] tmp = new int[wd.getMatrix().length];
        for (int r = 0; r < wd.getMatrix().length; r++) {
            if (getGrade()[r] > 1) {
                tmp[r] = r + 1;
            }
        }
        return tmp;
    }
}
