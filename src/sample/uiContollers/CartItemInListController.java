package sample.uiContollers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import sample.CartItem;
import sample.Store;

import java.io.IOException;

public class CartItemInListController {

    @FXML
    Label priceLabel, nameLabel, platformLabel;
    @FXML
    ImageView titleImageView;

    CartPageController myController;
    Store myStore;
    CartItem me;

    public void initialize()
    {
        myStore = Store.getInstance();
    }

    public void remove() throws IOException {
        myStore.removeFromCart(me.getTitle());
        myController.refreshList();
    }

    public void setController(CartPageController controller)
    {
        this.myController = controller;
    }

    void setItem(CartItem item)
    {
        me = item;
        priceLabel.setText(me.getTitle().getPrice().toString());
        nameLabel.setText(item.getTitle().getName());
        platformLabel.setText(item.getTitle().getPlatform());
    }

}
