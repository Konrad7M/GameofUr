package com.company;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Klasa publiczna Field rozszerza klase Canvas
 *
 * reprezentuje pojedyncze pole na planszy gry
 *
 *
 */


public class Field extends Canvas implements MouseListener {

    /**
     * obkiety typu BufferedImage przechowuja obrazy reprezentujace stany pola gdzie
     * jpg jest kontenerem rysowanego obrazu
     *
     */

    public BufferedImage empty;
    public BufferedImage jpg;
    public BufferedImage red;
    public BufferedImage blu;

    /**
     * wskaznik na kolejne pole na planszy
     */
    public Field next;
    /**
     * współrzedne na których Field będzie rysowany na JFrame
     */
    private int Xcor;
    private int Ycor;
    /**
     * typ planszy determinuj ruch pionka
     */
    protected int type;
    //private int index;
    /**
     * nazwa gracza
     */

    public   String p;

    /**
     * Pionek obecnie znajdujący się na polu
     */
    public Pion one;

    /**
     * zmienna boolowska - mowi czy pole jest zajete czy nie
     */
    boolean occupied;

    /**
     * Konstruktor tworzy obiekt pole
     *
     * @param x współrzędna x na Jframe
     * @param y współrzędna y na JFrame
     * @param t typ pola
     * @param i index
     * @param nxt   Wskaznik na kolejne pole
     */

    public Field(int x,int y,int t,int i,Field nxt){
        super();
        next = nxt;
        this.setLocation(x,y);

        Xcor = x;
        Ycor = y;
        type = t;

        loadimg();


        //index = i;
        occupied = false;
        addMouseListener(this);
        int w = jpg.getWidth();
        int h = jpg.getHeight();
        setSize(w,h);

        }


    /**
     * Nadpisana metoda wysująca obiekt na JFrame
     * zawsze rusuje obraz z kontenera jpg
     *
     * @param g
     */

    @Override
    public void paint(Graphics g) {

    super.paint(g);
    if (jpg != null) {
        int x = (getWidth() - jpg.getWidth()) / 2;
        int y = (getHeight() - jpg.getHeight()) / 2;
        g.drawImage(jpg, x, y, this);

        this.setVisible(true);
    }
}

    /**
     * metoda ładuje pliki graficzne z dysku twardego do kontenerów
     */
    public void loadimg(){

        try {
            jpg = empty = ImageIO.read(new File("resrc\\plainflds\\flds.jpg"));
            red = ImageIO.read(new File("resrc\\plainflds\\fldsRED.jpg"));
            blu = ImageIO.read(new File("resrc\\plainflds\\fldsBLU.jpg"));
        }catch(IOException e1)
        {System.out.println("Nczy");}

}

    /**
     * ustawia na polu czerowny pionek
     * kończy turę gracza
     * oraz wyłącza tryb bicia najbliższego pionka
     */

    public void placeRED(){
        occupied = true;
        jpg = red;
        this.repaint();
        one.owner.turn = false;
        one.owner.slayermode = false;


}

    /**
     * ustawia na polu niebieski pionek
     * kończy turę gracza
     * oraz wyłącza tryb bicia najbliższego pionka
     */
    public void placeBLU(){

        jpg = blu;
        occupied = true;
        this.repaint();
        one.owner.turn = false;
        one.owner.slayermode = false;


    }

    /**
     * zdejmuje obecnego pionka z pola
     *
     */


    public void placeEmpty(){

        jpg = empty;
        occupied = false;
        this.repaint();
        one = null;

    }

    /**
     * Gdy ta metoda zostanie wywołana pionek zostanie przeniesiony na swoje pole startowe
     */

    public void resetPion(){


        one.owner.begin.welcomeback(one);


    }

    /**
     * metoda scout rekurencyjnie sprawdza czy pionek znajdujący się na polu może poruszyć się
     * o liczbę pól podaną w
     * @param roll
     * natomiast
     * @param p przechowuje obiekt gracza by móc udentyfikować pola niedostępne i okazje do
     * dzięki rekurencyjnosci odpowiedź mogą dać obiekty klas dziedziczących z Field
     * Jeśli @param roll jest różny od zera to metoda przekazuje zapytanie do kolejnego obiektu,
     * jesli nie sprawdza dostępność pola. Pole dostępne to takie które jest puste bądź zajęte przez przeciwnika
     *
     *
     * @return Field czyli pole na ktore może udać się pionek
     *  dla gracza z flagą - slayermode wyszukuje najbliższy wybranemu pionkowi pionek przeciwnika
     *
     */

    public Field scout(int roll,Player p){

        Field rtnk = null ;


        if(!p.slayermode) {
            if (roll == 0) {

                if (this.occupied == true) {
                    if (this.one.owner == p) {

                        return null;
                    } else {
                        return this;
                    }
                } else
                    rtnk = this;

            } else {
                

                rtnk = this.next.scout(roll - 1, p);

            }
        }else {

                if (occupied && (p != this.one.owner))
                {rtnk = this;   one.owner.slayermode = false;}
                else
                    rtnk = this.next.scout(0, p);
        }


        return rtnk;
    }

    /**
     * metoda służaca do debugowania
     * @param couter - ilość pół do przebycia
     */


public void testrun(int couter)
{
    if(this.next!=null)
    {++couter;
        this.next.placeRED();
    this.next.testrun(couter);}
    else{

        System.out.println(couter);



    }



}

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * metoda klicknięcia
     * gdy zosttanie uruchomiona pionek znajdujący się na polu Field zostanie przesuniety o liczbę oczek z res z obiektu
     * klasy Player który jest "włąścicielem" pionka pod warunkiem że ten pionek istnieje i jest tura właściciela
     *
     * @param e
     */



    @Override
    public void mouseClicked(MouseEvent e) {


        if((one != null) && (one.owner.turn == true)){

            boolean check = one.moveP(one.owner.res);




        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
