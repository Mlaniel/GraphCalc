package sample.control;

import sample.base.WegDistanz;
import sample.view.MainPane;

import java.util.ArrayList;

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

    public String getKanten() {
        String kompList = wd.getKanten();
        return kompList;
    }

    public String[] computeKomponenten() {
        String[] str = new String[getKanten().length()];

        ArrayList temp = wd.computeKomponent();

        for (int i = 0; i < temp.size(); i++) {
            str[i] = (temp.get(i).toString());
        }

        return str;
    }

    public String getKomp() {
        String str = "";
        int komp = wd.getKomp();

        str += komp;

        return str;
    }

    public String getBridges() {
        ArrayList<int[]> bridges = wd.getBridges(wd.wMatrix.getMatrix());
        String str = "";

        for (int i = 0; i < bridges.size(); i++) {
            int[] tmp = bridges.get(i);
            for (int j : tmp) {
                str += j + ", ";
            }
        }

        return str;
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
