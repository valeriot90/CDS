/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GpRoom;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author valtan
 */

public class Room implements Serializable {
    
    public String nome; // nome dell'aula
    public int posti; // posti a sedere nell'aula
    private int giorni = 6;
    private int ore =11;
    
    private String[][] vistaSettimanale= new String[ore][giorni];
    
    public Room(){
    }
    
    public Room(String nome){
        this.nome = nome;
        
        for(int i = 0; i < giorni; i++){
            for(int j = 0; j < ore; j++){
                vistaSettimanale[j][i]="";
            }
        }
    }
    
    /*
    Da RoomService creo un nuovo oggetto Room e ci metto la vista settimanale.
    Da qui la richiamo e la uso/print
    E' una matrice di stringhe!
    */
    public String[][] getView(){
        return this.vistaSettimanale;
    }

    public String getNome(){
        return nome;
    }

    
    /*
    @i e' l'ora
    @j giorni della settimana passati come indice
    
    Deve stampare il contenuto del DB (i valori stringa contenuti in esso)
    Cioe' per singola ora lo stato della prenotazione
    Se non riesce a collegarsi ritorna:
    
    */
    public String getDay(int i, int j){
        int giorno=j+2;
        int today=controllaDay();
        String result="";

        result = vistaSettimanale[i-8][j];
        return result;
    }
    
    public String getDayofWeek(int j){
        String day="";
        switch(j){
            case 0:     day="lunedi";
                        break;
            case 1:     day="martedi";
                        break;
            case 2:     day="mercoledi";
                        break;
            case 3:     day="giovedi";
                        break;
            case 4:     day="venerdi";
                        break;
            case 5:     day="sabato";
                        break;
            case -1:     day="domenica";
                        break;
        }
        return day;
    
    }

// "sono un americano semplice", la settimana inizia di domenica
//  1 dom, 2 lun, 3 mar, 4 mer, 5 gio, 6 ven, 7 sab
    public int controllaDay(){
        //System.out.println("Room.java " +GregorianCalendar.getInstance().get(Calendar.DAY_OF_WEEK));
        return GregorianCalendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }
    
// "sono un americano semplice", almeno l'orologio lo leggo come le persone normali
    public int controllaHour(){
        //System.out.println("Room.java "+GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY));
        return GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
    
}
