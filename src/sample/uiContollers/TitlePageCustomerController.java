package sample.uiContollers;

import javafx.scene.control.*;
import sample.Title;


public class TitlePageCustomerController extends TitlePageController {


    public void addToCart()
    {
        if(!myStore.addToCart(title))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Out of Stock!");
            alert.showAndWait();
        }
    }

    @Override
    public void fillTitleData(Title myTitle) {
        title = myTitle;
        priceLabel.setText(priceLabel.getText() + myTitle.getPrice().toString());
        ratingLabel.setText(ratingLabel.getText() + myTitle.getRating().toString());
        platformLabel.setText(platformLabel.getText() + myTitle.getPlatform());
        genresLabel.setText(genresLabel.getText() + myTitle.getGenreString());
        descriptionTextArea.setText(myTitle.getDescription());
        developerLabel.setText(developerLabel.getText() + myTitle.getDeveloper());
        releaseDateLabel.setText(releaseDateLabel.getText() + myTitle.getReleaseDate().toString());
    }

}
