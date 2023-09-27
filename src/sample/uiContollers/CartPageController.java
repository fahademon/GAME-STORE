package sample.uiContollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import sample.CartItem;
import sample.Store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class CartPageController {

    MainPageCustomerController mainPageController;
    @FXML
    VBox cartItemsList;
    @FXML
    Label totalPriceLabel;

    @FXML
    TextField cardNumberText, expirationText, CVVText;

    Store myStore;

    public void initialize() throws IOException {
        myStore = Store.getInstance();
    }

    public void refreshList() throws IOException {
        cartItemsList.getChildren().clear();

        for(CartItem i: myStore.getCartItems())
        {
            FXMLLoader cartItemLoader = new FXMLLoader(FXMLFileLoader.loadResource("CartItemInList.fxml"));
            cartItemsList.getChildren().add(cartItemLoader.load());
            CartItemInListController cartItemInList = cartItemLoader.getController();
            cartItemInList.setController(this);
            cartItemInList.setItem(i);
        }
        totalPriceLabel.setText("Price: Rs. " + myStore.getCartTotal().toString());

    }


    public void checkout() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you want to checkout?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == buttonTypeYes)
        {
            Integer orderNumber = myStore.checkout(cardNumberText.getText(), expirationText.getText(), CVVText.getText());
            if(orderNumber != null)
            {
                purchaseSuccessfulAlert(orderNumber);


            }
            else
            {
                purchaseUnsuccessfulAlert();
            }

        }

    }

    private void purchaseUnsuccessfulAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Payment not verified!");
        alert.showAndWait();
    }

    private void purchaseSuccessfulAlert(Integer orderNumber) throws IOException {
        Alert orderDetails = new Alert(Alert.AlertType.INFORMATION);
        orderDetails.setTitle("Order Details");
        orderDetails.setHeaderText("Order No. " + orderNumber + "\n with Total: Rs. " + myStore.getCartTotal().toString());

        mainPageController.changeTabToBrowse();
        orderDetails.showAndWait();
        clearCart();
    }

    void setMainPageController(MainPageCustomerController controller)
    {
        mainPageController = controller;
    }

    public void clearCart() throws IOException {
        ArrayList<CartItem> tempItems = new ArrayList<CartItem>();
        tempItems.addAll(myStore.getCartItems());
        for(CartItem i: tempItems)
        {
            myStore.removeFromCart(i.getTitle());
        }
        refreshList();
    }


}
