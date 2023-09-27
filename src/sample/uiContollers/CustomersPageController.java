package sample.uiContollers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Account;

import java.io.IOException;
import java.util.Date;

public class CustomersPageController extends UsersPageController{

    @FXML
    TableView customersContainer;

    @Override
    public void initialize() throws IOException {
        super.initialize();
        fillCustomersContainer();
        search();
    }

    private void fillCustomersContainer() {
        TableColumn<Account, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(200);
        usernameColumn.setCellValueFactory(data -> data.getValue().usernameProperty());

        TableColumn<Account, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(200);
        emailColumn.setCellValueFactory(data -> data.getValue().emailProperty());

        TableColumn<Account, Date> dateCreatedColumn = new TableColumn<>("Date Created");
        dateCreatedColumn.setMinWidth(200);
        dateCreatedColumn.setCellValueFactory(data -> data.getValue().dateCreatedProperty());

        customersContainer.getColumns().addAll(usernameColumn, emailColumn, dateCreatedColumn);
    }

    @Override
    public void search() {
        super.search();
        customersContainer.setItems(myStore.searchCustomers(filter));
    }
}
