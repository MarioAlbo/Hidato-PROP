import com.google.gson.Gson;
import java.io.File;


public class Ctrl_Persistencia {
    private String path_CP = "C:\\Users\\Mario\\IdeaProjects\\prova\\src\\Persistencia\\fichero_CP";
    private PersistenciaPartida pp;

    private String path_usuario = "C:\\Users\\Mario\\IdeaProjects\\prova\\src\\Persistencia\\fichero_usuario_";
    private PersistenciaUsuari pu;

    private String path_hidato = "C:\\Users\\Mario\\IdeaProjects\\prova\\src\\Persistencia\\fichero_hidato_";
    private PersistenciaHidato ph;

    private String path_taulell = "C:\\Users\\Mario\\IdeaProjects\\prova\\src\\Persistencia\\fichero_taulell_";
    private PersistenciaTaulell pt;

    /**
     * Recibe un objecto Conj_partida en forma de String para que Persistencia_partida lo guarde en un fichero
     * @param cp objecto Conj_partida convertido a String
     */
    public void guardar_CP(String cp) {
        pp = new PersistenciaPartida();
        File conj_partides_fitxer = new File(path_CP);
        pp.guardar_CP(cp, conj_partides_fitxer);
    }

    /**
     * Recibe un String que corresponde al objecto de la clase Conj_partides y lo envia a Ctrl_Domini
     * @return devuelve el objeto de Conj_partida en forma de string a la capa de Domini
     */
    public String cargar_CP() {
        pp = new PersistenciaPartida();
        File conj_partides_fitxer = new File(path_CP);
        return pp.cargar_CP(conj_partides_fitxer);
    }

    /**
     * Recibe un objecto Usuari en forma de String para que Persistencia_partida lo guarde en un fichero
     * @param u objecto Usuari en forma de String
     * @param path nickname del usuario para añadir al nombre del fichero que guarde String u
     */
    public void guardar_usuari(String u, String path) {
        pu = new PersistenciaUsuari();
        File usuari_fitxer = new File(path_usuario + path);
        pu.guardar_U(u, usuari_fitxer);
    }

    /**
     * Recibe un String que corresponde al objecto de la clase Usuari y lo envia a Ctrl_Domini
     * @param s nickname del usuario que lo identifica para cargarlo
     * @return devuelve un String que corresponde al objecto Usuario identificado por String s
     */
    public String cargar_usuari(String s) {
        pu = new PersistenciaUsuari();
        File usuari_fitxer = new File(path_usuario + s);
        return pu.cargar_U(usuari_fitxer);
    }

    /**
     * recibe un String y lo envia a PersistenciaHidato para que guarde este en un fichero
     * @param h objecto Hidato convertido a String
     * @param path Integer que se añadirá al final del fichero ppara identificar que pertenece al objecto h
     */
    public void guardar_hidato(String h, Integer path) {
        ph = new PersistenciaHidato();
        File hidato_fitxer = new File(path_hidato + path);
        ph.guardar_H(h, hidato_fitxer);
    }

    /**
     * devuelve a la capa de Domini en forma de String un objecto de la clase Hidato
     * @param idH Identificador del Hidato
     * @return devuelve un String que corresponde a un objecto de la clase Hidato
     */
    public String cargar_hidato(Integer idH) {
        ph = new PersistenciaHidato();
        File hidato_fitxer = new File(path_hidato + idH);
        return ph.cargar_H(hidato_fitxer);
    }

    /**
     * elimina de la capa de persistencia el Hidato identificado por path
     * @param path Identificador del Hidato a borrar
     */
    public void borrar_hidato(Integer path) {
        ph = new PersistenciaHidato();
        File file_hidato = new File(path_hidato + path);
        ph.borrar_hidato(file_hidato);
    }

    /**
     * guarda en la capa de persistencia una instancia de la clase Taulell
     * @param t String que corresponde a un objeto Taulell
     * @param idt Identificador del objeto que se guardará en la capa de persistencia
     */
    public void guardar_taulell(String t, Integer idt) {
        pt = new PersistenciaTaulell();
        File taulell_fitxer = new File(path_taulell + idt);
        pt.guardar_T(t, taulell_fitxer);
    }

    /**
     * devuelve a la capa de Domini en forma de String un objecto de la clase Taulell
     * @param idt Integer que identifica al objeto que se quiere cargar
     * @return devuelve un String que corresponde a un objecto de la clase Taulell
     */
    public String cargar_taulell(Integer idt) {
        pt = new PersistenciaTaulell();
        File taulell_fitxer = new File(path_taulell + idt);
        return pt.cargar_T(taulell_fitxer);
    }

    /**
     * elimina de la capa de persistencia el Hidato identificado por idt
     * @param idt Identificador del Taulell a borrar
     */
    public void borrar_taulell(Integer idt) {
        pt = new PersistenciaTaulell();
        File file_taulell = new File(path_taulell + idt);
        pt.borrar_taulell(file_taulell);
    }
}