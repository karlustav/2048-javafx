package com.example.javafx2028;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;

public class Mang extends Application {

    private Valjak valjak;
    private GridPane manguGrid;
    private Label skoorVaartus;
    private Label parimVaartus;
    private Label teade;
    private StackPane juurStackPane;
    private BorderPane peaosa;
    private StackPane mangLabiPopup;
    private Label mangLabiSkoor;
    private StackPane voitPopup;
    private Button mangiEdasi;
    public static int skoor = 0;
    public static int parim = 0;

    public static boolean voit = false;
    private boolean voitTeade = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage peaLava) {
        // tekitab Valjak isendi
        valjak = new Valjak();
        peaLava.setTitle("2048");

        // praegune ja parim skoor
        HBox skoorParim = looSkoorVaade();
        skoorParim.setAlignment(Pos.CENTER);

        // teade mängu lõpu jaoks
        teade = new Label();
        teade.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        teade.setVisible(false);
        teade.setManaged(false);

        // teade ja skoorid
        VBox vbox = new VBox();
        vbox.getChildren().addAll(skoorParim, teade);

        // mängu grid
        manguGrid = looManguVaade();

        // ruudud
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                StackPane ruut = new StackPane();
                ruut.setPrefSize(100, 100);
                ruut.setStyle("-fx-background-color: #cdc1b4; -fx-background-radius: 4;");
                manguGrid.add(ruut, j, i);
            }
        }

        // kõik ühe grupi sisse
        peaosa = new BorderPane();
        peaosa.setTop(vbox);
        peaosa.setCenter(manguGrid);

        // mängu kaotamisel popup
        mangLabiPopup = new StackPane();
        mangLabiPopup.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6);");
        mangLabiPopup.setAlignment(Pos.CENTER);
        mangLabiPopup.setVisible(false); // algselt peidetud

        VBox mangLabiKast = new VBox(20); // Spacing for elements inside the popup
        mangLabiKast.setAlignment(Pos.CENTER);
        mangLabiKast.setStyle("-fx-background-color: #f0f0f0; " + // Light grey background for the box
                "-fx-padding: 40px; " +
                "-fx-border-color: #555555; " +       // Darker border
                "-fx-border-width: 2px; " +
                "-fx-background-radius: 10px; " +
                "-fx-border-radius: 8px;");
        mangLabiKast.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE); // mahuta sisu

        Label mangLabiKiri = new Label("Mäng Läbi!");
        mangLabiKiri.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        mangLabiKiri.setTextFill(Color.web("#333333"));

        mangLabiSkoor = new Label("Skoor: 0"); // Will be updated
        mangLabiSkoor.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        mangLabiSkoor.setTextFill(Color.web("#444444"));

        Label restart = new Label("Vajuta R, et uuesti alustada");
        restart.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        restart.setTextFill(Color.web("#555555"));

        mangLabiKast.getChildren().addAll(mangLabiKiri, mangLabiSkoor, restart);
        mangLabiPopup.getChildren().add(mangLabiKast);

        // võitmise popup
        voitPopup = new StackPane();
        voitPopup.setStyle("-fx-background-color: rgba(0, 100, 0, 0.4);");
        voitPopup.setAlignment(Pos.CENTER);
        voitPopup.setVisible(false); // algselt peidetud

        VBox voitKast = new VBox(20);
        voitKast.setAlignment(Pos.CENTER);
        voitKast.setStyle("-fx-background-color: #e8f5e9; " + // Light green background for the box
                "-fx-padding: 40px; " +
                "-fx-border-color: #4caf50; " +    // Green border
                "-fx-border-width: 2px; " +
                "-fx-background-radius: 10px; " +
                "-fx-border-radius: 8px;");
        voitKast.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE); // mahuta sisu

        Label voitKiri = new Label("VÕITSID!");
        voitKiri.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        voitKiri.setTextFill(Color.web("#1b5e20"));

        mangiEdasi = new Button("Jätka mängu");
        mangiEdasi.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        mangiEdasi.setPadding(new Insets(10, 20, 10 ,20));
        mangiEdasi.setOnAction(e -> {
            voitPopup.setVisible(false); // peida võidu popup
            peaosa.setDisable(false);     // peaosa interaktiivsus
            manguGrid.requestFocus();
        });

        voitKast.getChildren().addAll(voitKiri, mangiEdasi);
        voitPopup.getChildren().add(voitKast);

        // mängu peaosa hoidmine
        juurStackPane = new StackPane();
        juurStackPane.getChildren().addAll(peaosa, voitPopup, mangLabiPopup);

        Scene stseen = new Scene(juurStackPane, 4 * (100 + 10) + 20, 4 * (100 + 10) + 100);

        stseen.setOnKeyPressed(event -> {
            if (mangLabiPopup.isVisible() || voitPopup.isVisible()) { // kui mõni popup visible
                if (event.getCode() == KeyCode.R) { // R input restardi jaoks
                    startGame();
                }
                return; // eira inputti
            }
            if (valjak.checkGameOver() && event.getCode() != KeyCode.R) { // kui midagi muud peale R siis ära võta inputti
                return; // eira inputti
            }

            // inputid
            String suund = null;
            switch (event.getCode()) {
                case W: case UP:
                    suund = "yles";
                    break;
                case A: case LEFT:
                    suund = "vasakule";
                    break;
                case S: case DOWN:
                    suund = "alla";
                    break;
                case D: case RIGHT:
                    suund = "paremale";
                    break;
                case Q: // lahku mängust
                    System.exit(0);
                    break;
                case R: // restart
                    startGame();
                    return;
            }

            if (suund != null) {
                String vanaGrid = Arrays.deepToString(valjak.getValjak());
                valjak.update(suund);
                // uuenda kui midagi muutus (vana ja uue valjaku vahel)
                if (!vanaGrid.equals(Arrays.deepToString(valjak.getValjak()))) {
                    uuendaManguGrid();
                }
            }
        });

        peaLava.setScene(stseen);
        peaLava.show();
        startGame(); // alusta mäng
    }

    // alusta mäng
    public void startGame() {
        skoor = 0;
        voit = false;
        voitTeade = false;
        valjak = new Valjak(); // valjaku isend
        valjak.generateNewTile(); // alusta ühe ruuduga
        voitPopup.setVisible(false); // popup peidetud
        mangLabiPopup.setVisible(false); // popup peidetud
        peaosa.setDisable(false); // interaktiivsus
        teade.setText(""); // teade tühjaks mängu alguses
        teade.setVisible(false);
        teade.setManaged(false);
        uuendaManguGrid(); // loo algplats
        manguGrid.requestFocus(); // fookus
    }

    // uuenda mangu platsi ja skoori iga käigu korral
    private void uuendaManguGrid() {
        skoorVaartus.setText("" + skoor);
        if (skoor > parim) { // muuda parima skoori tulemust kui praegune on suurem
            parim = skoor;
            parimVaartus.setText("" + parim);
        } else {
            parimVaartus.setText("" + parim);
        }

        manguGrid.getChildren().removeIf(node -> !(node.getStyle().contains("#cdc1b4"))); // eemalda vanad ruudud

        Ruut[][] praeguneGrid = valjak.getValjak();
        for (int i = 0; i < 4; i++) { // row
            for (int j = 0; j < 4; j++) { // col
                // Re-add background cell for consistency if they were removed (they shouldn't be with the filter above)
                int finalI = i;
                int finalJ = j;
                if (manguGrid.getChildren().filtered(node -> GridPane.getRowIndex(node) == finalI && GridPane.getColumnIndex(node) == finalJ && node.getStyle().contains("#cdc1b4")).isEmpty()) {
                    StackPane ruut = new StackPane();
                    ruut.setPrefSize(100, 100);
                    ruut.setStyle("-fx-background-color: #cdc1b4; -fx-background-radius: 4;");
                    manguGrid.add(ruut, j, i);
                }

                if (praeguneGrid[i][j] != null) {
                    Ruut ruut = praeguneGrid[i][j];
                    StackPane uusRuut = looRuut(ruut.getVaartus());
                    manguGrid.add(uusRuut, j, i); // uue ruudu lisamine
                }
            }
        }

        // kontrolli mängu olukorda (kaotus/võit)
        if (valjak.checkGameOver()) {
            mangLabiSkoor.setText("Skoor: " + skoor);
            mangLabiPopup.setVisible(true); // näita popupi
            voitPopup.setVisible(false);
            peaosa.setDisable(true); // interaktiivsuse peatamine mänguga
            teade.setVisible(false);
            teade.setManaged(false);
        } else if (voit && !voitTeade) { // esimesel 2048 saavutamisel näita võidu popupi (järgmised korrad kui oled üle 2048 ei näita uuesti)
            voitPopup.setVisible(true);
            voitTeade = true;
            mangLabiPopup.setVisible(false);
            teade.setVisible(false);
            teade.setManaged(false);
        }
        manguGrid.requestFocus(); // fookus
    }

    // ruudu loomine
    private StackPane looRuut(int value) {
        StackPane ruut = new StackPane();
        ruut.setPrefSize(100, 100);

        Text tekst = new Text(String.valueOf(value));
        tekst.setFont(Font.font("Arial", FontWeight.BOLD, value < 100 ? 36 : (value < 1000 ? 32 : 24)));
        tekst.setFill(getTextColor(value));

        ruut.getChildren().addAll(tekst);
        ruut.setStyle(ruuduStiil(value));
        ruut.setAlignment(Pos.CENTER);
        return ruut;
    }

    // Värvid
    private String ruuduStiil(int value) {
        String stiil = "-fx-background-radius: 4; ";
        Color taust = switch (value) {
            case 2 -> Color.web("#eee4da");
            case 4 -> Color.web("#ede0c8");
            case 8 -> Color.web("#f2b179");
            case 16 -> Color.web("#f59563");
            case 32 -> Color.web("#f67c5f");
            case 64 -> Color.web("#f65e3b");
            case 128 -> Color.web("#edcf72");
            case 256 -> Color.web("#edcc61");
            case 512 -> Color.web("#edc850");
            case 1024 -> Color.web("#edc53f");
            case 2048 -> Color.web("#edc22e");
            default -> Color.web("#cdc1b4");
        };
        return stiil + "-fx-background-color: " + toHexString(taust) + ";";
    }

    // värvi kodeerimine
    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    // teksti värv
    private Color getTextColor(int value) {
        return (value < 8 || value == 0) ? Color.web("#776e65") : Color.web("#f9f6f2");
    }

    // ruudustik
    public GridPane looManguVaade() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.setStyle("-fx-background-color: #bbada0; -fx-background-radius: 5;");
        return gridPane;
    }


    // loob ülemise rea praeguse ja parima skoori jaoks
    public HBox looSkoorVaade() {
        // praegune skoor
        Label skoorKiri = new Label("SKOOR");
        skoorKiri.setFont(Font.font("Arial", 14));
        skoorVaartus = new Label("0");
        skoorVaartus.setFont(Font.font("Arial Bold", 24));
        VBox skoorKast = new VBox(skoorKiri, skoorVaartus);
        skoorKast.setAlignment(Pos.CENTER);

        // parim skoor
        Label parimLabel = new Label("PARIM");
        parimLabel.setFont(Font.font("Arial", 14));
        parimVaartus = new Label("0");
        parimVaartus.setFont(Font.font("Arial Bold", 24));
        VBox parimKast = new VBox(parimLabel, parimVaartus);
        parimKast.setAlignment(Pos.CENTER);


        // mõlema skooride kast
        HBox kast = new HBox(20, skoorKast, parimKast);
        kast.setAlignment(Pos.CENTER);
        kast.setPadding(new Insets(15));
        kast.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 10;");
        skoorKast.setStyle("-fx-background-color: #e0e0e0; -fx-background-radius: 8; -fx-padding: 10;");
        parimKast.setStyle("-fx-background-color: #e0e0e0; -fx-background-radius: 8; -fx-padding: 10;");

        return kast;
    }
}
