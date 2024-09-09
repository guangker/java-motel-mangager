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
public class ThanhToan {
    private String idThanhToan;
    private Date ngayThanhToan;
    private String phuongThucThanhToan, ghiChu, idKhachThue, idHoaDon;

    public ThanhToan() {
    }

    public ThanhToan(String idThanhToan, Date ngayThanhToan, String phuongThucThanhToan, String ghiChu, String idKhachThue, String idHoaDon) {
        this.idThanhToan = idThanhToan;
        this.ngayThanhToan = ngayThanhToan;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.ghiChu = ghiChu;
        this.idKhachThue = idKhachThue;
        this.idHoaDon = idHoaDon;
    }

    public String getIdThanhToan() {
        return idThanhToan;
    }

    public void setIdThanhToan(String idThanhToan) {
        this.idThanhToan = idThanhToan;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getIdKhachThue() {
        return idKhachThue;
    }

    public void setIdKhachThue(String idKhachThue) {
        this.idKhachThue = idKhachThue;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }
    
    
    
}
