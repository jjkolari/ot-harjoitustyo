/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budjettisovellus.domain;

/**
 * Gives the user needed attributes
 */
public class User {
    
    int id;
    String username;
    Balance balance;
    
    public User(int id, String username) {
        this.id = id;
        this.username = username;
        this.balance = new Balance();
    }
    
    public String getUsername() {
        return username;
    }
    
    
}
