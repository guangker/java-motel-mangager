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
public class Phong {
    private String idPhong, tenPhong, dienTich;
    private int soDien, soNuoc;
    private String ghiChu;
    private boolean trangThai;
    private Date ngayCapNhat;
    private String idLoaiPhong;

    public Phong() {
    }

    public Phong(String idPhong, String tenPhong, String dienTich, int soDien, int soNuoc, String ghiChu, boolean trangThai, Date ngayCapNhat, String idLoaiPhong) {
        this.idPhong = idPhong;
        this.tenPhong = tenPhong;
        this.dienTich = dienTich;
        this.soDien = soDien;
        this.soNuoc = soNuoc;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
        this.ngayCapNhat = ngayCapNhat;
        this.idLoaiPhong = idLoaiPhong;
    }

    public String getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getDienTich() {
        return dienTich;
    }

    public void setDienTich(String dienTich) {
        this.dienTich = dienTich;
    }

    public int getSoDien() {
        return soDien;
    }

    public void setSoDien(int soDien) {
        this.soDien = soDien;
    }

    public int getSoNuoc() {
        return soNuoc;
    }

    public void setSoNuoc(int soNuoc) {
        this.soNuoc = soNuoc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getIdLoaiPhong() {
        return idLoaiPhong;
    }

    public void setIdLoaiPhong(String idLoaiPhong) {
        this.idLoaiPhong = idLoaiPhong;
    }
    
    
    
}
