package sample.uiContollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Order;
import sample.Title;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class AccountPageCustomerController extends AccountPageController{

    @FXML
    VBox ownedTitlesContainer;
    @FXML
    VBox ordersContainer;

    ArrayList<Title> ownedTitles;
    ArrayList<Order> orders;
    @Override
    public void initialize() throws IOException {
        super.initialize();
        fillOrdersContainer();
        fillOwnedTitlesContainer();
    }

    private void fillOrdersContainer() throws IOException {

        for (Order order : orders = myStore.getOrders()) {
            FXMLLoader orderLoader = new FXMLLoader(FXMLFileLoader.loadResource("OrderContainer.fxml"));
            ordersContainer.getChildren().add(orderLoader.load());
            OrderContainerController orderInList = orderLoader.getController();
            orderInList.fillContainer(order);
        }

    }
    private void fillOwnedTitlesContainer() throws IOException {

        ownedTitlesContainer.getChildren().clear();
        fillOwnedTitlesList();
        for(Title i: ownedTitles)
        {
            FXMLLoader titleLoader = new FXMLLoader(FXMLFileLoader.loadResource("TitleInList.fxml"));
            ownedTitlesContainer.getChildren().add(titleLoader.load());
            TitleInListController titleInList = titleLoader.getController();
            titleInList.setMainPageController(this.mainPageController);
            titleInList.fillData(i);
        }
    }

    private void fillOwnedTitlesList()
    {
        ownedTitles = new ArrayList<>();
        for(Order i: orders)
            for(Title j: i.getTitles()) {
                Title tempTitle = find(j);
                if (tempTitle ==null)
                ownedTitles.add(new Title(j));
                else
                    tempTitle.getKeys().addAll(j.getKeys());
            }
    }

    private Title find(Title t)
    {
        for(Title i: ownedTitles)
            if(i.equals(t))
                return i;

        return null;
    }



    @Override
    public void deleteAccount() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you want to delete your account?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == buttonTypeYes)
        {

            if(myAccount.getEmail() == myStore.getActiveAccount().getEmail())
                changeSceneToSignIn();
            myStore.deleteCustomerAccount(myAccount);
        }

    }

    @Override
    public void changeSceneToSignIn() throws IOException
    {
        Parent loginParent = FXMLLoader.load(FXMLFileLoader.loadResource("SignInPageCustomer.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage) deleteAccountButton.getScene().getWindow();
        window.setScene(loginScene);
    }

    @Override
    public void saveChanges() {
        super.saveChanges();
        myStore.saveAccountChangesCustomer(myAccount);

    }

    @Override
    protected boolean usernameExists(String username) {
        return myStore.usernameExistsCustomer(username);
    }


}
