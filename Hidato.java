import java.util.ArrayList;

import java.util.Scanner;
import java.lang.String;


public class Hidato {
    public int idH,iduser;
    public static Taulell taulell;
    public char dificultat;
    public static ArrayList <ArrayList<Integer> > matAdj; //id [i][j] = i*col + j
    public static String[][] mContingut;


    public Hidato(int idh, int idusr, Taulell topo) {
        this.idH = idh;
        this.iduser = idusr;
        this.mContingut = topo.mContingut;
        this.matAdj = new ArrayList <ArrayList<Integer> >(topo.columnes*topo.files);
        if (topo.columnes*topo.files < 36) { //aprox 6 * 6
            this.dificultat = 'F'; //FACIL
        }
        else if (topo.columnes*topo.files < 64) { //aprox 8 * 8
            this.dificultat = 'N'; //NORMAL/INTERMIX
        }
        else if (topo.columnes*topo.files < 100) { //aprox 10 * 10
            this.dificultat = 'D';  //DIFICIL
        }
    }


    public static void generaMatAdj(){

        if (taulell.Tcela.equals("Q")){
            for (int i = 0; i < taulell.files; i++) {
                for (int j = 0; j < taulell.columnes; j++) {
                    matAdj.add(new ArrayList<Integer>());
                    if (taulell.mContingut[i][j] != "#" && taulell.mContingut[i][j] != "*") {
                        if (j - 1 >= 0 && taulell.mContingut[i][j - 1] != "#" && taulell.mContingut[i][j - 1] != "*")
                            matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j - 1);
                        if (j + 1 < taulell.columnes && taulell.mContingut[i][j + 1] != "#" && taulell.mContingut[i][j + 1] != "*")
                            matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j + 1);
                        if (i - 1 >= 0 && taulell.mContingut[i - 1][j] != "#" && taulell.mContingut[i - 1][j] != "*")
                            matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j);
                        if (i + 1 < taulell.files && taulell.mContingut[i + 1][j] != "#" && taulell.mContingut[i + 1][j] != "*") {
                            matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j);
                        }
                    }
                }
            }
            if (taulell.Tadjacecnia.equals("CA")) {
                for (int i = 0; i < taulell.files; i++){
                    for (int j = 0; j < taulell.columnes; j++){
                        if (taulell.mContingut[i][j] != "#" && taulell.mContingut[i][j] != "*"){
                            if (j - 1 >= 0 && i - 1 >= 0 && taulell.mContingut[i - 1][j - 1] != "#" && taulell.mContingut[i - 1][j - 1] != "*")
                                matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j - 1);
                            if (j + 1 < taulell.columnes && i - 1 >= 0 && taulell.mContingut[i - 1][j + 1] != "#" && taulell.mContingut[i - 1][j + 1] != "*")
                                matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j + 1);
                            if (j + 1 < taulell.columnes && i + 1 < taulell.files && taulell.mContingut[i + 1][j + 1] != "#" && taulell.mContingut[i + 1][j + 1] != "*")
                                matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j + 1);
                            if (j - 1 >= 0 && i + 1 < taulell.files && taulell.mContingut[i + 1][j - 1] != "#" && taulell.mContingut[i + 1][j - 1] != "*")
                                matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j - 1);
                        }
                    }
                }
            }

        }
        else if (taulell.Tcela.equals("H")) {
            for (int i = 0; i < taulell.files; i++) {
                for (int j = 0; j < taulell.columnes; j++) {
                    matAdj.add(new ArrayList<Integer>());
                    if (taulell.mContingut[i][j] != "#" && taulell.mContingut[i][j] != "*") {
                        if (i % 2 == 1) {
                            if (i - 1 >= 0 && taulell.mContingut[i - 1][j] != "#" && taulell.mContingut[i - 1][j] != "*")
                                matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j);
                            if (i - 1 >= 0 && j + 1 < taulell.columnes && taulell.mContingut[i - 1][j + 1] != "#" && taulell.mContingut[i - 1][j + 1] != "*")
                                matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j + 1);
                            if (j - 1 >= 0 && taulell.mContingut[i][j - 1] != "#" && taulell.mContingut[i][j - 1] != "*")
                                matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j - 1);
                            if (j + 1 < taulell.columnes && taulell.mContingut[i][j + 1] != "#" && taulell.mContingut[i][j + 1] != "*")
                                matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j + 1);
                            if (i + 1 < taulell.files && taulell.mContingut[i + 1][j] != "#" && taulell.mContingut[i + 1][j] != "*")
                                matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j);
                            if (i + 1 < taulell.files && j + 1 < taulell.columnes && taulell.mContingut[i + 1][j + 1] != "#" && taulell.mContingut[i + 1][j + 1] != "*")
                                matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j + 1);
                        }
                        else {
                            if (i - 1 >= 0 && taulell.mContingut[i - 1][j] != "#" && taulell.mContingut[i - 1][j] != "*")
                                matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j);
                            if (i - 1 >= 0 && j - 1 >= 0 && taulell.mContingut[i - 1][j - 1] != "#" && taulell.mContingut[i + -1][j - 1] != "*")
                                matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j - 1);
                            if (j - 1 >= 0 && taulell.mContingut[i + 1][j] != "#" && taulell.mContingut[i + 1][j] != "*")
                                matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j - 1);
                            if (j + 1 < taulell.columnes && taulell.mContingut[i][j + 1] != "#" && taulell.mContingut[i][j + 1] != "*")
                                matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j + 1);
                            if (i + 1 < taulell.files && taulell.mContingut[i + 1][j] != "#" && taulell.mContingut[i + 1][j] != "*")
                                matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j);
                            if (i + 1 < taulell.files && j - 1 >= 0 && taulell.mContingut[i + 1][j - 1] != "#" && taulell.mContingut[i + 1][j - 1] != "*")
                                matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j - 1);
                        }
                    }
                }
            }
        }
        else if (taulell.Tcela.equals("T")){
            for (int i = 0; i < taulell.files; i++) {
                for (int j = 0; j < taulell.columnes; j++) {
                    matAdj.add(new ArrayList<Integer>());
                    if (taulell.mContingut[i][j] != "#" && taulell.mContingut[i][j] != "*") {
                        if (j - 1 >= 0 && taulell.mContingut[i][j - 1] != "#" && taulell.mContingut[i][j - 1] != "*")
                            matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j - 1);
                        if (j + 1 < taulell.columnes && taulell.mContingut[i][j + 1] != "#" && taulell.mContingut[i][j + 1] != "*")
                            matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j + 1);
                        if ((i + j) % 2 == 0) {
                            if (i + 1 < taulell.files && taulell.mContingut[i + 1][j] != "#" && taulell.mContingut[i + 1][j] != "*")
                                matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j);
                        }
                        else {
                            if (i - 1 >= 0 && taulell.mContingut[i - 1][j] != "#" && taulell.mContingut[i - 1][j] != "*")
                                matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j);
                        }
                    }
                }
            }
            if (taulell.Tadjacecnia.equals("CA")){
                for (int i = 0; i < taulell.files; i++) {
                    for (int j = 0; j < taulell.columnes; j++) {
                        if (taulell.mContingut[i][j] != "#" && taulell.mContingut[i][j] != "*") {
                            if ((i + j) % 2 == 0) {
                                if (i - 1 >= 0 && j - 1 >= 0 && taulell.mContingut[i - 1][j - 1] != "#" && taulell.mContingut[i - 1][j - 1] != "*")
                                    matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j - 1);
                                if (i - 1 >= 0 && taulell.mContingut[i - 1][j] != "#" && taulell.mContingut[i - 1][j] != "*")
                                    matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j);
                                if (i - 1 >= 0 && j + 1 < taulell.columnes && taulell.mContingut[i - 1][j + 1] != "#" && taulell.mContingut[i - 1][j + 1] != "*")
                                    matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j + 1);
                                if (j - 2 >= 0 && taulell.mContingut[i][j - 2] != "#" && taulell.mContingut[i][j - 2] != "*")
                                    matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j - 2);
                                if (j + 2 < taulell.columnes && taulell.mContingut[i + 2][j] != "#" && taulell.mContingut[i + 2][j] != "*")
                                    matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j + 2);
                                if (i + 1 < taulell.files && j - 2 >= 0 && taulell.mContingut[i + 1][j - 2] != "#" && taulell.mContingut[i + 1][j - 2] != "*")
                                    matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j - 2);
                                if (i + 1 < taulell.files && j - 1 >= 0 && taulell.mContingut[i + 1][j - 1] != "#" && taulell.mContingut[i + 1][j - 1] != "*")
                                    matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j - 1);
                                if (i + 1 < taulell.files && j + 1 < taulell.columnes && taulell.mContingut[i + 1][j + 1] != "#" && taulell.mContingut[i + 1][j + 1] != "*")
                                    matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j + 1);
                                if (i + 1 < taulell.files && j + 2 < taulell.columnes && taulell.mContingut[i + 1][j + 2] != "#" && taulell.mContingut[i + 1][j + 2] != "*")
                                    matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j + 2);
                            }
                            else {
                                if (i - 1 >= 0 && j - 2 >= 0 && taulell.mContingut[i - 1][j - 2] != "#" && taulell.mContingut[i - 1][j - 2] != "*")
                                    matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j - 2);
                                if (i - 1 >= 0 && j - 1 >= 0 && taulell.mContingut[i - 1][j - 1] != "#" && taulell.mContingut[i - 1][j - 1] != "*")
                                    matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j - 1);
                                if (i - 1 >= 0 && j + 1 < taulell.columnes && taulell.mContingut[i - 1][j + 1] != "#" && taulell.mContingut[i - 1][j + 1] != "*")
                                    matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j + 1);
                                if (i - 1 >= 0 && j + 2 < taulell.columnes && taulell.mContingut[i - 1][j + 2] != "#" && taulell.mContingut[i - 1][j + 2] != "*")
                                    matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j + 2);
                                if (j - 2 >= 0 && taulell.mContingut[i][j - 2] != "#" && taulell.mContingut[i][j - 2] != "*")
                                    matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j - 2);
                                if (j + 2 < taulell.columnes && taulell.mContingut[i][j + 2] != "#" && taulell.mContingut[i][j + 2] != "*")
                                    matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j + 2);
                                if (i + 1 < taulell.files && j - 1 >= 0 && taulell.mContingut[i + 1][j - 1] != "#" && taulell.mContingut[i + 1][j - 1] != "*")
                                    matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j - 1);
                                if (i + 1 < taulell.files && taulell.mContingut[i + 1][j] != "#" && taulell.mContingut[i + 1][j] != "*")
                                    matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j);
                                if (i + 1 < taulell.files && j + 1 < taulell.columnes && taulell.mContingut[i + 1][j + 1] != "#" && taulell.mContingut[i + 1][j + 1] != "*")
                                    matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void posarForats() {
        System.out.println("posar forats(*): coorX coordY ");
        System.out.println("per acabar: exit");
        Scanner teclado = new Scanner(System.in);
        String s = teclado.nextLine();
        while (!s.equals("exit")) {
            String[] h = s.split(" ");
            int coordX =  Integer.parseInt(h[0]);
            int coordY =  Integer.parseInt(h[1]);
            if (mContingut[coordX][coordY] != "#"){
                mContingut[coordX][coordY] = "*";
            }
            else {
                System.out.println("No pots posar un * en un forat!");
            }
            s = teclado.nextLine();
        }
    }

    public static void posarNumeros() {
        System.out.println("posar numeros: coorX coordY num ");
        System.out.println("per acabar: exit");
        Scanner teclado = new Scanner(System.in);
        String s = teclado.nextLine();
        while (!s.equals("exit")) {
            String[] h = s.split(" ");
            int coordX =  Integer.parseInt(h[0]);
            int coordY =  Integer.parseInt(h[1]);
            String num = h[2];
            if (mContingut[coordX][coordY] != "#"){
                mContingut[coordX][coordY] = num;
            }
            else {
                System.out.println("No pots posar un numero en un forat!");
            }
            s = teclado.nextLine();
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
        taulell=new Taulell();
        taulell.llegirTaulell();
        Hidato hidato = new Hidato(1,1, taulell);
        hidato.posarForats();
        hidato.posarNumeros();
        hidato.generaMatAdj();
        hidato.imprimirMatAdj();
        taulell.imprimirMContingut();

    }

}
