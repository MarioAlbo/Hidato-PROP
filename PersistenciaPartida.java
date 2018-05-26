import java.io.*;

public class PersistenciaPartida {
   public void guardar_CP(Conj_partida cp, File file) {
       ObjectOutputStream oos;
       try {
           oos = new ObjectOutputStream(new FileOutputStream(file));
           oos.writeObject(cp);
           oos.close();
       }
       catch (IOException e) {}
   }
   public Conj_partida cargar_CP(File file) {
       ObjectInputStream ois;
       Conj_partida cp = null;
       try {
           ois = new ObjectInputStream(new FileInputStream(file));
           cp =  (Conj_partida)ois.readObject();
           ois.close();
          return cp;
       }
       catch (IOException e) {}
       catch (ArrayIndexOutOfBoundsException ae) {
           System.out.println(ae.getMessage());
       }
       catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       return cp;
   }
}
