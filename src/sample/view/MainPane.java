package sample.view;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import sample.control.MainController;

public class MainPane extends BorderPane {

    private MainController mc;
    private MatrixGridPane matrixPane, dPane, wPane;
    private GridPane center, ergebnis;
    public Spinner<Integer> input;

    public MainPane(MainController mc) {
        input = new Spinner(2, 15, 5);
        addComponents();
        initEvents();
        this.mc = mc;
    }

    /*
     * Wichtig Anfang
     */
    public void addComponents() {
        setNewMatrixPane();

        display(null);

        setTop(new TopMenu(this));
        setCenter(center);
    }

    private void setNewMatrixPane() {
        matrixPane = new MatrixGridPane(input.getValue(), this);
    }

    private void initEvents() {
        input.setOnMouseClicked(event -> reloadCenter());
        input.getEditor().setOnKeyPressed(event -> reloadCenter());
    }

    /*
     * Buttons
     */
    public void submit(Object o) {
        display(o);
        initEvents();
    }

    private void reloadCenter() {
        setNewMatrixPane();
        display(null);
        setCenter(center);
    }

    /*
     * sontiges
     */
    private void display(Object o) {
        center = new GridPane();
        center.add(matrixPane, 0, 2);
        center.add(new Label("Adjazenzmatrix"), 0, 1);
        center.add(input, 0, 0);

        if (o != null) {
            center.getChildren().removeAll(dPane, wPane);

            mc.calculate();
            displayErgebnis();

            dPane = new MatrixGridPane(mc.getDistance());
            wPane = new MatrixGridPane(mc.getWay());

            center.add(wPane, 1, 2);
            center.add(new Label("Wegmatrix"), 1, 1);

            center.add(dPane, 2, 2);
            center.add(new Label("Distanzmatrix"), 2, 1);

            center.add(ergebnis, 0, 3);
            GridPane.setColumnSpan(ergebnis, 3);
        }
        setCenter(center);
    }

    public void displayErgebnis() {
        ergebnis = new GridPane();

        /*
        Ausgabe Knotengrade
         */
        Label lGrade = new Label("Knotengrade: ");
        ergebnis.add(lGrade, 0, 0);
        int[] grad = mc.getGrade();
        for (int i = 1; i <= getInput(); i++) {
            Label g = new Label(" Grad(" + i + ")=" + grad[i - 1] + " ");
            ergebnis.add(g, 0, i);
        }

        ergebnis.add(new Separator(), 1, 0);

        /*
        Ausgabe Kanten
        */
        Label lKanten = new Label("Kanten: ");
        ergebnis.add(lKanten, 2, 0);
        String kant = mc.getKanten();
        Label c = new Label(kant);
        ergebnis.add(c, 2, 1);
//        Label lBridge = new Label("Brücken: ");
//        ergebnis.add(lBridge, 2, 2);
//        Label b = new Label(mc.getBridges());
//        ergebnis.add(b, 2, 3);

        ergebnis.add(new Separator(), 3, 0);

        /*
        Ausgabe Komponenten
        */
        Label lKomp = new Label("Komponenten: ");
        ergebnis.add(lKomp, 4, 0);
        String[] str = mc.getKomponenten();
        for (int i = 0; i < getInput(); i++) {
            Label k = new Label(str[i]);
            ergebnis.add(k, 4, i + 1);
        }
    }

    public MatrixGridPane getMatrixPane() {
        return matrixPane;
    }

    public int getInput() {
        return input.getValue();
    }
}
