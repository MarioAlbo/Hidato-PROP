import java.io.Serializable;

public class Tiempo implements Runnable, Serializable {
    private Thread t;
    public int time = 0;

    /**
     * Crea una nova instancia de la classe Tiempo
     */
    public Tiempo(){
        t = new Thread(this,"t1");
        t.start();
    }

    /**
     * Incrementa la variable time en segons transcorreguts
     */
    public void  run(){
        while(!Partida.getAcabat()){
            try{
                t.sleep(1000);
            }
            catch (Exception e){
                System.out.println("Excepción:"+e.getMessage());
            }
            time++;
        }
        //t.destroy();
    }
}
