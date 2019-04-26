package com.company;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Klasa publiczna Start reprezentuje pole początkowe jednego z graczy
 * rozszerza klasę Field
 */

public class Start extends Field {


    Player owner;

    public BufferedImage[] red = new BufferedImage[8];
    public BufferedImage[] blu = new BufferedImage[8];

private int stonecounter;

Pion[] stones = new Pion[8];

    /**
     * konstruktor Klasy Start
     * @param x wspórzędna x
     * @param y wspórzędna
     * @param t typ
     * @param i index
     * @param nxt nnastępne pole
     */

    public Start(int x, int y, int t, int i, Field nxt) {
        super( x, y, t, i, nxt);
        next =nxt;

    }

    /**
     * ustawia licznik pionków na wartość z
     * @param i
     */
    public void setStoneC(int i)
    {stonecounter =i;}

    /**
     * metoda wczytuje obrazy z dyku twardego
     */
    @Override
    public void loadimg(){





        try {
            jpg = empty = ImageIO.read(new File("resrc\\start.jpg"));

            red[0] = ImageIO.read(new File("\\resrc\\reds\\red0.jpg"));
            red[1] = ImageIO.read(new File("\\resrc\\reds\\red1.jpg"));
            red[2] = ImageIO.read(new File("\\resrc\\reds\\red2.jpg"));
            red[3] = ImageIO.read(new File("\\resrc\\reds\\red3.jpg"));
            red[4] = ImageIO.read(new File("\\resrc\\reds\\red4.jpg"));
            red[5] = ImageIO.read(new File("\\resrc\\reds\\red5.jpg"));
            red[6] = ImageIO.read(new File("\\resrc\\reds\\red6.jpg"));
            red[7] = ImageIO.read(new File("\\resrc\\reds\\red7.jpg"));

            blu[0] = ImageIO.read(new File("\\resrc\\blus\\blu0.jpg"));
            blu[1] = ImageIO.read(new File("\\resrc\\blus\\blu1.jpg"));
            blu[2] = ImageIO.read(new File("\\resrc\\blus\\blu2.jpg"));
            blu[3] = ImageIO.read(new File("\\resrc\\blus\\blu3.jpg"));
            blu[4] = ImageIO.read(new File("\\resrc\\blus\\blu4.jpg"));
            blu[5] = ImageIO.read(new File("\\resrc\\blus\\blu5.jpg"));
            blu[6] = ImageIO.read(new File("\\resrc\\blus\\blu6.jpg"));
            blu[7] = ImageIO.read(new File("\\resrc\\blus\\blu7.jpg"));





        }catch(IOException e1)
        {System.out.println("Nczy");}
    }

    /**
     * ustawia odpowiedni obraz
     */

    public  void setimag(){
        if(owner.number == 3)
            jpg = red[7];
        else
            jpg = blu[7];

        jpg = empty;
        repaint();
    }

    /**
     * metoda wciśniecia klawisza myszki
     * przesuwa pionek liczbe pól właściciela pionka
     * @param e
     */

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("nib");
        if (owner.turn && stonecounter-1 >= 0) {
            occupied = true;
            one.moveP(one.owner.res);

            stonecounter = stonecounter - 1;
            one = owner.pions[(stonecounter-1)];

            switchImg(stonecounter);

        }


    }

    /**Przenosi wskazanu w
     * @param p
     * obiekt pionka z powrotem na pole startowe
     */
    public void welcomeback(Pion p )
    { ++  stonecounter;
       one = owner.pions[stonecounter-1] = p;
       p.current = this;
        switchImg(stonecounter);

    }

    /**
     * metoda zmienia obraz na wskazany przez
     * @param swt
     */
    public void switchImg(int swt){

        if(owner.number ==3 )
            jpg = red[swt];
        else
            jpg = blu[swt];

        jpg = empty;
        repaint();




    }



}





