package com.example.javafx2028;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenu extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // pealkiri
        Label h1 = new Label("2048");
        h1.setFont(Font.font("Arial", 60));
        h1.setStyle("-fx-text-fill: #776e65; -fx-padding: 40 0 0 0;");

        //nupud
        Button buttonMäng = new Button("Alusta");
        Button buttonTut = new Button("Kuidas mängida?");
        Button buttonSkoor = new Button("Skoorid");
        Button buttonVälju = new Button("Välju");
        Button buttonTagasitut = new Button("Tagasi");
        Button buttonTagasiskoor = new Button("Tagasi");

        // nuppude suurussed ja stiil
        String menüüNuppStyle = "-fx-background-radius: 5;" +   // nurgad veits kumeraks
                                "-fx-background-color: #d2b48c;"; // värv

        String tagasiNuppStyle = "-fx-background-radius: 5;" +
                                "-fx-background-color: #c0c0c0;";

        buttonMäng.setPrefSize(300, 50);
        buttonTut.setPrefSize(300, 50);
        buttonSkoor.setPrefSize(300, 50);
        buttonVälju.setPrefSize(300, 50);
        buttonTagasitut.setPrefSize(100, 25);
        buttonTagasiskoor.setPrefSize(100, 25);

        buttonMäng.setStyle(menüüNuppStyle);
        buttonTut.setStyle(menüüNuppStyle);
        buttonSkoor.setStyle(menüüNuppStyle);
        buttonVälju.setStyle(menüüNuppStyle);
        buttonTagasitut.setStyle(tagasiNuppStyle);
        buttonTagasiskoor.setStyle(tagasiNuppStyle);

        //tutorial paneel
        BorderPane bptut = new BorderPane();
        bptut.setStyle("-fx-background-color: #eee;");

        Label tutorialText = new Label("bomboclat");
        tutorialText.setAlignment((Pos.CENTER));
        bptut.setCenter(tutorialText);

        BorderPane.setAlignment(buttonTagasitut, Pos.TOP_RIGHT);
        bptut.setTop(buttonTagasitut);
        BorderPane.setMargin(buttonTagasitut, new Insets(25, 25, 0, 0)); // nurgast veits eemale

        bptut.setVisible(false);

        //skooride paneel
        BorderPane bpskoor = new BorderPane();
        bpskoor.setStyle("-fx-background-color: #eee;");

        BorderPane.setAlignment(buttonTagasiskoor, Pos.TOP_RIGHT);
        bpskoor.setTop(buttonTagasiskoor);
        BorderPane.setMargin(buttonTagasiskoor, new Insets(25, 25, 0, 0)); // nurgast eemale

        bpskoor.setVisible(false);

        //nuppude funktsionaalsus
        buttonMäng.setOnAction(e -> { // alusta mängu
            Mang mang = new Mang();
            try {
                mang.start(primaryStage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        buttonTut.setOnAction(e -> bptut.setVisible(true)); // näita tutoriali

        buttonSkoor.setOnAction(e -> bpskoor.setVisible(true)); //näita skoore

        buttonVälju.setOnAction(e -> Platform.exit()); // exit game

        buttonTagasitut.setOnAction(e -> bptut.setVisible(false)); //tagasi tutorial

        buttonTagasiskoor.setOnAction(e -> bpskoor.setVisible(false)); // tagasi skoorid

        //nüüd kõige selle paigutus
        BorderPane bpmain = new BorderPane(); // main menu, siia salvestan nupud ja pealkirja
        bpmain.setStyle("-fx-background-color: #faf8ef;");

        VBox vbnupp = new VBox(20, buttonMäng, buttonTut, buttonSkoor, buttonVälju); // nupdu ühte punti
        vbnupp.setAlignment(Pos.CENTER);
        bpmain.setCenter(vbnupp); // nupud main menu keskele
        BorderPane.setAlignment(h1, Pos.CENTER);
        bpmain.setTop(h1); // pealkiri main menu ekraani üles

        StackPane juur = new StackPane(bpmain, bptut, bpskoor); // juur = main menu, tutorial paneel, skooride paneel

        Scene stseen1 = new Scene(juur, 800, 800);
        primaryStage.setTitle("2048");
        primaryStage.setScene(stseen1);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(400);
        primaryStage.show();


    }
}
