package sample.uiContollers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class SignInPageAdminController extends SignInPageController{


    @Override
    public void validateCredentials() throws IOException {
        if(myStore.checkAccountAndLoginAdmin(idText.getText(), passwordText.getText()))
            changeSceneToMainPage();
        else
            signInErrorLabel.setText("Invalid username or password!");    }

    @Override
    public void changeSceneToMainPage() throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(FXMLFileLoader.loadResource("MainPageAdmin.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);

        Stage window = (Stage) signInButton.getScene().getWindow();
        window.setScene(mainPageScene);
    }


}
