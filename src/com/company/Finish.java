package com.company;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * reprezentuje pole "poza planszą nie da się go kliknąć
 * pilnuje by pionek mógł opuscić grę tylko przy dokładnej liczbie oczek
 */

public class Finish extends  Field {
    /**
     * Zwraca obiekt klasy Finish z atrybutami
     * @param x współrzedna x
     * @param y współrzedna x
     * @param t typ
     * @param i
     * @param nxt następne pole
     */

public  Finish(int x,int y,int t,int i,Field nxt){
    super( x, y, t, i, nxt);
}

    /**
     * metoda wczytująca pliki graficzne z dysku do obiektów grafiki
     */
    @Override
    public void loadimg(){

        try {
            jpg = empty = ImageIO.read(new File("resrc\\fin.jpg"));
            red = ImageIO.read(new File("resrc\\rozetaRED.jpg"));
            blu = ImageIO.read(new File("resrc\\rozetaBLU.jpg"));
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
     *
     * zgodnie z zasadami gry z ur można opuścić plansze (wejść na pole Klasy Finish należy mieć równą ilość ruchów by się na niej zatrzymać
     *          wprzeciwnym razie wzracane jest null
     * @return
     */

    @Override
    public Field scout(int roll,Player p){

    if(roll == 0 )
    { return this;}
    else
        return null;

    }


    /**
     * metoda kliknięciaa została wyczyszczona
     * @param e
     */

    @Override
    public void mouseClicked(MouseEvent e) {



        }




    }






