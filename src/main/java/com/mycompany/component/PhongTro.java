package com.mycompany.component;

import com.mycompany.model.Model_PhongTro;
import com.mycompany.qlnt.SharedData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class PhongTro extends javax.swing.JPanel {
    
    private String idPT= "";

    public PhongTro() {
        initComponents();
        setOpaque(false);
    }
    
    public void setData(Model_PhongTro data){
        lbPhong.setText(data.getName());
        lbTien.setText(data.getGia());
        buttonThem.setText("Thêm");
        if(data.getStatus().equals("1")){
            buttonTra.setBackground(Color.decode("#EDD75B"));
        }else{
            setBackground(Color.decode("#E5E2DA"));
            buttonTra.setVisible(false);
        }
        lbSoTienConNo.setText(data.getSoTienConNo());
        lbLoaiPhong.setText(data.getIdLoaiPhong());
        idPT = data.getId();
    }

   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbPhong = new javax.swing.JLabel();
        lbIcon = new javax.swing.JLabel();
        buttonTra = new javax.swing.JButton();
        buttonXem = new javax.swing.JButton();
        buttonChinhSua = new javax.swing.JButton();
        lbIcon1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lbLoaiPhong = new javax.swing.JLabel();
        lbSoTienConNo = new javax.swing.JLabel();
        buttonThem = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lbTien = new javax.swing.JLabel();

        setBackground(new java.awt.Color(105, 236, 78));
        setMaximumSize(new java.awt.Dimension(300, 300));
        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(210, 180));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbPhong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbPhong.setForeground(new java.awt.Color(51, 51, 51));
        lbPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPhong.setText("Phòng");
        add(lbPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 100, -1));

        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/icon/home.png"))); // NOI18N
        add(lbIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 30, 30));

        buttonTra.setBackground(new java.awt.Color(255, 255, 0));
        buttonTra.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonTra.setForeground(new java.awt.Color(255, 255, 255));
        buttonTra.setText("Trả");
        buttonTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTraActionPerformed(evt);
            }
        });
        add(buttonTra, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 3, 50, 20));

        buttonXem.setBackground(new java.awt.Color(102, 102, 102));
        buttonXem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonXem.setForeground(new java.awt.Color(255, 255, 255));
        buttonXem.setText("Xem");
        buttonXem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXemActionPerformed(evt);
            }
        });
        add(buttonXem, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 50, 80, -1));

        buttonChinhSua.setBackground(new java.awt.Color(51, 51, 255));
        buttonChinhSua.setForeground(new java.awt.Color(255, 255, 255));
        buttonChinhSua.setText("🛠 Sửa");
        buttonChinhSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChinhSuaActionPerformed(evt);
            }
        });
        add(buttonChinhSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 80, -1));

        lbIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/icon/user.png"))); // NOI18N
        add(lbIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/icon/money.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("❌ Xóa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 80, -1));

        lbLoaiPhong.setText("Trống");
        add(lbLoaiPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 80, -1));

        lbSoTienConNo.setForeground(new java.awt.Color(255, 51, 51));
        lbSoTienConNo.setText("Tiền");
        add(lbSoTienConNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 80, -1));

        buttonThem.setBackground(new java.awt.Color(0, 204, 102));
        buttonThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonThem.setForeground(new java.awt.Color(255, 255, 255));
        buttonThem.setText("Thêm");
        buttonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemActionPerformed(evt);
            }
        });
        add(buttonThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, -1));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Còn nợ:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        lbTien.setForeground(new java.awt.Color(255, 51, 51));
        lbTien.setText("Tiền");
        add(lbTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 60, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonChinhSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChinhSuaActionPerformed
        SuaThongTinPhongTro suaPhongTro = new SuaThongTinPhongTro(idPT);
        // Tạo một JDialog tùy chỉnh
        JDialog dialog = new JDialog();
        dialog.setTitle("Thông tin phòng"); // Thiết lập tiêu đề cho dialog

        // Thiết lập layout cho dialog
        dialog.setLayout(new BorderLayout());

        // Thêm panel thongTinPhong vào dialog
        dialog.add(suaPhongTro, BorderLayout.CENTER);
        
        // Cài đặt các thuộc tính khác của dialog
        dialog.setModal(true);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_buttonChinhSuaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int choice = JOptionPane.showOptionDialog(null, "Bạn có chắc xóa phòng này không?", "Xóa phòng trọ hiện tại",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Yes", "Quay lại"}, "Yes");
        switch (choice){
            case JOptionPane.YES_OPTION:{
                try {
                    Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
                    String status = "SELECT status FROM Phong WHERE status = '1' AND idPhong = ?";
                    PreparedStatement stt = conn.prepareStatement(status);
                    stt.setString(1, idPT);
                    ResultSet rs = stt.executeQuery();
                    // Nếu phòng có trạng thái (có kết quả trả về), không thực hiện xóa
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Không thể xóa phòng đang cho thuê!");
                    } else {
                        // Thực hiện lệnh xóa
                        String deleteQuery = "DELETE FROM Phong WHERE idPhong = ?";
                        PreparedStatement ps = conn.prepareStatement(deleteQuery);
                        ps.setString(1, idPT);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Phòng đã được xóa!!!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case JOptionPane.CANCEL_OPTION:
                System.exit(0);
                break; 
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buttonTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTraActionPerformed
        int choice = JOptionPane.showOptionDialog(null, "Bạn có chắc trả phòng này không?", "Trả phòng",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Yes", "Quay lại"}, "Yes");
        switch (choice){
            case JOptionPane.YES_OPTION:
                try {
                    Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
                    String traPT = "UPDATE dbo.HopDong SET trangThaiThue = 0 WHERE idPhong = ?";
                    PreparedStatement tra = conn.prepareStatement(traPT);
                    tra.setString(1, idPT);
                    tra.executeUpdate();
                    tra.close();
                    conn.close();
                    JOptionPane.showMessageDialog(this, "Phòng đã được trả");
                    }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case JOptionPane.CANCEL_OPTION:
                System.exit(0);
                break; 
        }        
    }//GEN-LAST:event_buttonTraActionPerformed

    private void buttonXemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXemActionPerformed
        ChiTietPhong thongTinPhong = new ChiTietPhong(idPT);
        // Tạo một JDialog tùy chỉnh
        JDialog dialog = new JDialog();
        dialog.setTitle("Thông tin phòng"); // Thiết lập tiêu đề cho dialog

        // Thiết lập layout cho dialog
        dialog.setLayout(new BorderLayout());

        // Thêm panel thongTinPhong vào dialog
        dialog.add(thongTinPhong, BorderLayout.CENTER);
        
        // Cài đặt các thuộc tính khác của dialog
        dialog.setModal(true);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_buttonXemActionPerformed

    private void buttonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemActionPerformed
        SharedData.getInstance().setIdPhong(idPT);
        ThemMotHopDong themHD = new ThemMotHopDong();
        // Tạo một JDialog tùy chỉnh
        JDialog dialog = new JDialog();
        dialog.setTitle("Thêm khách thuê vào phòng"); // Thiết lập tiêu đề cho dialog

        // Thiết lập layout cho dialog
        dialog.setLayout(new BorderLayout());

        // Thêm panel themHD vào dialog
        dialog.add(themHD, BorderLayout.CENTER);

        // Cài đặt các thuộc tính khác của dialog
        dialog.setModal(true);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        if(themHD.isConfirmed()){
            try {
            Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
             // Lấy id của hợp đồng cuối cùng từ bảng HopDong
            String querySelect = "SELECT TOP 1 idHopDong FROM HopDong ORDER BY idHopDong DESC";
            PreparedStatement psSelect = conn.prepareStatement(querySelect);
            ResultSet rs = psSelect.executeQuery();

            String idHopDong = null;
            if (rs.next()) {
                idHopDong = rs.getString("idHopDong");
            }   

           // Cập nhật idPhongTro cho hợp đồng cuối cùng
            if (idHopDong != null) {
                String queryUpdate = "UPDATE HopDong SET idPhong = ? WHERE idHopDong = ?";
                PreparedStatement psUpdate = conn.prepareStatement(queryUpdate);
                psUpdate.setString(1, idPT);
                psUpdate.setString(2, idHopDong);
                psUpdate.executeUpdate();
                psUpdate.close();
            }

            // Đóng kết nối và hiển thị thông báo
            rs.close();
            psSelect.close();
            conn.close();
            JOptionPane.showMessageDialog(this, "Thêm khách thuê thành công, load lại phòng để xem chi tiết");
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        dialog.setVisible(false);
    }//GEN-LAST:event_buttonThemActionPerformed

    @Override
      protected void paintComponent(Graphics grphcs){
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonChinhSua;
    private javax.swing.JButton buttonThem;
    private javax.swing.JButton buttonTra;
    private javax.swing.JButton buttonXem;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbIcon1;
    private javax.swing.JLabel lbLoaiPhong;
    private javax.swing.JLabel lbPhong;
    private javax.swing.JLabel lbSoTienConNo;
    private javax.swing.JLabel lbTien;
    // End of variables declaration//GEN-END:variables
}
