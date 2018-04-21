/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hidato;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author Adrián
 */
public class CtrlPersistencia {
    
    private CtrlDomini CtrlD;
    private File usuarios;
    
    public CtrlPersistencia(){
        usuarios = new File("users.txt");
        if(!usuarios.exists()){
            try{
                usuarios.createNewFile();
            }
            catch(IOException ex){}
        }
    }
    
    public void AltaUsuari(String nick, String psw){
        try{
            FileWriter wu = new FileWriter(usuarios,true);
            wu.write(nick+" "+psw+"\n");
            wu.close();
        }
        catch(IOException e){}
    }
    
}
