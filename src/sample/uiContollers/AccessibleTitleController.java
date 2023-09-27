package sample.uiContollers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Store;
import sample.Title;

import java.io.IOException;

public abstract class AccessibleTitleController {


    protected MainPageController mainPageController;

    @FXML
    Label nameLabel, platformLabel;

    protected Title title;
    Store myStore;


    public void initialize()
    {
        myStore = Store.getInstance();
    }

    public Title getTitle() {
        return title;
    }

    public void openTitlePage() throws IOException {
        mainPageController.openTitlePage(title);
    }
    public void fillData(Title title) {
        this.title = title;
        nameLabel.setText(title.getName());
        platformLabel.setText(title.getPlatform());
    }

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    public MainPageController getMainPageController() {
        return mainPageController;
    }
}
