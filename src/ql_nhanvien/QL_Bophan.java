/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ql_nhanvien;

/**
 *
 * @author Admin
 */
public class QL_Bophan {
    int MaBP;
    String TenBP;
    String PhuTrach;
    String Ghichu;

    public int getMaBP() {
        return MaBP;
    }

    public String getTenBP() {
        return TenBP;
    }

    public String getPhuTrach() {
        return PhuTrach;
    }

    public String getGhichu() {
        return Ghichu;
    }

    public QL_Bophan(int MaBP, String TenBP, String PhuTrach, String Ghichu) {
        this.MaBP = MaBP;
        this.TenBP = TenBP;
        this.PhuTrach = PhuTrach;
        this.Ghichu = Ghichu;
    }

    public void setMaBP(int MaBP) {
        this.MaBP = MaBP;
    }

    public void setTenBP(String TenBP) {
        this.TenBP = TenBP;
    }

    public void setPhuTrach(String PhuTrach) {
        this.PhuTrach = PhuTrach;
    }

    public void setGhichu(String Ghichu) {
        this.Ghichu = Ghichu;
    }
}
