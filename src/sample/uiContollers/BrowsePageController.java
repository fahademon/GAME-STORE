package sample.uiContollers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sample.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;


public class BrowsePageController {

    MainPageController mainPageController;

    @FXML
    ToggleGroup sortToggleGroup, releaseToggleGroup, sortOrderToggleGroup;

    @FXML
    GridPane titlesContainer;
    @FXML
    TextField searchTextBrowse;


    @FXML
    VBox genreVBox, platformVBox;

    @FXML
    Slider rating, price;

    ArrayList<String> genres, platforms;
    BrowseFilter browseFilter;
    Store myStore;
    public void initialize() throws IOException {

        setSystemVariables();
        setFilterCheckBoxes();
        setFilterSliders();
        setFilterToggleGroups();


    }

    private void setFilterToggleGroups() {
        for(Toggle i: sortToggleGroup.getToggles())
        {
            if(((RadioButton) i).getText().toLowerCase(Locale.ROOT).equals("price"))
            {
                i.setUserData(SortBy.PRICE);
            }
            else if(((RadioButton) i).getText().toLowerCase(Locale.ROOT).equals("date"))
            {
                i.setUserData(SortBy.DATE);
            }
            else
            {
                i.setUserData(SortBy.RATING);
            }
        }

        for(Toggle i: releaseToggleGroup.getToggles())
        {
            if(((RadioButton) i).getText().toLowerCase(Locale.ROOT).equals("all time"))
            {
                i.setUserData(TimePeriod.ALL_TIME);
            }
            else if(((RadioButton) i).getText().toLowerCase(Locale.ROOT).equals("this year"))
            {
                i.setUserData(TimePeriod.THIS_YEAR);
            }
            else if(((RadioButton) i).getText().toLowerCase(Locale.ROOT).equals("this month"))
            {
                i.setUserData(TimePeriod.THIS_MONTH);
            }
            else
            {
                i.setUserData(TimePeriod.THIS_WEEK);
            }
        }

        for(Toggle i: sortOrderToggleGroup.getToggles())
        {
            ((RadioButton) i).setUserData(((RadioButton) i).getText());
        }

        sortToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                browseFilter.setSortBy((SortBy) t1.getUserData());
                try {
                    search();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        releaseToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                browseFilter.setTimePeriod((TimePeriod) t1.getUserData());
                try {
                    search();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sortOrderToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                browseFilter.setOrder((String) t1.getUserData());
                try {
                    search();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setFilterSliders() {
        rating.valueChangingProperty().addListener((v, oldValue, newValue) -> {
            setRating(rating);
            try {
                search();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        price.valueChangingProperty().addListener((v, oldValue, newValue) -> {
            setPrice(price);
            try {
                search();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setFilterCheckBoxes() {
        for(String m: genres)
       {
           CheckBox tempCheckBox = new CheckBox(m);
           tempCheckBox.selectedProperty().addListener((v, oldValue, newValue) -> {
               setGenre(tempCheckBox);
               try {
                   search();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           });
           genreVBox.getChildren().add(tempCheckBox);
       }
        for(String m: platforms)
        {
            CheckBox tempCheckBox = new CheckBox(m);
            tempCheckBox.selectedProperty().addListener((v, oldValue, newValue) -> {
                setPlatform(tempCheckBox);
                try {
                    search();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            platformVBox.getChildren().add(tempCheckBox);
        }
    }

    private void setSystemVariables() {
        myStore = Store.getInstance();
        genres = myStore.getGenres();
        platforms = myStore.getPlatforms();
        browseFilter = BrowseFilter.getInstance();
    }

    public void fillTitlesContainer() throws IOException
    {
        titlesContainer.getChildren().clear();
        int maxCol = 3, rowCnt = 0, colCnt = 0;
        for(Title i: myStore.searchTitles(browseFilter))
        {
            FXMLLoader titleLoader = new FXMLLoader(FXMLFileLoader.loadResource("TitleInGrid.fxml"));
            Parent titleInGrid = titleLoader.load();
            TitleInGridCustomerController titleController = titleLoader.getController();
            titleController.fillData(i);
            titleController.setMainPageController(mainPageController);
            titlesContainer.add(titleInGrid, colCnt, rowCnt);
            colCnt++;

            if(colCnt > maxCol)
            {
                rowCnt++;
                colCnt = 0;
            }
        }
    }

    public void search() throws IOException
    {
        browseFilter.setSearchText(searchTextBrowse.getText());
        fillTitlesContainer();
    }

    void setGenre(CheckBox box)
    {
       if(box.isSelected())
           browseFilter.addGenre(box.getText());
       else
           browseFilter.removeGenre(box.getText());
    }
    void setPlatform(CheckBox box)
    {
        if(box.isSelected())
            browseFilter.addPlatform(box.getText());
        else
            browseFilter.removePlatform(box.getText());
    }

    void setRating(Slider slider)
    {
        browseFilter.setRating(slider.getValue());
    }
    void setPrice(Slider slider)
    {
        browseFilter.setMaxPrice(slider.getValue());
    }

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }
}
