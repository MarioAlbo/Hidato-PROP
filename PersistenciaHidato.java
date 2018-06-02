import java.io.*;

public class PersistenciaHidato {

    /**
     * Guarda en el File file el contenido de Hidato h serializado
     * @param h Hidato que serà serializado y guardado en el File file
     * @param file File que contendrá el contenido de Hidato h
     */
    public void guardar_H(Hidato h, File file) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(h);
            oos.close();
        }
        catch (IOException e) {}
    }

    /**
     * Devuelve el Hidato guardado guardado en el File file, si este existe
     * @param file File el cual contiene el Hidato serializado
     * @return devuelve el Hidato guardado en el File file
     */
    public Hidato cargar_H(File file) {
        ObjectInputStream ois;
        Hidato h = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            h = (Hidato) ois.readObject();
            ois.close();
            return h;
        } catch (FileNotFoundException e) {
            Hidato err = null;
            return err;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return h;
    }

    /**
     * Borrar el File file si este existe de la carpeta Persistencia
     * @param file File que será borrado si existe
     */
    public void borrar_hidato(File file) {
        file.delete();
    }
}
