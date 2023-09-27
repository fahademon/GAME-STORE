package sample.uiContollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Store;
import sample.Validator;

import java.io.IOException;
import java.util.Locale;


public class SignUpPageCustomerController {

    @FXML
    TextField signUpPageUsername, signUpPageEmail, signUpPageRepeatEmail;
    @FXML
    PasswordField signUpPagePassword, signUpPageRepeatPassword;

    @FXML
    Label signUpPageUsernameError, signUpPageEmailError, signUpPageRepeatEmailError, signUpPagePasswordError, signUpPageRepeatPasswordError;

    @FXML
    Button back, signUp;

    Store myStore;
    boolean[] conditions = {false, false, false, false, false};
    public void initialize()
    {
        signUp.setDisable(true);

        myStore = Store.getInstance();
        signUpPageUsername.textProperty().addListener((v, oldValue, newValue) -> {

            conditions[0] = false;
            if(newValue != "")
            {
                if(myStore.usernameExistsCustomer(newValue))
                    signUpPageUsernameError.setText("Username already exists!");
                else
                {
                    signUpPageUsernameError.setText("");
                    conditions[0] = true;
                }
            }
            else
                signUpPageUsernameError.setText("Username empty!");
            resetSignUpButton();
        });

        signUpPageEmail.textProperty().addListener((v, oldValue, newValue) -> {

            conditions[1] = false;
            if(!Validator.validateEmailFormat(newValue))
                signUpPageEmailError.setText("Email Invalid!");
            else if(myStore.emailExists(newValue))
                signUpPageEmailError.setText("Email already exists!");
            else
            {
                signUpPageEmailError.setText("");
                conditions[1] = true;
            }
            resetSignUpButton();
        });

        signUpPageRepeatEmail.textProperty().addListener((v, oldValue, newValue) -> {

            conditions[2] = false;
            if(!signUpPageEmail.getText().equals(newValue))
                signUpPageRepeatEmailError.setText("Emails don't match!");
            else
            {
                signUpPageRepeatEmailError.setText("");
                conditions[2] = true;
            }
            resetSignUpButton();
        });

        signUpPagePassword.textProperty().addListener((v, oldValue, newValue) -> {

            conditions[3] = false;
            if(!Validator.validatePasswordIntegrity(newValue))
                signUpPagePasswordError.setText(Validator.passwordErrorMessage);
            else
            {
                signUpPagePasswordError.setText("");
                conditions[3] = true;
            }
            resetSignUpButton();
        });

        signUpPageRepeatPassword.textProperty().addListener((v, oldValue, newValue) -> {

            conditions[4] = false;
            if(!signUpPagePassword.getText().equals(newValue))
                signUpPageRepeatPasswordError.setText("Passwords don't match!");
            else
            {
                signUpPageRepeatPasswordError.setText("");
                conditions[4] = true;
            }
            resetSignUpButton();
        });

    }
    void resetSignUpButton()
    {
        boolean flag = true;
        for(boolean i: conditions)
            if(!i)
                flag = false;

        signUp.setDisable(!flag);
    }

    public void makeAccount() throws IOException {
        myStore.saveAccountAndSetActiveCustomer(signUpPageUsername.getText(), signUpPageEmail.getText().toLowerCase(Locale.ROOT), signUpPagePassword.getText());
        changeSceneToMainPage();
    }

    public void changeSceneToMainPage() throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPageCustomer.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);

        Stage window = (Stage) signUp.getScene().getWindow();
        window.setScene(mainPageScene);
    }

    public void changeSceneToSignIn() throws IOException
    {
        myStore.signOut();
        Parent loginParent = FXMLLoader.load(getClass().getResource("SignInPageCustomer.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage) back.getScene().getWindow();
        window.setScene(loginScene);
    }
}
