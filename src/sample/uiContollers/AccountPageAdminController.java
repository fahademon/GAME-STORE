package sample.uiContollers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AccountPageAdminController extends AccountPageController{


    @Override
    public void changeSceneToSignIn() throws IOException {
        Parent loginParent = FXMLLoader.load(FXMLFileLoader.loadResource("SignInPageAdmin.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage) deleteAccountButton.getScene().getWindow();
        window.setScene(loginScene);
    }

    @Override
    public void deleteAccount() throws IOException {

        if(myStore.getAdminCount() <= 1)
            deletionNotPossible();
        else
        {
            String ownAccountWarning = "";
            boolean flag = false;

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            if (myAccount.getEmail() == myStore.getActiveAccount().getEmail()) {
                flag = true;
                ownAccountWarning = "\nThis is your own account so you will be signed out!";
            }

            alert.setTitle("Confirmation");

            alert.setContentText("Are you sure you want to delete this account?" + ownAccountWarning);

            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeNo = new ButtonType("No");

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonTypeYes) {

                myStore.deleteAdminAccount(myAccount);

                if (flag)
                    changeSceneToSignIn();
                else
                    ((MainPageAdminController) mainPageController).changeTabToAdmins();
            }
        }
    }

    private void deletionNotPossible() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Deletion not possible!");
        alert.setContentText("This is the last admin account!");
        alert.showAndWait();
    }

    @Override
    public void saveChanges() {
        super.saveChanges();
        myStore.saveAccountChangesAdmin(myAccount);
    }

    @Override
    protected boolean usernameExists(String username) {
        return myStore.usernameExistsAdmin(username);
    }


}
