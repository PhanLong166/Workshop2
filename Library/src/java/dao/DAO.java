/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.User;
import dto.Library;
import util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Ha Chang
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
    
    public boolean deleteBook(String bookID) {
        String sql = "DELETE FROM tbl_Library WHERE bookID = ?;";
        boolean response = true;
        
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, bookID);
            response = ps.executeUpdate() > 0 ? true : false;
        } catch (Exception ex) {}
        
        return response;
    }
    
    public boolean createBook(Library book) {
        String sql = "INSERT INTO tbl_Library VALUES (?, ?, ?, ?, ?, ?);";
        boolean response = true;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, book.getBookID());
            ps.setString(2, book.getBookName());
            ps.setFloat(3, book.getPrice());
            ps.setString(4, book.getAuthor());
            ps.setString(5, book.getCategory());
            ps.setInt(6, book.getQuantity());
            response = ps.executeUpdate() > 0 ? true : false;
        } catch (Exception ex) {
        }
        return response;
    }
    
    public boolean updateBook(String bookID, String bookName, float price, String author, String category, int quantity)  {
        String sql = "UPDATE tbl_Library SET bookName = ?, price = ?, author = ?, category = ?, quantity = ? WHERE bookID = ?;";
        boolean response = true;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, bookName);
            ps.setFloat(2, price);
            ps.setString(3, author);
            ps.setString(4, category);
            ps.setInt(5, quantity);
            ps.setString(6, bookID);
            response = ps.executeUpdate() > 0 ? true : false;
        } catch (Exception ex) {
        }
        return response;
    }
    
    public List<Library> getAllBooks() {
        List<Library> bookList = new ArrayList<>();
        Library book;
        String sql = "SELECT * FROM tbl_Library;";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                book = new Library(
                        rs.getString("bookID"), 
                        rs.getString("bookName"), 
                        rs.getFloat("price"), 
                        rs.getString("author"), 
                        rs.getString("category"), 
                        rs.getInt("quantity")
                );
                bookList.add(book);
            }
        } catch (Exception ex) {
        }
        
        return bookList;
    }
    
    public List<Library> getAllByIdAndName(String id, String name) {
        List<Library> bookList = new ArrayList<Library>();
        Library book;
        String sql = "SELECT * FROM tbl_Library WHERE bookID LIKE ? OR bookName LIKE ?;";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, '%' + id + '%');
            ps.setString(2, '%' + name + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                book = new Library(
                        rs.getString("bookID"), 
                        rs.getString("bookName"), 
                        rs.getFloat("price"), 
                        rs.getString("author"), 
                        rs.getString("category"), 
                        rs.getInt("quantity")
                );
                bookList.add(book);
            }
        } catch (Exception ex) {
        }
        
        return bookList;
    }
    
    public List<Library> getAllBooksWithCondition(float minPrice, float maxPrice) {
        List<Library> bookList = new ArrayList<>();
        Library book;
        
        try {
            connection = DBUtils.getConnection();
            String sql = "";
            if (minPrice == -1 && maxPrice != -1) {
                sql = "SELECT * FROM tbl_Library WHERE price <= ?;";
            }
            else if (minPrice != -1 && maxPrice == -1) {
                sql = "SELECT * FROM tbl_Library WHERE price >= ?;";
            }
            else if (minPrice == -1 && maxPrice == -1) {
                sql = "SELECT * FROM tbl_Library;";
            } else {
                sql = "SELECT * FROM tbl_Library WHERE price >= ? AND price <= ?;";
            }
            ps = connection.prepareStatement(sql);
            
            if (minPrice == -1 && maxPrice != -1) {
                ps.setFloat(1, maxPrice);
            }
            else if (minPrice != -1 && maxPrice == -1) {
                ps.setFloat(1, minPrice);
            }
            else if (minPrice == -1 && maxPrice == -1) {
            } else {
                ps.setFloat(1, minPrice);
                ps.setFloat(2, maxPrice);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                book = new Library(
                        rs.getString("bookID"), 
                        rs.getString("bookName"), 
                        rs.getFloat("price"), 
                        rs.getString("author"), 
                        rs.getString("category"), 
                        rs.getInt("quantity")
                );
                bookList.add(book);
            }
        } catch (Exception ex) {
        }
        
        return bookList;
    }
}
