/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ql_nhanvien;

/**
 *
 * @author Admin
 */
public class QL_TTNhanvienBP {
    String Manv;
    String Tennhanvien;
    String Ngaysinh;
    String Gioitinh;
    String Diachi;
    String TenBP;

    public QL_TTNhanvienBP(String Manv, String Tennhanvien, String Ngaysinh, String Gioitinh, String Diachi, String TenBP) {
        this.Manv = Manv;
        this.Tennhanvien = Tennhanvien;
        this.Ngaysinh = Ngaysinh;
        this.Gioitinh = Gioitinh;
        this.Diachi = Diachi;
        this.TenBP = TenBP;
    }

    public void setManv(String Manv) {
        this.Manv = Manv;
    }

    public void setTennhanvien(String Tennhanvien) {
        this.Tennhanvien = Tennhanvien;
    }

    public void setNgaysinh(String Ngaysinh) {
        this.Ngaysinh = Ngaysinh;
    }

    public void setGioitinh(String Gioitinh) {
        this.Gioitinh = Gioitinh;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public void setTenBP(String TenBP) {
        this.TenBP = TenBP;
    }

    public String getManv() {
        return Manv;
    }

    public String getTennhanvien() {
        return Tennhanvien;
    }

    public String getNgaysinh() {
        return Ngaysinh;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public String getDiachi() {
        return Diachi;
    }

    public String getTenBP() {
        return TenBP;
    }

    
    
}
