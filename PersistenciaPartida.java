import java.io.*;

public class PersistenciaPartida {

    /**
     * Función que escribe en un File file  el objecto serializado Conj_partida cp
     * @param cp objecto Conj_partida que se guardará en el File file
     * @param file File que tendrá como contenido Conj_partida cp serializado
     */
   public void guardar_CP(String cp, File file) {
       ObjectOutputStream oos;
       try {
           oos = new ObjectOutputStream(new FileOutputStream(file));
           oos.writeObject(cp);
           oos.close();
       }
       catch (IOException e) {}
   }

    /**
     * Lee del File file el Conj_partida guardado en caso de que File file exista
     * @param file File File que buscará para cargar Conj_partida
     * @return valor que contine File file si este existe
     */
   public String cargar_CP(File file) {
       ObjectInputStream ois;
       String cp = null;
       try {
           ois = new ObjectInputStream(new FileInputStream(file));
           cp =  (String) ois.readObject();
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
