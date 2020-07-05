/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnect;

import GpRoom.Room;
import GpUser.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LockType;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;
/**
 *
 * @author valtan
 */
@Startup
@Singleton
@ConcurrencyManagement (ConcurrencyManagementType.CONTAINER)
public class DBConnector {
    
    
    private Connection con = null;
    
    @Lock (LockType.READ)
    public void connect() throws SQLException, ClassNotFoundException{
     
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gap","root","root");
   
        }catch(SQLException ex){
            System.out.println("Eccezione DBConnector "+ex);
            return;
        }
        
    }
    
    public void close() throws SQLException{
        con.close();
    }
    
    @Lock (LockType.READ)
    public User login(String mail) throws SQLException{
        User user = null;
        try{
            
            Statement st = con.createStatement();
            PreparedStatement psta= con.prepareStatement("SELECT * FROM utenti WHERE mail  LIKE '" + mail + "'");
            ResultSet res = psta.executeQuery();
            if(res.next()){
                String firstname = res.getString("first_name");
                String lastname = res.getString("last_name");
                String email = res.getString("mail");
                String status = res.getString("status");
                String password = res.getString("password");
                user = new User(firstname, lastname, status, email, password);
                
                //System.out.println("Res query: "+ firstname +" "+lastname+" "+email+" "+status+" "+password);
            }
            else {System.out.println("Res query vuota loginservice");}
            con.close();
            
            return user;
        }catch(SQLException ex){
            System.out.println("Eccezione DBConnector "+ex);
            return user;
        }
    }
   
    @Lock(LockType.READ)
    public Room selectRoom(String nome){
        Room room = null;
        
        try{
            Statement st = con.createStatement();
            PreparedStatement psta= con.prepareStatement("SHOW TABLES");
            ResultSet res = psta.executeQuery();
            if(res.next()){
                String name = res.getString("Tables_in_gap");
                //System.out.println("ROOM SERVICE : getRoomByName "+ name);
                if(name.equals(nome)){
                    room = new Room(name);
                    return room;
                }
            }
            else {System.out.println("Res query vuota RoomService");}
            con.close();
            return room;
        }catch(SQLException ex){
            System.out.println("UAGLIO CHE DB ECCEZZZZZZIONALE: DBConnector");
            System.out.println("Eccezione: " + ex.getMessage());
            return room;
        }
       
    }
    
    @Lock(LockType.READ)
    public int newuser(User user){
        
        try{
            Statement st = con.createStatement(); 
            st.executeUpdate("INSERT INTO utenti VALUES ( '"
                            + user.getMail() + "' , '" + user.getPassword() + "' , '" + user.getStatus() + "' , '" + user.getFirstname() + "', '" + user.getLastname() + "')" );
            con.close();
        } catch (SQLException ex) { 
            return 4;
        }
        
            System.out.print("Utente inserito");
            return 0;
    }
    
    @Lock(LockType.READ)
    public int existuser(User user) throws SQLException{
        
        try{
            Statement st = con.createStatement();
            PreparedStatement psta= con.prepareStatement("SELECT mail FROM utenti WHERE mail  LIKE '" + user.getMail() + "'");
            ResultSet res = psta.executeQuery();
            
            if(res.next()){
                con.close();
                return 1;}
            else{
                con.close();
                return 2;
            }
            
        } catch (SQLException ex) {
            System.out.print("Eccezione DBConnector: " + ex.getMessage());
            System.out.println("WE JAMM BELL ECCEZZZZZZZIONE!!!!");
           // public void response.sendRedirect("");
            //Logger.getLogger(RegisterService.class.getName()).log(Level.SEVERE, null, ex);
            return 3;
        }
    }
    
    @Lock(LockType.READ)
    public ResultSet getViewOfRoom(String nomeaula, String FormattedDate, String FormattedDateOfTomorrow) throws SQLException{
        
        
            Statement st = con.createStatement();
            //PreparedStatement psta= con.prepareStatement("SELECT utente,giorno,orainizio,orafine FROM prenotazioni WHERE aula = " + nomeaula + " AND giorno >= " + giornoinizio + " AND giorno <= " + giornofine);  // DA MODIFICARE
            PreparedStatement psta= con.prepareStatement("SELECT utente,giorno,orainizio FROM prenotazioni WHERE aula = '" + nomeaula + "' AND giorno BETWEEN DATE('" + FormattedDate +"') AND DATE('" + FormattedDateOfTomorrow +"')");  // TODO DA MODIFICARE
            //System.out.println("SELECT utente,giorno,orainizio FROM prenotazioni WHERE aula = '" + nomeaula + "' AND giorno BETWEEN DATE('" + FormattedDate +"') AND DATE('" + FormattedDateOfTomorrow +"')");
            //PreparedStatement psta= con.prepareStatement("SELECT utente,giorno,orainizio,orafine FROM prenotazioni WHERE aula = '" + nomeaula + "' AND giorno>DATE('2018-11-27')");  // TODO DA MODIFICARE

            //System.out.println("Risultato roomservice "+ res); 
            //con.close();
            return  psta.executeQuery();
        
        

    }
    
    @Lock(LockType.WRITE)
    public void update (String ora, String giorno, String room, String mail) throws SQLException{
        
        
            PreparedStatement psta= con.prepareStatement("SELECT * FROM prenotazioni WHERE orainizio = " + ora + " AND giorno = DATE('" + giorno + "') AND aula = '"+ room + "'");
            //System.out.println("SELECT * FROM prenotazioni WHERE orainizio = " + ora + " AND giorno = ('" + giorno + "') AND aula = '"+ room + "'");
            //System.out.println("SELECT");
            ResultSet res = psta.executeQuery();
            //System.out.println("SELECT FATTA");

            if(res.next()){
                String risultato = res.getString("utente"); 
                //System.out.println("RISULTATO " +risultato);

                if(mail.equals(risultato)){ //mail e' uguale a mail del database rimuovo prenotazione
                    //psta= con.prepareStatement("UPDATE " + room + " SET " + giorno + " = '' WHERE ora = " + ora);
                    psta= con.prepareStatement("DELETE FROM gap.prenotazioni WHERE giorno = DATE('"+ giorno +"') AND aula = '" + room + "' AND orainizio = " + ora + " AND utente = '" + mail+"'");
                    //System.out.println("DELETE FROM gap.prenotazioni WHERE giorno = DATE('"+ giorno +"') AND aula = '" + room + "' AND orainizio = " + ora + " AND utente = '" + mail +"'");
                    psta.executeUpdate();
                }
                else if (!risultato.contains("unipi.it")){
                     //psta= con.prepareStatement("UPDATE " + room + " SET " + giorno + " = '' WHERE ora = " + ora);
                    psta= con.prepareStatement("DELETE FROM gap.prenotazioni WHERE giorno = DATE('"+ giorno +"') AND aula = '" + room + "' AND orainizio = " + ora + " AND utente = '" + risultato+"'");
                    //System.out.println("DELETE FROM gap.prenotazioni WHERE giorno = DATE('"+ giorno +"') AND aula = '" + room + "' AND orainizio = " + ora + " AND utente = '" + risultato +"'");
                    psta.executeUpdate(); 
                }

                    //System.out.println("UPDATE FATTO");
            }
            else {
                    psta= con.prepareStatement("INSERT INTO prenotazioni VALUES('" + mail + "','" + room + "','" + giorno + "','" + ora + "')");  
                    //System.out.println("INSERT INTO prenotazioni VALUES('" + mail + "','" + room + "','" + giorno + "','" + ora + "')");
                    psta.executeUpdate();

            }

            con.close();
        
    }
    
    @Lock(LockType.WRITE)
    public void delete(String FormattedDate) throws SQLException{
       
        
            PreparedStatement psta= con.prepareStatement("SET SQL_SAFE_UPDATES=0");
            psta.executeUpdate();
            psta= con.prepareStatement("DELETE FROM gap.prenotazioni WHERE giorno < DATE('"+ FormattedDate +"')"); 
            psta.executeUpdate();
            con.close();
        
    }
}
