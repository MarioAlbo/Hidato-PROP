import java.util.ArrayList;

public class HidatoT extends Hidato{

    public HidatoT(int idh, String nick, Taulell t) { //CREAR mContingut A PARTIR DE TAULELL
        super(idh,nick,t);
    }
    public HidatoT(int idh, String nick, int d){
        super(idh,nick,d);
    }

    @Override
    public void generaMatAdj(){
        matAdj = new ArrayList <ArrayList<Integer> >(taulell.getColumnes()*taulell.getFiles());

        for (int i = 0; i < taulell.getFiles(); i++) {
            for (int j = 0; j < taulell.getColumnes(); j++) {
                matAdj.add(new ArrayList<Integer>());
                if (taulell.getmContingut()[i][j] != "#" && taulell.getmContingut()[i][j] != "*") {
                    if (j - 1 >= 0 && taulell.getmContingut()[i][j - 1] != "#" && taulell.getmContingut()[i][j - 1] != "*")
                        matAdj.get(i * taulell.getColumnes() + j).add(i * taulell.getColumnes() + j - 1);
                    if (j + 1 < taulell.getColumnes() && taulell.getmContingut()[i][j + 1] != "#" && taulell.getmContingut()[i][j + 1] != "*")
                        matAdj.get(i * taulell.getColumnes() + j).add(i * taulell.getColumnes() + j + 1);
                    if ((i + j) % 2 == 0) {
                        if (i + 1 < taulell.getFiles() && taulell.getmContingut()[i + 1][j] != "#" && taulell.getmContingut()[i + 1][j] != "*")
                            matAdj.get(i * taulell.getColumnes() + j).add((i + 1) * taulell.getColumnes() + j);
                    }
                    else {
                        if (i - 1 >= 0 && taulell.getmContingut()[i - 1][j] != "#" && taulell.getmContingut()[i - 1][j] != "*")
                            matAdj.get(i * taulell.getColumnes() + j).add((i - 1) * taulell.getColumnes() + j);
                    }
                }
            }
        }
        if (taulell.getTadjacencia().equals("CA")){
            for (int i = 0; i < taulell.getFiles(); i++) {
                for (int j = 0; j < taulell.getColumnes(); j++) {
                    if (taulell.getmContingut()[i][j] != "#" && taulell.getmContingut()[i][j] != "*") {
                        if ((i + j) % 2 == 0) {
                            if (i - 1 >= 0 && j - 1 >= 0 && taulell.getmContingut()[i - 1][j - 1] != "#" && taulell.getmContingut()[i - 1][j - 1] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add((i - 1) * taulell.getColumnes() + j - 1);
                            if (i - 1 >= 0 && taulell.getmContingut()[i - 1][j] != "#" && taulell.getmContingut()[i - 1][j] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add((i - 1) * taulell.getColumnes() + j);
                            if (i - 1 >= 0 && j + 1 < taulell.getColumnes() && taulell.getmContingut()[i - 1][j + 1] != "#" && taulell.getmContingut()[i - 1][j + 1] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add((i - 1) * taulell.getColumnes() + j + 1);
                            if (j - 2 >= 0 && taulell.getmContingut()[i][j - 2] != "#" && taulell.getmContingut()[i][j - 2] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add(i * taulell.getColumnes() + j - 2);
                            if (j + 2 < taulell.getColumnes() && taulell.getmContingut()[i][j + 2] != "#" && taulell.getmContingut()[i][j + 2] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add(i * taulell.getColumnes() + j + 2);
                            if (i + 1 < taulell.getFiles() && j - 2 >= 0 && taulell.getmContingut()[i + 1][j - 2] != "#" && taulell.getmContingut()[i + 1][j - 2] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add((i + 1) * taulell.getColumnes() + j - 2);
                            if (i + 1 < taulell.getFiles() && j - 1 >= 0 && taulell.getmContingut()[i + 1][j - 1] != "#" && taulell.getmContingut()[i + 1][j - 1] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add((i + 1) * taulell.getColumnes() + j - 1);
                            if (i + 1 < taulell.getFiles() && j + 1 < taulell.getColumnes() && taulell.getmContingut()[i + 1][j + 1] != "#" && taulell.getmContingut()[i + 1][j + 1] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add((i + 1) * taulell.getColumnes() + j + 1);
                            if (i + 1 < taulell.getFiles() && j + 2 < taulell.getColumnes() && taulell.getmContingut()[i + 1][j + 2] != "#" && taulell.getmContingut()[i + 1][j + 2] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add((i + 1) * taulell.getColumnes() + j + 2);
                        }
                        else {
                            if (i - 1 >= 0 && j - 2 >= 0 && taulell.getmContingut()[i - 1][j - 2] != "#" && taulell.getmContingut()[i - 1][j - 2] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add((i - 1) * taulell.getColumnes() + j - 2);
                            if (i - 1 >= 0 && j - 1 >= 0 && taulell.getmContingut()[i - 1][j - 1] != "#" && taulell.getmContingut()[i - 1][j - 1] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add((i - 1) * taulell.getColumnes() + j - 1);
                            if (i - 1 >= 0 && j + 1 < taulell.getColumnes() && taulell.getmContingut()[i - 1][j + 1] != "#" && taulell.getmContingut()[i - 1][j + 1] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add((i - 1) * taulell.getColumnes() + j + 1);
                            if (i - 1 >= 0 && j + 2 < taulell.getColumnes() && taulell.getmContingut()[i - 1][j + 2] != "#" && taulell.getmContingut()[i - 1][j + 2] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add((i - 1) * taulell.getColumnes() + j + 2);
                            if (j - 2 >= 0 && taulell.getmContingut()[i][j - 2] != "#" && taulell.getmContingut()[i][j - 2] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add(i * taulell.getColumnes() + j - 2);
                            if (j + 2 < taulell.getColumnes() && taulell.getmContingut()[i][j + 2] != "#" && taulell.getmContingut()[i][j + 2] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add(i * taulell.getColumnes() + j + 2);
                            if (i + 1 < taulell.getFiles() && j - 1 >= 0 && taulell.getmContingut()[i + 1][j - 1] != "#" && taulell.getmContingut()[i + 1][j - 1] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add((i + 1) * taulell.getColumnes() + j - 1);
                            if (i + 1 < taulell.getFiles() && taulell.getmContingut()[i + 1][j] != "#" && taulell.getmContingut()[i + 1][j] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add((i + 1) * taulell.getColumnes() + j);
                            if (i + 1 < taulell.getFiles() && j + 1 < taulell.getColumnes() && taulell.getmContingut()[i + 1][j + 1] != "#" && taulell.getmContingut()[i + 1][j + 1] != "*")
                                matAdj.get(i * taulell.getColumnes() + j).add((i + 1) * taulell.getColumnes() + j + 1);
                        }
                    }
                }
            }
        }
    }
}
