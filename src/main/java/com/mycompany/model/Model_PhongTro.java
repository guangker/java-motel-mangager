package com.mycompany.model;

public class Model_PhongTro {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDienTich() {
        return dienTich;
    }

    public void setDienTich(String dienTich) {
        this.dienTich = dienTich;
    }

    public String getSoDien() {
        return soDien;
    }

    public void setSoDien(String soDien) {
        this.soDien = soDien;
    }

    public String getSoNuoc() {
        return soNuoc;
    }

    public void setSoNuoc(String soNuoc) {
        this.soNuoc = soNuoc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getIdLoaiPhong() {
        return idLoaiPhong;
    }

    public void setIdLoaiPhong(String idLoaiPhong) {
        this.idLoaiPhong = idLoaiPhong;
    }

    public String getSoTienConNo() {
        return soTienConNo;
    }

    public void setSoTienConNo(String soTienConNo) {
        this.soTienConNo = soTienConNo;
    }

    public Model_PhongTro(String id, String name, String dienTich, String gia, String soDien, String soNuoc, String ghiChu, String status, String ngayCapNhat, String idLoaiPhong, String soTienConNo) {
        this.id = id;
        this.name = name;
        this.dienTich = dienTich;
        this.gia = gia;
        this.soDien = soDien;
        this.soNuoc = soNuoc;
        this.ghiChu = ghiChu;
        this.status = status;
        this.ngayCapNhat = ngayCapNhat;
        this.idLoaiPhong = idLoaiPhong;
        this.soTienConNo = soTienConNo;
    }
    
    

    public Model_PhongTro(){
    }
    String id;
    String name;
    String dienTich;
    String gia;
    String soDien;
    String soNuoc;
    String ghiChu;
    String status;
    String ngayCapNhat;
    String idLoaiPhong;
    String soTienConNo;
}
