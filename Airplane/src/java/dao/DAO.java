/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Flight;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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
    
    public boolean addFlight(Flight flight) {
        boolean checkAdd = false;
        String ADD = "INSERT INTO tbl_Flight(flightNumber, destination, departureTime, delayed) VALUES (?, ?, ?, ?)";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(ADD);
            ps.setString(1, flight.getFlightNumber());
            ps.setString(2, flight.getDestination());
            ps.setString(3, flight.getDepartureTime());
            ps.setInt(4, flight.isDelayed());
            checkAdd = ps.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return checkAdd;
    }
    
    public List<Flight> getFlightList(String search){
        List<Flight> flightList = new ArrayList<>();
        String SEARCH = "SELECT * FROM tbl_Flight WHERE flightNumber LIKE ? OR destination LIKE ? or departureTime LIKE ?";
        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                ps = connection.prepareStatement(SEARCH);
                ps.setString(1, '%' + search + '%');
                ps.setString(2, '%' + search + '%');
                ps.setString(3, '%' + search + '%');
                rs = ps.executeQuery();
                while (rs.next()) {
                    flightList.add(new Flight(
                            rs.getString("flightNumber"),
                            rs.getString("destination"),
                            rs.getString("departureTime"),
                            rs.getInt("delayed")
                    ));
                }
            }
            return flightList;
            
        } catch(Exception e) {
            e.printStackTrace();
        } 
        
        return null;
    }
    
    public boolean updateFlight(Flight flight) {
        boolean checkUpdate = false;
        String UPDATE = "UPDATE tbl_Flight SET destination = ?, departureTime = ?, delayed = ? WHERE flightNumber LIKE ?";
        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                PreparedStatement ps = connection.prepareStatement(UPDATE);
                ps.setString(1, flight.getDestination());
                ps.setString(2, flight.getDepartureTime());
                ps.setInt(3, flight.isDelayed());
                ps.setString(4, flight.getFlightNumber());
                checkUpdate = ps.executeUpdate() > 0 ? true : false;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return checkUpdate;
    }
    
    public boolean deleteFlight(String flightNumber){
        boolean checkDelete = false;
        String DELETE = "DELETE tbl_Flight WHERE flightNumber LIKE ? ";
        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                ps = connection.prepareStatement(DELETE);
                ps.setString(1, flightNumber);
                checkDelete = ps.executeUpdate() > 0 ? true : false;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return checkDelete;
    }
}
