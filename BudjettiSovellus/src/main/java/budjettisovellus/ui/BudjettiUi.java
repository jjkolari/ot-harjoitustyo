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
    private Label balanceTogether;
    
    @Override
    public void init() throws Exception {
        balance = new Balance();
        
        
        //seuraava steppi on lisätä mahdollisuus yksilölliseen budjettii
        //jolloin tämä kohta muuttuu / kehittyy
    }
    
    public void updateBalance(TextField textField) {
        textField.clear();
        balanceTogether.setText("Yhteensä rahaa jäljellä: " 
                + balance.getBalance());
    }

    @Override
    public void start(Stage stage) {
        //tulossa login-näkymä
        BorderPane pane = new BorderPane();
        pane.setPrefSize(400, 200);
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
        //Integer.parseInt(incomeText.getText()) tulossa 0 tilalle
        Button expenseButton = new Button("Valmis");
        expenseButton.setOnAction((event) -> {
            balance.addExpense(expenseText.getText());
            updateBalance(expenseText);
        });
        //Integer.parseInt(expenseText.getText())

        insertPane1.getChildren().addAll(incomeText, incomeButton);
        insertPane2.getChildren().addAll(expenseText, expenseButton);
        
        vbox.getChildren().addAll(new Label("Lisää tulo: "), insertPane1, 
                new Label("Lisää meno: "), insertPane2, balanceTogether);
        
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
