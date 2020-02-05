package sample.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

public class MatrixGridPane extends GridPane {
    private MainPane mp;
    private int size;
    private Button[][] buttons;

    public MatrixGridPane(Integer value, MainPane mp) {
        if (value.intValue() > 0 && value.intValue() < 16) {
            this.size = value.intValue();
        }
        initComps();
        this.mp = mp;
    }

    public MatrixGridPane(int[][] temp) {
        size = temp.length;
        initLabels(temp);
        this.mp = null;
    }

    private void initComps() {
        GridPane matrix = new GridPane();
        buttons = new Button[size][size];

        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < size + 1; j++) {
                if (j == 0 || i == 0) {

                    Label num = new Label(Integer.toString(i == 0 ? j : i));
                    num.setPrefSize(30, 30);
                    num.setAlignment(Pos.CENTER);
                    this.add(num, j == 0 ? i : 0, i == 0 ? j : 0);
                }

                int r = i - 1;
                int c = j - 1;
                Button b = new Button("0");
                b.setPrefSize(30, 30);
                b.setOnAction(event -> click(b, r, c));
                if (i != 0 && j != 0) {
                    buttons[i - 1][j - 1] = b;
                    matrix.add(b, j - 1, i - 1);
                }
                if (i == j) {
                    b.setDisable(true);
                }
            }
        }

        this.add(matrix, 1, 1, size, size);
    }

    private void click(Button b, int r, int c) {
        if (b.getText() == "0") {
            buttons[r][c].setText("1");
            buttons[c][r].setText("1");
        } else {
            buttons[r][c].setText("0");
            buttons[c][r].setText("0");
        }
//        System.out.println(b.getText());
        mp.submit(buttons[r][c]);
    }

    public int[][] getButtonMatrix() {
        int[][] temp = new int[size][size];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                temp[i][j] = Integer.parseInt(buttons[i][j].getText());
            }
        }
        return temp;
    }

    private void initLabels(int[][] numMatrix) {
        GridPane matrix = new GridPane();
        matrix.setGridLinesVisible(true);
        Label[][] labels = new Label[size][size];

        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= size; j++) {
                if (j == 0 || i == 0) {

                    Label num = new Label(Integer.toString(i == 0 ? j : i));
                    num.setPrefSize(30, 30);
                    num.setTextAlignment(TextAlignment.CENTER);
                    num.setStyle("-fx-font-weight: bold");
                    num.setAlignment(Pos.CENTER);
                    this.add(num, j == 0 ? i : 0, i == 0 ? j : 0);
                } else {
                    int r = i - 1;
                    int c = j - 1;
                    Label l = new Label(Integer.toString(numMatrix[r][c]));
                    l.setPrefSize(30, 30);

                    l.setAlignment(Pos.CENTER);
                    labels[r][c] = l;
                    matrix.add(l, r, c);

                }
            }
        }

        this.add(matrix, 1, 1, size, size);
    }
}
