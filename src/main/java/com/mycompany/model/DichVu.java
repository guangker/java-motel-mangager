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
public class DichVu {
    private String idDichVu = "", tenDichVu;
    private float gia;
    private String loaiDichVu;
    private Date ngayCapNhat;
    private String idNhaTro;

    public DichVu() {
    }

    public DichVu(String idDichVu, String tenDichVu, float gia, String loaiDichVu, Date ngayCapNhat, String idNhaTro) {
        this.idDichVu = idDichVu;
        this.tenDichVu = tenDichVu;
        this.gia = gia;
        this.loaiDichVu = loaiDichVu;
        this.ngayCapNhat = ngayCapNhat;
        this.idNhaTro = idNhaTro;
    }

    public String getIdDichVu() {
        return idDichVu;
    }

    public void setIdDichVu(String idDichVu) {
        this.idDichVu = idDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public String getLoaiDichVu() {
        return loaiDichVu;
    }

    public void setLoaiDichVu(String loaiDichVu) {
        this.loaiDichVu = loaiDichVu;
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

    @Override
    public String toString() {
        return "DichVu{" + "idDichVu=" + idDichVu + ", tenDichVu=" + tenDichVu + ", gia=" + gia + ", loaiDichVu=" + loaiDichVu + ", ngayCapNhat=" + ngayCapNhat + ", idNhaTro=" + idNhaTro + '}';
    }
    
    
}
