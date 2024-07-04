/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ql_nhanvien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
/**
 *
 * @author Admin
 */
public class FQL_Bophan extends javax.swing.JFrame {
    public int ID;
    String MaCa;
    /**
     * Creates new form FQL_Bophan
     */
    public FQL_Bophan() {
        initComponents();
        Load_table_BP();
    }
    private void Themmoi() {
        String sql = "INSERT INTO BOPHAN(TENBOPHAN,PHUTRACH,GHICHU) VALUES(?,?,?)";
        try {
            Connection conn = QL_Controller.getJDBCConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            pstm.setNString(1,_TenBP.getText());
            pstm.setNString(2,_Phutrach.getText());
            pstm.setNString(3,_Ghichu.getText());
            
             if(JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm dữ liệu?","Thông báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                pstm.executeUpdate();
                JOptionPane.showConfirmDialog(this,"Thêm thành công","Thông báo",JOptionPane.OK_OPTION);
            } else {
                return;
            }
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(this,"Loi :" + ex.getMessage());
        }
        Load_table_BP();
        reText();
        Reset_table();
    }
    // sửa
    private void Sua() {
        String sql = "UPDATE BOPHAN SET TENBOPHAN = ? , PHUTRACH = ? , GHICHU = ? WHERE MABP = ?";
        try {
            Connection conn = QL_Controller.getJDBCConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            pstm.setNString(1,_TenBP.getText());
            pstm.setNString(2,_Phutrach.getText());
            pstm.setNString(3,_Ghichu.getText());
            pstm.setString(4,_MaBP.getText());
            
             if(JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa dữ liệu?","Thông báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                pstm.executeUpdate();
                JOptionPane.showConfirmDialog(this,"Sửa thành công","Thông báo",JOptionPane.OK_OPTION);
            } else {
                return;
            }
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(this,"Loi :" + ex.getMessage());
        }
        Load_table_BP();
        reText();
        Reset_table();
    }
    // xóa
    private void Xoa() {
        String sql = "DELETE FROM BOPHAN WHERE MABP  =  '" + _MaBP.getText() + "'";
            try {
            Connection conn = QL_Controller.getJDBCConnection();
            Statement stm = conn.createStatement();
            stm.execute(sql);
            JOptionPane.showConfirmDialog(this,"Xóa thành công!", "Thông báo",JOptionPane.OK_OPTION);
            conn.close();
            } catch (SQLException e) {
                JOptionPane.showConfirmDialog(this,"Loi :" + e.getMessage());
            }
        Load_table_BP();
        reText();
        Reset_table();
    }
    // tim kiem
    private void Timkiem() {
        if (_Timkiem.getText().equals("")) {
            Reset_table();
        } else {
                  String sql = "SELECT NHANVIEN.MANV , NHANVIEN.HOTEN,NHANVIEN.GIOITINH,NHANVIEN.NGAYSINH,NHANVIEN.DIACHI,BOPHAN.TENBOPHAN FROM NHANVIEN LEFT OUTER JOIN  BOPHAN ON NHANVIEN.MABP = BOPHAN.MABP WHERE NHANVIEN.MANV = '"+ _Timkiem.getText() +"'";
                    ArrayList<QL_TTNhanvienBP> List_TTnv = new ArrayList<>();
                    DefaultTableModel model = (DefaultTableModel)jTable_TTNhanvien.getModel();
                    model.setRowCount(0);
                    try {
                        Connection conn = QL_Controller.getJDBCConnection();
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        while(rs.next()) {
                            String Manv = rs.getString(1);
                            String Hoten = rs.getNString(2);
                            String Ngaysinh = rs.getString(3);
                            String Gioitinh = rs.getNString(4);
                            String Diachi = rs.getNString(5);
                            String TenBP = rs.getNString(6);
                            QL_TTNhanvienBP nvbp = new QL_TTNhanvienBP(Manv,Hoten,Ngaysinh,Gioitinh,Diachi,TenBP);
                            List_TTnv.add(nvbp);
                        }
                        for(QL_TTNhanvienBP _Bp : List_TTnv) {
                             model.addRow(new Object[] {
                                 _Bp.getManv(),_Bp.getTennhanvien(),_Bp.getNgaysinh(),_Bp.getGioitinh(),_Bp.getDiachi(),_Bp.getTenBP()
                            });
                        }
                        conn.close();
                    } catch (SQLException e) {
                        JOptionPane.showConfirmDialog(this, "Loi: " + e.getMessage());
              }
        }
    }
    // xoa thong tin ca lam
    private void Xoa_CA() {
        String sql = "DELETE FROM CALAM WHERE MANV  =  '" + MaCa + "';";
            try {
            Connection conn = QL_Controller.getJDBCConnection();
            Statement stm = conn.createStatement();
            stm.execute(sql);
            conn.close();
            } catch (SQLException e) {
                JOptionPane.showConfirmDialog(this,"Loi :" + e.getMessage());
        }
    }
    private void Xoa_NV() {
            String sql = "DELETE FROM NHANVIEN WHERE MABP  =  '" + _MaBP.getText() + "';";
            try {
            Connection conn = QL_Controller.getJDBCConnection();
            Statement stm = conn.createStatement();
            stm.execute(sql);
            conn.close();
            } catch (SQLException e) {
                JOptionPane.showConfirmDialog(this,"Loi :" + e.getMessage());
            }
    }
    // reset Text
    private void reText() {
        _MaBP.setText("");
        _TenBP.setText("");
        _Phutrach.setText("");
        _Ghichu.setText("");

    }
    // kiem tra co nhan vien ton tai trong bo phan khong
    private boolean Check_NV() {
        String sql = "SELECT * FROM NHANVIEN WHERE MABP = '" + _MaBP.getText()+"'";
        try {
            Connection conn = QL_Controller.getJDBCConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                MaCa = rs.getString("MANV");
                return true;
            } 
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(this, "Loi :" + e.getMessage());
        }
        return false;
    }
    // kiem tra thong tin
    private boolean Check_TT() {
        if(_TenBP.getText().equals("")) return true;
        return false;
    }
    // load table bộ phận
    private void Load_table_BP() {
        String sql = "SELECT * FROM BOPHAN ORDER BY MABP";
        ArrayList<QL_Bophan> List_Bp = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel)jTable_Bophan.getModel();
        model.setRowCount(0);
        try {
            Connection conn = QL_Controller.getJDBCConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                int ID = rs.getInt(1);
                String TenBp = rs.getNString(2);
                String Phutrach = rs.getNString(3);
                String Ghichu = rs.getNString(4);
                QL_Bophan bp = new QL_Bophan(ID,TenBp,Phutrach,Ghichu);
                List_Bp.add(bp);
            }
            for(QL_Bophan _Bp : List_Bp) {
                 model.addRow(new Object[] {
                     _Bp.getMaBP(),_Bp.getTenBP(),_Bp.getPhuTrach(),_Bp.getGhichu()
                });
            }
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(this, "Loi: " + e.getMessage());
        }
    }
    // Load table nhan vien
    private void Load_table_TTNV(String MABP) {
        String sql = "SELECT NHANVIEN.MANV , NHANVIEN.HOTEN,NHANVIEN.GIOITINH,NHANVIEN.NGAYSINH,NHANVIEN.DIACHI,BOPHAN.TENBOPHAN FROM NHANVIEN LEFT OUTER JOIN  BOPHAN ON NHANVIEN.MABP = BOPHAN.MABP WHERE NHANVIEN.MABP = '"+MABP +"'";
        ArrayList<QL_TTNhanvienBP> List_TTnv = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel)jTable_TTNhanvien.getModel();
        model.setRowCount(0);
        try {
            Connection conn = QL_Controller.getJDBCConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                String Manv = rs.getString(1);
                String Hoten = rs.getNString(2);
                String Ngaysinh = rs.getString(3);
                String Gioitinh = rs.getNString(4);
                String Diachi = rs.getNString(5);
                String TenBP = rs.getNString(6);
                QL_TTNhanvienBP nvbp = new QL_TTNhanvienBP(Manv,Hoten,Ngaysinh,Gioitinh,Diachi,TenBP);
                List_TTnv.add(nvbp);
            }
            for(QL_TTNhanvienBP _Bp : List_TTnv) {
                 model.addRow(new Object[] {
                     _Bp.getManv(),_Bp.getTennhanvien(),_Bp.getNgaysinh(),_Bp.getGioitinh(),_Bp.getDiachi(),_Bp.getTenBP()
                });
            }
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(this, "Loi: " + e.getMessage());
        }
    }
    // restet table bang thong tin nhan vien
    private void Reset_table() {
        ArrayList<QL_TTNhanvienBP> List_TTnv = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel)jTable_TTNhanvien.getModel();
        model.setRowCount(0);
        for(QL_TTNhanvienBP _Bp : List_TTnv) {
                 model.addRow(new Object[] {
                     _Bp.getManv(),_Bp.getTennhanvien(),_Bp.getNgaysinh(),_Bp.getGioitinh(),_Bp.getDiachi(),_Bp.getTenBP()
            });
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        _Ghichu = new javax.swing.JTextField();
        _Phutrach = new javax.swing.JTextField();
        _TenBP = new javax.swing.JTextField();
        btn_Them = new javax.swing.JButton();
        Sửa = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Capnhat = new javax.swing.JButton();
        btn_QuayLai = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        _MaBP = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Bophan = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_TTNhanvien = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        _Timkiem = new javax.swing.JTextField();
        btn_Timkiem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/re/images/hr.png"))); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tên bộ phận");

        jLabel4.setText("Phụ trách");

        jLabel5.setText("Ghi chú");

        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        Sửa.setText("Sửa");
        Sửa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SửaActionPerformed(evt);
            }
        });

        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_Capnhat.setText("Cập nhật");
        btn_Capnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapnhatActionPerformed(evt);
            }
        });

        btn_QuayLai.setText("Quay lại");
        btn_QuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QuayLaiActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 51, 102));
        jLabel6.setText("@Bài tập lớn OOP Java");

        jLabel8.setText("Mã Bộ phận");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_Them, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_Xoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_QuayLai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Sửa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_Capnhat, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(_Phutrach)
                                    .addComponent(_TenBP, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(_Ghichu, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(_MaBP)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 23, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_MaBP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_TenBP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_Phutrach, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_Ghichu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sửa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Capnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(btn_QuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(10, 10, 10))
        );

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel2.setBackground(new java.awt.Color(204, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Quản lý bộ phận");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTable_Bophan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã bộ phận", "Tên bộ phận", "Phụ trách", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Bophan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_BophanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Bophan);

        jTable_TTNhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Địa chỉ", "Tên bộ phận"
            }
        ));
        jScrollPane2.setViewportView(jTable_TTNhanvien);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 0, 51))); // NOI18N

        jLabel7.setText("* Tìm kiếm theo Mã nhân viên");

        btn_Timkiem.setText("Tìm kiếm ");
        btn_Timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_Timkiem)
                    .addComponent(btn_Timkiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_Timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 975, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable_BophanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_BophanMouseClicked
        // TODO add your handling code here:
        if(jTable_Bophan.getSelectedRow() == -1) {
            Reset_table();
            return;
        }
        int row = jTable_Bophan.getSelectedRow();
        _MaBP.setText(jTable_Bophan.getValueAt(row, 0).toString());
        _TenBP.setText(jTable_Bophan.getValueAt(row, 1).toString());
        if(jTable_Bophan.getValueAt(row, 2)!= null && jTable_Bophan.getValueAt(row, 3) != null) {
             _Phutrach.setText(jTable_Bophan.getValueAt(row, 2).toString());
             _Ghichu.setText(jTable_Bophan.getValueAt(row, 3).toString());
        } else {
            _Phutrach.setText("");
            _Ghichu.setText("");
        }
        Load_table_TTNV(jTable_Bophan.getValueAt(row, 0).toString());
    }//GEN-LAST:event_jTable_BophanMouseClicked

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
        if(Check_TT()) {
            if(JOptionPane.showConfirmDialog(this, "Vui lòng điền thông tin Tên bộ phận?","Thông báo",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                return;
            }
        }
        Themmoi();
        Load_table_BP();
        Reset_table();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void SửaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SửaActionPerformed
        // TODO add your handling code here:
        if(Check_TT()) {
            if(JOptionPane.showConfirmDialog(this, "Vui lòng điền thông tin Tên bộ phận?","Thông báo",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                return;
            }
        }
        Sua();
    }//GEN-LAST:event_SửaActionPerformed
//btn xoa
    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        // TODO add your handling code here:
        if(_MaBP.getText().equals("")) {
              if(JOptionPane.showConfirmDialog(this, "Vui lòng điền thông tin mã bộ ?","Thông báo",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                return;
            }
        } else {
            if(Check_NV()) {
                if(JOptionPane.showConfirmDialog(this, "Bộ phận này đã có nhân viên .\n Bạn có muốn xóa toàn bộ thông tin không?","Thông báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ) {
                    Xoa_CA();
                    Xoa_NV();
                    Xoa();
                }
            } else {
                Xoa();
            }
            Load_table_BP();
            Reset_table();
        }    
    }//GEN-LAST:event_btn_XoaActionPerformed
// btn cap nhat
    private void btn_CapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapnhatActionPerformed
        // TODO add your handling code here:
        Load_table_BP();
        Reset_table();
    }//GEN-LAST:event_btn_CapnhatActionPerformed
// Quay lai
    private void btn_QuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QuayLaiActionPerformed
        // TODO add your handling code here:
        FQL_Nhanvien nv = new FQL_Nhanvien();
        nv.ID = ID;
        nv.setLocationRelativeTo(null);
        nv.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_QuayLaiActionPerformed

    private void btn_TimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimkiemActionPerformed
        // TODO add your handling code here:
        Timkiem();
    }//GEN-LAST:event_btn_TimkiemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FQL_Bophan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FQL_Bophan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FQL_Bophan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FQL_Bophan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FQL_Bophan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Sửa;
    private javax.swing.JTextField _Ghichu;
    private javax.swing.JTextField _MaBP;
    private javax.swing.JTextField _Phutrach;
    private javax.swing.JTextField _TenBP;
    private javax.swing.JTextField _Timkiem;
    private javax.swing.JButton btn_Capnhat;
    private javax.swing.JButton btn_QuayLai;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Timkiem;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Bophan;
    private javax.swing.JTable jTable_TTNhanvien;
    // End of variables declaration//GEN-END:variables
}
