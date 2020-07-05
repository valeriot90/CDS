/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GpService;
import DBConnect.DBConnector;
import java.sql.SQLException;
import GpUser.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
/**
 *
 * @author valtan
 */

/*
0 = utente registrato
1 = utente già presente
2 = mail in formato non corretto
3 = controlla se unipi.it e' professore
4 = errore immissione utente
*/

public class RegisterService {
    @EJB
    DBConnector connessione = new DBConnector();
    
    public int register(User user) throws SQLException, ClassNotFoundException{
            if(isUserExists(user)==1){ 
                System.out.print("Utente esiste");
                return 1;
            }
             if(isUserExists(user)==3){ 
                System.out.print("Eccezione!!!!!!! :):):)");
                return 5;
            }
            
            //System.out.println("MO C ARPGLIAM TUTT COS");
            //FacesContext context = FacesContext.getCurrentInstance();
            String mail = user.getMail();
            if(mail.contains("@")){
                //System.out.println("E' ARRIVATA LA MAIL" +mail);
                 //System.out.println("CIUCC");
                String [] splits = mail.split("\\@");
                 //System.out.println("CAVALL");
                boolean mailprof = splits[1].equals("unipi.it"); 
                 //System.out.println("E RE");
                // prof is true if email is a doc email (ends with @unipi.it)
                String checkprof = user.getStatus();
                //System.out.println("MELA PROFESSORE"+checkprof);
                boolean prof = checkprof.equals("professore");
            
                if(prof){
                    if(!mailprof){
                       //FacesMessage message = new FacesMessage("The specified mail "
                       //      + "is not valid, use @unipi.it. domain "
                       //                                       + "Please try again");
                       //context.addMessage(null, message);
                       return 3;
                    }
                }

            }
            EmailValidator truemail = new EmailValidator();
            if(!truemail.validate(mail)){
                //FacesMessage message = new FacesMessage("The specified mail is "
                //                              + "not valid.  Please try again");
                //context.addMessage(null, message);
                return 2;
            }
            /**
            * divide mail in 2 strings,one before and other after the @ symbol,
            * then check if the second part of the email is unipi.it -> Doc.
            */
            
            
            //try{
                //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gap","root","root");
                connessione.connect();    
                return connessione.newuser(user);
                
                /*Statement st = con.createStatement(); 

                st.executeUpdate("INSERT INTO utenti VALUES ( '"
                            + user.getMail() + "' , '" + user.getPassword() + "' , '" + user.getStatus() + "' , '" + user.getFirstname() + "', '" + user.getLastname() + "')" );
                con.close();*/
            /*} catch (SQLException ex) { 
                return 4;
            }finally{
                
            }
            System.out.print("Utente inserito");
            return 0;*/
    }

    public int isUserExists(User user) throws SQLException, ClassNotFoundException {
        //try {
            
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gap","root","root");
            connessione.connect();
            return connessione.existuser(user);
        /*    Statement st = con.createStatement();
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
            System.out.print("Eccezione: " + ex.getMessage());
            System.out.println("WE JAMM BELL ECCEZZZZZZZIONE!!!!");
           // public void response.sendRedirect("");
            //Logger.getLogger(RegisterService.class.getName()).log(Level.SEVERE, null, ex);
            return 3;
        }*/
    }
          
}

class EmailValidator {
	private Pattern pattern;
	private Matcher matcher;
        
	private static final String EMAIL_PATTERN =
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailValidator(){pattern = Pattern.compile(EMAIL_PATTERN);}
	/**
	 * Validate hex with regular expression
	 * @param hex for validation
	 * @return true  if hex is valid, false otherwise.
	 */
	public boolean validate(final String hex) {
		matcher = pattern.matcher(hex);
		return matcher.matches();
	}
}