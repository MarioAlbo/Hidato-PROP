import java.io.*;

public class PersistenciaTaulell {
    public void guardar_T (Taulell t, File file) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(t);
            oos.close();
        }
        catch (IOException e) {}
    }

    public Taulell cargar_T (File file) {
        ObjectInputStream ois;
        Taulell t = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            t = (Taulell) ois.readObject();
            ois.close();
            return t;
        } catch (FileNotFoundException e) {
            Taulell err = null;
            return err;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return t;
    }

    public void borrar_taulell(File file) {
        if (file.delete()) System.out.println("Taulell borrat correctament");
        else System.out.println("El Taulell no estava guardat");
    }
}
