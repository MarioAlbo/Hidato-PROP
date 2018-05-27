import java.io.File;

public class Ctrl_Persistencia {
    String path_CP = "C:\\Users\\Mario\\IdeaProjects\\prova\\src\\Persistencia\\fichero_CP";
    PersistenciaPartida pp = new PersistenciaPartida();

    String path_usuario = "C:\\Users\\Mario\\IdeaProjects\\prova\\src\\Persistencia\\fichero_usuario_";
    PersistenciaUsuari pu = new PersistenciaUsuari();

    String path_hidato = "C:\\Users\\Mario\\IdeaProjects\\prova\\src\\Persistencia\\fichero_hidato_";
    PersistenciaHidato ph = new PersistenciaHidato();

    String path_taulell = "C:\\Users\\Mario\\IdeaProjects\\prova\\src\\Persistencia\\fichero_taulell_";
    PersistenciaTaulell pt = new PersistenciaTaulell();

    public void guardar_CP(Conj_partida cp) {
        File conj_partides_fitxer = new File(path_CP);
        pp.guardar_CP(cp, conj_partides_fitxer);
    }

    public Conj_partida cargar_CP() {
        File conj_partides_fitxer = new File(path_CP);
        return pp.cargar_CP(conj_partides_fitxer);
    }

    public void guardar_usuari(Usuari u) {
        File usuari_fitxer = new File (path_usuario + u.getnikname());
        pu.guardar_U(u,usuari_fitxer);
    }

    public Usuari cargar_usuari(String s) {
        File usuari_fitxer = new File (path_usuario + s);
        Usuari usuari = pu.cargar_U(usuari_fitxer);
        return usuari;
    }

    public void guardar_hidato(Hidato h) {
        File hidato_fitxer = new File(path_hidato + h.getIdH());
        ph.guardar_H(h, hidato_fitxer);
    }

    public Hidato cargar_hidato(String idH) {
        File hidato_fitxer = new File(path_hidato + idH);
        return ph.cargar_H(hidato_fitxer);
    }

    public void borrar_hidato(Hidato h) {
        File file_hidato = new File(path_hidato + h.getIdH());
        ph.borrar_hidato(file_hidato);
    }

    public void guardar_taulell (Taulell t) {
        File taulell_fitxer = new File(path_taulell + t.getTcela() + "," + t.getTadjacecnia() + "," + t.getFiles() + "," + t.getColumnes());
        pt.guardar_T(t, taulell_fitxer);
    }

    public Taulell cargar_taulell(String t) {
        File taulell_fitxer = new File(path_taulell + t);
        return pt.cargar_T(taulell_fitxer);
    }

    public void borrar_taulell(Taulell t) {
        File file_taulell = new File(path_taulell +  t.getTcela() + "," + t.getTadjacecnia() + "," + t.getFiles() + "," + t.getColumnes());
        pt.borrar_taulell(file_taulell);
    }
}
