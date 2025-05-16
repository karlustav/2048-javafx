package com.example.javafx2028;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class MainMenu extends Application {

    public static void main(String[] args) {
        
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // pealkiri
        Label h1 = new Label("2048");
        h1.setFont(Font.font("Arial", 60));
        h1.setStyle("-fx-text-fill: #776e65;");
        h1.setPadding(new Insets(40, 0, 0, 0)); // padding aint üles

        //nupud
        Button buttonMäng = new Button("Alusta");
        Button buttonTut = new Button("Kuidas mängida?");
        Button buttonSkoor = new Button("Skoorid");
        Button buttonVälju = new Button("Välju");

        buttonMäng.setOnAction(e -> {
            Mang mang = new Mang();
            mang.start(primaryStage);
        });

        // nuppude suurussed ja stiil
        String ilusNupp = "-fx-background-radius: 5;" +   // nurgad veits kumeraks
                "-fx-padding: 10 20 10 20;" + // padding
                "-fx-background-color: #d2b48c;"; // värv

        buttonMäng.setPrefSize(300, 50);
        buttonTut.setPrefSize(300, 50);
        buttonSkoor.setPrefSize(300, 50);
        buttonVälju.setPrefSize(300, 50);

        buttonMäng.setStyle(ilusNupp);
        buttonTut.setStyle(ilusNupp);
        buttonSkoor.setStyle(ilusNupp);
        buttonVälju.setStyle(ilusNupp);


        //nüüd tegeleme paigutusega
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #faf8ef;");

        VBox vbox = new VBox(20, buttonMäng, buttonTut, buttonSkoor, buttonVälju);
        vbox.setAlignment(Pos.CENTER);

        borderPane.setTop(h1);
        BorderPane.setAlignment(h1, Pos.CENTER);
        borderPane.setCenter(vbox);


        Scene stseen1 = new Scene(borderPane, 800, 800);
        primaryStage.setTitle("2048");
        primaryStage.setScene(stseen1);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(400);
        primaryStage.show();


    }
}
