/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GpUser;

import java.io.Serializable;

/**
 *
 * @author valtan
 */
public class User implements Serializable {
    
    private String firstName;
    private String lastName;
    private String status;
    private String mail;
    private String password;

    public User() {
    }

    
    public User(String firstName, String lastName, String status, String email,  String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.mail = email;
        this.password = password;
    }
    
    public String getFirstname() {
        return this.firstName;
    }
    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }
    
    public String getLastname() {
        return this.lastName;
    }
    public void setLastname(String lastname) {
        this.lastName = lastname;
    }    
    
    public String getStatus(){
        return status;
    }    
    public void setStatus(String status){
        this.status = status;
    }
    
    public String getMail() {
        return this.mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}

