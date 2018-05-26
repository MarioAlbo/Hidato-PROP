import java.io.File;

public class Ctrl_Persistencia {
    File conj_partides_fitxer = new File ("C:\\Users\\Mario\\IdeaProjects\\prova\\src\\Persistencia\\fichero_CP");
    PersistenciaPartida pp = new PersistenciaPartida();

    File usuari_fitxer = new File ("C:\\Users\\Mario\\IdeaProjects\\prova\\src\\Persistencia\\fichero_usuario");
    PersistenciaUsuari pu = new PersistenciaUsuari();

    File hidato_fitxer = new File ("C:\\Users\\Mario\\IdeaProjects\\prova\\src\\Persistencia\\fichero_hidato");
    PersistenciaHidato ph = new PersistenciaHidato();

    File taulell_fitxer = new File ("C:\\Users\\Mario\\IdeaProjects\\prova\\src\\Persistencia\\fichero_taulell");
    PersistenciaTaulell pt = new PersistenciaTaulell();

    public void guardar_CP(Conj_partida cp) {
        pp.guardar_CP(cp, conj_partides_fitxer);
    }

    public Conj_partida cargar_CP() {
        return pp.cargar_CP(conj_partides_fitxer);
    }

    public void guardar_usuari(Usuari u) {
        pu.guardar_U(u,usuari_fitxer);
    }

    public Usuari cargar_usuari() {
        return pu.cargar_U(usuari_fitxer);
    }

    public void guardar_hidato(Hidato h) {
        ph.guardar_H(h, hidato_fitxer);
    }

    public Hidato cargar_hidato() {
        return ph.cargar_H(hidato_fitxer);
    }

    public void guardar_taulell (Taulell t) {
        pt.guardar_T(t, taulell_fitxer);
    }

    public Taulell cargar_taulell() {
        return pt.cargar_T(taulell_fitxer);
    }
}
