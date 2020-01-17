package sample.view;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class TopMenu extends MenuBar {
    private Menu mData;
    private MenuItem miExit;
    private MainPane window;

    public TopMenu(MainPane win) {
        window = win;
        initComps();
        addComps();
        initEvents();
    }

    private void addComps() {
        mData.getItems().addAll(miExit);
        this.getMenus().add(mData);
    }

    public void initComps() {
               /*
        MENU KOMPONENT IMPLEMENTIEREN
         */
        mData = new Menu("Datei");
        miExit = new MenuItem("Exit");

    }

    public void initEvents() {
        miExit.setOnAction(event -> Platform.exit());
    }
}
