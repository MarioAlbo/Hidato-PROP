public class Usuari {
    private String nikname;
    private String psw;

    public String getnikname(){
        return nikname;
    }

    public String getPsw(){
        return psw;
    }

    public Usuari(String nom, String contraseña){
        System.out.println("Nuevo usuario: "+nom+" "+contraseña);
        nikname = nom;
        psw = contraseña;
    }

    public void cambiarcontraseña(String contraseña){
        psw = contraseña;
    }

    public void cambiarnikname(String nom){
        nikname = nom;
    }
}
 
