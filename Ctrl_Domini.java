import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import Persistencia.*;

public class Ctrl_Domini {
    ;

    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    /**
     * @author Adrián
     */
    public class CtrlDomini {
        //Ctrl_Persistencia cp = new Ctrl_Persistencia();
        private Map<Integer, Usuari> usuaris;
        private HashTaulell Taulells = new HashTaulell();
        private HashHidatoH HidatosH = new HashHidatoH();
        private HashHidatoQ HidatosQ = new HashHidatoQ();
        private HashHidatoT HidatosT = new HashHidatoT();
        private Conj_partida Partidas = new Conj_partida();
        private Conj_partida cj = new Conj_partida();//DECLARAR


        public void AltaUsuari(String usr, String psw) {
            usuaris.put(usuaris.size(), new Usuari(usr, psw));
        }

        public boolean validaUsr(String usr, String psw) {
            Iterator it = usuaris.keySet().iterator();
            while (it.hasNext()) {
                Integer key = (Integer) it.next();
                Usuari a = usuaris.get(key);
                if (a.getnikname().equals(usr) && a.getPsw().equals(psw)) return true;
            }
            return false;
        }

        public void CrearHidatoManualment() {
            Taulell taulell = new Taulell();
        }

        public boolean nuevoTablero(String s, String[][] m, String nom, boolean nuevo) {
            Taulell t = new Taulell(s, m);
            if (nuevo && Taulells.getTaulells().containsKey(nom)) return false;
            Taulells.getTaulells().put(nom, t);
            return true;
        }

        public String[][] mostrarTablero(String k) {
            return Taulells.getTaulells().get(k).getmContingut();
        }

        public String[] getTaulells() {
            String[] T = new String[Taulells.getTaulells().size()];
            int i = 0;
            for (String key : Taulells.getTaulells().keySet()) {
                Taulell t = Taulells.getTaulells().get(key);
                T[i] = key + " --> " + t.getTcela() + "," + t.getTadjacencia() + "," + t.getFiles() + "," + t.getColumnes();
                i++;
            }
            return T;
        }

        public void borrar_tablero(String idT) {
            Taulells.getTaulells().remove(idT);
        }

        public void nuevoHidatoQ(String[][] m, String idT, String nick, int idH) {
            HidatoQ h = new HidatoQ(HidatosQ.getHidatosQ().size(), nick, Taulells.getTaulells().get(idT), m);
            if (idH == -1) HidatosQ.getHidatosQ().put(HidatosQ.getHidatosQ().size(), h);
            else {
                HidatosQ.getHidatosQ().put(idH, h);
                guardar_hidatoQ(HidatosQ);
            }
        }

        public void nuevoHidatoT(String[][] m, String idT, String nick, int idH) {
            HidatoT h = new HidatoT(HidatosT.getHidatosT().size(), nick, Taulells.getTaulells().get(idT), m);
            if (idH == -1) HidatosT.getHidatosT().put(HidatosT.getHidatosT().size(), h);
            else {
                HidatosT.getHidatosT().put(idH, h);
                guardar_hidatoT(HidatosT);
            }
        }

        public void nuevoHidatoH(String[][] m, String idT, String nick, int idH) {
            HidatoH h = new HidatoH(HidatosH.getHidatosH().size(), nick, Taulells.getTaulells().get(idT), m);
            if (idH == -1) HidatosH.getHidatosH().put(HidatosH.getHidatosH().size(), h);
            else {
                HidatosH.getHidatosH().put(idH, h);
                guardar_hidatoH(HidatosH);
            }
        }

        public String[] getHidatos() {
            String[] T = new String[HidatosQ.getHidatosQ().size() + HidatosT.getHidatosT().size() + HidatosH.getHidatosH().size()];
            int i = 0;
            for (Integer key : HidatosQ.getHidatosQ().keySet()) {
                Hidato h = HidatosQ.getHidatosQ().get(key);
                T[i] = key + " --> Q," + h.taulell.getTadjacencia() + "," + h.getNickname() + "," + h.getFilesH() + "," + h.getColumnesH();
                i++;
            }
            for (Integer key : HidatosT.getHidatosT().keySet()) {
                Hidato h = HidatosT.getHidatosT().get(key);
                T[i] = key + " --> T," + h.taulell.getTadjacencia() + "," + h.getNickname() + "," + h.getFilesH() + "," + h.getColumnesH();
                i++;
            }
            for (Integer key : HidatosH.getHidatosH().keySet()) {
                Hidato h = HidatosH.getHidatosH().get(key);
                T[i] = key + " --> H," + h.taulell.getTadjacencia() + "," + h.getNickname() + "," + h.getFilesH() + "," + h.getColumnesH();
                i++;
            }
            return T;
        }

        public String[] getRanking(String id) {
            return cj.ranking(id);
        }

        public String[][] mostrarHidato(Integer k, String tc) {
            switch (tc) {
                case "Q":
                    return HidatosQ.getHidatosQ().get(k).getMContingut();
                case "T":
                    return HidatosT.getHidatosT().get(k).getMContingut();
                default:
                    return HidatosH.getHidatosH().get(k).getMContingut();
            }
        }

        public int novaPartida(String nick, String tc, Integer idH) {
            Hidato h;
            if (tc.equals("Q")) h = HidatosQ.getHidatosQ().get(idH);
            else if (tc.equals("T")) h = HidatosT.getHidatosT().get(idH);
            else h = HidatosH.getHidatosH().get(idH);
            int idP = Partidas.partides_guardades.size();
            Partidas.partides_guardades.put(idP, new Partida(idP, nick, h));
            guardar_partida(Partidas);
            return idP;
        }

        public String[][] getMContingutPartida(Integer idP) {
            return Partidas.partides_guardades.get(idP).getHidato().getMContingut();
        }

        public void guardarMatriz(String[][] m, int idp) {
            Partida p = Partidas.partides_guardades.get(idp);
            for (int i = 0; i < m.length; i++)
                for (int j = 0; j < m[0].length; j++)
                    if (!"?".equals(m[i][j]) && !"*".equals(m[i][j]) && !"#".equals(m[i][j])) p.posarNum(i, j, m[i][j]);
        }

        public boolean validarPartida(int idp) {
            Partida p = Partidas.partides_guardades.get(idp);
            return p.validar();
        }

        public void guardarPartida(int idP, String[][] m, int t) {
            Partida p = Partidas.partides_guardades.get(idP);
            p.setTemps(t);
            Partidas.partides_guardades.put(idP, p);
        }

        public String[][] resolverHidato(int idP, boolean b) {
            Partida p = Partidas.partides_guardades.get(idP);
            return p.resol_hidato(b);
        }

        /***************************************************/


        Ctrl_Persistencia cp = new Ctrl_Persistencia();

        /**
         * Guarda en la capa de persistencia la instancies de la clase Conj_partida en forma de String
         *
         * @param conj_partida objeto a guardar en la capa de persistencia
         */
        public void guardar_partida(Conj_partida conj_partida) {
            Gson gson = new Gson();
            String p = gson.toJson(conj_partida);
            cp.guardar_partida(p);
        }

        /**
         * Devuelve el objeto Conj_partida guardado en la capa de persistencia en forma de String
         *
         * @return devuelve la instancia de la clase Conj_artida que se encuentra en la capa de persistencia
         */
        public Conj_partida cargar_Partida() {
            Gson gson = new Gson();
            String s = cp.cargar_partida();
            return gson.fromJson(s, Conj_partida.class);
        }

        /**
         * Guarda una instancia de la clase Usuari en forma de String en la capa de persistencia
         *
         * @param usuari objecto que será convertido a String para ser guardado
         */
        public Boolean guardar_usuari(Usuari usuari) {
            Gson gson = new Gson();
            Usuari us = cargar_usuari(usuari.getnikname());
            if (us == null) {
                String u = gson.toJson(usuari);
                cp.guardar_usuari(u);
                return true;
            }
            return false;
        }

        /**
         * Devuelve un objeto Usuari guardado en la capa de persistencia en forma de String
         *
         * @param nick Identificador del objecto a devolver
         * @return devuelve una instancia de la clase Usuari que se encuentra en la capa de persistencia
         */
        public Usuari cargar_usuari(String nick) {
            Gson gson = new Gson();
            return gson.fromJson(cp.cargar_usuari(), Usuari.class);
        }

        /**
         * Guarda una instancia de la clase Hidato en forma de String en la capa de persistencia
         *
         * @param hidato objecto que será convertido a String para ser guardado
         */
        public void guardar_hidatoT(HashHidatoT hidato) {
            Gson gson = new Gson();
            String h = gson.toJson(hidato);
            cp.guardar_hidatoT(h);
        }

        /**
         * Guarda una instancia de la clase Hidato en forma de String en la capa de persistencia
         *
         * @param hidato objecto que será convertido a String para ser guardado
         */
        public void guardar_hidatoQ(HashHidatoQ hidato) {
            Gson gson = new Gson();
            String h = gson.toJson(hidato);
            cp.guardar_hidatoQ(h);
        }

        /**
         * Guarda una instancia de la clase Hidato en forma de String en la capa de persistencia
         *
         * @param hidato objecto que será convertido a String para ser guardado
         */
        public void guardar_hidatoH(HashHidatoH hidato) {
            Gson gson = new Gson();
            String h = gson.toJson(hidato);
            cp.guardar_hidatoH(h);
        }

        /**
         * Devuelve un objeto Hidato guardado en la capa de persistencia en forma de String
         * @return devuelve una instancia de la clase Hidato que se encuentra en la capa de persistencia
         */
        public HashHidatoQ cargar_hidatoQ() {
            Gson gson = new Gson();
            return gson.fromJson(cp.cargar_hidatoQ(), HashHidatoQ.class);
            /**
             * Devuelve un objeto Hidato guardado en la capa de persistencia en forma de String
             *
             * @param idH Identificador del objecto a devolver
             * @return devuelve una instancia de la clase Hidato que se encuentra en la capa de persistencia
             */
        }
        public HashHidatoT cargar_hidatoT() {
            Gson gson = new Gson();
            return gson.fromJson(cp.cargar_hidatoT(), HashHidatoT.class);
        }
        /**
         * Devuelve un objeto Hidato guardado en la capa de persistencia en forma de String
         * @return devuelve una instancia de la clase Hidato que se encuentra en la capa de persistencia
         */
        public HashHidatoH cargar_hidatoH() {
            Gson gson = new Gson();
            return gson.fromJson(cp.cargar_hidatoH(), HashHidatoH.class);
        }

        /**
         * Guarda una instancia de la clase Taulell en forma de String en la capa de persistencia
         * @param taulell objecto que será convertido a String para ser guardado
         */
        public void guardar_taulell(HashTaulell taulell) {
            cp = new Ctrl_Persistencia();
            Gson gson = new Gson();
            String t = gson.toJson(taulell);
            cp.guardar_taulell(t);
        }

        /**
         * Devuelve un objeto Taulell guardado en la capa de persistencia en forma de String
         * @return devuelve una instancia de la clase Taulell que se encuentra en la capa de persistencia
         */
        public HashTaulell cargar_taulell() {
            cp = new Ctrl_Persistencia();
            Gson gson = new Gson();
            return gson.fromJson(cp.cargar_taulell(), HashTaulell.class);
        }
    }
}

