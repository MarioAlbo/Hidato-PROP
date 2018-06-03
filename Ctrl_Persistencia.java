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

    public void guardar_CP(String cp) {
        pp = new PersistenciaPartida();
        File conj_partides_fitxer = new File(path_CP);
        pp.guardar_CP(cp, conj_partides_fitxer);
    }

    public String cargar_CP() {
        pp = new PersistenciaPartida();
        File conj_partides_fitxer = new File(path_CP);
        return pp.cargar_CP(conj_partides_fitxer);
    }

    public void guardar_usuari(String u, String path) {
        pu = new PersistenciaUsuari();
        File usuari_fitxer = new File(path_usuario + path);
        pu.guardar_U(u, usuari_fitxer);
    }

    public String cargar_usuari(String s) {
        pu = new PersistenciaUsuari();
        File usuari_fitxer = new File(path_usuario + s);
        return pu.cargar_U(usuari_fitxer);
    }

    public void guardar_hidato(String h, Integer path) {
        ph = new PersistenciaHidato();
        File hidato_fitxer = new File(path_hidato + path);
        ph.guardar_H(h, hidato_fitxer);
    }

    public String cargar_hidato(Integer idH) {
        ph = new PersistenciaHidato();
        File hidato_fitxer = new File(path_hidato + idH);
        return ph.cargar_H(hidato_fitxer);
    }

    public void borrar_hidato(Integer path) {
        ph = new PersistenciaHidato();
        File file_hidato = new File(path_hidato + path);
        ph.borrar_hidato(file_hidato);
    }

    public void guardar_taulell(String t, Integer idt) {
        pt = new PersistenciaTaulell();
        File taulell_fitxer = new File(path_taulell + idt);
        pt.guardar_T(t, taulell_fitxer);
    }

    public String cargar_taulell(Integer idt) {
        pt = new PersistenciaTaulell();
        File taulell_fitxer = new File(path_taulell + idt);
        return pt.cargar_T(taulell_fitxer);
    }

    public void borrar_taulell(Integer idt) {
        pt = new PersistenciaTaulell();
        File file_taulell = new File(path_taulell + idt);
        pt.borrar_taulell(file_taulell);
    }
}