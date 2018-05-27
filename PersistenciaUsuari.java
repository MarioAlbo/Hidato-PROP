import java.io.*;

public class PersistenciaUsuari {
    public void guardar_U(Usuari u, File file) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(u);
            oos.close();
        }
        catch (IOException e) {}
    }
    public Usuari cargar_U(File file) {
        ObjectInputStream ois;
        Usuari u = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            u = (Usuari) ois.readObject();
            ois.close();
            return u;
        } catch (FileNotFoundException e) {
            Usuari err = null;
            return err;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return u;
    }
}
