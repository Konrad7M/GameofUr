package com.company;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Klasa publiczna Rozeta rozszerza klasę Field
 *
 * reprezentuje sprecjalne pole z którego pionek nie może być strącony przez przeciwnika w przypadku zainicjowania takiego manewru
 * o ile jest to możliwe pionek zostanie przesunięty na następne pole.
 * Gdy pionek ustawi się na rozecie graczowi przysługuje dodatkowy rzut kością i ruch dowolnym pionkiem
 * dla gracza z flagą - slayermode wyszukuje najbliższy wybranemu pionkowi pionek przeciwnika
 */


public class Rozeta extends Field {


    /**
     * Konstruktor obiektów klasy Rozeta
     * @param x współrzędna x
     * @param y współrzędna y
     * @param t typ
     * @param i
     * @param nxt następne pola
     */

    public Rozeta(int x, int y, int t, int i, Field nxt) {
        super(x, y, 1, i, nxt);
       // super(x, y, t, i, nxt);
    }

    /**
     * metoda wczytująca pliki graficzne z dysku do obiektów grafiki
     */

    @Override
    public void loadimg(){

        try {
            jpg = empty = ImageIO.read(new File("resrc\\rozetas\\rozeta.jpg"));
            red = ImageIO.read(new File("resrc\\rozetas\\rozetaRED.jpg"));
            blu = ImageIO.read(new File("resrc\\rozetas\\rozetaBLU.jpg"));
        }catch(IOException e1)
        {System.out.println("Nczy");}
    }

    /**
     *
     * @param roll
     * natomiast
     * @param p przechowuje obiekt gracza by móc udentyfikować pola niedostępne i okazje do
     * dzięki rekurencyjnosci odpowiedź mogą dać obiekty klas dziedziczących z Field
     * Jeśli @param roll jest różny od zera to metoda przekazuje zapytanie do kolejnego obiektu,
     * jesli nie sprawdza dostępność pola. Pole dostępne to takie które jest puste bądź zajęte przez przeciwnika
     *          w ostatnim przypadku o ile jest to możliwe pionek ląduje ustawiany jest na polu następnym
     * @return Field
     */

    @Override
    public Field scout(int roll,Player p) {

        Field rtnk = null;

        if(!p.slayermode)
        {
         if (roll == 0) {
            if (occupied) {
                if (p == one.owner)
                    rtnk = null;
                else
                    rtnk = this.next.scout(0, p);
            } else
                rtnk = this;

        } else {
            rtnk = this.next.scout(roll - 1, p);
        }
        }else{
            if(occupied && (p != this.one.owner))
            {rtnk = this;   one.owner.slayermode = false;}
            else
                rtnk = this.next.scout(0, p);


        }

        return rtnk;
    }

    /**
     * metoda ustawia grafikę niebieskiego pionka i flage dodatkowego ruchu
     * oraz wyłacza tryb wyłącza tryb bicia najbliższego pionka
     */
    @Override
    public void placeBLU(){

//        jpg = blu;
//        occupied = true;
//        this.repaint();
            super.placeBLU();
        one.owner.bonusturn = true;
        one.owner.turn = true;
        one.owner.slayermode = false;
    }
    /**
     * metoda ustawia grafikę czerwonego  pionka i flage dodatkowego ruchu
     * oraz wyłacza tryb wyłącza tryb bicia najbliższego pionka
     */
    @Override
    public void placeRED(){

//        occupied = true;
//        jpg = red;
//        this.repaint();

        super.placeRED();
        one.owner.bonusturn = true;
        one.owner.turn = true;
        one.owner.slayermode = false;

    }




}
