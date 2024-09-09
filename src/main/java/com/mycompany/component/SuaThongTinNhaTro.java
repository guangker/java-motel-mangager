package com.mycompany.component;

import com.mycompany.qlnt.SharedData;
import com.mycompany.qlnt.XacThuc;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SuaThongTinNhaTro extends javax.swing.JPanel {

    Map<String, String> idTenQuanLy = new HashMap<>();
    
    public SuaThongTinNhaTro() {
        initComponents();
        setPlaceHolder();
        loaiPhong();
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
    
    private void setPlaceHolder(){
        try {
            Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
            String query = "SELECT idNhaTro, tenNhaTro, chuTro,diaChi, soDienThoai, email "
                + "FROM dbo.NhaTro WHERE idNhaTro = ? ;"; 
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, SharedData.getInstance().getIdNT());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                addPlaceholder(tfTenTro, rs.getString("tenNhaTro"));
                addPlaceholder(tfTenChuTro, rs.getString("chuTro"));
                addPlaceholder(tfDiaChi, rs.getString("diaChi"));
                addPlaceholder(tfSDT, rs.getString("soDienThoai"));
                addPlaceholder(tfEmail, rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loaiPhong(){
        try {
            Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
            String query = "SELECT idQL, fullName FROM NVQuanLy";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String idQL = rs.getString("idQL");
                String fullName = rs.getString("fullName");
                idTenQuanLy.put(idQL, fullName);
                cbNguoiQL.addItem(fullName);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.mycompany.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfTenTro = new javax.swing.JTextField();
        tfTenChuTro = new javax.swing.JTextField();
        tfDiaChi = new javax.swing.JTextField();
        tfSDT = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        cbNguoiQL = new com.mycompany.swing.Combobox();
        buttonSua = new com.mycompany.swing.Button();

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Tên nhà trọ:");

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tên chủ trọ:");

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Địa chỉ:");

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Số điện thoại:");

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Email:");

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Người quản lý:");

        tfTenTro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tfTenChuTro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tfDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tfSDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tfEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        buttonSua.setText("Sửa");
        buttonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfTenTro)
                            .addComponent(tfTenChuTro)
                            .addComponent(tfDiaChi)
                            .addComponent(tfSDT)
                            .addComponent(tfEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                            .addComponent(cbNguoiQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(buttonSua, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfTenTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfTenChuTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbNguoiQL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(buttonSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSuaActionPerformed
        // Kiểm tra định dạng email
        if (!XacThuc.isValidEmail(tfEmail.getText())) {
            XacThuc.setTextFieldBorderColor(tfEmail, Color.red);
            JOptionPane.showMessageDialog(this, "Email không hợp lệ. Vui lòng nhập lại.");
            return; // Không thực hiện lệnh tiếp theo nếu dữ liệu không hợp lệ
        }

        // Kiểm tra định dạng số điện thoại
        if (!XacThuc.isValidSDT(tfSDT.getText())) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Vui lòng nhập lại.");
            XacThuc.setTextFieldBorderColor(tfSDT, Color.red);
            return; // Không thực hiện lệnh tiếp theo nếu dữ liệu không hợp lệ
        }
        int choice = JOptionPane.showOptionDialog(null, "Bạn có muốn tiếp tục không?", "Xác nhận thêm phòng mới",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Yes", "Quay lại"}, "Yes");
        switch (choice){
            case JOptionPane.YES_OPTION:{
                try {
                Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
                String queryUpdate = "UPDATE NhaTro  SET tenNhaTro = ?, chuTro = ?, diaChi = ?, soDienThoai = ?, "
                        + "email = ?, idQL = ? WHERE idNhaTro = ?";
                    PreparedStatement psUpdate = conn.prepareStatement(queryUpdate);
                    psUpdate.setString(1, tfTenTro.getText());
                    psUpdate.setString(2, tfTenChuTro.getText());
                    psUpdate.setString(3, tfDiaChi.getText());
                    psUpdate.setString(4, tfSDT.getText());
                    psUpdate.setString(5, tfEmail.getText());
                    String idQL = null;
                    for (Map.Entry<String, String> entry : idTenQuanLy.entrySet()) {
                        if (entry.getValue().equals(cbNguoiQL.getSelectedItem().toString())) {
                            idQL = entry.getKey();
                            break;
                        }
                    }
                   psUpdate.setString(6, idQL);
                   psUpdate.setString(7, SharedData.getInstance().getIdNT());
                    psUpdate.executeUpdate();
                    psUpdate.close();
                    JOptionPane.showMessageDialog(this, "Sửa thông tin nhà trọ thành công");
                    setPlaceHolder();
                    SwingUtilities.getWindowAncestor(this).dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
                break;
            }
             case JOptionPane.CANCEL_OPTION:
                System.exit(0);
                break;
        }
        setPlaceHolder();
    }//GEN-LAST:event_buttonSuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.swing.Button buttonSua;
    private com.mycompany.swing.Combobox cbNguoiQL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private com.mycompany.swing.PanelBorder panelBorder1;
    private javax.swing.JTextField tfDiaChi;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfSDT;
    private javax.swing.JTextField tfTenChuTro;
    private javax.swing.JTextField tfTenTro;
    // End of variables declaration//GEN-END:variables
}
