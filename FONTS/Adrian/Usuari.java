import java.io.Serializable;

public class Usuari implements Serializable {
    private String nikname;
    private String psw;

    /**
     * dona el nom de l'usuari
     * @return: retorna el nick de l'usari en forma de String
     */
    public String getnikname(){
        return nikname;
    }

    /**
     * dona la contrasenya de l'usuari
     * @return: retorna psw de l'usuari
     */
    public String getPsw(){
        return psw;
    }

    /**
     * Crea una instacia de la clase Usuari i imprimeix els paramentres
     * @param nom: nick de l'objecte usuari
     * @param contrasenya: psw de l'objecte usuari
     */
    public Usuari(String nom, String contrasenya){
        nikname = nom;
        psw = contrasenya;
    }

    /**
     * canvia el valor del parametre psw de la instancia d'Usuari
     * @param contrasenya: nou valor del parametre psw
     */
    public void cambiarcontrasenya(String contrasenya){
        psw = contrasenya;
    }

    /**
     * canvia el valor del parametre nicke de la instacia d'Usuari
     * @param nom: nou valor del parametre de la instancia de la classe Usuari
     */
    public void cambiarnikname(String nom){
        nikname = nom;
    }
}

