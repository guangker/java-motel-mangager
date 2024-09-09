package com.mycompany.component;
import com.mycompany.model.Model_PhongTro;
import com.mycompany.qlnt.SharedData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class ChiTietPhong extends javax.swing.JPanel {

    public ChiTietPhong(String idPhong) {
        initComponents();
        layThongTin(idPhong);
    }
    
    private void layThongTin(String idPhong){
        
        Connection con = ketNoiDB.ketNoiDB.layKetNoi();

        String sql = "SELECT p.idPhong, p.tenPhong, p.dienTich, p.idDien, p.idNuoc, e.value AS eValue, w.value AS wValue, p.ghichu, p.status, " +
                     "p.ngayCapNhat, p.soTienConNo, lp.tenLoaiPhong, lp.gia, kt.fullName AS tenKhachThue, hd.trangThaiThue " +
                     "FROM Phong AS p " +
                     "JOIN LoaiPhong AS lp ON p.idLoaiPhong = lp.idLoaiPhong " +
                     "LEFT JOIN HopDong AS hd ON p.idPhong = hd.idPhong " +
                     "LEFT JOIN KhachThue AS kt ON hd.IDKhachThue = kt.IDKhachThue " +
                     "LEFT JOIN ElectricMeter AS e ON p.idDien = e.idDien LEFT JOIN WaterMeter AS w ON p.idNuoc = W.idNuoc " +
                     "WHERE p.idPhong = ? AND hd.trangThaiThue = 1";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idPhong);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                jlMaPhong.setText(rs.getString("idPhong"));
                jlDienTich.setText(rs.getString("dienTich"));
                jlSoDien.setText(rs.getString("eValue"));
                jlSoNuoc.setText(rs.getString("wValue"));
                taGhiChu.setText(rs.getString("ghichu"));
                jlLoaiPhong.setText(rs.getString("tenLoaiPhong"));
                jlGia.setText(rs.getString("gia"));
                jlNgayCN.setText(rs.getString("ngayCapNhat").toString());

                String stt = rs.getString("status");
                if (stt.equals("1")) {
                    jlStatus.setText("Đang thuê");
                    // Nếu có thông tin khách thuê
                    String tenKhachThue = rs.getString("tenKhachThue");
                    if(rs.getString("trangThaiThue").equals("1")){
                        cbKhachThue.addItem(tenKhachThue);
                    }                
                    jlSoTienNo.setText(rs.getString("soTienConNo"));
                } else {
                    jlStatus.setText("Còn trống");
                    cbKhachThue.addItem("Không có khách thuê");
                    // Nếu không có khách thuê, bạn có thể xử lý hiển thị trạng thái nợ ở đây nếu cần
                }
            } 
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException exSQL) {
            Logger.getLogger(ChiTietPhong.class.getName()).log(Level.SEVERE, null, exSQL);
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbKhachThue = new com.mycompany.swing.Combobox();
        jlStatus = new javax.swing.JLabel();
        jlGia = new javax.swing.JLabel();
        jlSoDien = new javax.swing.JLabel();
        jlDienTich = new javax.swing.JLabel();
        jlMaPhong = new javax.swing.JLabel();
        jlNgayCN = new javax.swing.JLabel();
        button1 = new com.mycompany.swing.Button();
        jLabel9 = new javax.swing.JLabel();
        jlSoNuoc = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jlLoaiPhong = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taGhiChu = new com.mycompany.swing.TextArea();
        jLabel11 = new javax.swing.JLabel();
        jlSoTienNo = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Mã phòng:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Diện tích:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Số điện:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Số nước:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Ghi chú:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Giá:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Ngày cập nhật:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Khách thuê:");

        cbKhachThue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKhachThueActionPerformed(evt);
            }
        });

        jlStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlStatus.setText("jLabel9");

        jlGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlGia.setText("jLabel9");

        jlSoDien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlSoDien.setText("jLabel9");

        jlDienTich.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlDienTich.setText("jLabel9");

        jlMaPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlMaPhong.setText("jLabel9");

        jlNgayCN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlNgayCN.setText("jLabel9");

        button1.setBackground(new java.awt.Color(255, 51, 51));
        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setText("Đóng");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Trạng thái:");

        jlSoNuoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlSoNuoc.setText("jLabel9");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Loại phòng:");

        jlLoaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlLoaiPhong.setText("jLabel9");

        taGhiChu.setColumns(20);
        taGhiChu.setRows(5);
        jScrollPane2.setViewportView(taGhiChu);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Số tiền còn nợ: ");

        jlSoTienNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlSoTienNo.setForeground(new java.awt.Color(255, 0, 51));
        jlSoTienNo.setText("jLabel12");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbKhachThue, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jlMaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlDienTich, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlNgayCN, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlSoDien, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlSoNuoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jlGia, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlSoTienNo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jlMaPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jlDienTich))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jlSoDien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jlSoNuoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jlLoaiPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jlStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jlGia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jlNgayCN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbKhachThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jlSoTienNo))
                .addGap(18, 18, 18)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // Đóng panel ChiTietPhong
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_button1ActionPerformed

    private void cbKhachThueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKhachThueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKhachThueActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.swing.Button button1;
    private com.mycompany.swing.Combobox cbKhachThue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlDienTich;
    private javax.swing.JLabel jlGia;
    private javax.swing.JLabel jlLoaiPhong;
    private javax.swing.JLabel jlMaPhong;
    private javax.swing.JLabel jlNgayCN;
    private javax.swing.JLabel jlSoDien;
    private javax.swing.JLabel jlSoNuoc;
    private javax.swing.JLabel jlSoTienNo;
    private javax.swing.JLabel jlStatus;
    private com.mycompany.swing.PanelBorder panelBorder1;
    private com.mycompany.swing.TextArea taGhiChu;
    // End of variables declaration//GEN-END:variables
}
