package Hidato;

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
                
        String s = "";
        while(!s.equals("0")){
            Scanner teclado = new Scanner(System.in);
            System.out.println("COMANDOS:\n"
                + "0 --> Sortir\n1 --> Crear usuari\n"
                + "2 --> Crear hidato manualment *\n"
                + "3 --> Crear hidato automaticament *\n"
                + "4 --> Modificar hidato\n5 --> Jugar *\n"
                + "6 --> Crear Taulell\n7 --> Modificar Taulell\n"
                + "8 --> Crear Hidato a partir de Taulell");
            s = teclado.nextLine();
            if(s.length()== 1){
                switch (s){
                    case "1":
                        System.out.print("nick:");
                        String nick = teclado.nextLine();
                        System.out.print("password:");
                        String password = teclado.nextLine();
                        Usuari u = new Usuari(nick,password);
                        System.out.println("Usuari: "+u.getnikname()+" "+u.getPsw());
                        break;
                    case "2":
                        //new Hidato(...,"manual")
                        break;
                    case "3":
                        //new Hidato(...,"auto")
                        break;
                    case "4":
                        System.out.print("id Hidato:");
                        int idH = Integer.parseInt(teclado.nextLine());
                        if(Hidatos.containsKey(idH)) {
                            Hidato hidato = Hidatos.get(idH);
                            hidato.posarForats();
                            hidato.posarNumeros();
                            hidato.imprimirMContingut();
                            Hidatos.put(idH,hidato);
                        } 
                        else System.out.println("L'hidato "+idH+" no exsisteix");
                        break;
                    case "5":
                        if(Hidatos.size() > 0){
                            System.out.print("nickname:");
                            nick = teclado.nextLine();
                            System.out.print("Seleciona hidato: [0.."+
                                    (Hidatos.size()-1)+"]");
                            idH = Integer.parseInt(teclado.nextLine());
                            Partida partida = new Partida(Partidas.size(),nick,
                                    Hidatos.get(idH));
                            menuPartida(partida);
                            Partidas.put(Partidas.size(),partida);
                        }
                        else System.out.println("Crea un hidato abans");
                        break;
                    case "6":
                        System.out.print("nom del taulell:");
                        String name = teclado.nextLine();
                        Taulell taulell= new Taulell();
                        taulell.llegirTaulell();
                        taulell.posarForats();
                        Taulells.put(name,taulell);
                        break;
                    case "7":
                        if(Taulells.size()>0){
                            System.out.print("nom del taulell:");
                            name = teclado.nextLine();
                            if(Taulells.containsKey(name)){
                                taulell = Taulells.get(name);
                                taulell.posarForats();
                                taulell.treuForats();
                                taulell.imprimirMContingut();
                                Taulells.put(name,taulell);
                            }
                            else System.out.println("Taulell "+name+" no exsisteix");
                        }
                        else System.out.println("Crea un Taulell abans");
                        break;
                    case "8":
                        if(Taulells.size()>0){
                            System.out.print("nom del creador del hidato:");
                            nick = teclado.nextLine();
                            System.out.print("nom del taulell:");
                            name = teclado.nextLine();
                            if(Taulells.containsKey(name)){
                                idH = Hidatos.size();
                                Hidato hidato = new Hidato(idH,nick,Taulells.get(name));
                                Hidatos.put(idH,hidato);
                            }
                            else System.out.println("Taulell "+name+" no exsisteix");
                        }
                        else System.out.println("Crea un Taulell abans");
                    default:
                        break;
                }
            }
        }
    }
}