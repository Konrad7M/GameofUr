package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Klasa reprezentująca kość i przełącznik między turami rozszerza klasę field
 */

public class Presser extends Field {

    Player first,second;
    public BufferedImage[] red = new BufferedImage[5];
    public BufferedImage[] blu = new BufferedImage[5];
    public BufferedImage dice ;
    boolean frst,endgame;


    /**
     * Tworzy obiekt przycisku i graficznej  reprezentacji kości do gry
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @param t typ
     * @param i
     * @param nxt następne pole(zbędne
     * @param p1 gracz 1
     * @param p2 gracz 2
     */

    public Presser(int x,int y,int t,int i,Field nxt,Player p1,Player p2){


        super(x,y,t,i,null);
        first=p1;
        second=p2;
        frst = true;
        endgame =false;




        loadimg2();


    }

    /**
     * metoda ładuje pliki graficzne do obiektów graficznych z dysku
     */


    public void loadimg2(){

        try {

            dice = ImageIO.read(new File("resrc\\dices\\dice.jpg"));
            jpg = dice;

            red[0] = ImageIO.read(new File("resrc\\dices\\red0.jpg"));
            red[1] = ImageIO.read(new File("resrc\\dices\\red1.jpg"));
            red[2] = ImageIO.read(new File("resrc\\dices\\red2.jpg"));
            red[3] = ImageIO.read(new File("resrc\\dices\\red3.jpg"));
            red[4] = ImageIO.read(new File("resrc\\dices\\red4.jpg"));

            blu[0] = ImageIO.read(new File("resrc\\dices\\blu0.jpg"));
            blu[1] = ImageIO.read(new File("resrc\\dices\\blu1.jpg"));
            blu[2] = ImageIO.read(new File("resrc\\dices\\blu2.jpg"));
            blu[3] = ImageIO.read(new File("resrc\\dices\\blu3.jpg"));
            blu[4] = ImageIO.read(new File("resrc\\dices\\blu4.jpg"));






        }catch(IOException e1)
        {System.out.println("Nczy");}



    }


    /**
     * metoda kliknięcia
     *
     * ustawia pierwszą turę
     *
     * w następnych przełącza między graczami
     * egzekwuje dodatkowe tury podyktowane
     * wyświetla wylosowane liczby
     * kolorem wskazuje czyj jest ruch
     * przy spełnieniu waruków zwycięstwa wraca do grafiki początkowej i blokuje możliwośc dalszego losowania
     *
     *
     * @param e
     */

    @Override
    public void mouseClicked(MouseEvent e) {
        int rol;
        System.out.println("rzut");
        if(frst)
        {

            first.wait = true;
        frst = false;}
        if(endgame) {switchnumb(first,0); return;}


        if(first.bonusturn || second.bonusturn)
        {
            if (first.bonusturn) {
                rol = first.diceRollD4()+1;
                //.slayermode = false;
                switchnumb(first,rol);

                first.bonusturn = false;
                second.wait = true;
                first.myTurn(rol);
                first.wait = false;

            }

            if (second.bonusturn) {

                //first.slayermode = false;
                rol = second.diceRollD4();
                second.bonusturn = false;

                switchnumb(second,rol);

                first.wait = true;
                second.myTurn(rol);
                second.wait = false;
            }
        }
        else
        if((first.wait && !second.wait) )
        {
            //second.slayermode = false;
            if(second.checkWinCondition()) {endgame = true;switchnumb(first,-1); return;}
            second.wait = true;
            rol = first.diceRollD4();
            switchnumb(first,rol);

            first.myTurn(rol);
            first.wait = false;
        }
        else
        {   //first.slayermode = false;
            if(first.checkWinCondition()) {endgame = true;switchnumb(first,-1); return;}
            first.wait = true;
            rol = second.diceRollD4();
            switchnumb(second,rol);

            second.myTurn(rol);
            second.wait = false;
        }

    }

    /**
     * metoda zmiany grafiki kości na taką z wyolosowaną wartością
     * @param p
     * @param lever
     */

    public void switchnumb(Player p,int lever){

        if(lever>4) return;

        if (p == null)
        {jpg = dice;repaint();return;}

        if(p.number == 3 )
            jpg = red[lever];
        if(p.number == 4)
            jpg = blu[lever];
        if(lever == -1)
            jpg = dice;

            repaint();


    }


}
