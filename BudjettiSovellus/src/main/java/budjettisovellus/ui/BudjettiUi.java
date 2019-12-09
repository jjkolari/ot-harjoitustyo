package budjettisovellus.ui;

import budjettisovellus.domain.Balance;
import budjettisovellus.domain.Transaction;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
                getTheBalanceScene(stage, getUsername.getText());
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

        if (username.equals("jjkolari")) {
            return true;
        } else {

            errorInLogin.setText("Käytä käyttäjätunnusta jjkolari");
            errorInLogin.setTextFill(Color.web("#FF6347"));

            return false;
        }

    }

    public void getTheBalanceScene(Stage stage, String username) {
        //creates the scene where you can add income/expense
        //and show your balance/statics
        BorderPane pane = new BorderPane();
        pane.setPrefSize(300, 200);
        pane.setPadding(new Insets(20, 20, 20, 20));
        VBox vbox = new VBox(10);
        HBox insertPane1 = new HBox(20);
        HBox insertPane2 = new HBox(20);
        HBox text1 = new HBox(110);
        HBox text2 = new HBox(100);

        TextField incomeText = new TextField();
        TextField expenseText = new TextField();
        Label errorText = new Label("");
        errorText.setTextFill(Color.web("#FF6347"));

        balanceTogether = new Label("Yhteensä rahaa jäljellä: "
                + balance.getBalance());

        Button incomeButton = new Button("Valmis");
        incomeButton.setOnAction((event) -> {
            errorText.setText("");
            balance.addIncome(incomeText.getText());
            updateBalance(incomeText);
        });

        Button expenseButton = new Button("Valmis");
        expenseButton.setOnAction((event) -> {
            errorText.setText("");
            if (!balance.addExpense(expenseText.getText())) {
                errorText.setText("Rahasi eivät riitä");
            }
            updateBalance(expenseText);
        });

        Button getStatics = new Button("Analyysi");
        getStatics.setOnAction((event) -> {
            errorText.setText("");
            createStatics(balance.getTransactions(), stage);
        });

        insertPane1.getChildren().addAll(incomeText, incomeButton);
        insertPane2.getChildren().addAll(expenseText, expenseButton);

        vbox.getChildren().addAll(new Label("Lisää tulo: "), insertPane1,
                new Label("Lisää meno: "), insertPane2, balanceTogether, getStatics);

        pane.setCenter(vbox);

        Scene loginScene = new Scene(pane);
        stage.setScene(loginScene);
        stage.setTitle("Budjettisovellus: " + username);
        stage.show();
    }

    public void createStatics(List<Transaction> transactions, Stage stage) {
        //creates statics as a linechart
        //needs a lot of improvment still
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Aika (sekunti)");
        yAxis.setLabel("Rahamäärä (euro)");

        LineChart<Number, Number> linechart = new LineChart<>(xAxis, yAxis);
        linechart.setTitle("Lisäämäsi menot ja tulot");

        XYChart.Series balanceWTime = new XYChart.Series();
        balanceWTime.setName("Rahatilanne");

        transactions.stream().forEach(t -> {
            balanceWTime.getData().add(new XYChart.Data(t.getBalance(), t.getTime().getSecond()));
        });

        XYChart.Series incomes = new XYChart.Series();
        incomes.setName("Tulot");

        transactions.stream().forEach(t -> {
            //all the incomes have boolean true
            if (t.getIncome()) {
                incomes.getData().add(new XYChart.Data(t.getAmount(), t.getTime().getSecond()));
            }
        });

        XYChart.Series expenses = new XYChart.Series();
        incomes.setName("Menot");

        transactions.stream().forEach(t -> {
            //all the expenses have boolean false
            if (!t.getIncome()) {
                expenses.getData().add(new XYChart.Data(t.getAmount(), t.getTime().getSecond()));
            }
        });

        linechart.getData().addAll(balanceWTime, incomes, expenses);
        
        StackPane spLineChart = new StackPane();
        spLineChart.getChildren().add(linechart);

        Button button = new Button("Takaisin");
        button.setOnMouseClicked((event)->{
            getTheBalanceScene(stage, getUsername.getText());
        });
        StackPane spButton = new StackPane();
        spButton.getChildren().add(button);
        
        VBox vbox = new VBox();
        VBox.setVgrow(spLineChart, Priority.ALWAYS);//Make line chart always grow vertically
        vbox.getChildren().addAll(spLineChart, spButton);
        
        Scene scene = new Scene(vbox, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
