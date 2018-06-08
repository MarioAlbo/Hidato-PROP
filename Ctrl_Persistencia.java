import java.io.File;


public class Ctrl_Persistencia {
    private String path_partida = ".\\prova\\src\\Persistencia\\fichero_partida";
    private PersistenciaPartida pp;

    private String path_usuario = ".\\src\\Persistencia\\fichero_usuario";
    private PersistenciaUsuari pu;

    private String path_hidatoT = ".\\src\\Persistencia\\fichero_hidatoT";
    private String path_hidatoQ = ".\\src\\Persistencia\\fichero_hidatoQ";
    private String path_hidatoH = ".\\src\\Persistencia\\fichero_hidatoH";
    private PersistenciaHidato ph;

    private String path_taulell = ".\\src\\Persistencia\\fichero_taulell";
    private PersistenciaTaulell pt;

    /**
     * Recibe un objecto Conj_partida en forma de String para que Persistencia_partida lo guarde en un fichero
     * @param p objecto Conj_partida convertido a String
     */
    public void guardar_partida(String p) {
        pp = new PersistenciaPartida();
        File partida_fitxer = new File(path_partida);
        pp.guardar_partida(p, partida_fitxer);
    }

    /**
     * Recibe un String que corresponde al objecto de la clase Conj_partides y lo envia a Ctrl_Domini
     * @return devuelve el objeto de Conj_partida en forma de string a la capa de Domini
     */
    public String cargar_partida() {
        pp = new PersistenciaPartida();
        File partida_fitxer = new File(path_partida);
        return pp.cargar_partida(partida_fitxer);
    }

    /**
     * Recibe un objecto Usuari en forma de String para que Persistencia_partida lo guarde en un fichero
     * @param u objecto Usuari en forma de String
     */
    public void guardar_usuari(String u) {
        pu = new PersistenciaUsuari();
        File usuari_fitxer = new File(path_usuario);
        pu.guardar_U(u, usuari_fitxer);
    }

    /**
     * Recibe un String que corresponde al objecto de la clase Usuari y lo envia a Ctrl_Domini
     * @return devuelve un String que corresponde al objecto Usuario identificado por String s
     */
    public String cargar_usuari() {
        pu = new PersistenciaUsuari();
        File usuari_fitxer = new File(path_usuario);
        return pu.cargar_U(usuari_fitxer);
    }

    /**
     * recibe un String y lo envia a PersistenciaHidato para que guarde este en un fichero
     * @param h objecto Hidato convertido a String
     */
    public void guardar_hidatoT(String h) {
        ph = new PersistenciaHidato();
        File hidato_fitxer = new File(path_hidatoT);
        ph.guardar_H(h, hidato_fitxer);
    }

    /**
     * devuelve a la capa de Domini en forma de String un objecto de la clase Hidato
     * @return devuelve un String que corresponde a un objecto de la clase Hidato
     */
    public String cargar_hidatoT() {
        ph = new PersistenciaHidato();
        File hidato_fitxer = new File(path_hidatoT);
        return ph.cargar_H(hidato_fitxer);
    }

    /**
     * recibe un String y lo envia a PersistenciaHidato para que guarde este en un fichero
     * @param h objecto Hidato convertido a String
     */
    public void guardar_hidatoQ(String h) {
        ph = new PersistenciaHidato();
        File hidato_fitxer = new File(path_hidatoQ);
        ph.guardar_H(h, hidato_fitxer);
    }

    /**
     * devuelve a la capa de Domini en forma de String un objecto de la clase Hidato
     * @return devuelve un String que corresponde a un objecto de la clase Hidato
     */
    public String cargar_hidatoQ() {
        ph = new PersistenciaHidato();
        File hidato_fitxer = new File(path_hidatoQ);
        return ph.cargar_H(hidato_fitxer);
    }

    /**
     * recibe un String y lo envia a PersistenciaHidato para que guarde este en un fichero
     * @param h objecto Hidato convertido a String
     */
    public void guardar_hidatoH(String h) {
        ph = new PersistenciaHidato();
        File hidato_fitxer = new File(path_hidatoH);
        ph.guardar_H(h, hidato_fitxer);
    }

    /**
     * devuelve a la capa de Domini en forma de String un objecto de la clase Hidato
     * @return devuelve un String que corresponde a un objecto de la clase Hidato
     */
    public String cargar_hidatoH() {
        ph = new PersistenciaHidato();
        File hidato_fitxer = new File(path_hidatoH);
        return ph.cargar_H(hidato_fitxer);
    }

    /**
     * guarda en la capa de persistencia una instancia de la clase Taulell
     * @param t String que corresponde a un objeto Taulell
     */
    public void guardar_taulell(String t) {
        pt = new PersistenciaTaulell();
        File taulell_fitxer = new File(path_taulell);
        pt.guardar_T(t, taulell_fitxer);
    }

    /**
     * devuelve a la capa de Domini en forma de String un objecto de la clase Taulell
     * @return devuelve un String que corresponde a un objecto de la clase Taulell
     */
    public String cargar_taulell() {
        pt = new PersistenciaTaulell();
        File taulell_fitxer = new File(path_taulell);
        return pt.cargar_T(taulell_fitxer);
    }
}