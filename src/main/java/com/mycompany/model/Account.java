/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.sql.Date;

/**
 *
 * @author HUNG
 */
public class Account {
    private String idAccount, username, password, idRole;
    private Date ngayTao, ngayCapNhat;
    private String email, verifyCode;
    public Account() {
    }

    public Account(String idAccount, String username, String password, String email, String idRole, String verifyCode) {
        this.idAccount = idAccount;
        this.username = username;
        this.password = password;
        this.idRole = idRole;
        this.email = email;
        this.verifyCode = verifyCode;
    }

    public Account(String idAccount, String username, String password, String email, String idRole) {
        this.idAccount = idAccount;
        this.username = username;
        this.password = password;
        this.idRole = idRole;
        this.email = email;
    }

    
    
    public Account(String idAccount, String username, String password, String email, Date ngayTao, Date ngayCapNhat, String idRole) {
        this.idAccount = idAccount;
        this.username = username;
        this.password = password;
        this.idRole = idRole;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
        this.email = email;

    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Account(String idAccount, String username, String password, String email, Date ngayTao, Date ngayCapNhat, String idRole, String verifyCode) {
        this.idAccount = idAccount;
        this.username = username;
        this.password = password;
        this.idRole = idRole;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
        this.email = email;
        this.verifyCode = verifyCode;
    }
    
    
    
}
