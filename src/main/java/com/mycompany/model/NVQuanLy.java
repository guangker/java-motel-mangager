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
public class NVQuanLy {
    private String idQL, fullname, numberPhone, email, cmnd;
    private Date ngaySinh, ngayCapNhat;
    private Boolean status;
    private Date StartDay, EndDay;
    
    private String idAccount;

    public NVQuanLy() {
    }

    public NVQuanLy(String idQL, String fullname, String numberPhone, String email, String cmnd, Date ngaySinh, Date ngayCapNhat, Boolean status, Date StartDay, Date EndDay, String idAccount) {
        this.idQL = idQL;
        this.fullname = fullname;
        this.numberPhone = numberPhone;
        this.email = email;
        this.cmnd = cmnd;
        this.ngaySinh = ngaySinh;
        this.ngayCapNhat = ngayCapNhat;
        this.status = status;
        this.StartDay = StartDay;
        this.EndDay = EndDay;
        this.idAccount = idAccount;
    }

    public String getIdQL() {
        return idQL;
    }

    public void setIdQL(String idQL) {
        this.idQL = idQL;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getStartDay() {
        return StartDay;
    }

    public void setStartDay(Date StartDay) {
        this.StartDay = StartDay;
    }

    public Date getEndDay() {
        return EndDay;
    }

    public void setEndDay(Date EndDay) {
        this.EndDay = EndDay;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }
    
    
}
