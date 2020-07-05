/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GpService;

import GpUser.User;
import GpRoom.Room;
import DBConnect.DBConnector;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
/**
 *
 * @author valtan
 */
public class LoginService {
    @EJB
    DBConnector connessione = new DBConnector();
    
    
    public boolean authenticateUser(String mail, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        User user = getUserByUserMail(mail);  
        
        if(user!=null && user.getMail().equals(mail) && user.getPassword().equals(password)){
            return true;
        }else{ 
            return false;
        }
    }

    public User getUserByUserMail(String mail) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        User user = null;
        try {
            System.out.println("E' stata chiamata LoginService.java: mi collego al DB");
           
           connessione.connect();
           return connessione.login(mail);
           
        }catch(Exception ex){
            System.out.println("Eccezione LoginService(getUserByUserMail) "+ex);
            return user;
        }
    }

    /*
    PER AGGIUNGERE NUOVE AULE NEL GESTORE BASTA AGGIUNGERE IL LORO NOME
    NELLA LISTA QUI PRESENTE
    */
    public List<Room> getListOfRoom() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        List<Room> list = new ArrayList<Room>();
            list.add(new Room("a11"));
            list.add(new Room("a12"));
            list.add(new Room("a13"));
            list.add(new Room("a14"));
            list.add(new Room("a21"));
            list.add(new Room("a22"));
            list.add(new Room("a23"));
            list.add(new Room("a24"));
            list.add(new Room("b11"));
            list.add(new Room("b12"));
            list.add(new Room("b13"));
            list.add(new Room("b14"));
            list.add(new Room("b21"));
            list.add(new Room("b22"));
            list.add(new Room("b23"));
            list.add(new Room("b24"));
            list.add(new Room("c11"));
            list.add(new Room("c12"));
            list.add(new Room("c13"));
            list.add(new Room("c14"));
            list.add(new Room("c21"));
            list.add(new Room("c22"));
            list.add(new Room("c23"));
            list.add(new Room("c24"));
            
            
            
            return list;
     }
    
}

