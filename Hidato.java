import java.util.ArrayList;

import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;

public class Hidato {
    public int idH;
    public String nickname;
    public static Taulell taulell;
    public static ArrayList <ArrayList<Integer> > matAdj; //id [i][j] = i*col + j
    public static String[][] mContingut;


    public Hidato(int idh, String nick, Taulell t) { //CREAR mContingut A PARTIR DE TAULELL
        this.idH = idh;
        this.nickname = nick;
        this.mContingut = t.mContingut;
        this.taulell = t;
        posarForats();
        posarNumeros();
        generaMatAdj();
    }

    public Hidato(int idh, String nick, int dificultat){ //CREAR mContingut AUTOMATICAMENT
        this.idH = idh;
        this.nickname = nick;
        String d;
        if (dificultat == 1){
            d = "Q,CA,3,3";
        }
        else if (dificultat == 2){
            d = "Q,CA,4,4";
        }
        else if (dificultat == 3){
            d = "H,C,4,5";
        }
        else if (dificultat == 4){
            d = "T,C,5,5";
        }
        else {
            d = "T,CA,6,6";
        }
        Taulell t = new Taulell(d);
        this.taulell = t;
        this.mContingut = t.mContingut;
        generaMatAdj();
        generaAutomaticament();

    }

    private void generaAutomaticament(){
        Random rand = new Random();
        boolean f = false;
        Integer cx1 = 0;
        Integer cy1 = 0;
        Integer cx2 = 0;
        Integer cy2 = 0;
        while (!f) {
            cx1 = rand.nextInt(taulell.files);
            cy1 = rand.nextInt(taulell.columnes);
            mContingut[cx1][cy1] = "1";
            cx2 = rand.nextInt(taulell.files);
            cy2 = rand.nextInt(taulell.columnes);
            while (cx1 == cx2 && cy1 == cy2) {
                cx2 = rand.nextInt(taulell.files);
                cy2 = rand.nextInt(taulell.columnes);
            }

            mContingut[cx2][cy2] = Integer.toString(taulell.files * taulell.columnes);
            f = resol();
        }
        ArrayList<Integer> posicions = new ArrayList<Integer>(); // x y n
        for (int i = 0; i < (taulell.files * taulell.columnes)/4; i++){
            Integer cx = rand.nextInt(taulell.files);
            Integer cy = rand.nextInt(taulell.columnes);
            posicions.add(cx);
            posicions.add(cy);
            posicions.add(Integer.parseInt(mContingut[cx][cy]));
        }
        for (int i = 0; i < taulell.files; i++) {
            for (int j = 0; j < taulell.columnes; j++) {
                mContingut[i][j] = "?";
            }
        }

        mContingut[cx1][cy1] = "1";
        mContingut[cx2][cy2] = Integer.toString(taulell.files * taulell.columnes);
        for (int i = 0; i < posicions.size(); i = i + 3){
            mContingut[posicions.get(i)][posicions.get(i+1)] = Integer.toString(posicions.get(i+2));
        }
    }

    public void generaMatAdj(){
        matAdj = new ArrayList <ArrayList<Integer> >(taulell.columnes*taulell.files);

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

    public void posarForats() {
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

    public void posarNumeros() {
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

    public void imprimirMatAdj(){
        for (int i = 0; i < matAdj.size(); i++){
            System.out.print("per i = " + i + "  ");
            for (int j = 0; j < matAdj.get(i).size(); j++){
                System.out.print(matAdj.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    private int[] given, start;

    public boolean resol(){
        setup();
        return solve(start[0],start[1],1,0);
    }

    public void setup(){

        int filas = mContingut.length;
        int columnas =  mContingut[0].length;

        List<Integer> list = new ArrayList<>(filas *columnas);

        for(int i = 0; i < filas;i++) {
            for (int j = 0; j <columnas; j++) {
                String cell = mContingut[i][j];
                switch (cell) {
                    case "?":
                        break;
                    case "*":
                        break;
                    case "#":
                        break;
                    default:
                        int val = Integer.parseInt(cell);
                        list.add(val);
                        if (val == 1) start = new int[]{i, j};
                }
            }
        }

        Collections.sort(list);
        given = new int[list.size()];
        for (int i = 0; i < given.length; i++)
            given[i] = list.get(i);

    }

    private int pasaraenter(String s){
        if(s.equals("?")) return 0;
        else if(s.equals("#"))return -1;
        else if (s.equals("*"))return  -2;
        else return Integer.parseInt(s);
    }

    private boolean solve(int r, int c, int n, int next) {

        String val = mContingut[r][c];
        int back = pasaraenter(val);

        if (n > given[given.length - 1])return true;

        if (!(val).equals("?") && back != n)return false;

        if (val.equals("?") && given[next] == n) return false;

        if (back == n)
            next++;

        mContingut[r][c] = ""+n;

        int t = mContingut[r].length;
        int pos = r*t + c;

        for(int i = 0; i < matAdj.get(pos).size(); i++) {
            int nc = matAdj.get(pos).get(i);
            if (solve(nc/t,nc%t ,n + 1, next))return true;
        }

        if(back == 0)mContingut[r][c] = "?";
        else if(back == -1) mContingut[r][c] = "#";
        else if (back == -2) mContingut[r][c] = "*";
        else mContingut[r][c] = ""+back;

        return false;
    }

    public void imprimirMContingut(){
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

    public boolean validar(){
        int files = mContingut.length;
        int columnes = mContingut[0].length;
        if(start.length == 0)setup();
        int pos = start[0]*columnes + start[1];
        for(int i = 2; i <= files*columnes; i++){
            boolean b = false;
            for(int j = 0; j < matAdj.get(pos).size() && !b; j++){
                int p = matAdj.get(pos).get(j);
                if((""+i).equals(mContingut[p/columnes][p%columnes])){
                    b = true;
                    pos = p;
                }
            }
            if(!b)return false;
        }
        return true;
    }
}
