
import java.util.Scanner;

public class TaulellDriver {
    public static void main(String[] args) {
        Boolean b = true;
        String s;
        Taulell taulell_prova = new Taulell("Q,C,4,4");
        while (b) {
            System.out.println("COMANDOS:\n"
                    + "0 --> Sortir\n"
                    + "1 --> Posar forats\n"
                    + "2 --> Treure forats\n"
                    + "3 --> Imprimir maatriu de contingut\n");
            Scanner teclado = new Scanner(System.in);
            s = teclado.nextLine();
            switch (s) {
                case "0":
                    b = false;
                    break;
                case "1":
                    taulell_prova.imprimirMContingut();
                    taulell_prova.posarForats();
                    taulell_prova.imprimirMContingut();
                    break;
                case "2":
                    taulell_prova.imprimirMContingut();
                    taulell_prova.treuForats();
                    taulell_prova.imprimirMContingut();
                    break;
                case "3":
                    taulell_prova.imprimirMContingut();
                    break;
            }
        }
    }
}
