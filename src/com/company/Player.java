package com.company;

import java.util.Random;
import java.util.Scanner;

/**
 * Obiekt gracza
 * Przechowuje pionki, liczbę ruchów w turze pola startowe i końcowe, liczbę pionków i znaczniki tury
 *
 */


public class Player {



    String name;
    public boolean turn;
    static int maxNumber0fPieces = 7;
    int number0fpieces;
    boolean victory;
    int number,res,succes;
    public Finish finished;
    public Start begin;
    boolean wait;
    boolean bonusturn;
    boolean slayermode;

    Pion[] pions = new Pion[maxNumber0fPieces];

    /**
     * Konstruktor Obiektów klasy Player
     * zwraca obiekt z parametrami:
     *
     * @param nm nazwa gracza
     * @param mb numer identyfikacyjny gracza
     * @param str pole startowe
     * @param fin pole końcowe
     * @param pieces ilosc pionków
     */

    public Player(String nm,int mb,Start str,Finish fin,int pieces){
        name = nm;
        bonusturn = false;
        finished = fin;
        begin = str;
        number = mb;
        victory = false;
        begin.owner = this;
         number0fpieces = pieces;
        begin.setStoneC(pieces);
        wait = false;
        begin.setimag();

        for(int i = 0; i < number0fpieces; i++)
        pions[i] = new Pion(this,begin);
        placeStones();

        begin.one = pions[number0fpieces-1];


    }

    /**
     * umieszcza pionki na polu koczątkowym
     *
     */


    public void placeStones(){
        begin.stones = pions;


    }

    /**metoda sprawdza czy wszytkie pionki gracza ukończyły wyścig
     *
     * @return victory
     */

    public boolean checkWinCondition(){

        if(succes-1 == number0fpieces)
        {victory = true;System.out.println(name + "zwyciestwo");}
        return victory;
    }

    /**
     * metoda sprawdza czy dla podane liczby oczek istnieje pionek który może sie poruszyć
     * @param roll
     * @return tak lub nie
     */

    public boolean checkPosibleMoves(int roll){

        if(roll == 0) return false;

        for(int i = 0; i < number0fpieces; i++)
        {if(pions[i].ingame == true && pions[i].current.scout(roll,this )!= null) return true;}

        return false;


    }

    /**
     * metoda losująca liczbę całkowitą od 0 do 4 na 4 kostkach D2
     * @return liczba oczek
     */

    public int diceRollD2x4()
    {
//
//       Math rand1 = new Math();
//
 int result = 0;
//
//        result += (int)rand1.random()*;
//        result += (int)rand1.random();
//        result += (int)rand1.random();
//        result += (int)rand1.random();
//        res = result;
       return result;
        }

    /**metoda losuję liczbę całkowitą z przedziału od 0 do 4 na 1 kostce D4
     *
     * @return roll
     */

    public int diceRollD4()
    {
        Random rand1 = new Random();
        int result = 0;
        result += rand1.nextInt(5);



        return result;
    }


    /**
     * metoda wywołuje turę gracza i sprawdza możliwość ruchu wylosowanej ilości ruchów
     * @param roll ilość ruchów
     */

    public void myTurn(int  roll){


    if(roll == 4)
        slayermode = true;

         //res = roll;
        turn = true;

         res = roll ;//= scan.nextInt();
        //System.out.println(name +"dice: "+ roll);
        if(roll == 0 ){ res = 0; return;}

        if(checkPosibleMoves(roll) == false) return;




    }

    /**
     * kończy turę
     */
    public void endTurn(){
        turn = false;
        //checkWinCondition();
    }


}
