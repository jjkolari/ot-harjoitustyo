package budjettisovellus.ui;

import budjettisovellus.domain.Balance;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BudjettiUi extends Application {

    Balance balance;
    Label balanceTogether;
    TextField getUsername;
    Button login;
    Button createNewUser;
    Label errorInLogin;

    @Override
    public void init() throws Exception {
        balance = new Balance();
        errorInLogin = new Label("");
        //seuraava steppi on lisätä mahdollisuus yksilölliseen budjettii
        //jolloin tämä kohta muuttuu / kehittyy
    }

    @Override
    public void start(Stage stage) {

        Scene loginScene = new Scene(createLoginPane());
        stage.setScene(loginScene);
        stage.setTitle("Budjettisovellus.");
        stage.show();

        login.setOnAction((event) -> {
            if (login(getUsername.getText(), stage)) {
                getTheBalanceScene(stage);
            }
        });

        createNewUser.setOnAction((event) -> {
            //Tulossa samaa aikaa tietokantojen kanssa
            errorInLogin.setText("Toiminto on kehityksen alla");
            errorInLogin.setTextFill(Color.web("#778899"));
        });

    }

    public void updateBalance(TextField textField) {
        textField.clear();
        balanceTogether.setText("Yhteensä rahaa jäljellä: "
                + balance.getBalance());
    }

    public GridPane createLoginPane() {
        Label guideText = new Label("Kirjaudu sisään käyttäjätunnuksella tai luo uusi tunnus");
        getUsername = new TextField();
        getUsername.setText("jjkolari");
        
        login = new Button("Kirjaudu");
        createNewUser = new Button("Uusi tunnus");

        GridPane loginPane = new GridPane();
        loginPane.add(guideText, 0, 0);
        loginPane.add(getUsername, 0, 1);

        HBox buttons = new HBox();
        buttons.setPadding(new Insets(0, 20, 20, 0));
        buttons.setSpacing(10);
        buttons.getChildren().addAll(login, createNewUser);

        loginPane.add(buttons, 0, 2);
        loginPane.add(errorInLogin, 0, 3);

        loginPane.setPrefSize(350, 180);
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.setPadding(new Insets(20, 20, 20, 20));

        return loginPane;
    }

    public boolean login(String username, Stage stage) {
        getUsername.clear();
        if (username.equals("jjkolari")) {
            return true;
        } else {
            
            errorInLogin.setText("Käyttäjätunnus on virheellinen");
            errorInLogin.setTextFill(Color.web("#FF6347"));
            
            return false;
        }

    }

    public void createNewUserScene(Stage stage) {
        Label label = new Label("Uuden tunnuksen luominen ei toimi vielä");
        Button button = new Button("Takaisin");

        GridPane pane = new GridPane();
        pane.add(label, 0, 0);
        pane.add(button, 0, 10);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Budjetti-sovellus.");
        stage.show();

        button.setOnAction((event) -> {
            Scene loginScene = new Scene(createLoginPane());
            stage.setScene(loginScene);
            stage.setTitle("Budjetti-sovellus.");
            stage.show();
        });
    }

    public void getTheBalanceScene(Stage stage) {
        BorderPane pane = new BorderPane();
        pane.setPrefSize(400, 200);
        pane.setPadding(new Insets(20, 20, 20, 20));
        VBox vbox = new VBox(10);
        HBox insertPane1 = new HBox(20);
        HBox insertPane2 = new HBox(20);

        final TextField incomeText = new TextField();
        final TextField expenseText = new TextField();

        balanceTogether = new Label("Yhteensä rahaa jäljellä: "
                + balance.getBalance());

        Button incomeButton = new Button("Valmis");
        incomeButton.setOnAction((event) -> {
            balance.addIncome(incomeText.getText());
            updateBalance(incomeText);
        });

        Button expenseButton = new Button("Valmis");
        expenseButton.setOnAction((event) -> {
            if (!balance.addExpense(expenseText.getText())) {
                
            }
            updateBalance(expenseText);
        });

        insertPane1.getChildren().addAll(incomeText, incomeButton);
        insertPane2.getChildren().addAll(expenseText, expenseButton);

        vbox.getChildren().addAll(new Label("Lisää tulo: "), insertPane1,
                new Label("Lisää meno: "), insertPane2, balanceTogether);

        pane.setCenter(vbox);

        Scene loginScene = new Scene(pane);
        stage.setScene(loginScene);
        stage.setTitle("Budjettisovellus.");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
