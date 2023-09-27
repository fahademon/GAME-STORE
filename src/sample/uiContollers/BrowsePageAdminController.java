package sample.uiContollers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Title;

import java.io.IOException;

public class BrowsePageAdminController extends BrowsePageController {

    @Override
    public void fillTitlesContainer() throws IOException {
        titlesContainer.getChildren().clear();
        int maxCol = 3, rowCnt = 0, colCnt = 0;
        for(Title i: myStore.searchTitles(browseFilter))
        {
            FXMLLoader titleLoader = new FXMLLoader(FXMLFileLoader.loadResource("TitleInGridAdmin.fxml"));
            Parent titleInGrid = titleLoader.load();
            TitleInGridAdminController titleController = titleLoader.getController();
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

    public void addTitle() throws IOException {

        Stage newTitleInputWindow = new Stage();
        newTitleInputWindow.setTitle("New Title");
        FXMLLoader windowLoader = new FXMLLoader(FXMLFileLoader.loadResource("NewTitleInput.fxml"));
        Parent newTitleParent = windowLoader.load();
        NewTitleInputController newTitleInputController = windowLoader.getController();
        newTitleInputController.setMainPageController(this.mainPageController);
        newTitleInputWindow.setScene(new Scene(newTitleParent));
        newTitleInputWindow.show();

    }

}
