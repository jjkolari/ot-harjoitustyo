/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budjettisovellus.domain;

import budjettisovellus.dao.UserDao;
import java.sql.SQLException;

/**
 * the application logic class
 */
public class BudgetService {

    private User userLoggedIn;
    private UserDao userDao;

    public BudgetService(UserDao userDao) {
        this.userDao = userDao;

    }

    /**
     * Creates new user
     *
     * @param username Submitted username
     * @return
     */
    public boolean createUser(String username) throws SQLException {

        if (userDao.findByUsername(username) != null) {
            return false;
        }

        try {
            userDao.createNewUser(username);
        } catch (Exception e) {
            return false;
        }

        userLoggedIn = userDao.findByUsername(username);
        return true;
    }
    
    /**
     * Logs in user with given username
     * @param username
     * @return
     * @throws SQLException 
     */
    public boolean login(String username) throws SQLException {
        User user = userDao.findByUsername(username);
        System.out.println(user);
        if (user == null) {
            return false;
        }

        userLoggedIn = user;

        return true;
    }

}
