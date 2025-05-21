package com.example.javafx2028;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

// Mängu väljaku klass
public class Valjak {

    // 4x4 ruudustik, mis hoiab Ruut isendeid (või null)
    private Ruut[][] valjak;

    // ArrayList, mis hoiab kõiki ruutusid. Lihtsustab kõikide ruutude läbi vaatamist
    private ArrayList<Ruut> ruudud;

    // Konstruktor, lähtestab valjak ja ruudud
    public Valjak() {
        valjak = new Ruut[4][4];
        ruudud = new ArrayList<Ruut>();
    }

    // Võtab parameetriks inputi ja liigutab kõiki ruute vastavalt sellele
    public void update(String suund) {

        // Jätab meelde vana väljaku deepToStringi, et seda hiljem uuega võrrelda.
        // Kui on täpselt sama, siis on sisestatud vigane input
        String vana = Arrays.deepToString(valjak);

        // Vaatab kõik suunad eraldi ja väljaku positsiooinid läbi ja liigutab
        // ruute ühe võrra kolm korda, et need liiguksid äärest äärde
        // Ei saa vaadata läbi kõiki ruute, sest liikumisel tekitatakse uus ruut.
        if (suund.equals("yles")) {
            for (int i = 0; i < 3; i++) {
                for (int y = 0; y < 4; y++) {
                    for (int x = 0; x < 4; x++) {
                        Ruut ruut = valjak[y][x];
                        if (ruut != null) {
                            ruut.liigu(suund);
                        }
                    }
                }
            }
        }
        else if (suund.equals("alla")) {
            for (int i = 0; i < 3; i++) {
                for (int y = 3; y >= 0; y--) {
                    for (int x = 0; x < 4; x++) {
                        Ruut ruut = valjak[y][x];
                        if (ruut != null) {
                            ruut.liigu(suund);
                        }
                    }
                }
            }
        }
        else if (suund.equals("vasakule")) {
            for (int i = 0; i < 3; i++) {
                for (int x = 0; x < 4; x++) {
                    for (int y = 0; y < 4; y++) {
                        Ruut ruut = valjak[y][x];
                        if (ruut != null) {
                            ruut.liigu(suund);
                        }
                    }
                }
            }
        }
        else if (suund.equals("paremale")) {
            for (int i = 0; i < 3; i++) {
                for (int x = 3; x >= 0; x--) {
                    for (int y = 0; y < 4; y++) {
                        Ruut ruut = valjak[y][x];
                        if (ruut != null) {
                            ruut.liigu(suund);
                        }
                    }
                }
            }
        }

        // Kui valjak muutus parast inputi, siis tekitab uue ruudu.
        if (!vana.equals(Arrays.deepToString(valjak))) {
            generateNewTile();
        }

    }

    // Tekitab väljaku suvalisse vabasse kohta uue ruudu
    public void generateNewTile() {


        // 90% tõenäosusega tekib 2, 10% tekib 4
        int newTileValue = 2;
        if (Math.random() <= 0.1) {
            newTileValue = 4;
        }

        // Jätab meelde, mis positsioonid on vabad
        ArrayList<Integer[]> nullPositions = new ArrayList<Integer[]>();
        for (int i = 0; i < valjak.length; i++) {
            for (int j = 0; j < valjak[i].length; j++) {
                if (valjak[i][j] == null) {
                    nullPositions.add(new Integer[]{i, j});
                }
            }
        }


        // Tekitab uue ruudu suvalisse vabasse kohta
        int mitmes = (int) (Math.random() * nullPositions.size());
        Ruut uusRuut = new Ruut(newTileValue, nullPositions.get(mitmes)[0], nullPositions.get(mitmes)[1], this);

        // Lisab selle ruudu väljakule
        addRuut(uusRuut);
    }

    // Abifunktsioon, et kontrollida, kas mäng on läbi. Sama, mis update, aga võtab parameetriks ka väljaku.
    // Seda on vaja, sest tahame väljaku koopiat liigutada ilma põhiväljakut muutmata
    public void updateTest(Valjak testValjak, String suund) {
        if (suund.equals("yles")) {
            for (int i = 0; i < 3; i++) {
                for (int y = 0; y < 4; y++) {
                    for (int x = 0; x < 4; x++) {
                        Ruut ruut = testValjak.getValjak()[y][x];
                        if (ruut != null) {
                            ruut.liiguTest(suund);
                        }
                    }
                }
            }
        }
        else if (suund.equals("alla")) {
            for (int i = 0; i < 3; i++) {
                for (int y = 3; y >= 0; y--) {
                    for (int x = 0; x < 4; x++) {
                        Ruut ruut = testValjak.getValjak()[y][x];
                        if (ruut != null) {
                            ruut.liiguTest(suund);
                        }
                    }
                }
            }
        }
        else if (suund.equals("vasakule")) {
            for (int i = 0; i < 3; i++) {
                for (int x = 0; x < 4; x++) {
                    for (int y = 0; y < 4; y++) {
                        Ruut ruut = testValjak.getValjak()[y][x];
                        if (ruut != null) {
                            ruut.liiguTest(suund);
                        }
                    }
                }
            }
        }
        else if (suund.equals("paremale")) {
            for (int i = 0; i < 3; i++) {
                for (int x = 3; x >= 0; x--) {
                    for (int y = 0; y < 4; y++) {
                        Ruut ruut = testValjak.getValjak()[y][x];
                        if (ruut != null) {
                            ruut.liiguTest(suund);
                        }
                    }
                }
            }
        }

        // Siin ei pea uut ruutu lisama
    }



    // Kontrollib, kas mäng on läbi
    // Vaatab, kas iga võimaliku inputi korral jääb väljak samaks.
    // Kui valiidne käik on olemas, siis kindlasti väljak muutub.
    public boolean checkGameOver() {

        // Piisab, kui võrrelda uue ja vana väjaku deepToStringe
        String vana = Arrays.deepToString(valjak);

        // Vaatab läbi kõik suunad
        String[] suunad = {"yles", "alla", "paremale", "vasakule"};
        for (String suund : suunad) {

            // Tekitab uue väljaku, mis on koopia praegusest väljakust
            Valjak testValjak = new Valjak();

            // .clone() ei tööta, sest ta on kahemõõtmeline objektide massiiv ning ruudul on väli, mis viitab väljakule
            // Seega tuleb nullist teha uus väljak. Vaatab järjest praeguse väljaku läbi
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    // Iga ruudu korral lisab koopiasse sama väärtuse ja asukohaga ruudu
                    if (this.valjak[i][j] != null) {
                        Ruut vanaRuut = this.valjak[i][j];
                        Ruut copy = new Ruut(vanaRuut.getVaartus(), i, j, testValjak);
                        testValjak.addRuut(copy);
                    }
                }
            }

            // Liigutab koopiat antud suunas
            updateTest(testValjak, suund);

            // Kui midagi muutus, siis mäng pole läbi
            if (!Arrays.deepToString(testValjak.valjak).equals(vana)) {
                return false;
            }
        }
        // Kui ei muutunud midagi ühegi suuna korral, siis on mäng läbi
        try {
            Mang.lisaSkoor("skoorid.txt", "Stefan", Mang.skoor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    // See on AI-generated funktsioon
    // Tekitab väljaku, kus ei ole võimalik ühtegi liigutust teha.
    // See oli debuggimiseks, lõplikus versioonis ei kasutata
    public void generateGameOverBoard() {
        // Clear current board
        valjak = new Ruut[4][4];
        ruudud.clear();

        // Pattern of alternating values with no possible merges
        int[][] values = {
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2}
        };

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                Ruut ruut = new Ruut(values[y][x], y, x, this);
                addRuut(ruut);
            }
        }
    }

    // Lisab ruudu väljakusse
    public void addRuut(Ruut ruut) {
        valjak[ruut.getY()][ruut.getX()] = ruut;
        ruudud.add(ruut);
    }



    // Värvide osa on AI generated.
    // Muudab ruudu värvi vastavalt tema väärtusele
    String value;
    private static final String RESET = "\u001B[0m";

    // Javas on sisseehitatud koodid värvidele
    public static String getColor(int value) {
        return switch (value) {
            case 2 -> "\u001B[38;5;34m";     // Green
            case 4 -> "\u001B[38;5;120m";    // Light Green
            case 8 -> "\u001B[38;5;43m";     // Pale Lime Green
            case 16 -> "\u001B[38;5;51m";    // Cyan
            case 32 -> "\u001B[38;5;27m";    // Blue
            case 64 -> "\u001B[38;5;63m";    // Indigo
            case 128 -> "\u001B[38;5;165m";  // Magenta-Violet
            case 256 -> "\u001B[38;5;196m";  // Red
            case 512 -> "\u001B[38;5;208m";  // Orange
            case 1024 -> "\u001B[38;5;220m"; // Dark Yellow
            case 2048 -> "\u001B[38;5;226m"; // Yellow
            default -> RESET;          // Reset
        };
    }

    // See ka AI generated
    // Prindib kõik arvud, mis mängus esinevad koos värvidega, et saaks neid paremini korrigeerida.
    // Lõplikus versioonis seda välja ei kutsuta
    public static void printColorPreview() {
        int[] tiles = {2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};

        for (int tile : tiles) {
            String color = getColor(tile);
            System.out.printf("%s%5s\u001B[0m ", color, tile); // 5-width padding for alignment
        }
        System.out.println(); // Newline at end
    }


    // Getterid/Setterid
    public Ruut[][] getValjak() {
        return valjak;
    }
    public ArrayList<Ruut> getRuudud() {
        return ruudud;
    }
    public void setValjak(Ruut[][] valjak) {
        this.valjak = valjak;
    }

    // Prindib väljaku ilusti
    public void printValjak() {

        // Ülemine serv
        System.out.println("+" + ("-".repeat(6) + "+").repeat(4));

        // Nüüd vaatab väljaku kõik ruudud läbi
        for (int i = 0; i < valjak.length; i++) {
            for (int j = 0; j < valjak[i].length; j++) {
                Ruut muudetav = valjak[i][j];
                String color;

                // Kui pole null, siis muudab värvi vastavalt väärtusele
                if (muudetav != null) {
                    color = getColor(muudetav.getVaartus());
                }
                else {
                    color = RESET;
                }

                // Prindib välja ruudu piiritleja ja väärtuse nii, et väärtus võtab igal juhul 4 tähe võrra ruumi
                // Selleks, et väljak jääks ühtlaseks ükskõik millise väärtuse korral
                System.out.printf("| %s%4s%s ", color, (muudetav == null ? " " : muudetav.getVaartus()), RESET);

                // Kui on viimane väli siis prindib lõppu ka ruudu piiritleja
                if (j == 3) {
                    System.out.print("|");
                }
            }


            System.out.println();

            // Ridade vahele ja lõppu ka piiritleja
            System.out.println("+" + ("-".repeat(6) + "+").repeat(4));
        }
    }

}

