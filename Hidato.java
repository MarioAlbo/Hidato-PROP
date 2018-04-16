import java.util.Scanner;
import Taulell;
public class Hidato {
    public int idH,iduser,
    public Taulell topologia;
    public char dificultat;
    // ?? forats;

    public Hidato(int idh, int idusr, Taulell topo) {
        this.idH = idh;
        this.iduser = idusr;
        this.topologia = topo;
    }

    public void editar_idh(int idh) {
        idH = idh;
    }
    public void editar_iduser(int idusr) {
        iduser = idusr;
    }

}
