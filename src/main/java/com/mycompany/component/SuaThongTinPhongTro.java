package com.mycompany.component;

import com.mycompany.qlnt.SharedData;
import com.mycompany.qlnt.XacThuc;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SuaThongTinPhongTro extends javax.swing.JPanel {

    // Tạo một danh sách chứa các cặp idLoaiPhong và tenLoaiPhong
    Map<String, String> idTenLoaiPhongMap = new HashMap<>();
    public SuaThongTinPhongTro(String idPhong) {
        initComponents();
        setPlaceholder(idPhong);
        layLoaiPhong();
    }
    
        
    private void setPlaceholder(String idPhong){
        try {
            Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
            String sql = "SELECT p.idPhong, p.tenPhong, p.dienTich, p.idDien, p.idNuoc, p.status, lp.tenLoaiPhong"
                    + " FROM Phong AS p JOIN LoaiPhong AS lp ON p.idLoaiPhong = lp.idLoaiPhong WHERE p.idPhong = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idPhong);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lbMaTro.setText(rs.getString("idPhong"));
                addPlaceholder(tfTenPhong,rs.getString("tenPhong"));
                addPlaceholder(tfDienTich, rs.getString("dienTich"));
                addPlaceholder(tfMaDien, rs.getString("idDien"));
                addPlaceholder(tfMaNuoc,rs.getString("idNuoc"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
    }
    
    private void layLoaiPhong(){
        try {
            Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
            String query = "SELECT idLoaiPhong, tenLoaiPhong FROM LoaiPhong WHERE idNhaTro = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, SharedData.getInstance().getIdNT());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String idLoaiPhong = rs.getString("idLoaiPhong");
                String tenLoaiPhong = rs.getString("tenLoaiPhong");
                idTenLoaiPhongMap.put(idLoaiPhong, tenLoaiPhong);
                cbLoaiPhong.addItem(tenLoaiPhong);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void addPlaceholder(JTextField textField, String placeholder) {
        // Đặt màu chữ mờ cho placeholder
        textField.setForeground(Color.GRAY);
        // Đặt placeholder
        textField.setText(placeholder);

        textField.addFocusListener(new FocusListener() {
            //@Override
            public void focusGained(FocusEvent e) {
                // Khi JTextField được tập trung, kiểm tra nếu nội dung là placeholder thì xóa đi
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK); // Đặt màu chữ khi nhập dữ liệu
                }
            }

            //@Override
            public void focusLost(FocusEvent e) {
                // Khi JTextField mất tập trung, kiểm tra nếu nội dung rỗng thì đặt lại placeholder
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }
    private void capNhatThongTinPhongTro(){
        String idPhong = lbMaTro.getText();
        String tenPhong = tfTenPhong.getText();
        String dienTich = tfDienTich.getText();
        String idDien = tfMaDien.getText();
        String idNuoc = tfMaNuoc.getText();
//        String loaiPhong = cbLoaiPhong.getSelectedItem().toString(); 
        // Kiểm tra định dạng Diện tích
        if (!XacThuc.isValidDienTich(tfDienTich.getText())) {
            XacThuc.setTextFieldBorderColor(tfDienTich, Color.red);
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra. Vui lòng nhập lại.");
            return; // Không thực hiện lệnh tiếp theo nếu dữ liệu không hợp lệ
        }

        Connection con = ketNoiDB.ketNoiDB.layKetNoi();
        String sql = "UPDATE Phong SET tenPhong = ?, dienTich = ?, idDien = ?, idNuoc = ?, idLoaiPhong = (SELECT idLoaiPhong FROM LoaiPhong WHERE idLoaiPhong = ?) WHERE idPhong = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenPhong);
            ps.setString(2, dienTich);
            ps.setString(3, idDien);
            ps.setString(4, idNuoc);
            String idLoaiPhongChon = null;
                    for (Map.Entry<String, String> entry : idTenLoaiPhongMap.entrySet()) {
                        if (entry.getValue().equals(cbLoaiPhong.getSelectedItem().toString())) {
                            idLoaiPhongChon = entry.getKey();
                            break;
                        }
                    }
            ps.setString(5, idLoaiPhongChon);
            ps.setString(6, idPhong);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(panelBorder1, "Cập nhật thông tin phòng trọ thành công!");
                SwingUtilities.getWindowAncestor(this).dispose();  
            } else {
                JOptionPane.showMessageDialog(panelBorder1, "Không tìm thấy phòng trọ");
            }
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SuaThongTinPhongTro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.mycompany.swing.PanelBorder();
        jLabel6 = new javax.swing.JLabel();
        tfTenPhong = new javax.swing.JTextField();
        tfDienTich = new javax.swing.JTextField();
        tfMaDien = new javax.swing.JTextField();
        tfMaNuoc = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonSua = new com.mycompany.swing.Button();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbMaTro = new javax.swing.JLabel();
        cbLoaiPhong = new com.mycompany.swing.Combobox();

        setMaximumSize(new java.awt.Dimension(345, 407));
        setPreferredSize(new java.awt.Dimension(345, 407));

        panelBorder1.setMaximumSize(new java.awt.Dimension(345, 407));
        panelBorder1.setPreferredSize(new java.awt.Dimension(340, 405));

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Loại phòng:");

        tfTenPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tfDienTich.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tfMaDien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tfMaNuoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Tên phòng trọ:");

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Diện tích:");

        buttonSua.setBackground(new java.awt.Color(51, 51, 255));
        buttonSua.setText("Sửa");
        buttonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSuaActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Mã đồng hồ điện:");

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Mã đồng hồ nước:");

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Mã phòng trọ:");

        lbMaTro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbMaTro.setText("jLabel8");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tfMaNuoc, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfMaDien, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfDienTich, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfTenPhong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbMaTro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(buttonSua, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbMaTro))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(tfTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfDienTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfMaDien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(30, 30, 30)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfMaNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(buttonSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSuaActionPerformed
        capNhatThongTinPhongTro();
        // Đóng panel ChiTietPhong
//        SwingUtilities.getWindowAncestor(this).dispose();        
    }//GEN-LAST:event_buttonSuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.swing.Button buttonSua;
    private com.mycompany.swing.Combobox cbLoaiPhong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbMaTro;
    private com.mycompany.swing.PanelBorder panelBorder1;
    private javax.swing.JTextField tfDienTich;
    private javax.swing.JTextField tfMaDien;
    private javax.swing.JTextField tfMaNuoc;
    private javax.swing.JTextField tfTenPhong;
    // End of variables declaration//GEN-END:variables
}
