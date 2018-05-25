import java.util.*;


public class Main {
public static Conj_partida repositori_partides = new Conj_partida();
    private static void menuPartida(Partida p){
        String s = "";
        while(!s.equals("0")){
            Scanner teclado = new Scanner(System.in);
            System.out.println("Menu:\n"
                    + "0 --> Sortir\n"
                    + "1 --> Posar Num\n"
                    + "2 --> Borrar Num\n"
                    + "3 --> Surrender\n"
                    + "4 --> Validar\n"
                    + "5 --> Guardar \n");

            s = teclado.nextLine();
            if(s.length() == 1){
                switch(s){
                    case "1":
                        System.out.println("Posar numero: coordx coordy num");
                        String n = teclado.nextLine();
                        String[] h = n.split(" ");
                        p.posarNum(Integer.parseInt(h[0]),Integer.parseInt(h[1]),h[2]);
                        break;
                    case "2":
                        System.out.println("Borrar numero: coordx coordy");
                        n = teclado.nextLine();
                        h = n.split(" ");
                        p.borrarNum(Integer.parseInt(h[0]),Integer.parseInt(h[1]));
                        break;
                    case "3":
                        p.resol_hidato();
                        break;
                    case "4":
                        p.validar();
                        if(p.getAcabat()) s = "0";
                        break;
                    case "5":
                        repositori_partides.guardar_partida(p);
                        System.out.println("La partida " + p.getIdP()+ " ha sigut guardada");
                        break;
                    default:
                        p.acabar();
                        break;
                }
            }
        }
    }

    public static void main(String[] args){
        Map<String,Taulell> Taulells = new HashMap<>();
        Map<Integer,Hidato> Hidatos = new HashMap<>();
        Map<Integer,Partida> Partidas = new HashMap<>();
        Map<String, Usuari> jugadors = new HashMap<>();
        Integer idHidato = 0;
        String nombre = "Anon";
        String s = "";
        while(!s.equals("0")){
            Scanner teclado = new Scanner(System.in);
            System.out.println("COMANDOS:\n"
                    + "0 --> Sortir\n"
                    + "1 --> Crear usuari\n"
                    + "2 --> Crear hidato manualment\n"
                    + "3 --> Crear hidato automaticament\n"
                    + "4 --> Modificar hidato\n"
                    + "5 --> Jugar\n"
                    + "6 --> Crear Taulell\n"
                    + "7 --> Modificar Taulell\n"
                    + "8 --> Crear Hidato a partir de Taulell\n"
                    + "9 --> Veure Llista Hidatos\n"
                    + "10--> Veure Llita Taulells\n"
                    + "11--> Cargar Partida\n"
                    + "12--> Borrar Partida");
            s = teclado.nextLine();
            if(s.length()== 1 || s.length()== 2) switch (s) {
                case "1":
                    System.out.print("nick:");
                    String nick = teclado.nextLine();
                    System.out.print("password:");
                    String password = teclado.nextLine();
                    Usuari u = new Usuari(nick, password);
                    jugadors.put(nick, u);
                    System.out.println("Usuari: " + u.getnikname()
                            + " " + u.getPsw());
                    break;
                case "2":
                    System.out.println("id Hidato: " + ++idHidato);
                    Taulell t = new Taulell();
                    Hidato hM = new Hidato(idHidato,nombre,t);
                    Hidatos.put(idHidato,hM);
                    break;
                case "3":
                    System.out.print("dificultat [1..3]:");
                    Integer dificultat = Integer.parseInt(teclado.nextLine());
                    Hidato hA = new Hidato(++idHidato,nombre,dificultat);
                    Hidatos.put(idHidato,hA);
                    System.out.println("Hidato creat amb id: " + idHidato);
                    break;
                case "4":
                    System.out.print("id Hidato:");
                    int idH = Integer.parseInt(teclado.nextLine());
                    if (Hidatos.containsKey(idH)) {
                        Hidato hidato = Hidatos.get(idH);
                        hidato.posarForats();
                        hidato.posarNumeros();
                        hidato.imprimirMContingut();
                        Hidatos.put(idH, hidato);
                    } else System.out.println("L'hidato " + idH + " no "
                            + "exsisteix");
                    break;
                case "5":
                    if (Hidatos.size() > 0) {
                        System.out.print("nickname:");
                        nick = teclado.nextLine();
                        if (jugadors.containsKey(nick)) {
                            System.out.print("Seleciona hidato: \n");
                            for (Integer key : Hidatos.keySet()) {
                                System.out.print(key + " ");
                            }
                            System.out.println();
                            idH = Integer.parseInt(teclado.nextLine());
                           // Hidatos.get(idH).imprimirMContingut();
                            Partida partida = new Partida(Partidas.size(), nick,
                                    Hidatos.get(idH));
                            partida.getHidato().imprimirMContingut();
                            menuPartida(partida);
                            Partidas.put(Partidas.size(), partida);
                        }
                        else System.out.println("No existeix l'usuari amb nick " + nick);
                    }
                    else System.out.println("Crea un hidato abans");
                    break;
                case "6":
                    System.out.print("nom del taulell:");
                    String name = teclado.nextLine();
                    Taulell taulell = new Taulell(); //AQUESTA JA LLEGEIX
                    //taulell.llegirTaulell(); <------ NO SHA DE POSAR
                    taulell.posarForats();
                    Taulells.put(name, taulell);
                    break;
                case "7":
                    if (Taulells.size() > 0) {
                        System.out.print("nom del taulell:");
                        name = teclado.nextLine();
                        if (Taulells.containsKey(name)) {
                            taulell = Taulells.get(name);
                            taulell.posarForats();
                            taulell.treuForats();
                            taulell.imprimirMContingut();
                            Taulells.put(name, taulell);
                        } else System.out.println("Taulell " + name + " no "
                                + "exsisteix");
                    } else System.out.println("Crea un Taulell abans");
                    break;
                case "8":
                    if (Taulells.size() > 0) {
                        System.out.print("nom del creador del hidato:");
                        nick = teclado.nextLine();
                        System.out.print("nom del taulell:");
                        name = teclado.nextLine();
                        if (Taulells.containsKey(name)) {
                            idH = Hidatos.size() + 1;
                            Hidato hidato = new Hidato(idH, nick, Taulells.get(name));
                            Hidatos.put(idH, hidato);
                        } else System.out.println("Taulell " + name +" no "
                                + "exsisteix");
                    } else System.out.println("Crea un Taulell abans");
                    break;
                case "9":
                    if (Hidatos.size() == 0){
                        System.out.println("encara no hi ha cap hidato");
                    }
                    else {
                        System.out.print("id hidatos: ");
                        for (Integer key : Hidatos.keySet()) {
                            System.out.print(key + " ");
                        }
                        System.out.println();
                        System.out.print("veure Hidato amb id: ");
                        int idHida = Integer.parseInt(teclado.nextLine());
                        Hidatos.get(idHida).imprimirMContingut();
                    }
                    break;
                case "10":
                    if (Taulells.size() == 0){
                        System.out.println("encara no hi ha cap taulell");
                    }
                    else {
                        System.out.print("taulells: ");
                        for (String key : Taulells.keySet()) {
                            System.out.print(key + " ");
                        }
                        System.out.println();
                        System.out.print("veure Taulell de nom: ");
                        String nomTaulell = teclado.nextLine();
                        Taulells.get(nomTaulell).imprimirMContingut();
                    }
                    break;
                case "11":
                    if (repositori_partides.partides_guardades.size() == 0) {
                        System.out.print("No hi ha partides guardades\n");
                    }
                    else {
                        System.out.print("Partides: \n");
                        for (Map.Entry<Integer, Partida> entry : repositori_partides.partides_guardades.entrySet()) {
                            System.out.print(entry.getKey() + "\n");
                        }
                        System.out.print("Quina partidas vols carregar?\n ");
                        String idP = teclado.next();
                        int idp = Integer.parseInt(idP);
                        if (repositori_partides.partides_guardades.containsKey(idp)) {
                            Partida p = repositori_partides.cargar_partida(idp);
                            p.getHidato().imprimirMContingut();
                            menuPartida(p);
                        } else {
                            System.out.print("La partida amb ID " + idp + " no existeix\n");
                        }
                    }
                    break;
                case "12":
                    if (repositori_partides.partides_guardades.size() == 0) {
                        System.out.print("No hi ha partides guardades \n");
                    }
                    else {
                        System.out.print("Partides: \n");
                        for (Map.Entry<Integer, Partida> entry : repositori_partides.partides_guardades.entrySet()) {
                            System.out.print(entry.getKey() + "\n");
                        }
                        System.out.print("Quina partidas vols borrar?\n ");
                        String idPp = teclado.next();
                        int idpp = Integer.parseInt(idPp);
                        if (repositori_partides.partides_guardades.containsKey(idpp)) {
                            repositori_partides.borrar_partida(idpp);
                        } else {
                            System.out.print("La partida amb ID " + idpp + " no existeix\n");
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
}