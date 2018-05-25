import java.util.Scanner;

public class HidatoDriver {
    public static void main(String[] args) {
        Boolean b = true;
        String s;
        Hidato hidato_prova = new Hidato(1, "nicky", 1);
        while (b) {
            System.out.println("COMANDOS:\n"
                    + "0 --> Sortir\n"
                    + "1 --> Generar matriu d'adjacencia i la imprimeix per pantalla\n"
                    + "2 --> Posar forats(*) i imprimir matriu de contingut per pantalla\n"
                    + "3 --> Posar números i imprimir maatriu de contingut\n"
                    + "4 --> Resol un hidato de forma automàtica\n"
                    + "5 --> Idicar si un hidato està resolt de forma correcte");
            Scanner teclado = new Scanner(System.in);
            s = teclado.nextLine();
            switch (s) {
                case "0":
                    b = false;
                    break;
                case "1":
                    hidato_prova.generaMatAdj();
                    hidato_prova.imprimirMatAdj();
                    break;
                case "2":
                    hidato_prova.posarForats();
                    hidato_prova.imprimirMContingut();
                    break;
                case "3":
                    hidato_prova.posarNumeros();
                    hidato_prova.imprimirMContingut();
                    break;
                case "4":
                    hidato_prova.resol();
                    hidato_prova.imprimirMContingut();
                    break;
                case "5":
                    if (hidato_prova.validar()) {
                        System.out.println("Hidato resolt correctament");
                    }
                    else {
                        System.out.println("Hidato incorrecte");
                    }
                    break;
            }
        }
    }
}
