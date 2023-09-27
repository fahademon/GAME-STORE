package sample.uiContollers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Account;
import sample.Title;

import java.io.IOException;

public class MainPageAdminController extends MainPageController{



    public void changeTabToTitles() throws IOException {
        BrowsePageAdminController browsePageController = (BrowsePageAdminController) loadPage("BrowsePageAdmin");
        browsePageController.setMainPageController(this);
        browsePageController.fillTitlesContainer();
        pageLabel.setText("Titles");
    }
    public void changeTabToAdmins() throws IOException {
        AdminsPageController adminsPageController = (AdminsPageController) loadPage("AdminsPage");
        adminsPageController.setMainPageController(this);
        adminsPageController.fillAdminsContainer();
        pageLabel.setText("Admins");
    }
    public void changeTabToCustomers() {
        CustomersPageController customersPageController = (CustomersPageController) loadPage("CustomersPage");
        customersPageController.setMainPageController(this);
        pageLabel.setText("Customers");
    }


    public void changeSceneToSignIn() throws IOException
    {
        Parent loginParent = FXMLLoader.load(FXMLFileLoader.loadResource("SignInPageAdmin.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage) signOutButton.getScene().getWindow();
        window.setScene(loginScene);
    }

    @Override
    public void openTitlePage(Title myTitle) {
        TitlePageAdminController titlePageAdminController = (TitlePageAdminController) loadPage("TitlePageAdmin");
        titlePageAdminController.fillTitleData(myTitle);
    }

    public void openAdminPage(Account account) {
        AccountPageAdminController accountPageAdminController = (AccountPageAdminController) loadPage("AccountPageAdmin");
        accountPageAdminController.setMainPageController(this);
        accountPageAdminController.fillAccountData(account);

    }
}
