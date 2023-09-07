package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Matrix mt = new Matrix();


    public static void main(String[] args) {
        initUI();
        //Hey Thanks for reading it! :)
    }



    private static void initMatrixMulti (int j, Matrix mtx)  {

        for (int i = 1; i <= j; i++) {
        System.out.println("\n    MatrixMulti A^" + i + ": ");
        mtx.print2DArray(mtx.berechnenMatrix(i));
        }
    }

    private static void initDistanzMatrix (Matrix mtx)  { //todo Matrix as m
        System.out.println("\n    DistanzMatrix: ");
        mtx.print2DArray(mtx.distanzMatrixv1());
    }

    private static void initWay (Matrix mtx) {
        System.out.println("\nWegMatrix: ");
        mtx.print2DArray(mtx.wegMatrix());
    }

    private static void initRadDurchZen(Matrix mtx) {
        mtx.radDurchZentr();
    }

    private static void initExzentriz (Matrix mtx) {
        System.out.println("\n    Extrizitaeten:");
        ArrayList<Integer> extr = mtx.berechnExzentrizitaeten();
        int o = 0;
        String str;
        for (int l: extr) {
            o++;

            System.out.println(o + " -> " + l);

        }
    }

    private static void initComponents (Matrix mtx) {
        System.out.println("\n    Komponenten: " + mtx.componentsCount(mtx.wegMatrix()));
    }

    private static void initArtikulation () {
        Matrix mtx = new Matrix();
        System.out.println("\n    Artikulationen: \n" + mtx.berechArtikulat());
    }

    private static void initBruecken () {
        Matrix mtx = new Matrix();
        System.out.println("\n    Bruecken: \n" + mtx.berechBruecken());
    }

    private static void initUI () {
        int chooseNum;
        while (true) {
            System.out.println("""
                    
                    ====================================================================================================
                    
                    +-----------------------------------+
                    | Choose what to calculate:         |
                    +-----------------------------------+
                    | 1 -> Matrix, geben den Grad ein   |
                    | 2 -> DistanzMatrix                |
                    | 3 -> Exzentrizitaeten             |
                    | 4 -> RaduisDurchmesseZentrum      |
                    | 5 -> Komponenten Anzhal           |
                    | 6 -> Weg Matrix                   |
                    | 7 -> Artikulationen               |
                    | 8 -> Bruecken                     |
                    | 0 -> Exit                         |
                    +-----------------------------------+
                    
                    ====================================================================================================
                    """);
            chooseNum = sc.nextInt();
            switch (chooseNum) {
                case 1 -> {
                    System.out.println("Geben Sie den Grad ein: ");
                    initMatrixMulti(sc.nextInt(), mt);
                }
                case 2 -> initDistanzMatrix(mt);
                case 3 -> initExzentriz(mt);
                case 4 -> initRadDurchZen(mt);
                case 5 -> initComponents(mt);
                case 6 -> initWay(mt);
                case 7 -> initArtikulation();
                case 8 -> initBruecken();
                case 0 -> {return;}
                default -> System.out.println("Unknown input! ");
            }
        }
    }



}
