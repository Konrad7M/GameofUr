package com.company;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * Klasa publiczna krosroads rozszerza klasę Field
 *
 * reprezentuję pole na którym zmienia następuje rozgałeznienie ścieżki ruchu na 2 ścieżki 1 dla każdego gracza
 */
public class Crossroad extends Field {

    Field secondnext;

    /**
     * Konstruktor Crossroads od konstruktora Field różni się drugim polem Field
     *
     * @param x współrzędna  x
     * @param y współrzędna y
     * @param t typ pola
     * @param i index
     * @param nxt następne pole gracza 1
     * @param seconnext następne pole gracza 2
     */


    public Crossroad(int x, int y, int t, int i, Field nxt, Field seconnext) {
        super(x, y, t, i, nxt);
        secondnext = seconnext;
    }

    /**
     * metoda ładuje pliki wyświetlanych obrazów do obiektów
     */

    @Override
    public void loadimg() {

        try {
            jpg = empty = ImageIO.read(new File("resrc\\crossroad\\ches.jpg"));
            red = ImageIO.read(new File("resrc\\crossroad\\chesRed.jpg"));
            blu = ImageIO.read(new File("resrc\\crossroad\\chesBlu.jpg"));
        } catch (IOException e1) {
            System.out.println("Nczy");
        }
    }

    /**
     *
     * @param roll
     * natomiast
     * @param p przechowuje obiekt gracza by móc udentyfikować pola niedostępne i okazje do
     * dzięki rekurencyjnosci odpowiedź mogą dać obiekty klas dziedziczących z Field
     * Jeśli @param roll jest różny od zera to metoda przekazuje zapytanie do kolejnego obiektu,
     * jesli nie sprawdza dostępność pola. Pole dostępne to takie które jest puste bądź zajęte przez przeciwnika
     *
     * klasa Crossroads wyznacza inne pola dla różnych graczy
     *
     * @return
     */

    @Override
    public Field scout(int roll, Player p) {

        Field rtnk = null;

        if (roll <= 0) {

            if (occupied == true) {
                if (one.owner == p) {
                    System.out.println("nasz pionek");
                    return null;
                } else {
                    System.out.println("atak");
                    return this;
                }
            } else
                rtnk = this;

        } else {

            if (p.number == 3)
                rtnk = this.next.scout(roll - 1, p);
            else
                rtnk = this.secondnext.scout(roll - 1, p);

        }
        System.out.println("skok");


        return rtnk;


    }

    /**
     *
     * @param e
     */

    @Override
    public void mouseClicked(MouseEvent e) {


        if ((one != null) && (one.owner.turn == true)) {

            one.moveP(one.owner.res);




        }


    }

}










