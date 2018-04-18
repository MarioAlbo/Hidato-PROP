import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        /// PROBAR USUARIS ///

        /*
        Usuari a = new Usuari("adri", "1234");
        Usuari b = new Usuari("name", "contra");

        System.out.println(a.getnikname() + " --> " + a.getPsw());
        System.out.println(b.getnikname() + " --> " + b.getPsw());

        String tmp = a.getPsw();
        a.cambiarcontraseña(b.getPsw());
        b.cambiarcontraseña(tmp);

        System.out.println(a.getnikname() + " --> " + a.getPsw());
        System.out.println(b.getnikname() + " --> " + b.getPsw());

        */

        ///PROBAR PARTIDA///

        Usuari a = new Usuari("adri", "1234");

        Partida p = new Partida(1,a.getnikname(),0);

        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce acción(poner/borrar):");
        String s = teclado.nextLine();

        while(s.length()!= 0) {
            if(s.equals("poner")) {
                System.out.print("Introduce posicion(x,y) y numero (n):");
                String k[] = teclado.nextLine().split(" ");
                p.PosarNum(Integer.parseInt(k[0]), Integer.parseInt(k[1]), k[2]);
            }
            else if(s.equals("borrar")){
                System.out.print("Introduce posicion(x,y):");
                String k[] = teclado.nextLine().split(" ");
                p.borrarNum(Integer.parseInt(k[0]),Integer.parseInt(k[1]));
            }
            System.out.print("Introduce acción(poner/borrar):");
            s = teclado.nextLine();
        }

        p.acabar();
        System.out.println("Tiempo: "+p.getTemps()+"s");
    }
}
