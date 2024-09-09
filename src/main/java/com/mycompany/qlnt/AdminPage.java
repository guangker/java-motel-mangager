package com.mycompany.qlnt;

import com.mycompany.event.EventMenuSelected;
import com.mycompany.form.TongQuan;
import com.mycompany.form.ThongTinCaNhan;
import com.mycompany.form.DanhSachPhong;
import com.mycompany.form.KhachThueForm;
import com.mycompany.form.DichVuForm;
import com.mycompany.form.DienNuocForm;
import com.mycompany.form.Form_6;
import com.mycompany.form.Form_7;
import com.mycompany.form.Form_8;
import com.mycompany.form.Form_9;
import com.mycompany.model.Account;
import javax.swing.JComponent;

public class AdminPage extends javax.swing.JFrame {

    private TongQuan form1;
    private DanhSachPhong form2;
    private KhachThueForm form3;
    private DichVuForm form4;
    private DienNuocForm form5;
    private Form_6 form6;
    private Form_7 form7;
    private Form_8 form8;
    private Form_9 form9;
    private ThongTinCaNhan form10;
    
    public AdminPage(Account user) {
        SharedData.getInstance(); // Khởi tạo SharedData
        initComponents();
        pack();
        form1 = new TongQuan(user);
        form2 = new DanhSachPhong(user);
        form3 = new KhachThueForm(user);
        form4 = new DichVuForm(user);
        form5 = new DienNuocForm();
        form6 = new Form_6();
        form7 = new Form_7();
        form8 = new Form_8();
        form9 = new Form_9();
        form10 = new ThongTinCaNhan();
        menu.initMoving(AdminPage.this);
        menu.addEventMenuSelected(new EventMenuSelected(){
            @Override
            public void selected(int index) {
                System.out.println("selected index: " + index);
                if(index == 0){
                    form1.reloadForm();
                    setForm(form1);
                }else if(index==1){
                    form2.reloadForm();
                    setForm(form2);
                }else if(index == 2){
                    setForm(form3);
                }else if(index == 3){
                    setForm(form4);
                }else if(index ==4){
                    setForm(form5);
                }else if(index ==5){
                    setForm(form6);
                }else if(index ==6){
                    setForm(form7);
                }else if(index ==7){
                    setForm(form8);
                }else if(index ==8){
                    setForm(form9);
                }else if(index ==9){
                    setForm(form10);
                }
            }
        }); 
        setForm(new TongQuan(user)); //Chạy chương trình hiển thị luôn tổng quan
    }

    private void setForm(JComponent com){
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new com.mycompany.component.MenuQuanLy();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AdminPage().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private com.mycompany.component.MenuQuanLy menu;
    // End of variables declaration//GEN-END:variables
}
