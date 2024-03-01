/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Product;
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
                    return new User(rs.getString("username"), rs.getInt("password"), rs.getString("fullname"),rs.getInt("role"));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        } 
        return null;
    }
    
    public boolean addProduct(Product product) {
        boolean checkAdd = false;
        String ADD = "INSERT INTO tbl_Inventory (productID, productName, type, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = DBUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(ADD);
            ps.setString(1, product.getID());
            ps.setString(2, product.getName());
            ps.setString(3, product.getType());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getPrice());
            checkAdd = ps.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return checkAdd;
    }
    
    public List<Product> getProductList(String search){
        List<Product> productList = new ArrayList<>();
        String SEARCH = "SELECT * FROM tbl_Inventory WHERE productID LIKE ? OR productName LIKE ? or type LIKE ?";
        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                ps = connection.prepareStatement(SEARCH);
                ps.setString(1, '%' + search + '%');
                ps.setString(2, '%' + search + '%');
                ps.setString(3, '%' + search + '%');
                rs = ps.executeQuery();
                while (rs.next()) {
                    productList.add(new Product(
                            rs.getString("productID"),
                            rs.getString("productName"),
                            rs.getString("type"),
                            rs.getInt("quantity"),
                            rs.getInt("price")
                    ));
                }
            }
            return productList;
            
        } catch(Exception e) {
            e.printStackTrace();
        } 
        
        return null;
    }
    
    public boolean updateProduct(Product product) {
        boolean checkUpdate = false;
        String UPDATE = "UPDATE tbl_Inventory SET productName = ?, type = ?, quantity = ?, price = ? WHERE productID LIKE ?";
        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                PreparedStatement ps = connection.prepareStatement(UPDATE);
                ps.setString(1, product.getName());
                ps.setString(2, product.getType());
                ps.setInt(3, product.getQuantity());
                ps.setInt(4, product.getPrice());
                ps.setString(5, product.getID());
                checkUpdate = ps.executeUpdate() > 0 ? true : false;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return checkUpdate;
    }
    
    public boolean deleteProduct(String id){
        boolean checkDelete = false;
        String DELETE = "DELETE tbl_Inventory WHERE productID LIKE ? ";
        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                ps = connection.prepareStatement(DELETE);
                ps.setString(1, id);
                checkDelete = ps.executeUpdate() > 0 ? true : false;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return checkDelete;
    }
}
