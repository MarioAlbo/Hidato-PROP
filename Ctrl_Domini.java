import com.google.gson.Gson;

public class Ctrl_Domini {
    Ctrl_Persistencia cp;

    public void guardar_partida(Conj_partida conj_partida) {
        cp = new Ctrl_Persistencia();
        Gson gson = new Gson();
        String conj = gson.toJson(conj_partida);
        cp.guardar_CP(conj);
    }

    public Conj_partida cargar_ConjPartida() {
        cp = new Ctrl_Persistencia();
        cp.cargar_CP();
        Gson gson = new Gson();
        return gson.fromJson(cp.cargar_CP(),Conj_partida.class);
    }

    public void guardar_usuari(Usuari usuari) {
        cp = new Ctrl_Persistencia();
        Gson gson = new Gson();
        String u = gson.toJson(usuari);
        cp.guardar_usuari(u, usuari.getnikname());
    }

    public Usuari cargar_usuari(String nick) {
        cp = new Ctrl_Persistencia();
        Gson gson = new Gson();
        return gson.fromJson(cp.cargar_usuari(nick), Usuari.class);
    }

    public void guardar_hidato(Hidato hidato) {
        cp = new Ctrl_Persistencia();
        Gson gson = new Gson();
        String h = gson.toJson(hidato);
        cp.guardar_hidato(h,hidato.getIdH());
    }

    public Hidato cargar_hidato(Integer idH) {
        cp = new Ctrl_Persistencia();
        Gson gson = new Gson();
        return gson.fromJson(cp.cargar_hidato(idH), Hidato.class);
    }

    public void borrar_hidato(Integer idH) {
        cp = new Ctrl_Persistencia();
        cp.borrar_hidato(idH);
    }

    public void guardar_taulell(Taulell taulell) {
        cp = new Ctrl_Persistencia();
        Gson gson = new Gson();
        String t = gson.toJson(taulell);
        cp.guardar_taulell(t, taulell.getIdT());
    }

    public Taulell cargar_taulell(Integer idt) {
        cp = new Ctrl_Persistencia();
        Gson gson = new Gson();
        return gson.fromJson(cp.cargar_taulell(idt), Taulell.class);
    }

    public void borrar_taulell(Integer idt) {
        cp = new Ctrl_Persistencia();
        cp.borrar_taulell(idt);
    }
}
