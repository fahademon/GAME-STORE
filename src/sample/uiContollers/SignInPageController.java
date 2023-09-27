package sample.uiContollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Store;

import java.io.IOException;

public abstract class SignInPageController {
    @FXML
    Button signInButton;

    @FXML
    TextField idText;
    @FXML
    PasswordField passwordText;

    @FXML
    Label signInErrorLabel;

    Store myStore;
    public void initialize()
    {
        myStore = Store.getInstance();
    }

    public abstract void validateCredentials() throws IOException;

    public abstract void changeSceneToMainPage() throws IOException;

}
