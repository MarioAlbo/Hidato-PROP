/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hidato;

import java.util.Scanner;

public class Taulell {

    public static String Tcela;
    public static String Tadjacecnia;
    public static int files;
    public static int columnes;
    public static String[][] mContingut;

    public void llegirTaulell() {
        System.out.println("llegint taulell");
        Scanner teclado = new Scanner(System.in);
        String s = teclado.nextLine();
        String[] h = s.split(",");
        Tcela = h[0];
        Tadjacecnia = h[1];
        files = Integer.parseInt(h[2]);
        columnes = Integer.parseInt(h[3]);
        mContingut = new String[files][columnes];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                mContingut[i][j] = "?";
            }
        }
    }

    public void posarForats() {
        System.out.println("posar forats(#): coorX coordY");
        System.out.println("per acabar: exit");
        Scanner teclado = new Scanner(System.in);
        String s = teclado.nextLine();
        while (!s.equals("exit")) {
            String[] h = s.split(" ");
            int coordX =  Integer.parseInt(h[0]);
            int coordY =  Integer.parseInt(h[1]);
            mContingut[coordX][coordY] = "#";
            s = teclado.nextLine();
        }
    }

    public void treuForats() {
        System.out.println("treure forats: coorX coordY");
        System.out.println("per acabar: exit");
        Scanner teclado = new Scanner(System.in);
        String s = teclado.nextLine();
        while (!s.equals("exit")) {
            String[] h = s.split(" ");
            int coordX =  Integer.parseInt(h[0]);
            int coordY =  Integer.parseInt(h[1]);
            mContingut[coordX][coordY] = "?";
            s = teclado.nextLine();
        }
    }


    public void imprimirMContingut(){
        System.out.print(Tcela + "," + Tadjacecnia + "," + files + "," + columnes);
        System.out.println();
        for (int i = 0; i < mContingut.length; i++){
            for (int j = 0; j < mContingut[i].length; j++){
                if (j < mContingut[i].length - 1) {
                    System.out.print(mContingut[i][j] + ",");
                }
                else {
                    System.out.print(mContingut[i][j]);
                }
            }
            System.out.println();
        }
    }
}