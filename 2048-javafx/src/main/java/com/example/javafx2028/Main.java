package com.example.javafx2028;

import org.w3c.dom.ls.LSOutput;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.swing.*;


import java.util.Arrays;
import java.util.Scanner;

// Peaklass
public class Main {

    // Aitab inputi võtta nii, et ei pea kogu aeg Enterit vajutama
    private static final BlockingQueue<Character> keyQueue = new ArrayBlockingQueue<>(1);

    // Kas laual on vähemalt 2048?
    public static boolean voit = false;

    public static int skoor = 0;
    // static boolean abi = true;

    public static void main(String[] args) throws IOException {
        String input = null;
        // Tekitab Valjak isendi
        Valjak valjak = new Valjak();

        // Tekst kasutajale mängu alguses
        System.out.println("Tere tulemast mängu 2048!");
        System.out.println("Parima kogemuse jaoks kasuta musta terminali ja Maci kasutajad jälgige, et IntelliJ poleks fullscreen");
        System.out.println("NB! ÄRGE VAJUTAGE HIIREGA KUHUGI PÄRST PROGRAMMI KÄIVITAMIST!");
        System.out.println("Liikumiseks wasd, mängu lõpetamiseks q (Väiksed tähed)");
        System.out.println("Kõik ruudud liiguvad etteantud suunas, kaks sama väärtusega ruutu kombineeruvad");
        System.out.println("Eesmärk on saada väljakule ruut väärtusega 2048");
        System.out.println("Edu!");

        // Meetod, et kontrollida, kuidas numbrite värvid välja näevad. Hetkel välja kommenteeritud
        // Valjak.printColorPreview();

        // Alustab inputi kuulamist
        startKeyListener();

        // Tekitab alguses väljakule ühe ruudu
        valjak.generateNewTile();

        // Järgmised tegevused toimuvad kuni mängu lõppemiseni
        while(true) {
            // Prindib välja skoori ja väljaku
            System.out.println(skoor);
            valjak.printValjak();

            // Kontrollib, kas mäng on läbi ja kui on, siis väljastb lõppskoori ning katkestab programmi töö.
            if (valjak.checkGameOver()) {
                System.out.println("Mäng on läbi");
                System.out.println("Lõppskoor: " + skoor);
                System.exit(0); // lõpeta programmi töö
            }

            // Lähtestab inputi
            input = null;

            // Jääb siia kinni, kuni kasutaja sisestab klaviatuuril midagi
            try {
                input = String.valueOf(keyQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            // Konverteerib inputi nii, et Valjak funktsioonid saaks aru.
            // Tegelikult oleks võinud igale poole lihtsalt wasd panna
            if (input.equals("q")) {
                break;
            }
            else if (input.equals("w")) {
                input = "yles";
            }
            else if (input.equals("a")) {
                input = "vasakule";
            }
            else if (input.equals("s")) {
                input = "alla";
            }
            else if (input.equals("d")) {
                input = "paremale";
            }

            // Debuggimiseks
            //else if (input.equals("o")) {
            //    System.out.println("ooooooooooooooooooooooooooooooooo");
            //}

            else {
                continue;
            }

            //System.out.println(keyQueue);

            // Uuendab väljakut vastavalt inputile.
            valjak.update(input);
        }
    }

    // Võimaldab inputi paremini võtta.
    private static void startKeyListener() {
        // Tekitab Frame, mis kuulab inputi.
        // Javas muidu otse terminalis vist ei saa.
        Frame frame = new Frame();
        frame.setUndecorated(true);
        frame.setSize(1, 1);
        frame.setVisible(true);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyQueue.offer(e.getKeyChar());
                // abi = false;
            }
        });
    }

}