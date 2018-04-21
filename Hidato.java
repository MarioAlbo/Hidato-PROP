import java.util.ArrayList;
import java.util.Scanner;

public class Hidato {
    public int idH,iduser;
    public static Taulell taulell;
    public char dificultat;


    public Hidato(int idh, int idusr, Taulell topo) {
        this.idH = idh;
        this.iduser = idusr;
        this.taulell = topo;
    }
    public void posar_forats(Taulell t) {

    }
    public void editar_idh(int idh) {
        idH = idh;
    }
    public void editar_iduser(int idusr) {
        iduser = idusr;
    }



    public static void generaMatAdj(){

        if (taulell.Tcela.equals("Q")){
            for (int i = 0; i < taulell.files; i++) {
                for (int j = 0; j < taulell.columnes; j++) {
                    if (taulell.mContingut[i][j] != "#") {
                        taulell.matAdj.add(new ArrayList<Integer>());
                        if (j - 1 >= 0 && taulell.mContingut[i][j - 1] != "#" && taulell.mContingut[i][j - 1] != "*")
                            taulell.matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j - 1);
                        if (j + 1 < taulell.columnes && taulell.mContingut[i][j + 1] != "#" && taulell.mContingut[i][j + 1] != "*")
                            taulell.matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j + 1);
                        if (i - 1 >= 0 && taulell.mContingut[i - 1][j] != "#" && taulell.mContingut[i - 1][j] != "*")
                            taulell.matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j);
                        if (i + 1 < taulell.files && taulell.mContingut[i + 1][j] != "#" && taulell.mContingut[i + 1][j] != "*")
                            taulell.matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j);
                    }
                }
            }
            if (taulell.Tadjacecnia.equals("CA")) {
                for (int i = 0; i < taulell.files; i++){
                    for (int j = 0; j < taulell.columnes; j++){
                        if (taulell.mContingut[i][j] != "#" && taulell.mContingut[i][j] != "*"){
                            if (j - 1 >= 0 && i - 1 >= 0 && taulell.mContingut[i - 1][j - i] != "#" && taulell.mContingut[i - 1][j - 1] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j - 1);
                            if (j + 1 < taulell.columnes && i - 1 >= 0 && taulell.mContingut[i - 1][j + 1] != "#" && taulell.mContingut[i - 1][j + 1] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j + 1);
                            if (j + 1 < taulell.columnes && i + 1 < taulell.files && taulell.mContingut[i + 1][j + 1] != "#" && taulell.mContingut[i + 1][j + 1] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j + 1);
                            if (j - 1 >= 0 && i + 1 < taulell.files && taulell.mContingut[i + 1][j - 1] != "#" && taulell.mContingut[i + 1][j - 1] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j - 1);
                        }
                    }
                }
            }

        }
        else if (taulell.Tcela.equals("H")) {
            for (int i = 0; i < taulell.files; i++) {
                for (int j = 0; j < taulell.columnes; j++) {
                    taulell.matAdj.add(new ArrayList<Integer>());
                    if (taulell.mContingut[i][j] != "#" && taulell.mContingut[i][j] != "*") {
                        if (i % 2 == 1) {
                            if (i - 1 >= 0 && taulell.mContingut[i - 1][j] != "#" && taulell.mContingut[i - 1][j] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j);
                            if (i - 1 >= 0 && j + 1 < taulell.columnes && taulell.mContingut[i - 1][j + 1] != "#" && taulell.mContingut[i - 1][j + 1] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j + 1);
                            if (j - 1 >= 0 && taulell.mContingut[i][j - 1] != "#" && taulell.mContingut[i][j - 1] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j - 1);
                            if (j + 1 < taulell.columnes && taulell.mContingut[i][j + 1] != "#" && taulell.mContingut[i][j + 1] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j + 1);
                            if (i + 1 < taulell.files && taulell.mContingut[i + 1][j] != "#" && taulell.mContingut[i + 1][j] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j);
                            if (i + 1 < taulell.files && j + 1 < taulell.columnes && taulell.mContingut[i + 1][j + 1] != "#" && taulell.mContingut[i + 1][j + 1] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j + 1);
                        }
                        else {
                            if (i - 1 >= 0 && taulell.mContingut[i - 1][j] != "#" && taulell.mContingut[i - 1][j] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j);
                            if (i - 1 >= 0 && j - 1 >= 0 && taulell.mContingut[i - 1][j - 1] != "#" && taulell.mContingut[i + -1][j - 1] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j - 1);
                            if (j - 1 >= 0 && taulell.mContingut[i + 1][j] != "#" && taulell.mContingut[i + 1][j] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j - 1);
                            if (j + 1 < taulell.columnes && taulell.mContingut[i][j + 1] != "#" && taulell.mContingut[i][j + 1] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j + 1);
                            if (i + 1 < taulell.files && taulell.mContingut[i + 1][j] != "#" && taulell.mContingut[i + 1][j] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j);
                            if (i + 1 < taulell.files && j - 1 >= 0 && taulell.mContingut[i + 1][j - 1] != "#" && taulell.mContingut[i + 1][j - 1] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j - 1);
                        }
                    }
                }
            }
        }
        else if (taulell.Tcela.equals("T")){
            for (int i = 0; i < taulell.files; i++) {
                for (int j = 0; j < taulell.columnes; j++) {
                    taulell.matAdj.add(new ArrayList<Integer>());
                    if (taulell.mContingut[i][j] != "#" && taulell.mContingut[i][j] != "*") {
                        if (j - 1 >= 0 && taulell.mContingut[i][j - 1] != "#" && taulell.mContingut[i][j - 1] != "*")
                            taulell.matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j - 1);
                        if (j + 1 < taulell.columnes && taulell.mContingut[i][j + 1] != "#" && taulell.mContingut[i][j + 1] != "*")
                            taulell.matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j + 1);
                        if ((i + j) % 2 == 0) {
                            if (i + 1 < taulell.files && taulell.mContingut[i + 1][j] != "#" && taulell.mContingut[i + 1][j] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j);
                        }
                        else {
                            if (i - 1 >= 0 && taulell.mContingut[i - 1][j] != "#" && taulell.mContingut[i - 1][j] != "*")
                                taulell.matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j);
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
                                    taulell.matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j - 1);
                                if (i - 1 >= 0 && taulell.mContingut[i - 1][j] != "#" && taulell.mContingut[i - 1][j] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j);
                                if (i - 1 >= 0 && j + 1 < taulell.columnes && taulell.mContingut[i - 1][j + 1] != "#" && taulell.mContingut[i - 1][j + 1] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j + 1);
                                if (j - 2 >= 0 && taulell.mContingut[i][j - 2] != "#" && taulell.mContingut[i][j - 2] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j - 2);
                                if (j + 2 < taulell.columnes && taulell.mContingut[i + 2][j] != "#" && taulell.mContingut[i + 2][j] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j + 2);
                                if (i + 1 < taulell.files && j - 2 >= 0 && taulell.mContingut[i + 1][j - 2] != "#" && taulell.mContingut[i + 1][j - 2] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j - 2);
                                if (i + 1 < taulell.files && j - 1 >= 0 && taulell.mContingut[i + 1][j - 1] != "#" && taulell.mContingut[i + 1][j - 1] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j - 1);
                                if (i + 1 < taulell.files && j + 1 < taulell.columnes && taulell.mContingut[i + 1][j + 1] != "#" && taulell.mContingut[i + 1][j + 1] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j + 1);
                                if (i + 1 < taulell.files && j + 2 < taulell.columnes && taulell.mContingut[i + 1][j + 2] != "#" && taulell.mContingut[i + 1][j + 2] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j + 2);
                            }
                            else {
                                if (i - 1 >= 0 && j - 2 >= 0 && taulell.mContingut[i - 1][j - 2] != "#" && taulell.mContingut[i - 1][j - 2] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j - 2);
                                if (i - 1 >= 0 && j - 1 >= 0 && taulell.mContingut[i - 1][j - 1] != "#" && taulell.mContingut[i - 1][j - 1] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j - 1);
                                if (i - 1 >= 0 && j + 1 < taulell.columnes && taulell.mContingut[i - 1][j + 1] != "#" && taulell.mContingut[i - 1][j + 1] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j + 1);
                                if (i - 1 >= 0 && j + 2 < taulell.columnes && taulell.mContingut[i - 1][j + 2] != "#" && taulell.mContingut[i - 1][j + 2] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add((i - 1) * taulell.columnes + j + 2);
                                if (j - 2 >= 0 && taulell.mContingut[i][j - 2] != "#" && taulell.mContingut[i][j - 2] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j - 2);
                                if (j + 2 < taulell.columnes && taulell.mContingut[i][j + 2] != "#" && taulell.mContingut[i][j + 2] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add(i * taulell.columnes + j + 2);
                                if (i + 1 < taulell.files && j - 1 >= 0 && taulell.mContingut[i + 1][j - 1] != "#" && taulell.mContingut[i + 1][j - 1] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j - 1);
                                if (i + 1 < taulell.files && taulell.mContingut[i + 1][j] != "#" && taulell.mContingut[i + 1][j] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j);
                                if (i + 1 < taulell.files && j + 1 < taulell.columnes && taulell.mContingut[i + 1][j + 1] != "#" && taulell.mContingut[i + 1][j + 1] != "*")
                                    taulell.matAdj.get(i * taulell.columnes + j).add((i + 1) * taulell.columnes + j + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] arg){
        taulell=new Taulell();
        taulell.llegirTaulell();
        generaMatAdj();
        taulell.imprimirMatAdj();
        taulell.imprimirMContingut();
        //System.out.println(taulell.getFiles());
    }

}
