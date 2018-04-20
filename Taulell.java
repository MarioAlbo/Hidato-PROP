import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Taulell {

    private static String Tcela;
    private static String Tadjacecnia;
    private static int files;
    private static int columnes;
    private static ArrayList <ArrayList<Integer> > matAdj; //id [i][j] = i*col + j
    private static int[][] mContingut;

    public static void llegirTaulell(){
        Scanner teclado = new Scanner(System.in);
        String s = teclado.nextLine();
        String h[]= s.split(",");
        Tcela = h[0];
        Tadjacecnia = h[1];
        files = Integer.parseInt(h[2]);
        columnes = Integer.parseInt(h[3]);
        matAdj = new ArrayList <ArrayList<Integer> >(columnes*files) ;
    }

    public static void generaMatAdj(){

        if (Tcela.equals("Q")){
                for (int i = 0; i < files; i++) {
                    for (int j = 0; j < columnes; j++) {
                        matAdj.add(new ArrayList<Integer>());
                        if (j - 1 >= 0) matAdj.get(i * columnes + j).add(i * columnes + j - 1);
                        if (j + 1 < columnes) matAdj.get(i * columnes + j).add(i * columnes + j + 1);
                        if (i - 1 >= 0) matAdj.get(i * columnes + j).add((i - 1) * columnes + j);
                        if (i + 1 < files) matAdj.get(i * columnes + j).add((i + 1) * columnes + j);
                    }
                }
            if (Tadjacecnia.equals("CA")) {
                for (int i = 0; i < files; i++){
                    for (int j = 0; j < columnes; j++){
                        if (j-1 >=0 && i-1 >=0) matAdj.get(i*columnes + j).add((i-1) * columnes + j - 1);
                        if (j+1 < columnes && i-1 >=0) matAdj.get(i*columnes + j).add((i-1) * columnes + j + 1);
                        if (j+1 < columnes && i+1 < files) matAdj.get(i*columnes + j).add((i+1) * columnes + j + 1);
                        if (j-1 >=0 && i+1 < files) matAdj.get(i*columnes + j).add((i+1) * columnes + j - 1);
                    }
                }
            }
        }
        else if (Tcela.equals("H")) {
            for (int i = 0; i < files; i++) {
                for (int j = 0; j < columnes; j++) {
                    matAdj.add(new ArrayList<Integer>());
                    if (i%2==1) {
                        if (i - 1 >= 0) matAdj.get(i * columnes + j).add((i - 1) * columnes + j);
                        if (i - 1 >= 0 && j + 1 < columnes)
                            matAdj.get(i * columnes + j).add((i - 1) * columnes + j + 1);
                        if (j - 1 >= 0) matAdj.get(i * columnes + j).add(i * columnes + j - 1);
                        if (j + 1 < columnes) matAdj.get(i * columnes + j).add(i * columnes + j + 1);
                        if (i + 1 < files) matAdj.get(i * columnes + j).add((i + 1) * columnes + j);
                        if (i + 1 < files && j + 1 < columnes)
                            matAdj.get(i * columnes + j).add((i + 1) * columnes + j + 1);
                    }
                    else {
                        if (i - 1 >= 0) matAdj.get(i * columnes + j).add((i - 1) * columnes + j);
                        if (i - 1 >= 0 && j - 1 >= 0)
                            matAdj.get(i * columnes + j).add((i - 1) * columnes + j - 1);
                        if (j - 1 >= 0) matAdj.get(i * columnes + j).add(i * columnes + j - 1);
                        if (j + 1 < columnes) matAdj.get(i * columnes + j).add(i * columnes + j + 1);
                        if (i + 1 < files) matAdj.get(i * columnes + j).add((i + 1) * columnes + j);
                        if (i + 1 < files && j - 1 >= 0)
                            matAdj.get(i * columnes + j).add((i + 1) * columnes + j - 1);
                    }
                }
            }
        }
        else if (Tcela.equals("T")){
            for (int i = 0; i < files; i++) {
                for (int j = 0; j < columnes; j++) {
                    matAdj.add(new ArrayList<Integer>());
                    if (j - 1 >= 0) matAdj.get(i * columnes + j).add(i * columnes + j - 1);
                    if (j + 1 < columnes) matAdj.get(i * columnes + j).add(i * columnes + j + 1);
                    if ((i + j) % 2 == 0) {
                        if (i + 1 < files) matAdj.get(i * columnes + j).add((i + 1) * columnes + j);
                    } else {
                        if (i - 1 >= 0) matAdj.get(i * columnes + j).add((i - 1) * columnes + j);
                    }
                }
            }
            if (Tadjacecnia.equals("CA")){
                for (int i = 0; i < files; i++) {
                    for (int j = 0; j < columnes; j++) {
                        if ((i + j) % 2 == 0) {
                            if (i - 1 >= 0 && j - 1 >= 0) matAdj.get(i * columnes + j).add((i - 1) * columnes + j - 1);
                            if (i - 1 >= 0) matAdj.get(i * columnes + j).add((i - 1) * columnes + j);
                            if (i - 1 >= 0 && j + 1 < columnes)
                                matAdj.get(i * columnes + j).add((i - 1) * columnes + j + 1);
                            if (j - 2 >= 0) matAdj.get(i * columnes + j).add(i * columnes + j - 2);
                            if (j + 2 < columnes) matAdj.get(i * columnes + j).add(i * columnes + j + 2);
                            if (i + 1 < files && j - 2 >= 0)
                                matAdj.get(i * columnes + j).add((i + 1) * columnes + j - 2);
                            if (i + 1 < files && j - 1 >= 0)
                                matAdj.get(i * columnes + j).add((i + 1) * columnes + j - 1);
                            if (i + 1 < files && j + 1 < columnes)
                                matAdj.get(i * columnes + j).add((i + 1) * columnes + j + 1);
                            if (i + 1 < files && j + 2 < columnes)
                                matAdj.get(i * columnes + j).add((i + 1) * columnes + j + 2);
                        } else {
                            if (i - 1 >= 0 && j - 2 >= 0) matAdj.get(i * columnes + j).add((i - 1) * columnes + j - 2);
                            if (i - 1 >= 0 && j - 1 >= 0) matAdj.get(i * columnes + j).add((i - 1) * columnes + j - 1);
                            if (i - 1 >= 0 && j + 1 < columnes)
                                matAdj.get(i * columnes + j).add((i - 1) * columnes + j + 1);
                            if (i - 1 >= 0 && j + 2 < columnes)
                                matAdj.get(i * columnes + j).add((i - 1) * columnes + j + 2);
                            if (j - 2 >= 0) matAdj.get(i * columnes + j).add(i * columnes + j - 2);
                            if (j + 2 < columnes) matAdj.get(i * columnes + j).add(i * columnes + j + 2);
                            if (i + 1 < files && j - 1 >= 0)
                                matAdj.get(i * columnes + j).add((i + 1) * columnes + j - 1);
                            if (i + 1 < files) matAdj.get(i * columnes + j).add((i + 1) * columnes + j);
                            if (i + 1 < files && j + 1 < columnes)
                                matAdj.get(i * columnes + j).add((i + 1) * columnes + j + 1);
                        }
                    }
                }
            }
        }
    }

    public static void imprimirMatAdj(){
        for (int i = 0; i < matAdj.size(); i++){
            System.out.print("per i = " + i + "  ");
            for (int j = 0; j < matAdj.get(i).size(); j++){
                System.out.print(matAdj.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] arg){

        llegirTaulell();
        generaMatAdj();
        imprimirMatAdj();
    }


}
