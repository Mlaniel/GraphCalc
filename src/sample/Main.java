/*  @author: Daniel Mladek
 *  @version: 1.0a
 *  @desc: Graphen/Matrixrechner für den Programmierunterricht
 */

package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.control.MainController;

public class Main extends Application {

    private static Stage eingabe;
    MainController mc;

    @Override
    public void start(Stage primaryStage) throws Exception {
        eingabe = primaryStage;
        mc = new MainController();
        Scene scene = new Scene(MainController.getMainPane(), 900, 500);

        eingabe.setScene(scene);
        eingabe.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void showAlert(Alert.AlertType at, ButtonType bt, String msg) {
        Alert alert = new Alert(at, msg, bt);
        alert.initModality(Modality.WINDOW_MODAL);
        alert.setHeaderText(null);

        alert.showAndWait();
    }
}
