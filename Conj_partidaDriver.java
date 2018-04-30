import java.util.Scanner;

public class Conj_partidaDriver {
    public static void main(String[] args) {
        String s = "";
        Conj_partida conj_partida_prova = new Conj_partida();
        Boolean b = true;
        Usuari usuari_prova = new Usuari("nick", "123");

        int diff = 1;
        Hidato hidato_prova = new Hidato(1,usuari_prova.getnikname(),diff);

        while (b) {
            System.out.println("COMANDOS:\n"
                    + "0 --> Sortir\n"
                    + "1 --> Guardar Partida\n"
                    + "2 --> Cargar Partida\n"
                    + "3 --> Borrar Partida Guardada\n"
                    + "Abans de (2) o (3) assegurar-se de que hi hagi alguna partida guardad (1)");

            Scanner teclado = new Scanner(System.in);
            Partida partida_prova = new Partida(1, usuari_prova.getnikname(), hidato_prova);
            s = teclado.nextLine();
            switch (s) {
                case "0":
                    b = false;
                    break;
                case "1":
                    conj_partida_prova.guardar_partida(partida_prova);
                    System.out.println("La partida " + partida_prova.getIdP() + " ha sigut guardada");
                    break;
                case "2":
                    Partida partida_prova2 = conj_partida_prova.cargar_partida(1);
                    System.out.println("La partida " + partida_prova2.getIdP() + "ha sigut cargada");
                    break;
                case "3":
                    conj_partida_prova.borrar_partida(1);
                    System.out.println("La partida " + partida_prova.getIdP() + " ha sigut borrada");
                    break;
            }
        }
    }
}
