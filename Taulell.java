//package domini;
import java.util.Scanner;
import java.io.Serializable;

public class Taulell implements Serializable {
    private String idT;
    private String Tcela;
    private String Tadjacecnia;
    private int files;
    private int columnes;
    private String[][] mContingut;

    /**
     * Dona el valor del atribut Tcela
     * @return retorn el tipus de cel·la
     */
    public String getTcela() {
        return Tcela;
    }

    /**
     * Dona el valor del atribut Tadjacencia
     * @return retorna el tipus de adjacencia
     */
    public String getTadjacencia() {
        return Tadjacecnia;
    }

    /**
     * Dona el valor del atribut columnes
     * @return retorna el el número de columnes del taulell
     */
    public int getColumnes() {
        return columnes;
    }

    /**
     * Dona el valor del atribut files
     * @return retorna el el número de files del taulell
     */
    public int getFiles() {
        return files;
    }

    /**
     * Dona el atribut mContingut
     * @return retorna la representació gráfica del taulell
     */
    public String[][] getmContingut() {
        return mContingut;
    }

    /**
     * Dona el atribut idT
     * @return retorna el identidicador del Tauell
     */
    public String getIdT() {return idT;}

    /**
     * Crea una nova instancia de la classe Taulell amb la especificació de tipus de cel·la, andajencia i tamany
     * @param s: String que indica tipus de cel·la, adjacencia i tamany
     */
    public Taulell(String s){
        String[] h = s.split(",");
        Tcela = h[0];
        Tadjacecnia = h[1];
        files = Integer.parseInt(h[2]);
        columnes = Integer.parseInt(h[3]);
        mContingut = new String[files][columnes];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                mContingut[i][j] = "?";
            }
        }
    }

    /**
     * Creua una nova instancia de la classe Taulell a la qual s'introdueixen les caractesistiques per manualment
     */
    public Taulell() {
        System.out.println("llegint taulell: (Tc,Ta,X,Y)");
        Scanner teclado = new Scanner(System.in);
        String s = teclado.nextLine();
        String[] h = s.split(",").clone();
        Tcela = h[0];
        Tadjacecnia = h[1];
        files = Integer.parseInt(h[2]);
        columnes = Integer.parseInt(h[3]);
        mContingut = new String[files][columnes];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                mContingut[i][j] = "?";
            }
        }
    }

    public Taulell(String s,String[][] m){
        String[] h = s.split(",").clone();
        Tcela = h[0];
        Tadjacecnia = h[1];
        files = m.length;
        columnes = m[0].length;
        mContingut = m.clone();
    }

    /**
     * Possa forats(#) a les coordenades indicades
     */
    public void posarForats() {
        System.out.println("posar forats(#): coorX coordY");
        System.out.println("per acabar: exit");
        Scanner teclado = new Scanner(System.in);
        String s = teclado.nextLine();
        while (!s.equals("exit")) {
            String[] h = s.split(" ");
            int coordX =  Integer.parseInt(h[0]);
            int coordY =  Integer.parseInt(h[1]);
            mContingut[coordX][coordY] = "#";
            s = teclado.nextLine();
        }
    }

    /**
     * Treu els forats(#) a les coordenades indicades
     */
    public void treuForats() {
        System.out.println("treure forats: coorX coordY");
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
     * Mostra per pantalla la matriu de contingut del Taulell
     */
    public void imprimirMContingut(){
        System.out.print(this.Tcela + "," + this.Tadjacecnia + "," + this.files + "," + this.columnes);
        System.out.println();
        for (int i = 0; i < this.mContingut.length; i++){
            for (int j = 0; j < this.mContingut[i].length; j++){
                if (j < this.mContingut[i].length - 1) {
                    System.out.print(this.mContingut[i][j] + ",");
                }
                else {
                    System.out.print(this.mContingut[i][j]);
                }
            }
            System.out.println();
        }
    }
}