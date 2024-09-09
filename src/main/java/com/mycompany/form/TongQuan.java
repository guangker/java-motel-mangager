package com.mycompany.form;

import com.mycompany.model.Account;
import com.mycompany.model.StatusType;
import com.mycompany.qlnt.SharedData;
import com.mycompany.swing.scrollbar.ModernScrollBarUI;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class TongQuan extends javax.swing.JPanel {

    String idQL = "";
        
    private HashMap<String, String> nhaTroIdMap = new HashMap<>();
            
    private void layIDQL(Account user){
        try {
            Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
            String query = "SELECT idQL FROM NVQuanLy WHERE idAccount = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getIdAccount());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                idQL = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public TongQuan(Account user) {
        initComponents();
        layIDQL(user);
        spTable.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        layThongTin();
        listNhaTro();
        listNhaTro.setSelectedIndex(0);
        //layThongTin();
        setOpaque(false);
    }

    public void reloadForm() {
        listNhaTro.setSelectedItem(SharedData.getInstance().getTenTro());
//        String selectedItem = (String) listNhaTro.getSelectedItem();
//        System.out.println("Selected Item: " + selectedItem);
//        if (selectedItem != null) {
//            String idNhaTro = nhaTroIdMap.get(selectedItem);
//            System.out.println("ID Nhà Trọ: " + idNhaTro);
//            if (idNhaTro != null) {
//                SharedData.getInstance().setIdNT(idNhaTro);
//                layThongTin();
//                layThongTinPhong();
//            } else {
//                System.out.println("ID Nhà Trọ không tìm thấy trong map.");
//            }
//        } else {
//            System.out.println("Không có nhà trọ nào được chọn.");
//        }
    }
    
    private void layThongTin(){
        SharedData.getInstance().setIdQL(idQL);
        Connection con = ketNoiDB.ketNoiDB.layKetNoi();
        
        String thongtin = "UPDATE dbo.NhaTro SET soPhong = ( SELECT COUNT(*) FROM Phong AS p INNER JOIN LoaiPhong AS lp "
                + "ON p.idLoaiPhong = lp.idLoaiPhong INNER JOIN NhaTro AS nt ON lp.idNhaTro = nt.idNhaTro WHERE nt.idNhaTro = ?) "
                + "WHERE idNhaTro = ?;" 
                
                + "UPDATE NhaTro SET soPhongTrong = (SELECT COUNT(*) FROM Phong INNER JOIN LoaiPhong ON Phong.idLoaiPhong = LoaiPhong.idLoaiPhong "
                + "WHERE LoaiPhong.idNhaTro = NhaTro.idNhaTro AND Phong.status = 0 ); ";
                
        
        String sql = "SELECT nt.idNhaTro, nt.tenNhaTro, nt.chuTro, nt.diaChi, nt.soDienThoai, nt.email, "
                + "nt.soPhong, nt.soPhongTrong, (nt.soPhong - nt.soPhongTrong) AS soPhongDaThue, "
                + "nt.NgayCapNhat, nt.idQL, nvql.fullName FROM dbo.NhaTro AS nt "
                + "JOIN dbo.NVQuanLy AS nvql ON nt.idQL = nvql.idQL WHERE nt.idQL = '"
                + SharedData.getInstance().getIdQL() + "' AND nt.idNhaTro = '" + SharedData.getInstance().getIdNT() + "';";

        try {
            PreparedStatement tt = con.prepareStatement(thongtin);
            tt.setString(1, SharedData.getInstance().getIdNT());
            tt.setString(2, SharedData.getInstance().getIdNT());
            tt.executeUpdate();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String tenChuTro = rs.getString("chuTro");
            chuTro.setText(tenChuTro);
            String tenNT = rs.getString("tenNhaTro");
            nhaTro.setText(tenNT);
            String dcNhaTro = rs.getString("diaChi");
            diaChi.setText(dcNhaTro);
            String sdt = rs.getString("soDienThoai");
            sodienthoai.setText(sdt);
            String email = rs.getString("email");
            eMail.setText(email);
            String sp = rs.getString("soPhong");
            soPhong.setText(sp);
            String spTrong = rs.getString("soPhongTrong");
            soPhongTrong.setText(spTrong);
            String spDaThue = rs.getString("soPhongDaThue");
            soPhongDaThue.setText(spDaThue);
            String nguoiQuanLy = rs.getString("fullName");
            nguoiQL.setText(nguoiQuanLy);
            SharedData.getInstance().setSoPhongTro(rs.getInt("soPhong"));
        }
        rs.close();
            ps.close();
            con.close();
        } catch (SQLException exSQL) {
            Logger.getLogger(sql);
        }
        
        layThongTinPhong();
    }
    
    private void listNhaTro(){
        try {
            Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
            String query = "SELECT idNhaTro, tenNhaTro, idQL FROM NhaTro WHERE idQL = ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, SharedData.getInstance().getIdQL());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String id = rs.getString("idNhaTro");
                String nhaTro = rs.getString("tenNhaTro");
                listNhaTro.addItem(nhaTro);
                nhaTroIdMap.put(nhaTro, id); // Lưu tên nhà trọ và idNhaTro vào HashMap
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        listNhaTro.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedItem = (String) listNhaTro.getSelectedItem();
            String idNhaTro = nhaTroIdMap.get(selectedItem); // Lấy idNhaTro từ HashMap
            SharedData.getInstance().setIdNT(idNhaTro); // Gán idNhaTro cho setIdNT
            SharedData.getInstance().setTenTro(selectedItem);
            layThongTin();
            layThongTinPhong();
        }
        });
    }

    
    private void layThongTinPhong(){
        DefaultTableModel dtm = (DefaultTableModel) tableThongTinPhong.getModel();
        dtm.setNumRows(0);
        Connection con = ketNoiDB.ketNoiDB.layKetNoi();
        String sql = "SELECT p.idPhong, p.tenPhong, p.dienTich, lp.tenLoaiPhong, p.status, p.ngayCapNhat, "
                + "p.ghichu FROM Phong AS p INNER JOIN LoaiPhong AS lp ON p.idLoaiPhong = lp.idLoaiPhong "
                + "INNER JOIN NhaTro AS nt ON lp.idNhaTro = nt.idNhaTro WHERE nt.idNhaTro =  ? ORDER BY idPhong ASC;";
        Vector vt;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, SharedData.getInstance().getIdNT());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getString("idPhong"));
                vt.add(rs.getString("tenPhong"));
                vt.add(rs.getString("dienTich"));
                vt.add(rs.getString("tenLoaiPhong"));
                // Thay đổi giá trị của trường "status"
                String status = rs.getString("status");
                if (status.equals("1")) {
                    vt.add(StatusType.Đã_cho_thuê);
                } else if (status.equals("0")) {
                    vt.add(StatusType.Còn_trống);
                } 
                vt.add(rs.getString("ngayCapNhat"));
               // vt.add(rs.getString("fullName"));
                vt.add(rs.getString("ghichu"));
                dtm.addRow(vt);
            }
            tableThongTinPhong.setModel(dtm);
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException exSQL) {
            Logger.getLogger(sql);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.mycompany.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        tableThongTinPhong = new com.mycompany.swing.Table();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        chuTro = new javax.swing.JLabel();
        diaChi = new javax.swing.JLabel();
        nhaTro = new javax.swing.JLabel();
        sodienthoai = new javax.swing.JLabel();
        eMail = new javax.swing.JLabel();
        soPhong = new javax.swing.JLabel();
        soPhongTrong = new javax.swing.JLabel();
        soPhongDaThue = new javax.swing.JLabel();
        nguoiQL = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        listNhaTro = new com.mycompany.swing.Combobox();

        setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setBackground(new java.awt.Color(102, 204, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        jLabel1.setText("Danh sách phòng ");

        spTable.setBorder(null);

        tableThongTinPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phòng", "Số phòng", "Kích thước", "Loại phòng", "Trạng thái", "Ngày cập nhật", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(tableThongTinPhong);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nhà trọ:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Địa chỉ:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Chủ trọ:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("SĐT:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Email");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Số phòng:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Số phòng trống:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Số phòng đã thuê:");

        chuTro.setText("jLabel10");

        diaChi.setText("jLabel10");

        nhaTro.setText("jLabel11");

        sodienthoai.setText("jLabel12");

        eMail.setText("jLabel13");

        soPhong.setText("jLabel14");

        soPhongTrong.setText("jLabel15");

        soPhongDaThue.setText("jLabel16");

        nguoiQL.setText("jLabel10");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Người quản lý:");

        listNhaTro.setBackground(new java.awt.Color(153, 204, 255));
        listNhaTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listNhaTroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(diaChi)
                            .addComponent(nhaTro)
                            .addComponent(chuTro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soPhongTrong))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soPhongDaThue))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(soPhong)))
                        .addGap(136, 136, 136)
                        .addComponent(listNhaTro, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eMail)
                            .addComponent(sodienthoai))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(72, 72, 72)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nguoiQL)
                        .addGap(520, 520, 520))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(nhaTro)
                    .addComponent(soPhong)
                    .addComponent(listNhaTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(diaChi)
                    .addComponent(soPhongTrong))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(chuTro)
                    .addComponent(soPhongDaThue))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(sodienthoai))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(eMail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nguoiQL)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 977, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listNhaTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listNhaTroActionPerformed
//        String selectedItem = (String) listNhaTro.getSelectedItem();
    }//GEN-LAST:event_listNhaTroActionPerformed

    protected void paintChildren(Graphics grphcs){
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#1CB5E0"), 0, getHeight(), Color.decode("#000046"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
        g2.fillRect(getWidth(),0,getWidth(),getHeight());
        super.paintChildren(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chuTro;
    private javax.swing.JLabel diaChi;
    private javax.swing.JLabel eMail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.mycompany.swing.Combobox listNhaTro;
    private javax.swing.JLabel nguoiQL;
    private javax.swing.JLabel nhaTro;
    private com.mycompany.swing.PanelBorder panelBorder1;
    private javax.swing.JLabel soPhong;
    private javax.swing.JLabel soPhongDaThue;
    private javax.swing.JLabel soPhongTrong;
    private javax.swing.JLabel sodienthoai;
    private javax.swing.JScrollPane spTable;
    private com.mycompany.swing.Table tableThongTinPhong;
    // End of variables declaration//GEN-END:variables
}
