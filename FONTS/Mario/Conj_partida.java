import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Conj_partida {
    Map<Integer,Partida> partides_guardades = new HashMap<>();

    /**
     * Guarda una partida com a value d'un Map que te com a key el id de la partida
     * @param p: partida que es guardarà com a value
     */
    public void guardar_partida(Partida p) {
           partides_guardades.put(p.getIdP(), p);
    }

    /**
     * retorna una partida identificada al Map amb la key = idp
     * @param idp: key de la partida a retornar
     * @return: retorn de la partida amb key = idp
     */
    public Partida cargar_partida(int idp) {
            return partides_guardades.get(idp);
    }

    /**
     * elimina del Map la entrada que te com key = idp
     * @param idp: key del Map que es borrarà
     */
    public void borrar_partida(int idp) {
        for(Iterator<Map.Entry<Integer, Partida>> it = partides_guardades.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Integer, Partida> entry = it.next();
            if(entry.getKey().equals(idp)) {
                it.remove();
            }
        }
    }
}
