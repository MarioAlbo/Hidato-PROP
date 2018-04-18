import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Partida {
    private int idP;
    private String idplayer;
    private int idHidato;
    private Tiempo temps;
    private static boolean acabat;

    /////////////////////////////////////////////////////////////////
    /* * * * * * * * * *  G E T T E R S  * * * * * * * * * * * * * */
    /////////////////////////////////////////////////////////////////

    public int getIdP() {
        return idP;
    }

    public String getIdplayer() {
        return idplayer;
    }

    public int getIdHidato() {
        return idHidato;
    }

    public int getTemps() {
        return temps.time;
    }

    public static boolean getAcabat() {
        return acabat;
    }

    ////////////////////////////////////////////////////////////////
    /* * * * * * * * * * *  H I D A T O * * * * * * * * * * * * * */
    ////////////////////////////////////////////////////////////////

    private String[][] hidato;
    private String tipocelda;
    private String tipoadyacencia;
    private String[][] hidatooriginal;

    private int[] given, start;

    public void llegirHidato(){
        System.out.println("Introdueix hidato:");
        Scanner teclado = new Scanner(System.in);
        String s = teclado.nextLine();
        String h[]= s.split(",");
        tipocelda = h[0];
        tipoadyacencia = h[1];
        hidato = new String[Integer.parseInt(h[2])][Integer.parseInt(h[3])];
        hidatooriginal = new String[Integer.parseInt(h[2])][Integer.parseInt(h[3])];
        for(int i=0;i<hidato.length;i++) {
            String c[] = teclado.nextLine().split(",");
            for(int j=0; j<hidato[0].length;j++)hidatooriginal[i][j] = hidato[i][j] = c[j];
        }

    }

    public void imprimirHidato(){
        for(int i = 0; i < hidato.length;i++) {
            for (int j = 0; j < hidato[0].length; j++) {
                System.out.print(hidato[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public boolean resol(){
        setup();
        return solve(start[0],start[1],1,0);
    }

    public void setup(){

        int filas = hidato.length;
        int columnas =  hidato[0].length;

        List<Integer> list = new ArrayList<>(filas *columnas);

        for(int i = 0; i < filas;i++) {
            for (int j = 0; j <columnas; j++) {
                String cell = hidato[i][j];
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

    private boolean solve(int r, int c, int n, int next) {

        String val = hidato[r][c];
        int back = pasaraenter(val);

        if (n > given[given.length - 1])return true;

        if (!(val).equals("?") && back != n)return false;

        if (val.equals("?") && given[next] == n) return false;

        if (back == n)
            next++;

        hidato[r][c] = ""+n;

        if(tipocelda.equals("Q")){
            if(tipoadyacencia.equals("CA")){
                for (int i = -1; i < 2; i++)
                    for (int j = -1; j < 2; j++)
                        if (((r + i >= 0) && (c + j>= 0)) && ((r + i < hidato.length) && (c + j< hidato[0].length))&& solve(r + i, c + j, n + 1, next))
                            return true;
            }
            else if(tipoadyacencia.equals("C")){
                if ((r - 1 >= 0) && solve(r - 1, c , n + 1, next))return true;
                if ((c - 1 >= 0) && solve(r , c - 1, n + 1, next))return true;
                if ((r + 1 < hidato.length) && solve(r + 1, c, n + 1, next))return true;
                if ((c + 1 < hidato[0].length) && solve(r , c + 1, n + 1, next))return true;
            }
        }
        else if(tipocelda.equals("T")){
            if(tipoadyacencia.equals("CA")) {
                if( r%2 == 0 && c%2 == 0) {
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if ((((r - 1 >= 0) && (c + j >= 0)) && (c + j < hidato[0].length)) && solve(r - 1, c + j, n + 1, next))
                                return true;
                        }
                        for (int j = -2; j < 3; j++)
                            if (((r + i >= 0) && (c + j >= 0)) && ((r + i < hidato.length) && (c + j < hidato[0].length)) && solve(r + i, c + j, n + 1, next))
                                return true;
                    }
                }
                else {
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (((c + j >= 0)) && ((r + 1 < hidato.length) && (c + j < hidato[0].length)) && solve(r + 1, c + j, n + 1, next))
                                return true;
                        }
                        for (int j = -2; j < 3; j++)
                            if (((r + i >= 0) && (c + j >= 0)) && ((r + i < hidato.length) && (c + j < hidato[0].length)) && solve(r + i, c + j, n + 1, next))
                                return true;
                    }
                }
            }
            else if(tipoadyacencia.equals("C")) {
                if(r%2 == 0 && c%2 == 0 ||(r%2 != 0 && c%2 != 0)) {
                    for (int j = -1; j < 2; j++){
                        if (((c + j >= 0) && (c + j < hidato[0].length)) && solve(r , c + j, n + 1, next))
                            return true;
                    }
                    if (((r + 1 < hidato.length)) && solve(r + 1, c , n + 1, next))
                        return true;
                }
                else {
                    for (int j = -1; j < 2; j++) {
                        if (((c + j >= 0) && (c + j < hidato[0].length)) && solve(r , c + j, n + 1, next))
                            return true;
                    }
                    if (((r - 1 >= 0) && (r - 1 < hidato.length)) && solve(r - 1, c , n + 1, next))
                        return true;
                }
            }
        }

        else if(tipocelda.equals("H")){
            if(r%2==0) {
                for (int i = -1; i < 2; i++)
                    if(i==0) {
                        for (int j = -1; j < 2; j++)
                            if ((c + j >= 0) && ((r + i < hidato.length) && (c + j < hidato[0].length)) && solve(r + i, c + j, n + 1, next))
                                return true;
                    }
                    else{
                        for (int j = -1; j < 1; j++)
                            if (((r + i >= 0) && (c + j >= 0)) && ((r + i < hidato.length) && (c + j < hidato[0].length)) && solve(r + i, c + j, n + 1, next))
                                return true;
                    }
            }
            else {
                for (int i = -1; i < 2; i++)
                    if(i==0) {
                        for (int j = -1; j < 2; j++)
                            if (((c + j >= 0)) && ((r + i < hidato.length) && (c + j < hidato[0].length)) && solve(r + i, c + j, n + 1, next))
                                return true;
                    }
                    else{
                        for (int j = 0; j < 2; j++)
                            if ((r + i >= 0) && ((r + i < hidato.length) && (c + j < hidato[0].length)) && solve(r + i, c + j, n + 1, next))
                                return true;
                    }
            }
        }

        if(back == 0)hidato[r][c] = "?";
        else if(back == -1) hidato[r][c] = "#";
        else if (back == -2) hidato[r][c] = "*";
        else hidato[r][c] = ""+back;

        return false;
    }


    /////////////////////////////////////////////////////////////////
    /* * * * * * * * * *  P A R T I D A  * * * * * * * * * * * * * */
    /////////////////////////////////////////////////////////////////
    public void acabar(){
        acabat=true;
    }

    public Partida(int id,String jugador, int idHi){
        idP = id;
        idplayer = jugador;
        idHidato = idHi;
        temps = new Tiempo();
        acabat = false;
        this.llegirHidato();
    }


    public void PosarNum(int x, int y, String n){
        String c = hidato[hidato.length-1-y][x];
        if(c.equals("#") || isNumeric(c) || c.equals("*")) System.out.println("No es pot col·locar perque hi ha:("+c+")");
        else {hidato[hidato.length-1-y][x] = n;imprimirHidato();}
    }

    public void borrarNum(int x, int y){
        String c = hidatooriginal[hidatooriginal.length-1-y][x];
        if(c.equals("#") || isNumeric(c) || c.equals("*")) System.out.println("No es pot borrar perque hi ha:("+c+")");
        else {hidato[hidato.length-1-y][x] = "?";imprimirHidato();}
    }

    public void ajuda(){

        int filas = hidato.length;
        int columnas = hidato[0].length;
        List<Integer> valors_actuals = new ArrayList<>(filas*columnas);

        for(int i = 0; i < filas;i++) {
            for (int j = 0; j <columnas; j++) {
                String cell = hidato[i][j];
                switch (cell) {
                    case "?":
                        break;
                    case "*":
                        break;
                    case "#":
                        break;
                    default:
                        int val = Integer.parseInt(cell);
                        valors_actuals.add(val);
                }
            }
        }

        Collections.sort(valors_actuals);
        int [] valors = new int[valors_actuals.size()];
        for (int i = 0; i < valors.length; i++)
            valors[i] = valors_actuals.get(i);
    }

    /////////////////////////////////////////////////////////////////
    /* * * * * * * * * *   V A R I A S   * * * * * * * * * * * * * */
    /////////////////////////////////////////////////////////////////

    public boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

    private int pasaraenter(String s){
        if(s.equals("?")) return 0;
        else if(s.equals("#"))return -1;
        else if (s.equals("*"))return  -2;
        else return Integer.parseInt(s);
    }

}
