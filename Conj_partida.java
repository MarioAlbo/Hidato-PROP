import java.util.HashMap;
import java.util.Map;

public class Conj_partida {
    Map<Integer,Partida> partides_guardades = new HashMap<>();

    public void guardar_partida(Partida p) {
           partides_guardades.put(p.getIdP(), p);
    }

    public Partida cargar_partida(int idp) {
            return partides_guardades.get(idp);
    }

    public void borrar_partida(int idp) {
        partides_guardades.values().remove(idp);
    }
}
