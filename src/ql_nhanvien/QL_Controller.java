/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ql_nhanvien;

import java.util.logging.Level;
import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Admin
 */
public class QL_Controller {

    public int ID;
    // tao ket noi
    public static Connection getJDBCConnection() {
        final String url = "jdbc:mysql://localhost:3306/ql_nhanvien";
        final String user = "root";
        final String password = "21102003";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QL_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }catch (SQLException ex){
            Logger.getLogger(QL_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // kiểm tra dang nhap
    public boolean login(String username, String password){
        
        Connection sqlconn = getJDBCConnection(); 
        
        String sql= "SELECT * FROM ACCOUNT WHERE USER=? AND PASS=?";
        
        try {
            PreparedStatement ps = sqlconn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){ 
                ID = rs.getInt("ID");
                return true;
            } else {
                return false;
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QL_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
        // tao tai khoan
    public void register(QL_Account account){
        Connection sqlconn = getJDBCConnection(); 
        
        String sql = "INSERT INTO ACCOUNT(USER, PASS, NAME, QUYEN) VALUES (?, ?, ?, ?)";
        
        try{   
            PreparedStatement ps = sqlconn.prepareStatement(sql);
            ps.setString(1, account.getUser());
            ps.setString(2, account.getPass());
            ps.setString(3, account.getName());
            ps.setString(4, account.getQuyen());
            
            int rs = ps.executeUpdate();
            
            if(rs != 0){
                System.out.println("Them thanh cong");   
            }else {
                System.out.println("That bai");
            }
            
        }  catch (SQLException ex) {
            Logger.getLogger(QL_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*public static void main(String[] args) {
        FQL_Dangnhap dn = new FQL_Dangnhap();
        dn.setVisible(true);
        dn.setLocationRelativeTo(null);
    }*/

}

