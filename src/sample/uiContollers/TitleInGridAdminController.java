package sample.uiContollers;


import java.io.IOException;

public class TitleInGridAdminController extends AccessibleTitleController{


    public void removeTitle() throws IOException {
        myStore.removeFromInventory(title);
        ((MainPageAdminController) mainPageController).changeTabToTitles();
    }

}
