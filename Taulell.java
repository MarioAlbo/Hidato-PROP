import java.lang.String;
import java.util.Scanner;
//import java.util.List;
import java.util.ArrayList;


public class Taulell {

    public static String Tcela;
    public static String Tadjacecnia;
    public static int files;
    public static int columnes;
    public static String[][] mContingut;

    public static void llegirTaulell() {
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

    public static void posarForats() {
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

    public static void treuForats() {
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


    public static void imprimirMContingut(){
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


    public static void main(String[] arg){

        llegirTaulell();
        posarForats();
        treuForats();
        imprimirMContingut();
    }


}
