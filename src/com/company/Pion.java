package com.company;

/**
 * Klasa publiczna Pion reprezentuje pojedynczego pionka na plaszy
 * ma on swoje obecne pole , właściciela
 */


public class Pion {

    Player owner;

    Field current;

    Field next;

    boolean ingame;

    /**
     * konstruktor zwraca obiekt z parametrami
     * @param own właściciel
     * @param frst pole startowe
     */

    Pion(Player own,Field frst){

        owner = own;
        current = frst;
        ingame = true;

    }

    /**
     * metoda ustawia Pionka na polu podanym w
     *
     * @param f pole docelowe
     *
     *
     * @param iden identyfikator właścciela
     */

    public void setPion(Field f,int iden)
    {

        current.placeEmpty();
        current.occupied = false;
        current = f;
        current.occupied = true;
        current.one = this;
      if(current.type == 3) {owner.succes++; }

        if(iden == 3){ f.placeRED(); }else {f.placeBLU();}
//        if(current == owner.finished) owner.finished.stones++;
        next = null;

    }

    /**
     * metoda próbuje przestawić pionek o podaną w
     * @param roll
     * liczbę oczek wywołuje najpierw metodę sprawdzającą dstępność pola a następnie metodę ustwienia pionka
     * i jęsli to konieczne również metodę strącenia przeciwnika
     * @return
     */

    public boolean moveP(int roll){
        next = current.scout(roll,owner);
        if(next == null || next == current)
        {return false;}

            if(next.occupied == true){
            next.resetPion();}

            setPion(next,owner.number);
            return true;

       }

    }





