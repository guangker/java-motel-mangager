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
public class BaoTri {
    private String idBaoTri, mota;
    private Date ngayTao;
    private boolean trangThai;
    private Date ngayCapNhat;
    private float gia;
    private String idPhong;

    public BaoTri() {
    }

    public BaoTri(String idBaoTri, String mota, Date ngayTao, boolean trangThai, Date ngayCapNhat, float gia, String idPhong) {
        this.idBaoTri = idBaoTri;
        this.mota = mota;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.ngayCapNhat = ngayCapNhat;
        this.gia = gia;
        this.idPhong = idPhong;
    }

    public String getIdBaoTri() {
        return idBaoTri;
    }

    public void setIdBaoTri(String idBaoTri) {
        this.idBaoTri = idBaoTri;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
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

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public String getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }
    
    
}
