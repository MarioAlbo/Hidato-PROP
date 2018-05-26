import java.io.*;

public class PersistenciaHidato {
    public void guardar_H(Hidato h, File file) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(h);
            oos.close();
        }
        catch (IOException e) {}
    }

    public Hidato cargar_H(File file) {
        ObjectInputStream ois;
        Hidato h = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            h = (Hidato) ois.readObject();
            ois.close();
            return h;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return h;
    }
}
