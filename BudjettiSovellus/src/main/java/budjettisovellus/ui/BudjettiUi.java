package budjettisovellus.ui;

import budjettisovellus.dao.DatabaseDao;
import budjettisovellus.dao.UserDao;
import budjettisovellus.domain.Balance;
import budjettisovellus.domain.BudgetService;
import budjettisovellus.domain.Transaction;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * Class handles the application
 */
public class BudjettiUi extends Application {
    
    static BudgetService budgetService;
    
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
            try {
                if (login(getUsername.getText(), stage)) {
                    getTheBalanceScene(stage, getUsername.getText());
                }
            } catch (SQLException ex) {
                Logger.getLogger(BudjettiUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        createNewUser.setOnAction((event) -> {

            createNewUserScene(stage);
        });

    }
    
    /**
     * Method updates the balance for the home view
     * @param textField the textfield with the amount given
     */
    public void updateBalance(TextField textField) {
        textField.clear();
        balanceTogether.setText("Yhteensä rahaa jäljellä: "
                + balance.getBalance());
    }
    
    /**
     * Method creates the login stage
     * @return created GridPane for stage
     */
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
    
    public void createNewUserScene(Stage stage) {
        VBox pane = new VBox();
        pane.setPadding(new Insets(20, 20, 20, 20));
        
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(20, 20, 20, 20));
        TextField username = new TextField();
        Button submit = new Button("Valmis");
        hbox.getChildren().addAll(username, submit);
        
        submit.setOnAction((event) -> {
            String theUsername = username.getText().trim();
            username.clear();
            
            try {
                budgetService.createUser(theUsername);
                getTheBalanceScene(stage, theUsername);
            } catch (SQLException ex) {
                errorInLogin.setText("Käyttäjätunnus on jo olemassa");
                errorInLogin.setTextFill(Color.web("#FF6347"));
            }
            
            
        });
        
        pane.getChildren().add(new Label("Luo uusi käyttäjätunnus: "));
        pane.getChildren().add(hbox);
        
        Scene loginScene = new Scene(pane);
        stage.setScene(loginScene);
        stage.setTitle("Budjettisovellus.");
        stage.show();
        
    }
    
    /**
     * Method checks if login is possible
     * and does the action needed
     * @param username Users submitted username
     * @param stage View going on so it can be editted
     * @return 
     * @throws java.sql.SQLException 
     */
    public boolean login(String username, Stage stage) throws SQLException {
        System.out.println(budgetService.login(username));
        if (budgetService.login(username)) {
            getTheBalanceScene(stage, username);
        } else {
            errorInLogin.setText("Käyttäjätunnusta ei löytynyt");
            errorInLogin.setTextFill(Color.web("#FF6347"));

            return false;
        }

        return true;

    }

    /**
     * Method creates and shows the main view as the stage
     * where you can add income/expense
     * @param stage application's stage
     * @param username users username that login
     */
    public void getTheBalanceScene(Stage stage, String username) {
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

        Button getStaticsButton = new Button("Analyysi");
        getStaticsButton.setOnAction((event) -> {
            errorText.setText("");
            createStatics(balance.getTransactions(), stage);
        });
        
        HBox analyseAndLogOut = new HBox(20);
       
        
        Button logoutButton = new Button("Kirjaudu ulos");
        logoutButton.setOnAction((event) -> {
            errorText.setText("");
            start(stage);
        });

        insertPane1.getChildren().addAll(incomeText, incomeButton);
        insertPane2.getChildren().addAll(expenseText, expenseButton);
        analyseAndLogOut.getChildren().addAll(getStaticsButton, logoutButton);

        vbox.getChildren().addAll(new Label("Lisää tulo: "), insertPane1,
                new Label("Lisää meno: "), insertPane2, balanceTogether, analyseAndLogOut);

        pane.setCenter(vbox);

        Scene loginScene = new Scene(pane);
        stage.setScene(loginScene);
        stage.setTitle("Budjettisovellus: " + username);
        stage.show();
    }

    /**
     * creates statics from users balance
     * @param transactions all the transactions made by user
     * @param stage application's stage
     */
    public void createStatics(List<Transaction> transactions, Stage stage) {
        //creates statics as a linechart
        //needs a lot of improvment still
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Aika (päivä)");
        yAxis.setLabel("Rahamäärä (euro)");

        LineChart<Number, Number> linechart = new LineChart<>(xAxis, yAxis);
        linechart.setTitle("Lisäämäsi menot ja tulot");

        XYChart.Series balanceWTime = new XYChart.Series();
        balanceWTime.setName("Rahatilanne");

        transactions.stream().forEach(t -> {
            balanceWTime.getData().add(new XYChart.Data(t.getBalance(), t.getDate().getDayOfMonth()));
        });

        XYChart.Series incomes = new XYChart.Series();
        incomes.setName("Tulot");

        transactions.stream().forEach(t -> {
            //all the incomes have boolean true
            if (t.getIncome()) {
                incomes.getData().add(new XYChart.Data(t.getAmount(), t.getDate().getDayOfMonth()));
            }
        });

        XYChart.Series expenses = new XYChart.Series();
        incomes.setName("Menot");

        transactions.stream().forEach(t -> {
            //all the expenses have boolean false
            if (!t.getIncome()) {
                expenses.getData().add(new XYChart.Data(t.getAmount(), t.getDate().getDayOfMonth()));
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

    public static void main(String[] args) throws SQLException {
        DatabaseDao database = new DatabaseDao("jdbc:sqlite:users.db");
        database.init();
        
        UserDao userDao = new UserDao(database);
        budgetService = new BudgetService(userDao);
        
        launch(args);
    }

}
