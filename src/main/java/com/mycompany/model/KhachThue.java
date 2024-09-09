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
public class KhachThue {
    private String idKhachThue;
    private String fullname, numberPhone, email, cmnd;
    private byte[] image;
    private Date ngaySinh, ngayCapNhat;
    private String idAccount;

    public KhachThue() {
    }

    public KhachThue(String idKhachThue, String fullname, String numberPhone, String email, String cmnd, byte[] image, Date ngaySinh, Date ngayCapNhat, String idAccount) {
        this.idKhachThue = idKhachThue;
        this.fullname = fullname;
        this.numberPhone = numberPhone;
        this.email = email;
        this.cmnd = cmnd;
        this.image = image;
        this.ngaySinh = ngaySinh;
        this.ngayCapNhat = ngayCapNhat;
        this.idAccount = idAccount;
    }

    public String getIdKhachThue() {
        return idKhachThue;
    }

    public void setIdKhachThue(String idKhachThue) {
        this.idKhachThue = idKhachThue;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    @Override
    public String toString() {
        return "KhachThue{" + "idKhachThue=" + idKhachThue + ", fullname=" + fullname + ", numberPhone=" + numberPhone + ", email=" + email + ", cmnd=" + cmnd + ", image=" + image + ", ngaySinh=" + ngaySinh + ", ngayCapNhat=" + ngayCapNhat + ", idAccount=" + idAccount + '}';
    }

    
    
    
}
