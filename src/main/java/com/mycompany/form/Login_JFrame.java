    
package com.mycompany.form;

import com.mycompany.component.MessageNotifLogin;
import com.mycompany.component.PanelCover;
import com.mycompany.component.PanelLoading;
import com.mycompany.component.PanelLoginAndRegister;
import com.mycompany.component.PanelVerifyCode;
import com.mycompany.model.Account;
import com.mycompany.model.ModelLogin;
import com.mycompany.model.ModelMessage;
import com.mycompany.qlnt.AdminPage;
import com.mycompany.qlnt.UserPage;
import com.mycompany.service.AccountService;
import com.mycompany.service.MailService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.mail.Service;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Login_JFrame extends javax.swing.JFrame {

    private MigLayout layout;
    private PanelCover cover;
    private PanelLoginAndRegister loginAndRegister;
    private PanelLoading loading;
    private PanelVerifyCode verifyCode;
    private boolean isLogin;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;
    private AccountService service;
//    private final double coverSize = 40;

    private final DecimalFormat  df = new DecimalFormat("##0.###");
    
    public Login_JFrame() {
        initComponents();
        init();
    }

    private void init()
    {
        service = new AccountService();
        layout = new MigLayout("fill,insets 0");
        cover = new PanelCover();
        loading = new PanelLoading();
        verifyCode = new PanelVerifyCode(); 
        ActionListener eventRegister = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        };
        
        ActionListener evenLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        };
        
        loginAndRegister = new PanelLoginAndRegister(eventRegister, evenLogin); 
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;

                double size = coverSize;
                if(fraction <= 0.5f)
                {
                    size += fraction * size;
                }else
                {
                    size += addSize -fraction * addSize;
                }
                
                if(isLogin)
                {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if(fraction >=0.5f)
                    {
                        cover.registerRight(fractionCover *100);

                    }else
                    {
                        cover.loginRight((1f - fractionCover)*100);

                    }
                }else
                {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if(fraction <=0.5f)
                    {
                        cover.registerLeft(fraction * 100);
                    }else
                    {
                        cover.loginLeft((1f - fraction)*100);
                    }
                }
                if(fraction >=0.5f)
                {
                    loginAndRegister.showRegister(isLogin);
                }
                
                fractionCover= Double.valueOf(df.format(fractionCover).replace(',', '.'));
                fractionLogin= Double.valueOf(df.format(fractionLogin).replace(',', '.'));

                layout.setComponentConstraints(cover, "width "+ size + "%, pos " + fractionCover+ "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width "+ loginSize + "%, pos " + fractionLogin+ "al 0 n 100%");

                bg.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }
        };
        
        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);  // set smooth animation

        bg.setLayout(layout);
        bg.setLayer(loading, JLayeredPane.POPUP_LAYER);
        bg.setLayer(verifyCode, JLayeredPane.POPUP_LAYER);
        bg.add(loading, "pos 0 0 100% 100% ");
        bg.add(verifyCode, "pos 0 0 100% 100% ");
        bg.add(cover,"width " + coverSize + "%, pos 0al 0 n 100%");
        bg.add(loginAndRegister,"width " + loginSize + "%, pos 1al 0 n 100%");

        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 if(!animator.isRunning())
                {
                    animator.start();
                }
            }
        });
            
        verifyCode.addEventButtonOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    Account user =  loginAndRegister.getUser();
                    if(service.verifyCodeWithAccount(user.getIdAccount(), verifyCode.getInputCode()))
                    {
                        service.doneVerify(user.getIdAccount());
                        showMessage(MessageNotifLogin.MessageType.SUCCESS, "Đăng ký thành công!");
                        verifyCode.setVisible(false);
                        service.insertNVQL(user);
                        
                    }else {
                        showMessage(MessageNotifLogin.MessageType.ERROR, "Verify code incorrect");
                    }
                } catch (SQLException exception) {
                    showMessage(MessageNotifLogin.MessageType.ERROR, "ERROR register");
                }
            }
        });
        
        
        verifyCode.addEventButtonCancel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyCode.setVisible(false);
            }
        });
    }
    
    private void register()
    {
//        loading.setVisible(true);
//        verifyCode.setVisible(true);
        Account user = loginAndRegister.getUser();
        try {
            if(service.isDuplicateUser(user.getUsername()))
            {
                showMessage(MessageNotifLogin.MessageType.ERROR, "Tài khoản đã tồn tại !");
            }else if(service.isDuplicateEmail(user.getEmail()))
            {            
                showMessage(MessageNotifLogin.MessageType.ERROR, "Email đã tồn tại !");
            }else
            {
                service.insertAccount(user);          
                sendMain(user);
            }
        } catch (SQLException e) {
            showMessage(MessageNotifLogin.MessageType.ERROR, "Error Register");
            e.printStackTrace();
            System.out.println(user.getUsername());
        }
    }

    
    private void login()
    {
        
        ModelLogin data = loginAndRegister.getDataLogin();
//        System.out.println("username: " + data.getUsername() + ", password: " + data.getPassword());
        try {
            Account user = service.login(data);
            if(user != null)
            {
                this.dispose();
                switch (user.getIdRole()) {
                    case "R001":
                        new AdminPage(user).setVisible(true);
                        break;
                    case "R002":
                        new UserPage(user).setVisible(true);
                        break;
                }
            }else{
                showMessage(MessageNotifLogin.MessageType.ERROR, "Tài khoản hoặc mật khẩu không chính xác!");
            }
                
        } catch (SQLException e) {
            showMessage(MessageNotifLogin.MessageType.ERROR, "Error Login");
        }
    }
    
    
    private void sendMain(Account user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                loading.setVisible(true);
                ModelMessage mess = new MailService().sendMain(user.getEmail(), user.getVerifyCode());
                if(mess.isSuccess())
                {
                    loading.setVisible(false);
                    verifyCode.setVisible(true);
                }else {
                    loading.setVisible(false);
                    verifyCode.setVisible(true);

                    showMessage(MessageNotifLogin.MessageType.ERROR, "Send Verify Code fail");

                }
            }
        }).start();
    }

    
    private void showMessage(MessageNotifLogin.MessageType messageType, String message)
    {
        MessageNotifLogin mess = new MessageNotifLogin();
        mess.showMessage(messageType, message);
        TimingTarget target = new TimingTarget() {
            @Override
            public void timingEvent(float fraction) {
                float f;
                if(mess.isShow())
                {
                    f= 40 * (1f - fraction);
                }else
                {
                    f = 40*fraction;
                }
                layout.setComponentConstraints(mess, "pos 0.5al " + (int) (f - 30));

                bg.repaint();
                bg.revalidate();
            }

            @Override
            public void begin() {
                if(!mess.isShow())
                {
                    bg.add(mess,"pos 0.5al -30", 0); //insert to bg first index 0
                    mess.setVisible(true);
                    bg.repaint();
                }
            }

            @Override
            public void end() {
                if(mess.isShow())
                {
                    bg.remove(mess);
                    bg.repaint();
                    bg.revalidate();
                }else {
                    mess.setShow(true);
                }
            }

            @Override
            public void repeat() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
            
            
        };
        
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                    animator.start();
                }catch(InterruptedException e){
                    System.err.println(e);
                }
            }
        }).start();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login_JFrame().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
