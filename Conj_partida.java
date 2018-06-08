//package domini;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Conj_partida {

    public Map<Integer,Partida> partides_guardades = new HashMap<>();


    public String[] ranking(String id){ //hidato h || int idh
        int idR = Integer.parseInt(id);
        Integer rnk[] = new Integer[5];
        rnk = new Integer[]{-1,-1,-1,-1,-1};
        for (int pg : partides_guardades.keySet()) {
            if (partides_guardades.get(pg).getIdHidato() == idR){
                if (partides_guardades.get(pg).getAcabat()){
                    boolean assign = false;
                    for (int i = 0; i<5 && !assign; i++){ //treu -1
                        if (rnk[i]==-1){
                            rnk[i] = pg;
                            assign = true;
                        }
                        else {
                            if (partides_guardades.get(pg).getTemps() < partides_guardades.get(rnk[i]).getTemps()){
                                rnk[i] = pg;
                                assign = true;
                            }
                        }
                    }
                }
            }
        }
        String[] rank = new String[5];
        for (int i = 0; i < 5; i++){
            if (rnk[i]==-1){
                rank[i] += "-1 -1"; //id nick temps acabat idH son false
            }
            else {
                rank[i] += partides_guardades.get(rnk[i]).getIdP() + " ";
                rank[i] += partides_guardades.get(rnk[i]).getTemps() + " ";
                rank[i] += partides_guardades.get(rnk[i]).getIdplayer();
            }
        }
        return rank;
    }
}
