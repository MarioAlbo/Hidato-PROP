public class Tiempo implements Runnable {
    private Thread t;
    public int time = 0;

    public Tiempo(){
        t = new Thread(this,"t1");
        t.start();
    }

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
