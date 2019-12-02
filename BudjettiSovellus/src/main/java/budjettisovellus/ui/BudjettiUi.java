package budjettisovellus.ui;

import budjettisovellus.domain.Balance;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BudjettiUi extends Application {
    
    private Balance balance;
    
    @Override
    public void init() throws Exception {
        balance = new Balance();
        //seuraava steppi on lisätä mahdollisuus yksilölliseen budjettii
        //jolloin tämä kohta muuttuu / kehittyy
    }

    @Override
    public void start(Stage stage) {
        //tulossa login-näkymä
        BorderPane pane = new BorderPane();
        pane.setPrefSize(400, 200);
        VBox vbox = new VBox(10);
        HBox insertPane1 = new HBox(20);
        HBox insertPane2 = new HBox(20);

        TextField incomeText = new TextField();
        TextField expenseText = new TextField();
        Button incomeButton = new Button("Valmis");
        incomeButton.setOnAction((event) -> {
            int money = 20;
            balance.addIncome(money);
            incomeText.clear();
        });
        //Integer.parseInt(incomeText.getText()) tulossa 0 tilalle
        Button expenseButton = new Button("Valmis");
        expenseButton.setOnAction((event) -> {
            balance.addExpense(10);
        });
        //Integer.parseInt(expenseText.getText())

        insertPane1.getChildren().addAll(incomeText, incomeButton);
        insertPane2.getChildren().addAll(expenseText, expenseButton);

        vbox.getChildren().add(new Label("Lisää tulo: "));
        vbox.getChildren().add(insertPane1);
        vbox.getChildren().add(new Label("Lisää meno: "));
        vbox.getChildren().add(insertPane2);
        vbox.getChildren().add(new Label("Yhteensä rahaa jäljellä: " 
                + balance.getBalance()));
        //Tähän balance.getBalance(); miksi ei toimi
        
        pane.setCenter(vbox);
        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.setTitle("Budjetti-sovellus.");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
