package sample.uiContollers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import sample.Store;
import sample.Title;

public abstract class TitlePageController {

    protected MainPageController mainPageController;
    @FXML
    Label priceLabel, ratingLabel, platformLabel, genresLabel, developerLabel, releaseDateLabel;

    @FXML
    TextArea descriptionTextArea;


    Title title;
    Store myStore;

    public void initialize()
    {
        myStore = Store.getInstance();
    }

    public abstract void fillTitleData(Title myTitle);

    public Title getTitle() {
        return title;
    }

}
