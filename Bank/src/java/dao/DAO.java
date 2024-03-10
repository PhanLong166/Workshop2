/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Account;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class DAO {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public User checkLogin(String username, int password){
        String LOGIN = "SELECT * FROM tbl_User WHERE username = ? AND password = ?";
        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                PreparedStatement ps = connection.prepareStatement(LOGIN);
                ps.setString(1, username);
                ps.setInt(2, password);
                rs = ps.executeQuery();
                while(rs.next()){
                    return new User(
                            rs.getString("username"),
                            rs.getInt("password"),
                            rs.getString("fullname"),
                            rs.getInt("role")
                    );
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        } 
        return null;
    }
    
    public Account getAccount(String username){
        String ACCOUNT = "SELECT * FROM tbl_BankAccount WHERE username = ?";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(ACCOUNT);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while(rs.next()){
                return new Account(
                        rs.getString("id"),
                        rs.getInt("balance"),
                        rs.getString("username")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean addAccount(Account account) {
        boolean checkAdd = false;
        String ADD = "INSERT INTO tbl_BankAccount (id, balance, username) VALUES (?, ?, ?)";
        try {
            connection = DBUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(ADD);
            ps.setString(1, account.getId());
            ps.setInt(2, account.getBalance());
            ps.setString(3, account.getUsername());
            checkAdd = ps.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return checkAdd;
    }
    
    public boolean addUser(User user) {
        boolean checkAdd = false;
        String ADD = "INSERT INTO tbl_User (username,password,fullname,role) VALUES (?, ?, ?, ?)";
        try {
            connection = DBUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(ADD);
            ps.setString(1, user.getUsername());
            ps.setInt(2, user.getPassword());
            ps.setString(3, user.getFullname());
            ps.setInt(4, user.getRole());
            checkAdd = ps.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return checkAdd;
    }
    
    public List<Account> getAccountList(){
        List<Account> accountList = new ArrayList<>();
        String SEARCH = "SELECT id,balance,username FROM tbl_BankAccount";
        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                ps = connection.prepareStatement(SEARCH);
                rs = ps.executeQuery();
                while (rs.next()) {
                    accountList.add(new Account(
                            rs.getString("id"),
                            rs.getInt("balance"),
                            rs.getString("username")
                    ));
                }
            }
            return accountList;
            
        } catch(Exception e) {
            e.printStackTrace();
        } 
        
        return null;
    }
    
    public List<User> getUserList(){
        List<User> listUser = new ArrayList<>();
        String LIST = "SELECT * FROM tbl_User";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(LIST);
            rs = ps.executeQuery();
            while(rs.next()){
                listUser.add(new User(
                        rs.getString("username"),
                            rs.getInt("password"),
                            rs.getString("fullname"),
                            rs.getInt("role")
                ));
            }
            return listUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateBalance(int balance, String id) {
        boolean checkUpdate = false;
        String UPDATE = "UPDATE tbl_BankAccount SET balance = ? WHERE id LIKE ?";
        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                PreparedStatement ps = connection.prepareStatement(UPDATE);
                ps.setInt(1, balance);
                ps.setString(2, id);
                checkUpdate = ps.executeUpdate() > 0 ? true : false;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return checkUpdate;
    }
    
    public boolean updateInfo(String fullname, String username) {
        boolean checkUpdate = false;
        String UPDATE = "UPDATE tbl_User SET fullname = ? WHERE username LIKE ?";
        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                PreparedStatement ps = connection.prepareStatement(UPDATE);
                ps.setString(1, fullname);
                ps.setString(2, username);
                checkUpdate = ps.executeUpdate() > 0 ? true : false;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return checkUpdate;
    }
    
    public boolean deleteAccount(String username){
        boolean checkDelete = false;
        String DELETE = "DELETE tbl_BankAccount WHERE username LIKE ? ";
        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                ps = connection.prepareStatement(DELETE);
                ps.setString(1, username);
                checkDelete = ps.executeUpdate() > 0 ? true : false;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return checkDelete;
    }
    
    public boolean deleteUser(String username){
        boolean checkDelete = false;
        String DELETE = "DELETE tbl_User WHERE username LIKE ? ";
        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                ps = connection.prepareStatement(DELETE);
                ps.setString(1, username);
                checkDelete = ps.executeUpdate() > 0 ? true : false;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return checkDelete;
    }
    
    
}
