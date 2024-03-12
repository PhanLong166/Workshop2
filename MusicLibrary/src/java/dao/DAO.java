/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Music;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public boolean addMusic(String musicName, String artist, String src) {
        boolean checkAdd = false;
        String ADD = "INSERT INTO tbl_MusicLibrary (musicName, artist, src) VALUES (?, ?, ?)";
        try {
            connection = DBUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement(ADD);
            ps.setString(1, musicName);
            ps.setString(2, artist);
            ps.setString(3, src);
            checkAdd = ps.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return checkAdd;
    }
    
    public List<Music> getAllMusic() throws ClassNotFoundException, SQLException{
        List<Music> musicList = new ArrayList<>();
        String ALL = "SELECT * FROM tbl_MusicLibrary";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(ALL);
            rs = ps.executeQuery();
            while(rs.next()){
                musicList.add(new Music(
                        rs.getInt("musicID"),
                        rs.getString("musicName"),
                        rs.getString("artist"),
                        rs.getString("src")
                ));
            }
            return musicList;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Music> getMusicList(String search){
        List<Music> musicList = new ArrayList<>();
        String SEARCH = "SELECT musicID, musicName, artist, src FROM tbl_MusicLibrary WHERE musicName LIKE ? OR artist LIKE ?";
        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                ps = connection.prepareStatement(SEARCH);
                ps.setString(1, '%' + search + '%');
                ps.setString(2, '%' + search + '%');
                rs = ps.executeQuery();
                while (rs.next()) {
                    musicList.add(new Music(rs.getInt("musicID"),rs.getString("musicName"),rs.getString("artist"), rs.getString("src")));
                }
            }
            return musicList;
            
        } catch(Exception e) {
            e.printStackTrace();
        } 
        
        return null;
    }
    
    public boolean updateMusic(Music music) {
        boolean checkUpdate = false;
        String UPDATE = "UPDATE tbl_MusicLibrary SET musicName = ?, artist = ?, src = ? WHERE musicID LIKE ?";
        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                PreparedStatement ps = connection.prepareStatement(UPDATE);
                ps.setString(1, music.getName());
                ps.setString(2, music.getArtist());
                ps.setString(3, music.getSrc());
                ps.setInt(4, music.getId());
                checkUpdate = ps.executeUpdate() > 0 ? true : false;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return checkUpdate;
    }
    
    public boolean deleteMusic(int id){
        boolean checkDelete = false;
        String DELETE = "DELETE tbl_MusicLibrary WHERE musicID LIKE ? ";
        try {
            connection = DBUtils.getConnection();
            if(connection != null){
                ps = connection.prepareStatement(DELETE);
                ps.setInt(1, id);
                checkDelete = ps.executeUpdate() > 0 ? true : false;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return checkDelete;
    }
    
    public Music playSong(int id){
        Music song = new Music();
        String PLAY = "SELECT * FROM tbl_MusicLibrary WHERE musicID = ?";
        try {
            connection = DBUtils.getConnection();
            if(connection != null) {
                ps = connection.prepareStatement(PLAY);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                song = new Music(rs.getInt("musicID")
                        ,rs.getString("musicName")
                        ,rs.getString("artist")
                        ,rs.getString("src"));
            }
            return song;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
