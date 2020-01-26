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

    /*
    Needs to calculate each component individually
    and store them in an int[] array,
    which will be stored into an ArrayList
     */
    public ArrayList<int[]> getKomponenten() {
        ArrayList<int[]> kompList = new ArrayList();
        int[] range = getBridge();
        int count = 0;

        for (int i = 0; i < wd.getMatrix().length; i++) {
            if (count != wd.getMatrix().length) {
                int[] temp = new int[range.length];
                for (int r = 0; r < wd.getMatrix().length; r++) {
                    for (int c = 0; c < wd.getMatrix().length; c++) {
                        temp[r] += (range[r] == 1 ? c : 0);
                        count++;
                    }
                }
                kompList.add(temp);
            } else {
                return kompList;
            }
        }


        for (int knoten : range) {
            if (knoten == 1) {
                count++;

            }
        }
//        kompList.add(getKnotenSingle());
        return kompList;
    }

//        for (int r = 0; r < wd.getMatrix().length; r++) {
//            int[] val;
//            for (int c = 0; c < wd.getMatrix().length; c++) {
//                val[r] +=
//            }


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
