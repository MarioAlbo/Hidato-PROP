import java.util.ArrayList;
import java.util.Scanner;

public class PartidaDrive {
    public static void main(String[] args) {
        String s = "";
        Boolean b = true;
        Hidato hidato_prova = new Hidato(1,"nicky",1);
        Partida partida_prova = new Partida(1,"nicky",hidato_prova);

        while (b) {
            System.out.println("COMANDOS:\n"
                    + "0 --> Sortir\n"
                    + "1 --> Obetenir idP\n"
                    + "2 --> Obtenir idPlayer\n"
                    + "3 --> Obtenir el temps de partida\n"
                    + "4 --> Obtenir estat de la partida (acabada/inacabada)\n"
                    + "5 --> Canviar estat de la partida a ACABAT\n"
                    + "6 --> Igualar matriu\n"
                    + "7 --> Posar número\n"
                    + "8 --> Borrar número\n"
                    + "9 --> Hidato resoluble?\n"
                    + "10 --> Hidato resol?\n");

            Scanner teclado = new Scanner(System.in);
            s = teclado.nextLine();
            switch (s) {
                case "0":
                    b = false;
                    break;
                case "1":
                    System.out.println(partida_prova.getIdP());
                    break;
                case "2":
                    System.out.println(partida_prova.getIdplayer());
                    break;
                case "3":
                    System.out.println(partida_prova.getTemps());
                    break;
                case "4":
                    if (partida_prova.getAcabat()) {
                        System.out.println("Parrtida acabada");
                    }
                    else {
                        System.out.println("Partida inacabada");
                    }
                    break;
                case "5":
                    partida_prova.acabar();
                    break;
                case "6":
                   String[][] matriu_prova = partida_prova.getHidato().mContingut;
                   partida_prova.clonarMatriu(matriu_prova);
                   partida_prova.getHidato().imprimirMContingut();
                   break;
                case "7":
                    partida_prova.getHidato().imprimirMContingut();
                    System.out.println("Es posarà el número 7 a la posició (1,1)");
                    partida_prova.posarNum(1,1,"7");
                    partida_prova.getHidato().imprimirMContingut();
                    break;
                case "8":
                    partida_prova.getHidato().imprimirMContingut();
                    System.out.println("Es borrarà el número de la posició (1,1)");
                    partida_prova.borrarNum(1,1);
                    partida_prova.getHidato().imprimirMContingut();
                    break;
                case "9":
                    partida_prova.resol_hidato();
                    break;
                case  "10":
                    partida_prova.validar();
                    break;
            }
        }
    }
}
