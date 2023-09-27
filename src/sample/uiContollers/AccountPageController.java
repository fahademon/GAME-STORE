package sample.uiContollers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Account;
import sample.Store;
import sample.Validator;

import java.io.IOException;

public abstract class AccountPageController {

    protected MainPageController mainPageController;
    @FXML
    Button deleteAccountButton,
            saveChangesButton;

    @FXML
    TextField usernameText, emailText;
    @FXML
    PasswordField passwordText, confirmPasswordText;
    @FXML
    Label usernameErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel;


    Store myStore;
    Account myAccount;
    boolean[] conditions = {false, false, false};

    public void initialize() throws IOException {
        myStore = Store.getInstance();
    }

    public void fillAccountData(Account account) {
        myAccount = account;

        usernameText.textProperty().addListener((v, oldValue, newValue) -> {
            conditions[0] = false;
            if(!newValue.equals(myAccount.getUsername()))
            {
                if(!usernameExists(newValue))
                {
                    usernameErrorLabel.setText("");
                    conditions[0] = true;
                }
                else
                    usernameErrorLabel.setText("Username already exists!");
            }

            resetSaveChangesButton();

        });
        passwordText.textProperty().addListener((v, oldValue, newValue) -> {
            conditions[1] = false;
            if(!newValue.equals(myAccount.getPassword()))
            {
                if(Validator.validatePasswordIntegrity(newValue))
                {
                    passwordErrorLabel.setText("");
                    confirmPasswordText.setDisable(false);
                    conditions[1] = true;
                }
                else
                    passwordErrorLabel.setText(Validator.passwordErrorMessage);
            }
            else
                confirmPasswordText.setDisable(true);

            resetSaveChangesButton();
        });

        confirmPasswordText.textProperty().addListener((v, oldValue, newValue) -> {
            conditions[2] = false;
            if(newValue.equals(passwordText.getText()))
            {
                confirmPasswordErrorLabel.setText("");
                conditions[2] = true;
            }
            else
                confirmPasswordErrorLabel.setText("Passwords don't match!");
            resetSaveChangesButton();
        });

        emailText.setText(myAccount.getEmail());
        usernameText.setText(myAccount.getUsername());
        passwordText.setText(myAccount.getPassword());
    }

    void resetSaveChangesButton()
    {
        saveChangesButton.setDisable(!(conditions[0] || (conditions[1] && conditions[2])));
    }

    public void saveChanges()
    {
        myAccount.setPassword(passwordText.getText());
        myAccount.setUsername(usernameText.getText());
    }
    public abstract void deleteAccount() throws IOException;

    public abstract void changeSceneToSignIn() throws IOException;
    protected abstract boolean usernameExists(String username);

    public void setMainPageController(MainPageController mainPageController)
    {
        this.mainPageController = mainPageController;
    }
}
