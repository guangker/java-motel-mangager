package com.mycompany.form;
import com.mycompany.component.PhongTro;
import com.mycompany.model.Model_PhongTro;
import com.mycompany.qlnt.SharedData;
import com.mycompany.swing.scrollbar.ModernScrollBarUI;
import com.mycompany.component.ThemMotPhong;
import com.mycompany.component.SuaThongTinNhaTro;
import com.mycompany.component.ThemMotNhaTro;
import com.mycompany.model.Account;
import com.mycompany.qlnt.XacThuc;
import com.raven.swing.WrapLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class DanhSachPhong extends javax.swing.JPanel {
    
    private HashMap<String, String> nhaTroIdMap = new HashMap<>();
    
    public DanhSachPhong(Account user) {
        initComponents();
        listPhong.setLayout(new WrapLayout(WrapLayout.LEFT, 30, 15)); // Thiết lập layout cho listPhong
        listPhong.setOpaque(false);
        spListPhong.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        spListPhong.getVerticalScrollBar().setBackground(Color.WHITE);
        trangThaiPhong();
        listNhaTro();
    }

    public void reloadForm() {
//        listNhaTro();
        listNhaTro.setSelectedItem(SharedData.getInstance().getTenTro());
        listPhong.removeAll(); // Xóa tất cả các phòng đang hiển thị
        ChiTietPhongTro(); // Load lại dữ liệu danh sách phòng từ cơ sở dữ liệu
        updateListPhongSize(); // Cập nhật kích thước của danh sách phòng
        SharedData.getInstance().updateDB();
        listPhong.revalidate(); // Yêu cầu danh sách phòng vẽ lại giao diện
        listPhong.repaint();
    }

    private void ChiTietPhongTro() {
        try {
            Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
            String update ="UPDATE Phong SET status = CASE WHEN EXISTS (SELECT 1 FROM HopDong "
                    + "WHERE HopDong.idPhong = Phong.idPhong AND HopDong.trangThaiThue = 1) THEN 1 ELSE 0 END;";
            PreparedStatement statusPhong = conn.prepareStatement(update);
            statusPhong.executeUpdate();
            String trangThai = trangThaiPhong.getSelectedItem().toString();
            String query= "SELECT p.idPhong, p.tenPhong, p.dienTich, lp.gia, p.idDien, p.idNuoc, "
                        + "p.ghichu, p.status, p.ngayCapNhat, p.idLoaiPhong, p.soTienConNo FROM Phong AS p INNER JOIN LoaiPhong AS lp "
                        + "ON p.idLoaiPhong = lp.idLoaiPhong INNER JOIN NhaTro AS nt ON lp.idNhaTro = nt.idNhaTro "
                        + "WHERE nt.idNhaTro = ? ";
            if(trangThai.equals("Đang thuê")){
                query += "AND status = 1;";
            }else if(trangThai.equals("Còn trống")){
                query += "AND status = 0;";
            }
            PreparedStatement ps = conn.prepareStatement(query);
            String idNT = SharedData.getInstance().getIdNT();
            ps.setString(1, idNT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhongTro phongTro = new PhongTro();
                phongTro.setData(new Model_PhongTro(rs.getString("idPhong"),"Phòng " + rs.getString("tenPhong"),
                        rs.getString("dienTich"), rs.getString("gia"), rs.getString("idDien"),
                        rs.getString("idNuoc"), rs.getString("ghichu"),rs.getString("status"),
                        rs.getString("ngayCapNhat"), rs.getString("idLoaiPhong"), rs.getString("soTienConNo")));

                addPhongTro(phongTro);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void trangThaiPhong(){
        trangThaiPhong.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            reloadForm();
            }
        });
    }
    
    // Phương thức để thêm JPanel (phongTro) vào listPhong
    public void addPhongTro(PhongTro phongTro) {
        listPhong.add(phongTro); // Thêm phongTro vào listPhong
        // listPhong.repaint(); //Vẽ lại giao diện listPhong
    }

    // Cập nhật kích thước của listPhong dựa trên số lượng phongTro và kích thước của chúng
    private void updateListPhongSize() {
        int rowCount = 0;
        int componentCount = listPhong.getComponentCount();
        if(componentCount/4 == 0){
            rowCount = componentCount/4;
        }else{
            rowCount = componentCount/4 + 1;
        }
        int width = 4 * (210 + 10) + 10; // Tính toán chiều rộng mới
        int height = rowCount * (180 + 10) + 50; // Tính toán chiều cao mới
        listPhong.setPreferredSize(new Dimension(width, height));
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
//            listNhaTro.setSelectedItem(nhaTroIdMap.get(SharedData.getInstance().getIdNT()));
//            System.out.println(SharedData.getInstance().getIdNT());
            listNhaTro.setSelectedItem(SharedData.getInstance().getTenTro());
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
            reloadForm();
            }
        });
    }

    
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#1CB5E0"), 0, getHeight(), Color.decode("#000046"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
        g2.fillRect(getWidth(), 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spListPhong = new javax.swing.JScrollPane();
        listPhong = new com.mycompany.swing.PanelBorder();
        panelBorder1 = new com.mycompany.swing.PanelBorder();
        trangThaiPhong = new javax.swing.JComboBox<>();
        buttonThemPhongNhanh = new com.mycompany.swing.Button();
        buttonThemPhong = new com.mycompany.swing.Button();
        buttonSuaNhaTro = new com.mycompany.swing.Button();
        buttonXoaNhaTro = new com.mycompany.swing.Button();
        buttonThemNhaTro = new com.mycompany.swing.Button();
        listNhaTro = new com.mycompany.swing.Combobox();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));

        spListPhong.setBackground(new java.awt.Color(51, 102, 255));
        spListPhong.setBorder(null);
        spListPhong.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout listPhongLayout = new javax.swing.GroupLayout(listPhong);
        listPhong.setLayout(listPhongLayout);
        listPhongLayout.setHorizontalGroup(
            listPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        listPhongLayout.setVerticalGroup(
            listPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        spListPhong.setViewportView(listPhong);

        panelBorder1.setBackground(new java.awt.Color(102, 204, 255));

        trangThaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        trangThaiPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang thuê", "Còn trống" }));
        trangThaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trangThaiPhongActionPerformed(evt);
            }
        });

        buttonThemPhongNhanh.setBackground(new java.awt.Color(0, 204, 204));
        buttonThemPhongNhanh.setForeground(new java.awt.Color(255, 255, 255));
        buttonThemPhongNhanh.setText("+Thêm phòng nhanh");
        buttonThemPhongNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemPhongNhanhActionPerformed(evt);
            }
        });

        buttonThemPhong.setBackground(new java.awt.Color(0, 160, 89));
        buttonThemPhong.setForeground(new java.awt.Color(255, 255, 255));
        buttonThemPhong.setText("🛏 Thêm phòng");
        buttonThemPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemPhongActionPerformed(evt);
            }
        });

        buttonSuaNhaTro.setBackground(new java.awt.Color(0, 51, 153));
        buttonSuaNhaTro.setForeground(new java.awt.Color(255, 255, 255));
        buttonSuaNhaTro.setText("✎Sửa nhà");
        buttonSuaNhaTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSuaNhaTroActionPerformed(evt);
            }
        });

        buttonXoaNhaTro.setBackground(new java.awt.Color(255, 0, 51));
        buttonXoaNhaTro.setForeground(new java.awt.Color(255, 255, 255));
        buttonXoaNhaTro.setText("☓ Xóa nhà");
        buttonXoaNhaTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXoaNhaTroActionPerformed(evt);
            }
        });

        buttonThemNhaTro.setBackground(new java.awt.Color(0, 51, 153));
        buttonThemNhaTro.setForeground(new java.awt.Color(255, 255, 255));
        buttonThemNhaTro.setText("🏠Thêm nhà");
        buttonThemNhaTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemNhaTroActionPerformed(evt);
            }
        });

        listNhaTro.setBackground(new java.awt.Color(0, 153, 255));
        listNhaTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listNhaTroActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Trạng thái:");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(trangThaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(listNhaTro, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(0, 333, Short.MAX_VALUE)
                        .addComponent(buttonThemPhongNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonThemPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(buttonThemNhaTro, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonSuaNhaTro, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonXoaNhaTro, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(trangThaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(listNhaTro, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonXoaNhaTro, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(buttonSuaNhaTro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonThemNhaTro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonThemPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonThemPhongNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spListPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spListPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void buttonThemPhongNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemPhongNhanhActionPerformed
        try {
            Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
            if(!XacThuc.getInstance().isLPExists(conn, SharedData.getInstance().getIdNT())){
                JOptionPane.showMessageDialog(this, "Chưa có thông tin loại phòng!!!");
                return;
            }
            // Tạo câu truy vấn SQL để lấy dữ liệu idLoaiPhong từ bảng LoaiPhong
            String query = "SELECT idLoaiPhong, tenLoaiPhong FROM LoaiPhong WHERE idNhaTro = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, SharedData.getInstance().getIdNT());
            ResultSet rs = ps.executeQuery();

             // Tạo một danh sách chứa các cặp idLoaiPhong và tenLoaiPhong
            Map<String, String> idTenLoaiPhongMap = new HashMap<>();
            while (rs.next()) {
                String idLoaiPhong = rs.getString("idLoaiPhong");
                String tenLoaiPhong = rs.getString("tenLoaiPhong");
                idTenLoaiPhongMap.put(idLoaiPhong, tenLoaiPhong);
            }

             // Tạo một mảng chứa các tên loại phòng (tenLoaiPhong) để sử dụng trong dialog
            String[] tenLoaiPhongArray = idTenLoaiPhongMap.values().toArray(new String[0]);

            // Hiển thị dialog cho người dùng chọn tên loại phòng
            String tenLoaiPhongChon = (String) JOptionPane.showInputDialog(
            null,
            "Chọn tên loại phòng:",
            "Chọn loại phòng",
            JOptionPane.PLAIN_MESSAGE,
            null,
            tenLoaiPhongArray,
            tenLoaiPhongArray[0]); // Giá trị mặc định nếu không có lựa chọn nào được chọn

            // Nếu người dùng chọn một tên loại phòng, thì lấy idLoaiPhong tương ứng và sử dụng nó trong câu lệnh INSERT
        if (tenLoaiPhongChon != null) {
            String idLoaiPhongChon = null;
            for (Map.Entry<String, String> entry : idTenLoaiPhongMap.entrySet()) {
                if (entry.getValue().equals(tenLoaiPhongChon)) {
                    idLoaiPhongChon = entry.getKey();
                    break;
                }
            }
            if (idLoaiPhongChon != null) {
                String queryInsert = "INSERT INTO Phong (idPhong, tenPhong, dienTich, idDien, idNuoc, ghichu, status, ngayCapNhat, idLoaiPhong)" +
                         " VALUES(?,?,?,?,?,?,?,?,?);";
                PreparedStatement psInsert = conn.prepareStatement(queryInsert);
                psInsert.setString(1, "");
                psInsert.setNull(2, java.sql.Types.VARCHAR);
                psInsert.setNull(3, java.sql.Types.FLOAT);
                psInsert.setNull(4, java.sql.Types.INTEGER);
                psInsert.setNull(5, java.sql.Types.INTEGER);
                psInsert.setNull(6, java.sql.Types.VARCHAR);
                psInsert.setInt(7, 0);
                psInsert.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis())); // Lấy thời gian hiện tại
                psInsert.setString(9, idLoaiPhongChon);
                psInsert.executeUpdate();
                psInsert.close();
                JOptionPane.showMessageDialog(this, "Thêm thành công, load lại phòng để xem chi tiết");
            }
        }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_buttonThemPhongNhanhActionPerformed

    private void buttonThemPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemPhongActionPerformed
        ThemMotPhong pnThemPhong = new ThemMotPhong();
        // Tạo một JDialog tùy chỉnh
        JDialog dialog = new JDialog();
        dialog.setTitle("Thêm phòng"); // Thiết lập tiêu đề cho dialog

        // Thiết lập layout cho dialog
        dialog.setLayout(new BorderLayout());

        // Thêm panel themMotPhong vào dialog
        dialog.add(pnThemPhong, BorderLayout.CENTER);

        // Cài đặt các thuộc tính khác của dialog
        dialog.setModal(true);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_buttonThemPhongActionPerformed

    private void buttonSuaNhaTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSuaNhaTroActionPerformed
        SuaThongTinNhaTro pnSuaPhong = new SuaThongTinNhaTro();
        // Tạo một JDialog tùy chỉnh
        JDialog dialog = new JDialog();
        dialog.setTitle("Thông tin nhà trọ"); // Thiết lập tiêu đề cho dialog

        // Thiết lập layout cho dialog
        dialog.setLayout(new BorderLayout());

        // Thêm panel themMotPhong vào dialog
        dialog.add(pnSuaPhong, BorderLayout.CENTER);

        // Cài đặt các thuộc tính khác của dialog
        dialog.setModal(true);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_buttonSuaNhaTroActionPerformed

    private void buttonXoaNhaTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXoaNhaTroActionPerformed
        int choice = JOptionPane.showOptionDialog(null, "Bạn có chắc xóa nhà trọ không?", "Xóa nhà trọ hiện tại",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Yes", "Quay lại"}, "Yes");
        switch (choice){
            case JOptionPane.YES_OPTION:{
                try {
                    Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
                    String delete= "DELETE FROM NhaTro WHERE idNhaTro = ?";
                    PreparedStatement ps = conn.prepareStatement(delete);
                    ps.setString(1, SharedData.getInstance().getIdNT());
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case JOptionPane.CANCEL_OPTION:
                System.exit(0);
                break; 
        }
    }//GEN-LAST:event_buttonXoaNhaTroActionPerformed

    private void buttonThemNhaTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemNhaTroActionPerformed
        ThemMotNhaTro pnThemNhaTro = new ThemMotNhaTro();
        // Tạo một JDialog tùy chỉnh
        JDialog dialog = new JDialog();
        dialog.setTitle("Thêm nhà trọ"); // Thiết lập tiêu đề cho dialog

        // Thiết lập layout cho dialog
        dialog.setLayout(new BorderLayout());

        // Thêm panel themMotPhong vào dialog
        dialog.add(pnThemNhaTro, BorderLayout.CENTER);

        // Cài đặt các thuộc tính khác của dialog
        dialog.setModal(true);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_buttonThemNhaTroActionPerformed

    private void trangThaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trangThaiPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_trangThaiPhongActionPerformed

    private void listNhaTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listNhaTroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listNhaTroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.swing.Button buttonSuaNhaTro;
    private com.mycompany.swing.Button buttonThemNhaTro;
    private com.mycompany.swing.Button buttonThemPhong;
    private com.mycompany.swing.Button buttonThemPhongNhanh;
    private com.mycompany.swing.Button buttonXoaNhaTro;
    private javax.swing.JLabel jLabel1;
    private com.mycompany.swing.Combobox listNhaTro;
    private com.mycompany.swing.PanelBorder listPhong;
    private com.mycompany.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spListPhong;
    private javax.swing.JComboBox<String> trangThaiPhong;
    // End of variables declaration//GEN-END:variables
}
