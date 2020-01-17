package sample.view;

import javafx.scene.control.Label;
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
        }
        setCenter(center);
    }

    public void displayErgebnis() {
        ergebnis = new GridPane();

        Label lGrade = new Label("Knotengrade: ");
        ergebnis.add(lGrade, 0, 0);
        int[] grad = mc.getGrade();
        for (int i = 1; i <= getInput(); i++) {
            Label g = new Label("Grad(" + i + ")=" + grad[i - 1]);
            ergebnis.add(g, 0, i);
        }

        Label lBridge = new Label("Brücken: ");
        ergebnis.add(lBridge, 1, 0);
        String[] cBridge = mc.getBridge();
        for (int i = 1; i <= getInput(); i++) {
            Label b = new Label("Knoten " + i + ": " + (cBridge[i - 1] == null ? "_" : "Brücke"));
            ergebnis.add(b, 1, i);
        }
//        Label
    }

    public MatrixGridPane getMatrixPane() {
        return matrixPane;
    }

    public int getInput() {
        return input.getValue();
    }
}
