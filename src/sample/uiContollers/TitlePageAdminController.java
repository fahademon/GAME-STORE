package sample.uiContollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import sample.Key;
import sample.Title;
import sample.Validator;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class TitlePageAdminController extends TitlePageCustomerController{

    @FXML
    TextField developerText, titleText;

    @FXML
    DatePicker releaseDatePicker;

    @FXML
    TextField priceText;

    @FXML
    Slider ratingSlider;

    @FXML
    VBox genreCheckBoxContainer, keysContainer, platformRadioButtonContainer;

    ToggleGroup platformToggleGroup;

    ArrayList<String> genres;
    ArrayList<String> platforms;
    String originalName, originalDeveloper, originalPlatform;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    FileChooser keysFileChooser;


    public void initialize()
    {

        super.initialize();
        platformToggleGroup = new ToggleGroup();
        genres = myStore.getGenres();
        platforms = myStore.getPlatforms();
        setPriceTextFormatter();

        for(String m: genres)
        {
            CheckBox tempCheckBox = new CheckBox(m);
            genreCheckBoxContainer.getChildren().add(tempCheckBox);
        }
        for(String m: platforms)
        {
            RadioButton tempRadioButton = new RadioButton(m);
            platformRadioButtonContainer.getChildren().add(tempRadioButton);
            platformToggleGroup.getToggles().add(tempRadioButton);
        }




    }

    public void fillKeysContainer() throws IOException {
        keysContainer.getChildren().clear();

        for(Key i: title.getKeys())
        {
            FXMLLoader keyLoader = new FXMLLoader(getClass().getResource("KeyInList.fxml"));
            Parent keyInList = keyLoader.load();
            KeyInListController keyInListController = keyLoader.getController();
            keyInListController.setKey(i);
            keyInListController.setTitlePageAdminController(this);
            keysContainer.getChildren().add(keyInList);

        }
    }
    @Override
    public void fillTitleData(Title myTitle) {
        title = myTitle;
        originalName = title.getName();
        originalDeveloper = title.getDeveloper();
        originalPlatform = title.getPlatform();
        titleText.setText(myTitle.getName());
        priceText.setText(myTitle.getPrice().toString());
        ratingSlider.setValue(myTitle.getRating());
        for(Node m: genreCheckBoxContainer.getChildren())
        {
            if(title.getGenre().contains(((CheckBox)m).getText()))
            {
                ((CheckBox) m).setSelected(true);
            }
        }

        for(Toggle m: platformToggleGroup.getToggles())
        {
            if(((RadioButton) m).getText().equals(title.getPlatform()))
            {
                platformToggleGroup.selectToggle(m);
                break;
            }
        }
        descriptionTextArea.setText(myTitle.getDescription());
        developerText.setText(myTitle.getDeveloper());
        releaseDatePicker.converterProperty().set(new LocalDateStringConverter(dateFormat, null));
        releaseDatePicker.setValue(title.getReleaseDate());
                //setText(dateFormat.format(myTitle.getReleaseDate()));

        try {
            fillKeysContainer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveChanges()
    {
        Title changedTitle = new Title();
        for(Node m: genreCheckBoxContainer.getChildren())
        {
            if(((CheckBox)m).isSelected())
                changedTitle.addGenre(((CheckBox) m).getText());
        }
        changedTitle.setPlatform (((RadioButton) platformToggleGroup.getSelectedToggle()).getText());
        changedTitle.setName(titleText.getText());
        changedTitle.setDeveloper(developerText.getText());
        changedTitle.setReleaseDate(releaseDatePicker.getValue());
        changedTitle.setDescription(descriptionTextArea.getText());
        changedTitle.setRating(ratingSlider.getValue());
        changedTitle.setPrice(Double.parseDouble(priceText.getText()));

        for(Node i: keysContainer.getChildren())
        {
            changedTitle.addKey (new Key(((Label) ((HBox) i).getChildren().get(0)).getText()));
        }

        if(myStore.saveTitleChanges(originalName, originalDeveloper, originalPlatform, changedTitle) == null)
        {
            saveChangesFailedAlert();
        }
        else{
            originalName = changedTitle.getName();
            originalDeveloper = changedTitle.getDeveloper();
            originalPlatform = changedTitle.getPlatform();
        }
    }

    private void saveChangesFailedAlert() {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");

        alert.setContentText("Changes invalid or title already exists!");
        alert.showAndWait();

    }


    public void addKeys() throws IOException {


        boolean flag = true;
        HashSet<Key> tempKeySet = new HashSet<>();
        keysFileChooser = new FileChooser();
        keysFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File keysFile = keysFileChooser.showOpenDialog(priceText.getScene().getWindow());
        if(keysFile != null)
        {
            Scanner myReader = new Scanner(keysFile);
            while (myReader.hasNextLine()) {
                String keyString = myReader.nextLine();
                if(!Validator.validateKeyFormat(keyString))
                {
                    flag = false;
                    break;
                }
                else
                    tempKeySet.add(new Key(keyString));
            }
            myReader.close();

            if(!flag)
                displayError("Keys in file invalid!!");
            else
            {
                title.getKeys().addAll(tempKeySet);
                fillKeysContainer();
            }
        }

    }

    private void displayError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");

        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setPriceTextFormatter()
    {
        Pattern validEditingState = Pattern.compile("(([1-9][0-9]*)|0)?(\\.[0-9]*)?");
        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c ;
            } else {
                return null ;
            }
        };
        StringConverter<Double> converter = new StringConverter<Double>() {

            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return 0.0 ;
                } else {
                    return Double.valueOf(s);
                }
            }


            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };
        TextFormatter<Double> textFormatter = new TextFormatter<>(converter, 0.0, filter);
        priceText.setTextFormatter(textFormatter);
    }

    public void removeTitle() throws IOException {
        myStore.removeFromInventory(title);
        ((MainPageAdminController) mainPageController).changeTabToTitles();
    }
}
