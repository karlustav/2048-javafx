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
        Button buttonTagasi = new Button("Tagasi"); // välju nupuga tutorialist ja skooride paneelist

        // nuppude suurussed ja stiil
        String ilusNupp = "-fx-background-radius: 5;" +   // nurgad veits kumeraks
                "-fx-padding: 10 20 10 20;" + // padding
                "-fx-background-color: #d2b48c;"; // värv

        buttonMäng.setPrefSize(300, 50);
        buttonTut.setPrefSize(300, 50);
        buttonSkoor.setPrefSize(300, 50);
        buttonVälju.setPrefSize(300, 50);
        buttonTagasi.setPrefSize(100, 25);

        buttonMäng.setStyle(ilusNupp);
        buttonTut.setStyle(ilusNupp);
        buttonSkoor.setStyle(ilusNupp);
        buttonVälju.setStyle(ilusNupp);
        buttonTagasi.setStyle(ilusNupp);

        //tutorial paneel
        BorderPane bptut = new BorderPane();
        bptut.setStyle("-fx-background-color: #eee;");
        bptut.setVisible(false);

        Label tutorialText = new Label("bomboclat");
        tutorialText.setAlignment((Pos.CENTER));
        bptut.setCenter(tutorialText);

        BorderPane.setAlignment(buttonTagasi, Pos.TOP_LEFT);
        bptut.setTop(buttonTagasi);

        bptut.setVisible(false);
        
        //skooride paneel
        BorderPane bpskoor = new BorderPane();
        bpskoor.setStyle("-fx-background-color: #eee;");

        BorderPane.setAlignment(buttonTagasi, Pos.TOP_LEFT);
        bpskoor.setTop(buttonTagasi);

        bpskoor.setVisible(false);

        //nuppude funktsionaalsus
        buttonMäng.setOnAction(e -> { // alusta mängu
            Mang mang = new Mang();
            mang.start(primaryStage);
        });

        buttonTut.setOnAction(e -> bptut.setVisible(true)); // näita tutoriali

        buttonSkoor.setOnAction(e -> bpskoor.setVisible(true)); //näita skoore

        buttonVälju.setOnAction(e -> Platform.exit()); // exit game

        buttonTagasi.setOnAction(e -> { // väju tutoorialist/skooride paneelist
            bptut.setVisible(false);
            bpskoor.setVisible(false);
        });

        //nüüd tegeleme paigutusega
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #faf8ef;");

        VBox vbnupp = new VBox(20, buttonMäng, buttonTut, buttonSkoor, buttonVälju); // kõik peale "tagasi" nupu ekraani keskele
        vbnupp.setAlignment(Pos.CENTER);
        borderPane.setCenter(vbnupp);

        BorderPane.setAlignment(h1, Pos.CENTER);
        borderPane.setTop(h1); // pealkiri ekraani üles

        StackPane juur = new StackPane(borderPane, bptut, bpskoor); // main menu on kolm paneeli, kaks on peidetud(tutorial ja skoor)

        Scene stseen1 = new Scene(juur, 800, 800);
        primaryStage.setTitle("2048");
        primaryStage.setScene(stseen1);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(400);
        primaryStage.show();


    }
}
