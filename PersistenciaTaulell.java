import java.io.*;

public class PersistenciaTaulell {

    /**
     * Guarda en el File file el contenido de Taulell t serializado
     * @param t Taulell que será guardado en el File file
     * @param file File que contendrá el valor de Taulell t serializado
     */
    public void guardar_T (String t, File file) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(t);
            oos.close();
        }
        catch (IOException e) {}
    }

    /**
     * Devuelve el Tablero guardado guardado en el File file, si este existe
     * @param file File el cual contiene el Taulell serializado
     * @return devuelve el Taulell guardado en el File file
     */
    public String cargar_T (File file) {
        ObjectInputStream ois;
        String t = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            t = (String) ois.readObject();
            ois.close();
            return t;
        } catch (FileNotFoundException e) {
            String err = null;
            return err;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * Borrar el File file si este existe de la carpeta Persistencia
     * @param file File que será borrado si existe
     */
    public void borrar_taulell(File file) {
        file.delete();
    }
}
