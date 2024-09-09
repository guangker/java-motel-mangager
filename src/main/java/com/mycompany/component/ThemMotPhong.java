package com.mycompany.component;

import com.mycompany.qlnt.SharedData;
import com.mycompany.qlnt.XacThuc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ThemMotPhong extends javax.swing.JPanel {

    // Tạo một danh sách chứa các cặp idLoaiPhong và tenLoaiPhong
    Map<String, String> idTenLoaiPhongMap = new HashMap<>();
            
    public ThemMotPhong() {
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
        addPlaceholder(tfTenPhong, "Tên phòng: 1");
        addPlaceholder(tfDienTich, "Diện tích: 3x4 m2");
        addPlaceholder(tfMaDien, "E001");
        addPlaceholder(tfMaNuoc, "W002");
        addPlaceholder(tfGhiChu, "Ghi chú trong phòng");
        tfGhiChu.setEditable(false);
    }
    
    private void loaiPhong(){
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
            cbLoaiPhong.addItem("Thêm loại phòng");
            cbLoaiPhong.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox comboBox = (JComboBox) e.getSource();
                    String selectedOption = (String) comboBox.getSelectedItem();
                    String idLoaiPhongChon = null;
                    for (Map.Entry<String, String> entry : idTenLoaiPhongMap.entrySet()) {
                        if (entry.getValue().equals(cbLoaiPhong.getSelectedItem().toString())) {
                            idLoaiPhongChon = entry.getKey();
                            break;
                        }
                    }
                    if (selectedOption.equals("Thường")) {
                       tfGhiChu.setText("Kha 123");
                    }else if(selectedOption.equals("Vip")){
                        tfGhiChu.setText("Kha KS");
                    }else{
                        tfGhiChu.setText("Kha");
                    }
                }
            });
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfTenPhong = new javax.swing.JTextField();
        tfDienTich = new javax.swing.JTextField();
        tfMaDien = new javax.swing.JTextField();
        tfMaNuoc = new javax.swing.JTextField();
        tfGhiChu = new javax.swing.JTextField();
        cbLoaiPhong = new com.mycompany.swing.Combobox();
        xacNhan = new com.mycompany.swing.Button();

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Tên phòng:");

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Diện tích:");

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Mã đồng hồ điện:");

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Ghi chú:");

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Loại phòng:");

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Mã đồng hồ nước:");

        tfTenPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tfDienTich.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tfMaDien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tfMaNuoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tfGhiChu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbLoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLoaiPhongActionPerformed(evt);
            }
        });

        xacNhan.setText("Xác nhận");
        xacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xacNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfTenPhong)
                    .addComponent(tfDienTich)
                    .addComponent(tfMaDien)
                    .addComponent(tfMaNuoc)
                    .addComponent(tfGhiChu, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(cbLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(69, 69, 69))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(xacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfDienTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfMaDien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfMaNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(xacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void xacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xacNhanActionPerformed
        // Kiểm tra định dạng Diện tích
        if (!XacThuc.isValidDienTich(tfDienTich.getText())) {
            XacThuc.setTextFieldBorderColor(tfDienTich, Color.red);
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra. Vui lòng nhập lại.");
            return; // Không thực hiện lệnh tiếp theo nếu dữ liệu không hợp lệ
        }else{
            XacThuc.setTextFieldBorderColor(tfDienTich, new Color(75, 70, 75));
        }
        // Kiểm tra định dạng maDien
        if (!XacThuc.isIdDienExists(tfMaDien.getText())) {
            XacThuc.setTextFieldBorderColor(tfMaDien, Color.red);
            JOptionPane.showMessageDialog(this, "Mã đồng hồ điện không tồn tại hoặc đã có phòng sử dụng. Vui lòng nhập lại.");
            return; // Không thực hiện lệnh tiếp theo nếu dữ liệu không hợp lệ
        }else{
            XacThuc.setTextFieldBorderColor(tfDienTich, new Color(75, 70, 75));
        }
        
        // Kiểm tra định dạng maNuoc
        if (!XacThuc.isIdNuocExists(tfMaNuoc.getText())) {
            XacThuc.setTextFieldBorderColor(tfMaNuoc, Color.red);
            JOptionPane.showMessageDialog(this, "Mã đồng hồ nước không tồn tại hoặc đã có phòng sử dụng. Vui lòng nhập lại.");
            return; // Không thực hiện lệnh tiếp theo nếu dữ liệu không hợp lệ
        }
        
        int choice = JOptionPane.showOptionDialog(null, "Bạn có muốn tiếp tục không?", "Xác nhận thêm phòng mới",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Yes", "Quay lại"}, "Yes");
        switch (choice){
            case JOptionPane.YES_OPTION:{
                try {
                Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
                String queryInsert = "INSERT INTO Phong (idPhong, tenPhong, dienTich, idDien, idNuoc, ghichu, status, ngayCapNhat, idLoaiPhong, soTienConNo)" +
                             " VALUES(?,?,?,?,?,?,?,?,?,?);";
                    PreparedStatement psInsert = conn.prepareStatement(queryInsert);
                    psInsert.setString(1, "");
                    psInsert.setString(2, tfTenPhong.getText());
                    psInsert.setString(3, tfDienTich.getText());
                    psInsert.setString(4, tfMaDien.getText());
                    psInsert.setString(5, tfMaNuoc.getText());
                    psInsert.setString(6, tfGhiChu.getText());
                    psInsert.setInt(7, 0);
                    psInsert.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis())); // Lấy thời gian hiện tại
                    String idLoaiPhongChon = null;
                    for (Map.Entry<String, String> entry : idTenLoaiPhongMap.entrySet()) {
                        if (entry.getValue().equals(cbLoaiPhong.getSelectedItem().toString())) {
                            idLoaiPhongChon = entry.getKey();
                            break;
                        }
                    }
                    psInsert.setString(9, idLoaiPhongChon);
                    psInsert.setFloat(10, 0);
                    psInsert.executeUpdate();
                    psInsert.close();
                    JOptionPane.showMessageDialog(this, "Thêm thành công, load lại phòng để xem chi tiết");
                    SwingUtilities.getWindowAncestor(this).dispose();
                    setPlaceHolder();
            } catch (Exception e) {
                e.printStackTrace();
            }
                break;
            }
             case JOptionPane.CANCEL_OPTION:
                System.exit(0);
                break;
        }
    }//GEN-LAST:event_xacNhanActionPerformed

    private void cbLoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLoaiPhongActionPerformed
        cbLoaiPhong.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                String selectedOption = (String) comboBox.getSelectedItem();

                if (selectedOption.equals("Thêm loại phòng")) {
                    ThemLoaiPhong themLoaiPhong = new ThemLoaiPhong();
                    // Tạo một JDialog tùy chỉnh
                    JDialog dialog = new JDialog();
                    dialog.setTitle("Thông tin phòng"); // Thiết lập tiêu đề cho dialog

                    // Thiết lập layout cho dialog
                    dialog.setLayout(new BorderLayout());

                    // Thêm panel thongTinPhong vào dialog
                    dialog.add(themLoaiPhong, BorderLayout.CENTER);

                    // Cài đặt các thuộc tính khác của dialog
                    dialog.setModal(true);
                    dialog.pack();
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                }
            }
        });
    }//GEN-LAST:event_cbLoaiPhongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.swing.Combobox cbLoaiPhong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private com.mycompany.swing.PanelBorder panelBorder1;
    private javax.swing.JTextField tfDienTich;
    private javax.swing.JTextField tfGhiChu;
    private javax.swing.JTextField tfMaDien;
    private javax.swing.JTextField tfMaNuoc;
    private javax.swing.JTextField tfTenPhong;
    private com.mycompany.swing.Button xacNhan;
    // End of variables declaration//GEN-END:variables
}
