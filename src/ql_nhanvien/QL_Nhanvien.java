/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ql_nhanvien;

/**
 *
 * @author Admin
 */
public class QL_Nhanvien {
    String Manv;
    String Hoten;
    String Gioitinh;
    String Ngaysinh;
    String Email;
    String Sdt;
    String Diachi;
    String Luong;
    int MaBP;
    
    public QL_Nhanvien(String Manv, String Hoten, String Gioitinh, String Ngaysinh, String Email, String Sdt, String Diachi, String Luong, int Bophan) {
        this.Manv = Manv;
        this.Hoten = Hoten;
        this.Gioitinh = Gioitinh;
        this.Ngaysinh = Ngaysinh;
        this.Email = Email;
        this.Sdt = Sdt;
        this.Diachi = Diachi;
        this.Luong = Luong;
        this.MaBP = Bophan;
    }

    public String getManv() {
        return Manv;
    }

    public String getHoten() {
        return Hoten;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public String  getNgaysinh() {
        return Ngaysinh;
    }

    public String getEmail() {
        return Email;
    }

    public String getSdt() {
        return Sdt;
    }

    public String getDiachi() {
        return Diachi;
    }

    public String getLuong() {
        return Luong;
    }

    public int getMaBP() {
        return MaBP;
    }

    public void setManv(String Manv) {
        this.Manv = Manv;
    }

    public void setHoten(String Hoten) {
        this.Hoten = Hoten;
    }

    public void setGioitinh(String Gioitinh) {
        this.Gioitinh = Gioitinh;
    }

    public void setNgaysinh(String Ngaysinh) {
        this.Ngaysinh = Ngaysinh;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public void setLuong(String Luong) {
        this.Luong = Luong;
    }

    public void setMaBP(int Bophan) {
        this.MaBP = Bophan;
    }
    
    
}
