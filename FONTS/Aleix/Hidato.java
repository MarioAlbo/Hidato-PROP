import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;
import java.io.Serializable;

public abstract class Hidato implements Serializable{
    protected int idH;
    public String nickname;
    protected Taulell taulell;
    protected ArrayList <ArrayList<Integer> > matAdj; //id [i][j] = i*col + j
    protected String[][] mContingut;

    /**
     * Crea un Hidato a partir d'un Taulell
     * @param idh: identidicador del Hidato
     * @param nick: nom de l'usuari que crea el Hidato
     * @param t: Taulell del qual es crea el Hidato
     */
    public Hidato(int idh, String nick, Taulell t) { //CREAR mContingut A PARTIR DE TAULELL
        this.idH = idh;
        this.nickname = nick;
        this.mContingut = t.getmContingut();
        this.taulell = t;
        posarForats();
        posarNumeros();
        generaMatAdj();
    }

    public int getIdH() {return idH;}

    public String getNickname() {return nickname;}


    /**
     * Crea un Hidato y un Taulell de forma automatica a partit d'un paramentre de dificultat
     * @param idh: identifiacador del Hidato
     * @param nick: nom de l'usuari que crea el Hidato
     * @param dificultat: parametre de dificultad per crear el Taulell
     */

    public Hidato(int idh, String nick, int dificultat){ //CREAR mContingut AUTOMATICAMENT
        this.idH = idh;
        this.nickname = nick;
        String d;
        switch (dificultat) {
            case 1:
                d = "Q,C,3,3";
                break;
            case 2:
                d = "H,C,3,4";
                break;
            case 3:
                d = "T,C,4,5";
                break;
            case 4:
                d = "Q,CA,5,4";
                break;
            case 5:
                d = "H,C,5,5";
                break;
            case 6:
                d = "T,CA,5,6";
                break;
            case 7:
                d = "Q,C,6,6";
                break;
            case 8:
                d = "H,C,5,5";
                break;
            default:
                d = "T,C,7,7";
                break;
        }
        Taulell t = new Taulell(d);
        this.taulell = t;
        this.mContingut = t.getmContingut();
        generaAutomaticament();


    }

    /**
     * crea una matriu de contingut de forma random
     */
    protected void generaAutomaticament(){
        Random rand = new Random();
        boolean f = false;
        Integer cx1 = 0; //inicialitzacions que no es fan ervir pero necessaries pk compili
        Integer cy1 = 0;
        Integer cx2 = 0;
        Integer cy2 = 0;
        int intent = 1;
        while (!f) {
            //System.out.println("intent: "+ intent);
            //intent++;
            Integer numforats = 0;
            if (taulell.getTcela().equals("T") && taulell.getTadjacencia().equals("C")){

                int TCf = taulell.getFiles();
                int TCc = taulell.getColumnes();
                if (TCf%2 == 1 && TCc%2 == 1){
                    mContingut[TCf-1][0] = "#";
                    mContingut[TCf-1][TCc-1] = "#";
                    numforats = 2;
                }
                else if (TCf%2 == 1 && TCc%2 == 0){
                    mContingut[0][TCc-1] = "#";
                    mContingut[TCf-1][0] = "#";
                    numforats = 2;
                }
                else if (TCf%2 == 0 && TCc%2 == 0){
                    mContingut[0][TCc-1] = "#";
                    mContingut[TCf-1][TCc-1] = "#";
                    numforats = 2;
                }
                // no s'ha de posar forats en el cas de f->0 c->0

            }
            Integer ff = rand.nextInt((taulell.getFiles() * taulell.getColumnes())/3 + 2); //numero de forats que posarem
            //System.out.println("num forats: " + ff);
            /*
            for (int i = 0; i < ff; i++){
                Integer ff1 = rand.nextInt(taulell.getFiles());
                Integer ff2 = rand.nextInt(taulell.getColumnes());
                mContingut[ff1][ff2] = "#";
            } */
            generaMatAdj();
            cx1 = rand.nextInt(taulell.getFiles());
            cy1 = rand.nextInt(taulell.getColumnes());
            while (mContingut[cx1][cy1].equals("#")){
                cx1 = rand.nextInt(taulell.getFiles());
                cy1 = rand.nextInt(taulell.getColumnes());
            }
            mContingut[cx1][cy1] = "1";
            cx2 = rand.nextInt(taulell.getFiles());
            cy2 = rand.nextInt(taulell.getColumnes());
            while ((mContingut[cx2][cy2].equals("#")) || (mContingut[cx2][cy2].equals("1"))) {
                cx2 = rand.nextInt(taulell.getFiles());
                cy2 = rand.nextInt(taulell.getColumnes());
            }
            mContingut[cx2][cy2] = Integer.toString(taulell.getFiles() * taulell.getColumnes() - numforats);


            //imprimirMContingut();
            f = resol();
            if (!f){ // DESFER ELS CANVIS PER TORNAR A COMENÇAR
                for (int i = 0; i < taulell.getFiles(); i++){
                    for (int j = 0; j < taulell.getColumnes(); j++){
                        mContingut[i][j]="?";
                    }
                }
            }
        }

        ArrayList<Integer> posicions = new ArrayList<Integer>(); // x y n
        for (int i = 0; i < (taulell.getFiles() * taulell.getColumnes())/4; i++){
            Integer cx = rand.nextInt(taulell.getFiles());
            Integer cy = rand.nextInt(taulell.getColumnes());
            if (!(mContingut[cx][cy]).equals("#")){
                posicions.add(cx);
                posicions.add(cy);
                posicions.add(Integer.parseInt(mContingut[cx][cy]));
            }
        }
        for (int i = 0; i < taulell.getFiles(); i++) {
            for (int j = 0; j < taulell.getColumnes(); j++) {
                if(!(mContingut[i][j].equals("#"))) mContingut[i][j] = "?";
            }
        }
        mContingut[cx1][cy1] = "1";
        mContingut[cx2][cy2] = Integer.toString(taulell.getFiles() * taulell.getColumnes());
        for (int i = 0; i < posicions.size(); i = i + 3){
            mContingut[posicions.get(i)][posicions.get(i+1)] = Integer.toString(posicions.get(i+2));
        }
    }

    /**
     * crea una matriu de contingut a partir de la matriu d'adjacencies
     */
    abstract void generaMatAdj();

    /**
     * omple la matriu de contingut amb '*' a les coordenades indicades
     */
    public final void posarForats() {
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

    public void posarLliures() {
        System.out.println("posar lliures(?): coorX coordY ");
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

    /**
     * posa un numero a les coordenades indicades
     */
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
            boolean apareix = false;
            int reemplazarX = 0;
            int reemplazarY = 0;
            for (int i = 0; i < taulell.getFiles() && !(apareix); i++){
                for (int j = 0; j < taulell.getColumnes() && !(apareix); j++){
                    if (mContingut[i][j].equals(num)){
                        apareix = true;
                        reemplazarX = i;
                        reemplazarY = j;
                    }
                }
            }
            if (apareix){
                mContingut[coordX][coordY] = num;
                mContingut[reemplazarX][reemplazarY] = "?";
            }
            else {
                mContingut[coordX][coordY] = num;
            }
            s = teclado.nextLine();
        }
    }

    /**
     * mostra per pantalla la matriu d'adjacencies
     */
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

    /**
     * retorna una expressió booleana si el Hidato està resolt
     * @return: retorn un boolea indicant si l'Hodato està resolt
     */
    public boolean resol(){
        setup();
        return solve(start[0],start[1],1,0);
    }

    /**
     * indica la posició per começar a resoldre l'hidato (on es troba el '1')
     */
    private void setup(){

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

    /**
     * retorna el valor numeric d'una posició de la matriu de contingut si aquesta conté un numero en forma de String
     * @param s: String que volem comprobar si correspon a un número
     * @return: retorn el valor numeric del String si aquest és un numero i [0,-1,-2] si no ho és
     */
    private int pasaraenter(String s){
        if(s.equals("?")) return 0;
        else if(s.equals("#"))return -1;
        else if (s.equals("*"))return  -2;
        else return Integer.parseInt(s);
    }

    /**
     * indica si un Hidato està resolt correctament
     * @param r:
     * @param c
     * @param n
     * @param next
     * @return
     */
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

    /**
     * mostra per pantalla la matriu de contingut del Hidato
     */
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

    /**
     * indica si un Hidato és correcte
     * @return: retorn el valor booleà de validar el Hidato
     */
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

    public boolean comprovaNums(){
        Integer max = -1;
        Integer forats = 0;
        for (int i = 0; i < taulell.getFiles(); i++){
            for (int j = 0; j < taulell.getColumnes(); j++){
                if (mContingut[i][j].equals("#") || mContingut[i][j].equals("*")){
                    forats++;
                }
                else if (!(mContingut[i][j].equals("?"))){
                    if (Integer.parseInt(mContingut[i][j]) > max){
                        max = Integer.parseInt(mContingut[i][j]);
                    }
                }
            }
        }

        if (taulell.getFiles()*taulell.getColumnes() - forats < max) return false;
        else return true;

    }


    public void modifica(){
        Scanner teclado = new Scanner(System.in);
        String s = "";
        while (!s.equals("0")){
            System.out.println("OPCIONS:\n"
                    + "0 --> Sortir\n"
                    + "1 --> Posar forats\n"
                    + "2 --> Posar lliures\n"
                    + "3 --> Posar numeros\n");

            s = teclado.nextLine();
            switch(s){
                case "0":
                    break;
                case "1":
                    posarForats();
                    break;
                case "2":
                    posarLliures();
                    break;
                case "3":
                    posarNumeros();
            }
            boolean b = comprovaNums();
            if (!b){
                System.out.println("Hi ha masses forats! L'hidato no es pot resoldre");
                System.out.println("Has de treure algun forat o canviar el major numero");
            }
            else {
                String[][] mContingutAux = new String[taulell.getFiles()][taulell.getColumnes()];
                for (int i=0; i<taulell.getFiles(); i++){
                    for (int j=0; j<taulell.getColumnes(); j++){
                        mContingutAux[i][j] = this.mContingut[i][j];
                    }
                }

                b = resol();
                if (!b){
                    System.out.println("L'hidato que has fet no es pot resoldre");
                    this.mContingut = mContingutAux;
                }
                this.mContingut = mContingutAux;
            }
        }
    }
}