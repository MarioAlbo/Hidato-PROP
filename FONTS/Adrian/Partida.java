public class Partida {
    private final int idP;
    private final String idplayer;
    private final Tiempo temps;
    private static boolean acabat;
    private final Hidato hidato;
    private final String [][] mCoriginal;


    /////////////////////////////////////////////////////////////////
    /* * * * * * * * * *  G E T T E R S  * * * * * * * * * * * * * */
    /////////////////////////////////////////////////////////////////

    /**
     * Dona el atribut idP
     * @return: retorna el valor del atribut idP de la instacia de partida
     */
    public int getIdP() {
        return idP;
    }

    /**
     * Dona el atribut idplayer
     * @return: retorna el id del jugar de la partida
     */
    public String getIdplayer() {
        return idplayer;
    }

    /**
     * dona el temps de la partida
     * @return: retorna el temps de joc de la partida
     */
    public int getTemps() {
        return temps.time;
    }

    /**
     * retorna el valoor booleà del estat de la partida
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
        System.out.println("Temps:"+getTemps()+"s");
    }

    /**
     *Crea una instancia de Partida
     * @param id:identificador de la partida
     * @param jugador:Usuari que juga la partida
     * @param hi:Hidato sobre el que es juga la partida
     */
    public Partida(int id,String jugador, Hidato hi){
        idP = id;
        idplayer = jugador;
        temps = new Tiempo();
        acabat = false;
        hidato = hi;
        int sx = hi.mContingut.length;
        int sy = hi.mContingut[0].length;
        mCoriginal = new String [sx][sy];
        clonarMatriu(hi.mContingut);
    }

    /**
     * colana la de Strings que es passa per paramentre
     * @param a: Matriu a copiar
     */
    public void clonarMatriu(String[][] a){
        for(int i = 0; i < a.length ; i++) {
            for (int j = 0; j < a[0].length; j++) {
                mCoriginal[i][j] = a[i][j];
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
        String c = hidato.mContingut[x][y];
        String c2 = mCoriginal[x][y];
        if(c.equals("#") || isNumeric(c2) || c.equals("*")) System.out.println(
                "No es pot col·locar perque hi ha:("+c+")");
        else {hidato.mContingut[x][y] = n;hidato.imprimirMContingut();}
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
    public void resol_hidato(){
        if(!hidato.resol())System.out.println("No es pot resoldre");
        else {
            hidato.imprimirMContingut();
            acabar();
        }
    }

    /**
     * Comproba si el hidato està resolt correctament
     */
    public void validar(){
        if(hidato.validar()){
            System.out.println("L'Hidato esta resolt");
            this.acabar();
        }
        else System.out.println("L'Hidato es incorrecte");
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
