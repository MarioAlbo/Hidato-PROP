import java.util.Arrays;
import java.util.Scanner;
import java.io.Serializable;

public class Taulell implements Serializable {

    private Integer idT;
    private String Tcela;
    private String Tadjacecnia;
    private Integer files;
    private Integer columnes;
    private String[][] mContingut;

    public Taulell() {
        this.idT = -1;
        this.Tcela = null;
        this.Tadjacecnia = null;
        this.files = -1;
        this.columnes = 1;
        this.mContingut = new String[1][1];
    }

    /**
     * Dona el valor del atribut idT
     * @return retorna el identificador del Taulell
     */
    public Integer getIdT() {return idT;}

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
    public Taulell(Integer idT, String Tcela, String Tadj, int column, int files) {
        //System.out.println("llegint taulell: (Tc,Ta,X,Y)");
        //Scanner teclado = new Scanner(System.in);
        //String s = teclado.nextLine();
        //String[] h = s.split(",");
        this.idT = idT;
        this.Tcela = Tcela;
        this.Tadjacecnia = Tadj;
        this.files = files;
        this.columnes = column;
        /*this.Tcela = h[0];
        this.Tadjacecnia = h[1];
        this.files = Integer.parseInt(h[2]);
        this.columnes = Integer.parseInt(h[3]);*/
        this.mContingut = new String[files][columnes];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                mContingut[i][j] = "?";
            }
        }
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

   /* /**
     * Mostra per pantalla la matriu de contingut del Taulell
     */
/*    public void imprimirMContingut(){
        System.out.print(Tcela + "," + Tadjacecnia + "," + files + "," + columnes);
        System.out.println();
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
    } */
}