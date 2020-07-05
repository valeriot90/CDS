/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package performancetest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author valtan
 */
public class PerformanceTest implements Runnable{
 
    private String command;
    private final String[] roombuild= {"a","b","c"};
    private final String[] roomnumber = {"11", "12","13","14","21","22","23","24"};

    public PerformanceTest(String s){
        this.command=s;
    }

    @Override
    public void run() {
        long a , b = 0;
        System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        String FormattedDate = df.format(data);
        
        try {
            String ora = getOra(), giorno = FormattedDate + "-" + getGiorno(), room = getRoom(), mail = getMail();
            Thread.sleep(1000);
            a = getTime();
            System.out.println(mail + " " + room + " " + giorno + " " + ora);
            
            connect();
            update(ora, giorno, room, mail); // all parameters must be a String
            close();
            
            b = getTime();
            System.out.println("Time: " + (b-a));
            BufferedWriter bw = null;
            
            try{
                FileWriter fw = new FileWriter("LogPerformance.txt", true);
                bw = new BufferedWriter(fw);
                //PrintWriter out = new PrintWriter(bw);
            
                //out.println("Time: " + (b-a));
                bw.write("" + (b-a));
                bw.newLine();
                bw.flush();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {              
                try {
                    bw.close();
                    // just ignore it
                } catch (IOException ex) {
                    Logger.getLogger(PerformanceTest.class.getName()).log(Level.SEVERE, null, ex);
                }
               
             } // end try/catch/finally

            
        } catch (SQLException ex) {
            System.out.println("Eccezione sql in TestPerformance2: " + ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PerformanceTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PerformanceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName()+" End.");
    }

    //return the current TimeStamp in milliseconds
    private long getTime(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
         //System.out.println(timestamp.getTime());
         return timestamp.getTime();
    }

    @Override
    public String toString(){
        return this.command;
    }
    
    //return a random value from 8 to 18
    private String getOra(){
        return Integer.toString(randInt(8, 18));
    }
    
    //Thread.currentThread().getName() --> return --> pool-1-thread-1 
    private String getMail(){
        String tmp = Thread.currentThread().getName();
        String[] split = tmp.split("-");
        return split[2] + "" + split[3] + "@gmail.com";
    }
    
    //return a random room
    private String getRoom(){
        return roombuild[randInt(0,2)] + "" + roomnumber[randInt(0,7)];
    }
    
    //return a random value from current day (int) and current day+24%num_currmonth
    private String getGiorno(){
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat df = new SimpleDateFormat("dd");
        String FormattedDate = df.format(data);
        int day = Integer.valueOf(FormattedDate);
        if (day < (day+24) % c.getActualMaximum(c.DAY_OF_MONTH))
            return Integer.toString(randInt(day, ( (day+24) % c.getActualMaximum(c.DAY_OF_MONTH) ) ));
        else
            return Integer.toString(randInt(( (day+24) % c.getActualMaximum(c.DAY_OF_MONTH) ),day ));
    }
    
    //Return a random number between min and max
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int m = max - min;
        int randomNum = rand.nextInt(m) + min;
        //System.out.println(randomNum);
        return randomNum;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();
    
    private Connection con = null;
    
    public void connect() throws SQLException, ClassNotFoundException{
        r.lock();
        //System.out.println("Lock acquired by " + );
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gap","root","root");
   
        }catch(SQLException ex){
            System.out.println("Eccezione DBConnector "+ex);
            return;
        }
        finally { r.unlock(); }
        
    }
    
    public void close() throws SQLException{
        con.close();
    }
    
    public void update (String ora, String giorno, String room, String mail) throws SQLException{
        w.lock();
        try{
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
                    System.out.println("DELETE FROM gap.prenotazioni WHERE giorno = DATE('"+ giorno +"') AND aula = '" + room + "' AND orainizio = " + ora + " AND utente = '" + mail +"'");
                    psta.executeUpdate();
                }
                else if (!risultato.contains("unipi.it")){
                     //psta= con.prepareStatement("UPDATE " + room + " SET " + giorno + " = '' WHERE ora = " + ora);
                    psta= con.prepareStatement("DELETE FROM gap.prenotazioni WHERE giorno = DATE('"+ giorno +"') AND aula = '" + room + "' AND orainizio = " + ora + " AND utente = '" + risultato+"'");
                    System.out.println("DELETE FROM gap.prenotazioni WHERE giorno = DATE('"+ giorno +"') AND aula = '" + room + "' AND orainizio = " + ora + " AND utente = '" + risultato +"'");
                    psta.executeUpdate(); 
                }

                    //System.out.println("UPDATE FATTO");
            }
            else {
                    psta= con.prepareStatement("INSERT INTO prenotazioni VALUES('" + mail + "','" + room + "','" + giorno + "','" + ora + "')");  
                    System.out.println("INSERT INTO prenotazioni VALUES('" + mail + "','" + room + "','" + giorno + "','" + ora + "')");
                    psta.executeUpdate();

            }

            con.close();
        }
        finally{w.unlock();}
    }
}
