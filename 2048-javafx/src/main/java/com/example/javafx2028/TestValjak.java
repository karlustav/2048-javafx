package com.example.javafx2028;

import org.w3c.dom.ls.LSOutput;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.swing.*;


import java.util.Arrays;
import java.util.Scanner;


// Enam-vähem koopia Mainist, seda ei kasutata lõppversioonis
// Debuggimise eesmärgil
public class TestValjak {

    private static final BlockingQueue<Character> keyQueue = new ArrayBlockingQueue<>(1);

    public static int skoor = 0;
    static boolean abi = true;

    public static void main(String[] args) {
        String input = null;
        Valjak valjak = new Valjak();
        valjak.generateGameOverBoard();
        //Scanner scanner = new Scanner(System.in);
        startKeyListener();
        while(true) {
            System.out.println(skoor);

            valjak.printValjak();
            if (valjak.checkGameOver()) {
                System.out.println("Mäng on läbi");
                System.out.println("Lõppskoor: " + skoor);
                System.exit(0); // lõpeta programmi töö
            }

            input = null;
            try {
                input = String.valueOf(keyQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            System.out.println(input);
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
            else if (input.equals("o")) {
                System.out.println("ooooooooooooooooooooooooooooooooo");
            }
            else {
                continue;
            }

            //System.out.println(keyQueue);

            valjak.update(input);
        }
    }

    private static void startKeyListener() {
        Frame frame = new Frame();
        frame.setUndecorated(true);
        frame.setSize(1, 1);
        frame.setVisible(true);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyQueue.offer(e.getKeyChar());
                abi = false;
            }
        });
    }

}