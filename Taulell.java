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

    public static void llegirTaulell(){
        Scanner teclado = new Scanner(System.in);
        String s = teclado.nextLine();
        String[] h= s.split(",");
        Tcela = h[0];
        Tadjacecnia = h[1];
        files = Integer.parseInt(h[2]);
        columnes = Integer.parseInt(h[3]);
        mContingut = new String[files][columnes];
        String margen = h[4];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j ++) {
                mContingut[i][j] = "?";
            }
        }
        for (int i = 0; i < margen.length(); i = i + 4) {
            char f = margen.charAt(i);
            int ff = Integer.parseInt(String.valueOf(f));
            char c = margen.charAt(i + 2);
            int cc = Integer.parseInt(String.valueOf(c));
            mContingut[ff - 1][cc-1] = "#";
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
        //generaMatAdj();
        //imprimirMatAdj();
        imprimirMContingut();
    }


}
