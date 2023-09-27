package sample.uiContollers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import sample.Store;
import sample.Title;

import java.io.IOException;
import java.util.ArrayList;

public class NewTitleInputController {

    MainPageController mainPageController;
    @FXML
    TextField nameText, developerText;

    @FXML
    Label errorLabel;
    @FXML
    VBox platformRadioButtonContainer;

    ToggleGroup platformToggleGroup;

    Store myStore;
    ArrayList<String> platforms;

    public void initialize()
    {

        myStore = Store.getInstance();
        platformToggleGroup = new ToggleGroup();
        platforms = myStore.getPlatforms();

        for(String m: platforms)
        {
            RadioButton tempRadioButton = new RadioButton(m);
            platformRadioButtonContainer.getChildren().add(tempRadioButton);
            platformToggleGroup.getToggles().add(tempRadioButton);
            tempRadioButton.setUserData(m);
        }
        platformToggleGroup.getToggles().get(0).setSelected(true);
    }


    public void addToInventory() throws IOException {
        String newTitleName = nameText.getText();
        String newTitleDeveloper = developerText.getText();
        if(!nameText.getText().equals("") && !developerText.getText().equals(""))
        {
            Title addedTitle;
            if((addedTitle = myStore.addToInventory(newTitleName, newTitleDeveloper, (String) platformToggleGroup.getSelectedToggle().getUserData())) != null)
            {
                mainPageController.openTitlePage(addedTitle);
                nameText.getScene().getWindow().hide();
            }
            else
                errorLabel.setText("Invalid Data! Title already exists or insufficient information");
        }
    }

    public MainPageController getMainPageController() {
        return mainPageController;
    }

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }
}
