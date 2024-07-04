/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ql_nhanvien;

/**
 *
 * @author Admin
 */
public class QL_Account {
    private int ID;
    private String User;
    private String Pass;
    private String Name;
    private String Quyen;

    public QL_Account(int ID,String User, String Pass, String Name, String Quyen) {
        this.ID = ID;
        this.User = User;
        this.Pass = Pass;
        this.Name = Name;
        this.Quyen = Quyen;
    }

    public String getUser() {
        return User;
    }

    public String getPass() {
        return Pass;
    }

    public String getName() {
        return Name;
    }

    public String getQuyen() {
        return Quyen;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setQuyen(String Quyen) {
        this.Quyen = Quyen;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
    
    
}
