import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    
    private static void menuPartida(Partida p){
        String s = "";
        while(!s.equals("0")){
            Scanner teclado = new Scanner(System.in);
            System.out.println("Menu:\n"
                    + "0 --> Sortir\n"
                    + "1 --> Posar Num\n"
                    + "2 --> Borrar Num\n");
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
                    default:
                        p.acabar();
                        System.out.println("Temps:"+p.getTemps()+"s");
                        break;
                }
            }
        }
    }

    public static void main(String[] args){
        Map<String,Taulell> Taulells = new HashMap<>();
        Map<Integer,Hidato> Hidatos = new HashMap<>();
        Map<Integer,Partida> Partidas = new HashMap<>();
        Integer idHidato = 0;
        String nombre = "Anon";
        String s = "";
        while(!s.equals("0")){
            Scanner teclado = new Scanner(System.in);
            System.out.println("COMANDOS:\n"
                    + "0 --> Sortir\n1 --> Crear usuari\n"
                    + "2 --> Crear hidato manualment\n"
                    + "3 --> Crear hidato automaticament\n"
                    + "4 --> Modificar hidato\n5 --> Jugar\n"
                    + "6 --> Crear Taulell\n7 --> Modificar Taulell\n"
                    + "8 --> Crear Hidato a partir de Taulell\n"
                    + "9 --> Veure Llista Hidatos\n"
                    + "10--> Veure Llita Taulells");
            s = teclado.nextLine();
            if(s.length()== 1 || s.length()== 2) switch (s) {
                case "1":
                    System.out.print("nick:");
                    String nick = teclado.nextLine();
                    System.out.print("password:");
                    String password = teclado.nextLine();
                    Usuari u = new Usuari(nick, password);
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
                    System.out.print("dificultat [1..5]:");
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
                        System.out.print("Seleciona hidato: ");
                        for (Integer key : Hidatos.keySet()) {
                            System.out.print(key + " ");
                        }
                        System.out.println();
                        idH = Integer.parseInt(teclado.nextLine());
                        Hidatos.get(idH).imprimirMContingut();
                        Partida partida = new Partida(Partidas.size(), nick,
                                Hidatos.get(idH));
                        menuPartida(partida);
                        Partidas.put(Partidas.size(), partida);
                    } else System.out.println("Crea un hidato abans");
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
                default:
                    break;
            }
        }
    }
}
