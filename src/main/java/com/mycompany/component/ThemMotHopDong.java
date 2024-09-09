package com.mycompany.component;

import com.mycompany.qlnt.SharedData;
import com.mycompany.qlnt.XacThuc;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ThemMotHopDong extends javax.swing.JPanel {
    
    private boolean isConfirmed = false;  // Biến cờ

    public ThemMotHopDong() {
        initComponents();
        setPlaceHolder();
    }    

    public boolean isConfirmed() {
        return isConfirmed;
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
        addPlaceholder(tfIdKhachThue, "KT000");
        addPlaceholder(tfTienCoc, "500000");
    }
    
    @SuppressWarnings("unchecked")
    private void XacNhan() {
        // ... (các thành phần giao diện khác)

        buttonXacNhan.setText("Xác nhận");
        buttonXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXacNhanActionPerformed(evt);
            }
        });
    }
    

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.mycompany.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfIdKhachThue = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfTienCoc = new javax.swing.JTextField();
        buttonXacNhan = new com.mycompany.swing.Button();
        jdFrom = new com.toedter.calendar.JDateChooser();
        jdTo = new com.toedter.calendar.JDateChooser();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Mã khách thuê:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Thời gian từ:");

        tfIdKhachThue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Đến:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tiền cọc:");

        tfTienCoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        buttonXacNhan.setBackground(new java.awt.Color(51, 204, 0));
        buttonXacNhan.setForeground(new java.awt.Color(0, 0, 0));
        buttonXacNhan.setText("Xác nhận");
        buttonXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXacNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfIdKhachThue, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(tfTienCoc, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(jdFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(buttonXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfIdKhachThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addComponent(jdFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdTo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(buttonXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXacNhanActionPerformed
        Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
        // Kiểm tra định dạng ID
        if (!XacThuc.isIdKTExists(conn, tfIdKhachThue.getText(),SharedData.getInstance().getIdPhong())) {
            XacThuc.setTextFieldBorderColor(tfIdKhachThue, Color.red);
            JOptionPane.showMessageDialog(this, "ID khách thuê không tồn tại hoặc đang thuê phòng. Vui lòng nhập lại.");
            return; // Không thực hiện lệnh tiếp theo nếu dữ liệu không hợp lệ
        }
        
        // Lấy ngày từ JDateChooser
        Date dateFrom = jdFrom.getDate();
        Date dateTo = jdTo.getDate();

        // Kiểm tra nếu jdTo <= jdFrom thì hiển thị thông báo và không thực hiện lệnh tiếp theo
        if (dateTo.compareTo(dateFrom) <= 0) {
            JOptionPane.showMessageDialog(this, "Thời gian đến phải sau thời gian từ. Vui lòng nhập lại.");
            return;
        }
        
        // Kiểm tra định dạng số
        if (!XacThuc.isValidSo(tfTienCoc.getText())) {
            JOptionPane.showMessageDialog(this, "Tiền cọc không hợp lệ. Vui lòng nhập lại.");
            XacThuc.setTextFieldBorderColor(tfTienCoc, Color.red);
            return; // Không thực hiện lệnh tiếp theo nếu dữ liệu không hợp lệ
        }
        int choice = JOptionPane.showOptionDialog(null, "Bạn có muốn tiếp tục không?", "Xác nhận thêm khách thuê mới vào phòng",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Yes", "Quay lại"}, "Yes");
        switch (choice){
            case JOptionPane.YES_OPTION:{
                isConfirmed = true;  // Đặt cờ khi xác nhận
                try {
                String queryInsert = "INSERT INTO dbo.HopDong (idHopDong, effectiveFrom, effectiveTo, trangThaiThue, "
                        + "tienCoc, IDKhachThue, idPhong) VALUES (?,?,?,?,?,?,?);";
                    PreparedStatement psInsert = conn.prepareStatement(queryInsert);
                    // Lấy giá trị ngày từ JDateChooser
//                    Date dateFrom = jdFrom.getDate();
//                    Date dateTo = jdTo.getDate();
                    psInsert.setString(1, "");
                    psInsert.setTimestamp(2, new Timestamp(dateFrom.getTime()));
                    psInsert.setTimestamp(3, new Timestamp(dateTo.getTime()));
                    if(dateTo.compareTo(new Date()) >= 0){
                        psInsert.setString(4, "1");
                    }else{
                        psInsert.setString(4, "0");
                    }
                    psInsert.setFloat(5, Float.parseFloat(tfTienCoc.getText()));
                    psInsert.setString(6, tfIdKhachThue.getText());
                    psInsert.setNull(7, java.sql.Types.VARCHAR);
                    psInsert.executeUpdate();
                    psInsert.close();
                    setPlaceHolder();
                     // Đóng panel ThemMotHopDong
                    SwingUtilities.getWindowAncestor(this).dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
                break;
            }
             case JOptionPane.CANCEL_OPTION:
                //System.exit(0);
                break;
        }
    }//GEN-LAST:event_buttonXacNhanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.swing.Button buttonXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private com.toedter.calendar.JDateChooser jdFrom;
    private com.toedter.calendar.JDateChooser jdTo;
    private com.mycompany.swing.PanelBorder panelBorder1;
    private javax.swing.JTextField tfIdKhachThue;
    private javax.swing.JTextField tfTienCoc;
    // End of variables declaration//GEN-END:variables
}
