package com.mycompany.qlnt;

import com.mycompany.model.Account;
import com.mycompany.event.EventMenuSelected;
import com.mycompany.form.DichVuForm;
import com.mycompany.formKT.Form_1;
import com.mycompany.formKT.Form_2;
import com.mycompany.formKT.Form_3;
import com.mycompany.formKT.Form_4;
import javax.swing.JComponent;

public class UserPage extends javax.swing.JFrame {

    private Form_1 form1;
    private Form_2 form2;
    private Form_3 form3;
    private Form_4 form4;
    
    private void setForm(JComponent com){
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
    
    public UserPage(Account user) {
        initComponents();
        form1 = new Form_1();
        form2 = new Form_2();
        form3 = new Form_3();
        form4 = new Form_4();
        menuKhachThue.initMoving(UserPage.this);
        menuKhachThue.addEventMenuSelected(new EventMenuSelected(){
            @Override
            public void selected(int index) {
//                System.out.println("selected index: " + index);
                if(index == 0){
                    setForm(form1);
                }else if(index==1){
                    setForm(form2);
                }else if(index == 2){
                    setForm(form3);
                }else if(index == 8){
                    setForm(form4);
                }
            }
        }); 
        setForm(new Form_1()); //Chạy chương trình hiển thị luôn tổng quan
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuKhachThue = new com.mycompany.component.MenuKhachThue();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(menuKhachThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuKhachThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

 
//    public static void main(String args[]) {
//   
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new UserPage().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private com.mycompany.component.MenuKhachThue menuKhachThue;
    // End of variables declaration//GEN-END:variables
}
