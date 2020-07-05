/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GpService;

import DBConnect.DBConnector;
import GpRoom.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author valtan
 */
public class RoomService {
    DBConnector connessione = new DBConnector();
    
    public Room getRoomByName(String nome) throws SQLException, ClassNotFoundException{
        Room room = null;
            connessione.connect();
            return connessione.selectRoom(nome);
    }
    
    // ATTENTION PLEASE, THE TRAIN.. ah no.. giornofine < giornoinizio !!!
    // "sono un americano semplice", la settimana inizia di domenica
    //  1 dom, 2 lun, 3 mar, 4 mer, 5 gio, 6 ven, 7 sab
    public Room getViewOfRoom(String nomeaula, int orainizio, int orafine, Calendar giornoinizio, int weekindex) throws SQLException, ClassNotFoundException{
        Room aula = new Room(nomeaula);
        try {
            //Calendar c = Calendar.getInstance();
            Calendar tomorrow = (Calendar)giornoinizio.clone();
            //Calendar.getInstance().add(tomorrow.DATE, 5);
            System.out.println("Indice settimana "+ weekindex);
           
                giornoinizio.add(Calendar.DATE, 5*(weekindex-1));
                tomorrow.add(Calendar.DATE, (5*weekindex));
           
            Date data = giornoinizio.getTime();
            Date dateOfTomorrow = tomorrow.getTime();
            
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String FormattedDate = df.format(data);

            String FormattedDateOfTomorrow = df.format(dateOfTomorrow);
            
            connessione.connect();
            ResultSet res = connessione.getViewOfRoom(nomeaula, FormattedDate, FormattedDateOfTomorrow);

            System.out.println("We calendario di oggi: " + giornoinizio);
            int dayOfMonth = giornoinizio.getActualMaximum(giornoinizio.DAY_OF_MONTH);

            while(res.next()){ // scorro righe del risultato (query)

                // mi serve per fare il modulo del mese corrente ed individuare la colonna corretta
                //per inserire il valore nella vistaaula

                int ora = Integer.parseInt(res.getString("orainizio"))-8;
                String[] date = res.getString("giorno").split("-");
                int day = Integer.parseInt(date[2]);
                int indexOfColumn = 0;

                //System.out.println("giornoinizio " + giornoinizio );
                //System.out.println("day " + day );
                //System.out.println("dayOfMonth " + dayOfMonth );
                
                int giornoinizioint =giornoinizio.get(Calendar.DAY_OF_MONTH);
                //aula.getView()[ora][] = res.getString("utente");
                if ((day - giornoinizioint)>=0)
                    indexOfColumn = (day - giornoinizioint)%6;
                else
                    indexOfColumn = (day - giornoinizioint + dayOfMonth)%6;
                //System.out.println("righe " + ora );
                //System.out.println("index " + indexOfColumn );

                // vistaSettimanale e' una matrice di stringhe
                //per ogni ora dei giorni c'e' lo stato
                //che setto qui
                //dopo la query al DB
                aula.getView()[ora][indexOfColumn] = res.getString("utente");

                
            }
            
            connessione.close();
            return aula;
        }catch(SQLException ex){
            System.out.println("UAGLIO CHE DB ECCEZZZZZZIONALE: getviewofroom");
            System.out.println(ex.getMessage());
            return aula;
        }
    }

}
