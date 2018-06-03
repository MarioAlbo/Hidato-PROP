import com.google.gson.Gson;

public class Ctrl_Domini {
    Ctrl_Persistencia cp;

    /**
     * Guarda en la capa de persistencia la instancia de la clase Conj_partida en forma de String
     * @param conj_partida objeto a guardad en la capa de persistencia
     */
    public void guardar_partida(Conj_partida conj_partida) {
        cp = new Ctrl_Persistencia();
        Gson gson = new Gson();
        String conj = gson.toJson(conj_partida);
        cp.guardar_CP(conj);
    }

    /**
     * Devuelve el objeto Conj_partida guardado en la capa de persistencia en forma de String
     * @return devuelve la instancia de la clase Conj_partida que se encuentra en la capa de persistencia
     */
    public Conj_partida cargar_ConjPartida() {
        cp = new Ctrl_Persistencia();
        cp.cargar_CP();
        Gson gson = new Gson();
        return gson.fromJson(cp.cargar_CP(),Conj_partida.class);
    }

    /**
     * Guarda una instancia de la clase Usuari en forma de String en la capa de persistencia
     * @param usuari objecto que será convertido a String para ser guardado
     */
    public void guardar_usuari(Usuari usuari) {
        cp = new Ctrl_Persistencia();
        Gson gson = new Gson();
        String u = gson.toJson(usuari);
        cp.guardar_usuari(u, usuari.getnikname());
    }

    /**
     * Devuelve un objeto Usuari guardado en la capa de persistencia en forma de String
     * @param nick Identificador del objecto a devolver
     * @return devuelve una instancia de la clase Usuari que se encuentra en la capa de persistencia
     */
    public Usuari cargar_usuari(String nick) {
        cp = new Ctrl_Persistencia();
        Gson gson = new Gson();
        return gson.fromJson(cp.cargar_usuari(nick), Usuari.class);
    }

    /**
     * Guarda una instancia de la clase Hidato en forma de String en la capa de persistencia
     * @param hidato objecto que será convertido a String para ser guardado
     */
    public void guardar_hidato(Hidato hidato) {
        cp = new Ctrl_Persistencia();
        Gson gson = new Gson();
        String h = gson.toJson(hidato);
        cp.guardar_hidato(h,hidato.getIdH());
    }

    /**
     * Devuelve un objeto Hidato guardado en la capa de persistencia en forma de String
     * @param idH Identificador del objecto a devolver
     * @return devuelve una instancia de la clase Hidato que se encuentra en la capa de persistencia
     */
    public Hidato cargar_hidato(Integer idH) {
        cp = new Ctrl_Persistencia();
        Gson gson = new Gson();
        return gson.fromJson(cp.cargar_hidato(idH), Hidato.class);
    }

    /**
     * Elimina de la capa de persistencia un objecto de la clase Hidato identificado por idH
     * @param idH identificador del objecto Hidato
     */
    public void borrar_hidato(Integer idH) {
        cp = new Ctrl_Persistencia();
        cp.borrar_hidato(idH);
    }

    /**
     * Guarda una instancia de la clase Taulell en forma de String en la capa de persistencia
     * @param taulell objecto que será convertido a String para ser guardado
     */
    public void guardar_taulell(Taulell taulell) {
        cp = new Ctrl_Persistencia();
        Gson gson = new Gson();
        String t = gson.toJson(taulell);
        cp.guardar_taulell(t, taulell.getIdT());
    }

    /**
     * Devuelve un objeto Taulell guardado en la capa de persistencia en forma de String
     * @param idt identificador del objeto a devolver
     * @return devuelve una instancia de la clase Taulell que se encuentra en la capa de persistencia
     */
    public Taulell cargar_taulell(Integer idt) {
        cp = new Ctrl_Persistencia();
        Gson gson = new Gson();
        return gson.fromJson(cp.cargar_taulell(idt), Taulell.class);
    }

    /**
     * Elimina de la capa de persistencia un objecto de la clase Taulell identificado por idH
     * @param idt identificador del objecto Taulell
     */
    public void borrar_taulell(Integer idt) {
        cp = new Ctrl_Persistencia();
        cp.borrar_taulell(idt);
    }
}
