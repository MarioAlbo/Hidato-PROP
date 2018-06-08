//package domini;
public class Partida {
    private final int idP;
    private final String idplayer;
    private int temps;
    private static boolean acabat;
    private final Hidato hidato;
    private String [][] mC;
    private final String [][] mCoriginal;


    /**
     *Crea una instancia de Partida
     * @param id:identificador de la partida
     * @param jugador:Usuari que juga la partida
     * @param hi:Hidato sobre el que es juga la partida
     */
    public Partida(int id,String jugador, Hidato hi){
        idP = id;
        idplayer = jugador;
        temps = 0;
        acabat = false;
        hidato = hi;
        int sx = hi.mContingut.length;
        int sy = hi.mContingut[0].length;
        mCoriginal = new String [sx][sy];
        mC = new String [sx][sy];
        clonarMatriu(mCoriginal,hi.mContingut);
        clonarMatriu(mC,hi.mContingut);
    }

    /////////////////////////////////////////////////////////////////
    /* * * * * * * * * *  G E T T E R S  * * * * * * * * * * * * * */
    /////////////////////////////////////////////////////////////////

    /**
     * Dona el atribut idP
     * @return
     * @return: retorna el valor del atribut idP de la instacia de partida
     */
    public int getIdP() {
        return idP;
    }

    /**
     * Dona el atribut idplayer
     * @return
     * @return: retorna el id del jugar de la partida
     */
    public String getIdplayer() {
        return idplayer;
    }

    /**
     * dona el temps de la partida
     * @return
     * @return: retorna el temps de joc de la partida
     */
    public int getTemps() {
        return temps;
    }

    public void setTemps(int t){
        temps = t;
    }

    /**
     * retorna el valoor booleà del estat de la partida
     * @return
     * @return: retorna l'atribut getAcabat
     */
    public static boolean getAcabat() {
        return acabat;
    }

    /**
     * Canvia el estat de la partida a acabat i imprimeix el temps de la partida
     */
    public void acabar(){
        acabat=true;
    }

    public int getIdHidato(){
        return hidato.getIdH();
    }

    public String[][] getMC(){
        return mC;
    }



    /**
     * colana la de Strings que es passa per paramentre
     * @param a: Matriu a copiar
     */
    public void clonarMatriu(String[][]b, String[][] a){
        for(int i = 0; i < a.length ; i++) {
            for (int j = 0; j < a[0].length; j++) {
                b[i][j] = a[i][j];
            }
        }
    }

    /**
     * Col·loca un numero a la matriu de contingut
     * @param x: Component X de la coordenada on posar el numero
     * @param y: Component Y de la coordenada on posar el numero
     * @param n: Número que es col·loca a la coordenada (X,Y)
     */
    public void posarNum(int x, int y, String n){
        mC[x][y] = n;
    }

    /**
     * Borra un numero de la matriu de contingut
     * @param x: Component X de la coordenada on borrar el numero
     * @param y: Component X de la coordenada on borrar el numero
     */
    public void borrarNum(int x, int y){
        String c = mCoriginal[x][y];
        if(c.equals("#") || isNumeric(c) || c.equals("*")) System.out.println(
                "No es pot borrar perque hi ha:("+c+")");
        else {hidato.mContingut[x][y] = "?";hidato.imprimirMContingut();}
    }

    public void ajuda(){

    }

    /**
     * Imprimeix la matriu de contingut si l'Hidato està resolt. Altrament treu un missatge d’error.
     */
    public String[][] resol_hidato(boolean b){
        b = hidato.resol(mC);
        return mC;
    }

    /**
     * Comproba si el hidato està resolt correctament
     */
    public boolean validar(){
        boolean b;
        if(hidato.validar(mC)){
            b=true;
            this.acabar();
        }
        else b=false;
        return b;
    }

    /**
     * comproba si el String del paramentre correspon a un número
     * @param cadena: String a comprobar
     * @return: retorna el valor booleà de si cadena correspon un valor numeric
     */
    private boolean isNumeric(String cadena) {
        boolean resultado;
        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }

    /**
     * Dona el hidato de la partida
     * @return: retorn el atribut hidato de la instancia de Partida
     */
    public Hidato getHidato() {
        return hidato;
    }
}
