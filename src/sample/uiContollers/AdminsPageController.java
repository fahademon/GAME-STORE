package sample.uiContollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import sample.Account;

import java.io.IOException;

public class AdminsPageController extends UsersPageController {

    @FXML
    VBox adminsContainer;

    @Override
    public void initialize() throws IOException {
        super.initialize();
        fillAdminsContainer();
    }

    @Override
    public void search() {
        super.search();
        myStore.searchAdmins(filter);
    }

    public void fillAdminsContainer() throws IOException {
        adminsContainer.getChildren().clear();

        for(Account i: myStore.searchAdmins(filter))
        {
            FXMLLoader adminLoader = new FXMLLoader(FXMLFileLoader.loadResource("AdminInList.fxml"));
            Parent adminInList = adminLoader.load();
            AdminInListController adminInListController = adminLoader.getController();
            adminInListController.fillData(i);
            adminInListController.setMainPageController((MainPageAdminController) mainPageController);
            adminsContainer.getChildren().add(adminInList);

        }
    }
}
