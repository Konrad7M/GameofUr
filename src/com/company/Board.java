package com.company;

import javax.swing.*;

/**
 * Klasa publiczna Board
 * jest rozszerzeniem klasy JFrame przechowuje całość planszy razem z kością
 *
 *
 */



public class Board extends JFrame {

    Field fld[][] = new Field[3][];
    public Start startBlu ;
    public Finish finBlu ;
   public Presser turnbutonRed;
   public Presser turnbutonBlu;
    public Start startRed ;
    public Finish finRed ;

    /**
     * metoda konstruktora tworzy plansze o 24 widocznych polach i kości
     * @param n nazwa gry pojawiająca się górnym pasku okna
     */

    public Board(String n){

        super(n);
        setLocationRelativeTo(null);
        fld[0]= new Field[8];
        fld[1]= new Field[8];
        fld[2]= new Field[8];

        fld[0][5] = new Finish(100,75*6,3,0,fld[0][5]);
        fld[0][6] = new Rozeta(100,75*7,1,0,fld[0][5]);
        fld[0][7] = new Field(100,75*8,0,0,fld[0][6]);

        fld[2][5] = new Finish(300,6*75,3,0,fld[2][5]);
        fld[2][6] = new Rozeta(300,7*75,1,0,fld[2][5]);
        fld[2][7] = new Field(300,8*75,0,0,fld[2][6]);

        fld[1][7] = new Crossroad(200,8*75,0,0,fld[0][7],fld[2][7]);
        fld[1][6] = new Field(200,7*75,0,0,fld[1][7]);
        fld[1][5] = new Field(200,6*75,0,0,fld[1][6]);
        fld[1][4] = new Field(200,5*75,0,0,fld[1][5]);
        fld[1][3] = new Rozeta(200,4*75,1,0,fld[1][4]);
        fld[1][2] = new Field(200,3*75,0,0,fld[1][3]);
        fld[1][1] = new Field(200,75*2,0,0,fld[1][2]);
        fld[1][0] = new Field(200,75,0,0,fld[1][1]);






        fld[0][0] = new Rozeta(100,75,1,0,fld[1][0]);
        fld[0][1] = new Field(100,75*2,0,0,fld[0][0]);
        fld[0][2] = new Field(100,75*3,0,0,fld[0][1]);
        fld[0][3] = new Field(100,75*4,0,0, fld[0][2]);
        fld[0][4] = new Start(100,75*5,0,0, fld[0][3]);



        fld[2][0] = new Rozeta(300,1*75,1,0,fld[1][0]);
        fld[2][1] = new Field(300,2*75,0,0,fld[2][0]);
        fld[2][2] = new Field(300,3*75,0,0,fld[2][1]);
        fld[2][3] = new Field(300,4*75,0,0, fld[2][2]);
        fld[2][4] = new Start(300,5*75,0,0, fld[2][3]);





       startBlu = new Start(300,5*75,0,0, fld[2][3]);
         finBlu = new Finish(300,6*75,3,0,finBlu);

        startRed = new Start(100,75*5,0,0, fld[0][3]);
        finRed = new Finish(100,75*6,3,0,finRed);




        startBlu.setVisible(true);
        startRed.setVisible(true);
        finBlu.setVisible(true);
        finRed.setVisible(true);

    for(int i = 0;i <= 2;i++)
        for(int j = 0;j <= 7;j++)
        {
            this.add(fld[i][j]);
            fld[i][j].setVisible(true);
        }

        fld[0][4].setVisible(false);
        fld[0][5].setVisible(false);

        fld[2][4].setVisible(false);
        fld[2][5].setVisible(false);

        this.add(startBlu);
        this.add(startRed);
        this.add(finBlu);
        this.add(finRed);


        Field y = new Start(300,5*75,0,0, fld[2][3]);

        this.add(y);
        y.setVisible(false);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
