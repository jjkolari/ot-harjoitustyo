package budjettisovellus.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BudjettiUi extends Application {

    @Override
    public void start(Stage stage) {
        int balance = 0;
        VBox pane = new VBox(10);
        HBox insertPane1 = new HBox(20);
        HBox insertPane2 = new HBox(20);

        TextField incomeText = new TextField();
        TextField paymentText = new TextField();
        Button incomeButton = new Button("Valmis");
        incomeButton.setOnAction((event) -> {
            
        });
        Button paymentButton = new Button("Valmis");
        incomeButton.setOnAction((event) -> {
            
        });

        insertPane1.getChildren().addAll(incomeText, incomeButton);
        insertPane2.getChildren().addAll(paymentText, paymentButton);

        pane.getChildren().add(new Label("Lisää tulo: "));
        pane.getChildren().add(insertPane1);
        pane.getChildren().add(new Label("Lisää meno: "));
        pane.getChildren().add(insertPane2);
        pane.getChildren().add(new Label("Yhteensä rahaa jäljellä: " + balance));

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.setTitle("Budjetti-sovellus.");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
