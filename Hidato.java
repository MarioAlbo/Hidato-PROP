import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
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
        generaMatAdj();
    }

    public Hidato(int idh, String nick, int dificultat){ //CREAR mContingut AUTOMATICAMENT
        this.idH = idh;
        this.nickname = nick;
        String d;
        if (dificultat == 1){
            d = "Q,C,3,3";
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

        //AQUI S'HAURAN DE FER COSES AMB LA MCONTINGUT, QUE NECESSITARAN EL CODI DE LA IA

        //de moment ho deiemaixi
        this.mContingut = t.mContingut;
    }


    public static void generaMatAdj(){
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

    private static int[] given, start;

    public static boolean resol(){
        setup();
        return solve(start[0],start[1],1,0);
    }

    public static void setup(){

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

    private static int pasaraenter(String s){
        if(s.equals("?")) return 0;
        else if(s.equals("#"))return -1;
        else if (s.equals("*"))return  -2;
        else return Integer.parseInt(s);
    }

    private static boolean solve(int r, int c, int n, int next) {

        String val = mContingut[r][c];
        int back = pasaraenter(val);

        if (n > given[given.length - 1])return true;

        if (!(val).equals("?") && back != n)return false;

        if (val.equals("?") && given[next] == n) return false;

        if (back == n)
            next++;

        mContingut[r][c] = ""+n;

        if(taulell.Tcela.equals("Q")){
            if(taulell.Tadjacecnia.equals("CA")){
                for (int i = -1; i < 2; i++)
                    for (int j = -1; j < 2; j++)
                        if (((r + i >= 0) && (c + j>= 0)) && ((r + i < mContingut.length) && (c + j< mContingut[0].length))&& solve(r + i, c + j, n + 1, next))
                            return true;
            }
            else if(taulell.Tadjacecnia.equals("C")){
                if ((r - 1 >= 0) && solve(r - 1, c , n + 1, next))return true;
                if ((c - 1 >= 0) && solve(r , c - 1, n + 1, next))return true;
                if ((r + 1 < mContingut.length) && solve(r + 1, c, n + 1, next))return true;
                if ((c + 1 < mContingut[0].length) && solve(r , c + 1, n + 1, next))return true;
            }
        }
        else if(taulell.Tcela.equals("T")){
            if(taulell.Tadjacecnia.equals("CA")) {
                if( r%2 == 0 && c%2 == 0) {
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if ((((r - 1 >= 0) && (c + j >= 0)) && (c + j < mContingut[0].length)) && solve(r - 1, c + j, n + 1, next))
                                return true;
                        }
                        for (int j = -2; j < 3; j++)
                            if (((r + i >= 0) && (c + j >= 0)) && ((r + i < mContingut.length) && (c + j < mContingut[0].length)) && solve(r + i, c + j, n + 1, next))
                                return true;
                    }
                }
                else {
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (((c + j >= 0)) && ((r + 1 < mContingut.length) && (c + j < mContingut[0].length)) && solve(r + 1, c + j, n + 1, next))
                                return true;
                        }
                        for (int j = -2; j < 3; j++)
                            if (((r + i >= 0) && (c + j >= 0)) && ((r + i < mContingut.length) && (c + j < mContingut[0].length)) && solve(r + i, c + j, n + 1, next))
                                return true;
                    }
                }
            }
            else if(taulell.Tcela.equals("C")) {
                if(r%2 == 0 && c%2 == 0 ||(r%2 != 0 && c%2 != 0)) {
                    for (int j = -1; j < 2; j++){
                        if (((c + j >= 0) && (c + j < mContingut[0].length)) && solve(r , c + j, n + 1, next))
                            return true;
                    }
                    if (((r + 1 < mContingut.length)) && solve(r + 1, c , n + 1, next))
                        return true;
                }
                else {
                    for (int j = -1; j < 2; j++) {
                        if (((c + j >= 0) && (c + j < mContingut[0].length)) && solve(r , c + j, n + 1, next))
                            return true;
                    }
                    if (((r - 1 >= 0) && (r - 1 < mContingut.length)) && solve(r - 1, c , n + 1, next))
                        return true;
                }
            }
        }

        else if(taulell.Tcela.equals("H")){
            if(r%2==0) {
                for (int i = -1; i < 2; i++)
                    if(i==0) {
                        for (int j = -1; j < 2; j++)
                            if ((c + j >= 0) && ((r + i < mContingut.length) && (c + j < mContingut[0].length)) && solve(r + i, c + j, n + 1, next))
                                return true;
                    }
                    else{
                        for (int j = -1; j < 1; j++)
                            if (((r + i >= 0) && (c + j >= 0)) && ((r + i < mContingut.length) && (c + j < mContingut[0].length)) && solve(r + i, c + j, n + 1, next))
                                return true;
                    }
            }
            else {
                for (int i = -1; i < 2; i++)
                    if(i==0) {
                        for (int j = -1; j < 2; j++)
                            if (((c + j >= 0)) && ((r + i < mContingut.length) && (c + j < mContingut[0].length)) && solve(r + i, c + j, n + 1, next))
                                return true;
                    }
                    else{
                        for (int j = 0; j < 2; j++)
                            if ((r + i >= 0) && ((r + i < mContingut.length) && (c + j < mContingut[0].length)) && solve(r + i, c + j, n + 1, next))
                                return true;
                    }
            }
        }

        if(back == 0)mContingut[r][c] = "?";
        else if(back == -1) mContingut[r][c] = "#";
        else if (back == -2) mContingut[r][c] = "*";
        else mContingut[r][c] = ""+back;

        return false;
    }

    public static void imprimirMContingut(){
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
        taulell = new Taulell();
        Hidato hidato = new Hidato(1,"", taulell);
        hidato.posarForats();
        hidato.posarNumeros();
        hidato.generaMatAdj();
        hidato.imprimirMatAdj();
        taulell.imprimirMContingut();
        hidato.imprimirMatAdj();
        imprimirMContingut();
        resol();
        imprimirMContingut();
    }

}
