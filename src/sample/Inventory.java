package sample;

import java.util.ArrayList;

public class Inventory
{ 
    public static Inventory instance = null;

    private ArrayList<Title> runningInventory;


    PersistenceDBHandler persistenceDBHandler;
    private Inventory()
    {
    }
    public static Inventory getInstance()
    {
        if(instance == null)
            instance = new Inventory();
        return instance;
    }
    public void setPersistenceDBHandler(PersistenceDBHandler persistenceDBHandler) {
        this.persistenceDBHandler = persistenceDBHandler;
    }


    public ArrayList<Title> search(BrowseFilter criteria)
    {
        return runningInventory = persistenceDBHandler.getTitles(criteria);
    }
    public Title saveTitleChanges(String oldName, String oldDeveloper, String oldPlatform, Title newTitle)
    {
        return persistenceDBHandler.updateTitle(oldName, oldDeveloper, oldPlatform, newTitle);
    }

    public Title add(String newTitleName, String newTitleDeveloper, String newTitlePlatform) {
        return persistenceDBHandler.InsertTitle(newTitleName, newTitleDeveloper, newTitlePlatform);
    }

    public void remove(Title title) {
        persistenceDBHandler.setTitleExistence(title, false);
    }
}