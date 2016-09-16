/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atacadao.almoxarifado.model;

import com.atacadao.almoxarifado.entidade.Usuario;
import com.atacadao.almoxarifado.persistencia.usuarioConexao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class CacheUsuario {
    
    public static void salvandoUsuarios(String usuario){

       Path path = Paths.get("\\files\\user.do");
       
       if (!Files.isDirectory(path.getParent())) {
            try {
                Files.createDirectory(path.getParent());
                Files.createFile(path);
            } catch (IOException ex) {
                Logger.getLogger(CacheUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("user.do");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(usuarioConexao.buscarUsuarioPorLogin(usuario));
            objectOutputStream.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CacheUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CacheUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Usuario buscarUsuarios(){

       try {
            FileInputStream fileInputStream = new FileInputStream("user.do");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Usuario user = (Usuario) objectInputStream.readObject();
                return user;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CacheUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CacheUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;
    }
}
