package sample;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;


public class Account extends Displayable{

    private StringProperty username;
    private StringProperty email;
    private StringProperty password;
    private ObjectProperty<Date>  dateCreated= new SimpleObjectProperty<>(this, "dateCreated");


    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public Date getDateCreated() {
        return dateCreated.get();
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated.set(dateCreated);
    }


    public Account() {
        this.username = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
    }

    public Account(Account account) {
        this.username = account.username;
        this.email = account.email;
        this.password = account.password;
    }
    public Account(String username, String email, String password)
    {
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public ObjectProperty<Date> dateCreatedProperty() {
        return dateCreated;
    }

}
