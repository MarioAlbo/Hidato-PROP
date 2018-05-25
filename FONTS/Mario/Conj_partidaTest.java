import org.junit.Test;

import static org.junit.Assert.*;

public class Conj_partidaTest {

    @Test
    public void guardar_partida() {
        Hidato hidato_test = new Hidato(1,"Nicky", 2);
        Partida partida_test = new Partida(1, "nicky", hidato_test);
        Conj_partida conj_partida_test = new Conj_partida();
        int tmp = conj_partida_test.partides_guardades.size();
        conj_partida_test.guardar_partida(partida_test);
        assertFalse(tmp == conj_partida_test.partides_guardades.size());
    }

    @Test
    public void cargar_partida() {
        Hidato hidato_test = new Hidato(1,"Nicky", 2);
        Partida partida_test = new Partida(1, "nicky", hidato_test);
        Partida partida_prova;
        Conj_partida conj_partida_test = new Conj_partida();
        conj_partida_test.guardar_partida(partida_test);
        partida_prova = conj_partida_test.cargar_partida(1);
        assertNotNull(partida_prova);
    }

    @Test
    public void borrar_partida() {
        Hidato hidato_test = new Hidato(1,"Nicky", 2);
        Partida partida_test = new Partida(1, "nicky", hidato_test);
        Conj_partida conj_partida_test = new Conj_partida();
        conj_partida_test.guardar_partida(partida_test);
        int tmp = conj_partida_test.partides_guardades.size();
        conj_partida_test.borrar_partida(1);
        assertFalse(tmp == conj_partida_test.partides_guardades.size());
    }
}