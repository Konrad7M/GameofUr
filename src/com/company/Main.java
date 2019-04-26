package com.company;

import com.sun.istack.internal.localization.NullLocalizable;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

/**
 * Klasa główna
 */


public class Main {

    /**
     * metoda głowna tworzy i łączy ze sobą elementy pozostałych klas inicjuje
     * graczy ,kość i planszę
     * @param args
     */


    public static void main(String[] args) {


        Board game = new Board("Sumerian");

        Player playerBlue = new Player("Niebieski",4,game.startBlu,game.finBlu,7);
        Player playerRed = new Player("czerwony",3,game.startRed,game.finRed,7);
        System.out.println("zaladowano gre");

        Presser turnbutonBlu = new Presser(400,75,0,0,null, playerBlue,playerRed);


        game.add(turnbutonBlu);


        Field x = new Field(0,8*75,0,0,null);
        x.setVisible(false);
        game.add(x);

        game.pack();
        game.setLocation(100,0);
        game.setSize(700,750);




    }
}
