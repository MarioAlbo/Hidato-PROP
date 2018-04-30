import java.util.Scanner;

public class UsuariDriver {
    public static void main(String[] args) {
        Boolean b = true;
        String s;
        Usuari usuari_prova = new Usuari("Nicky", "123");
        while (b) {
            System.out.println("COMANDOS:\n"
                    + "0 --> Sortir\n"
                    + "1 --> Obtenit nickname\n"
                    + "2 --> Obtenir password\n"
                    + "3 --> Canviar nickname\n"
                    + "4 --> canviar password\n");
            Scanner teclado = new Scanner(System.in);
            s = teclado.nextLine();
            switch (s) {
                case "0":
                    b = false;
                    break;
                case "1":
                    System.out.println(usuari_prova.getnikname());
                    break;
                case "2":
                    System.out.println(usuari_prova.getPsw());
                    break;
                case "3":
                    System.out.println(usuari_prova.getnikname());
                    System.out.println("Introdueix nou nickname: ");
                    s = teclado.nextLine();
                    usuari_prova.cambiarnikname(s);
                    System.out.println(usuari_prova.getnikname());
                    break;
                case "4":
                    System.out.println(usuari_prova.getPsw());
                    System.out.println("Introdueix nova contrasenya: ");
                    s = teclado.nextLine();
                    usuari_prova.cambiarcontrasenya(s);
                    System.out.println(usuari_prova.getPsw());
                    break;
            }
        }
    }
}
