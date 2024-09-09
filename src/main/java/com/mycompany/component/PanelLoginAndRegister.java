
package com.mycompany.component;

import com.mycompany.model.Account;
import com.mycompany.model.ModelLogin;
import com.mycompany.swing.ButtonLogin;
import com.mycompany.swing.MyPasswordField;
import com.mycompany.swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    private Account user;
    private ModelLogin dataLogin;
    

    public ModelLogin getDataLogin() {
        return dataLogin;
    }
    
    public Account getUser() {
        return user;
    }
    
    public PanelLoginAndRegister(ActionListener eventRegister, ActionListener eventLogin) {
        initComponents();
        initRegister(eventRegister);
        initLogin(eventLogin);
//        login.setVisible(false);
//        register.setVisible(true);

    }

    private void initRegister(ActionListener eventRegister)
    {
        register.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]10[]10[]25[]push"));
        JLabel label = new JLabel("Tạo tài khoản");
        label.setFont(new  Font("sansserif", 1 ,30));
        label.setForeground(new Color(32, 73, 157));
        register.add(label);
        
        
        MyTextField txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\mycompany\\icon\\user.png"));
        txtUser.setHint("Username");
        register.add(txtUser, "w 60%");
        
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\mycompany\\icon\\mail.png"));
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 60%");
        
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\mycompany\\icon\\pass.png"));
        txtPass.setHint("Password");
        register.add(txtPass, "w 60%");
        
        MyPasswordField txtConfirmPass = new MyPasswordField();
        txtConfirmPass.setPrefixIcon(new ImageIcon(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\mycompany\\icon\\pass.png"));
        txtConfirmPass.setHint("Confirm Password");
        register.add(txtConfirmPass, "w 60%");
        
        String roles[] = {"Tạo tài khoản với quyền quản trị viên","User"};
        JRadioButton btnAdmin = new JRadioButton(roles[0]);
        register.add(btnAdmin,"w 60%");
        
        ButtonLogin cmd = new ButtonLogin();
        cmd.setBackground(new Color(32, 73, 157));
        cmd.setForeground(new Color(250,250,250));
        cmd.addActionListener(eventRegister);
        cmd.setText("Đăng ký");
        register.add(cmd, "w 40%,h 40");
        
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username =  txtUser.getText().trim();
                String email = txtEmail.getText().trim();
                String password =  String.valueOf(txtPass.getPassword());
                 
                
                String idRole = "";
                    if(btnAdmin.isSelected())
                    {
                        idRole = "R001";
                        System.out.println(idRole);
                    }else 
                    {
                        idRole = "R002";
                        System.out.println(idRole);
                    }
                user = new Account("ID",username, password,email,idRole);
            }
        });
    }
    
    private void initLogin(ActionListener evenLogin)
    {
        login.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]25[]push"));

        JLabel label = new JLabel("Đăng nhập");
        label.setFont(new  Font("sansserif", 1 ,30));
        label.setForeground(new Color(32, 73, 157));
        login.add(label);
        
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\mycompany\\icon\\user.png"));
        txtEmail.setHint("Tên đăng nhập");
        login.add(txtEmail, "w 60%");
        
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\mycompany\\icon\\pass.png"));
        txtPass.setHint("Mật khẩu");
        login.add(txtPass, "w 60%");
        
        
        JButton cmdForget = new JButton("Quên mật khẩu ?");
        cmdForget.setForeground(new Color(100,100,100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        
        ButtonLogin cmd = new ButtonLogin();
        cmd.setBackground(new Color(32, 73, 157));
        cmd.setForeground(new Color(250,250,250));
        cmd.addActionListener(evenLogin);
        cmd.setText("Đăng nhập");
        login.add(cmd, "w 40%,h 40");
        
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText().trim();
                String password = String.valueOf(txtPass.getPassword());
                dataLogin = new ModelLogin(email,password);
            }
        });
    }
    
    public void showRegister(boolean show)
    {
        if(show)
        {
            login.setVisible(true);
            register.setVisible(false);
        }else
        {
            login.setVisible(false);
            register.setVisible(true);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents



    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
