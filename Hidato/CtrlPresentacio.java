package Hidato;

public class CtrlPresentacio {
    private CtrlDomini CtrlD;
    
    public CtrlPresentacio(){
        CtrlD = new CtrlDomini();
    }
    
    public void AltaUsuari(String nick, String psw){
        CtrlD.AltaUsuari(nick, psw);
    }
}
