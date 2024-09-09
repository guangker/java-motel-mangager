package com.mycompany.component;

import com.mycompany.qlnt.SharedData;
import com.mycompany.qlnt.XacThuc;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ThemLoaiPhong extends javax.swing.JPanel {

    public ThemLoaiPhong() {
        initComponents();
        setPlaceHolder();
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
        addPlaceholder(tfLoaiPhong, "Phòng VIP");
        addPlaceholder(tfGia, "2500000");
    }
    
    private void themLoaiPhong(){
        try {
            // Kiểm tra định dạng số
            if (!XacThuc.isValidSo(tfGia.getText())) {
                XacThuc.setTextFieldBorderColor(tfGia, Color.red);
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra. Vui lòng nhập lại.");
                return; // Không thực hiện lệnh tiếp theo nếu dữ liệu không hợp lệ
            }
            Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
            String query = "INSERT INTO dbo.LoaiPhong (idLoaiPhong, tenLoaiPhong, ghichu, ngayCapNhat, idNhaTro, gia)"
                    + " VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "");
            ps.setString(2, tfLoaiPhong.getText());
            ps.setString(3, taGhiChu.getText());
            ps.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis())); // Lấy thời gian hiện tại
            ps.setString(5, SharedData.getInstance().getIdNT());
            ps.setString(6, tfGia.getText());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        themLoaiPhong = new com.mycompany.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btThem = new com.mycompany.swing.Button();
        tfLoaiPhong = new javax.swing.JTextField();
        tfGia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taGhiChu = new javax.swing.JTextArea();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Loại phòng:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Ghi chú:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Giá:");

        btThem.setText("Thêm");
        btThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemActionPerformed(evt);
            }
        });

        tfLoaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfLoaiPhong.setForeground(new java.awt.Color(0, 0, 0));
        tfLoaiPhong.setText("jTextField1");

        tfGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfGia.setForeground(new java.awt.Color(0, 0, 0));
        tfGia.setText("jTextField1");

        taGhiChu.setColumns(20);
        taGhiChu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        taGhiChu.setForeground(new java.awt.Color(0, 0, 0));
        taGhiChu.setRows(5);
        jScrollPane1.setViewportView(taGhiChu);

        javax.swing.GroupLayout themLoaiPhongLayout = new javax.swing.GroupLayout(themLoaiPhong);
        themLoaiPhong.setLayout(themLoaiPhongLayout);
        themLoaiPhongLayout.setHorizontalGroup(
            themLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(themLoaiPhongLayout.createSequentialGroup()
                .addGroup(themLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(themLoaiPhongLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(themLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(themLoaiPhongLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfGia))
                            .addGroup(themLoaiPhongLayout.createSequentialGroup()
                                .addGroup(themLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(themLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                                    .addComponent(tfLoaiPhong)))))
                    .addGroup(themLoaiPhongLayout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        themLoaiPhongLayout.setVerticalGroup(
            themLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(themLoaiPhongLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(themLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(themLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(themLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(themLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(themLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemActionPerformed
        themLoaiPhong();
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_btThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.swing.Button btThem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taGhiChu;
    private javax.swing.JTextField tfGia;
    private javax.swing.JTextField tfLoaiPhong;
    private com.mycompany.swing.PanelBorder themLoaiPhong;
    // End of variables declaration//GEN-END:variables
}
