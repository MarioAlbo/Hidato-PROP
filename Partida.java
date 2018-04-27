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

    public int getIdP() {
        return idP;
    }

    public String getIdplayer() {
        return idplayer;
    }

    public int getTemps() {
        return temps.time;
    }

    public static boolean getAcabat() {
        return acabat;
    }

    public void acabar(){
        acabat=true;
    }

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

    public void clonarMatriu(String[][] a){
        for(int i = 0; i < a.length ; i++)
            for(int j = 0; j < a[0].length; j++){
                String c = a[i][j];
                mCoriginal[i][j] = c;
            }
    }

    public void posarNum(int x, int y, String n){
        String c = hidato.mContingut[x][y];
        String c2 = mCoriginal[x][y];
        if(c.equals("#") || isNumeric(c2) || c.equals("*")) System.out.println(
                "No es pot col·locar perque hi ha:("+c+")");
        else {hidato.mContingut[x][y] = n;hidato.imprimirMContingut();}
    }

    public void borrarNum(int x, int y){
        String c = mCoriginal[x][y];
        if(c.equals("#") || isNumeric(c) || c.equals("*")) System.out.println(
                "No es pot borrar perque hi ha:("+c+")");
        else {hidato.mContingut[x][y] = "?";hidato.imprimirMContingut();}
    }

    public void ajuda(){

    }

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

}
