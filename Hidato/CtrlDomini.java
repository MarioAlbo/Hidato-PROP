package Hidato;

public class CtrlDomini { 
   // private CtrlPresentacio CtrlPres;
    private CtrlPersistencia CtrlPers;
    
    public CtrlDomini(){
        //CtrlPres=new CtrlPresentacio();
        CtrlPers=new CtrlPersistencia();
    }
    
    public void AltaUsuari(String nick, String psw){
        Usuari a = new Usuari(nick,psw);
        CtrlPers.AltaUsuari(nick, psw);
    }
}
