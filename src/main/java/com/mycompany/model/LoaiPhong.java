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
public class LoaiPhong {
    
    private String idLoaiPhong, tenLoaiPhong, ghiChu;
    private Date ngayCapNhat;
    private String idNhaTro;

    public LoaiPhong() {
    }

    public LoaiPhong(String idLoaiPhong, String tenLoaiPhong, String ghiChu, Date ngayCapNhat, String idNhaTro) {
        this.idLoaiPhong = idLoaiPhong;
        this.tenLoaiPhong = tenLoaiPhong;
        this.ghiChu = ghiChu;
        this.ngayCapNhat = ngayCapNhat;
        this.idNhaTro = idNhaTro;
    }

    public String getIdLoaiPhong() {
        return idLoaiPhong;
    }

    public void setIdLoaiPhong(String idLoaiPhong) {
        this.idLoaiPhong = idLoaiPhong;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getIdNhaTro() {
        return idNhaTro;
    }

    public void setIdNhaTro(String idNhaTro) {
        this.idNhaTro = idNhaTro;
    }
    
    
}
