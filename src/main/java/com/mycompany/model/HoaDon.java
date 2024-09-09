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
public class HoaDon {
    private String idHoaDon, tenHoaDon;
    private float soTien, giamGia;
    private Date DateFrom, DateTo, ngayTaoHoaDon;
    private boolean trangThaiThanhToan;

    public HoaDon() {
    }

    public HoaDon(String idHoaDon, String tenHoaDon, float soTien, float giamGia, Date DateFrom, Date DateTo, Date ngayTaoHoaDon, boolean trangThaiThanhToan) {
        this.idHoaDon = idHoaDon;
        this.tenHoaDon = tenHoaDon;
        this.soTien = soTien;
        this.giamGia = giamGia;
        this.DateFrom = DateFrom;
        this.DateTo = DateTo;
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getTenHoaDon() {
        return tenHoaDon;
    }

    public void setTenHoaDon(String tenHoaDon) {
        this.tenHoaDon = tenHoaDon;
    }

    public float getSoTien() {
        return soTien;
    }

    public void setSoTien(float soTien) {
        this.soTien = soTien;
    }

    public float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }

    public Date getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(Date DateFrom) {
        this.DateFrom = DateFrom;
    }

    public Date getDateTo() {
        return DateTo;
    }

    public void setDateTo(Date DateTo) {
        this.DateTo = DateTo;
    }

    public Date getNgayTaoHoaDon() {
        return ngayTaoHoaDon;
    }

    public void setNgayTaoHoaDon(Date ngayTaoHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
    }

    public boolean isTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(boolean trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }
    
    
}
