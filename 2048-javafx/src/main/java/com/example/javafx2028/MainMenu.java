package com.example.javafx2028;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class MainMenu extends Application {

    public static void main(String[] args) {
        
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Group juur = new Group();

        Canvas lõuend = new Canvas(1000, 1000);

        GraphicsContext gc = lõuend.getGraphicsContext2D();
        gc.setFill(Color.BEIGE);

        Button buttonMäng = new Button("Click Me");
        Button buttonSkoor = new Button("Click Me");
        Button buttonVälju = new Button("Click Me");

        juur.getChildren().addAll(lõuend);

        Scene stseen1 = new Scene(juur);
        primaryStage.setTitle("2048");


    }
}
