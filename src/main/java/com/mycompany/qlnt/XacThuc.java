package com.mycompany.qlnt;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class XacThuc {
    private static XacThuc instance;


    private XacThuc() {}

    public static XacThuc getInstance() {
        if (instance == null) {
            instance = new XacThuc();
        }
        return instance;
    }
    
    //Tô viền textField khi sai
    public static void setTextFieldBorderColor(JTextField textField, Color color) {
        Border border = BorderFactory.createLineBorder(color, 2); // Tạo border với màu và độ dày
        textField.setBorder(border);
    }
    
    // Hàm kiểm tra định dạng của diện tích (ví dụ: "3x4 m2")
    public static boolean isValidDienTich(String input) {
        // Biểu thức chính quy: một số, sau đó là ký tự 'x', tiếp theo là một số, sau đó là ký tự khoảng trắng và "m2"
        String regex = "\\d+x\\d+\\s+m2";
        return input.matches(regex);
    }

    // Hàm kiểm tra định dạng của số (ví dụ: "123")
    public static boolean isValidSo(String input) {
        // Biểu thức chính quy: một hoặc nhiều chữ số
        String regex = "\\d+";
        return input.matches(regex);
    }
    
    // Hàm kiểm tra định dạng email
    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }

    // Hàm kiểm tra định dạng số điện thoại (ví dụ: "+84123456789" hoặc "0123456789")
    public static boolean isValidSDT(String sdt) {
        String regex = "^(\\+84|0)\\d{9,10}$";
        return sdt.matches(regex);
    }
    
    // Hàm kiểm tra định dạng của maDien (ví dụ: "E001")
    public static boolean isValidMaDien(String input) {
        String regex = "^[E]\\d{3}$";
        return input.matches(regex);
    }
    
    // Hàm kiểm tra định dạng của maDien (ví dụ: "E001")
    public static boolean isValidMaNuoc(String input) {
        String regex = "^[W]\\d{3}$";
        return input.matches(regex);
    }
    
    public static boolean isIdKTExists(Connection conn, String idKT, String idPhong) {
        boolean canInsert = false;
        try {
            // Kiểm tra xem idKhachThue có tồn tại trong bảng KhachThue hay không
            String exist = "SELECT COUNT(*) FROM dbo.KhachThue WHERE idKhachThue = ?";
            PreparedStatement psE = conn.prepareStatement(exist);
            psE.setString(1, idKT);
            ResultSet rsE = psE.executeQuery();
            if (rsE.next() && rsE.getInt(1) > 0) {
                canInsert = true;
                rsE.close();
                psE.close();
            }else{
                return canInsert;
            }
            if (canInsert) {
                // Kiểm tra xem idKhachThue có đang ở trong idPhong với trạng thái thuê là 1 hay không
                String query1 = "SELECT COUNT(*) FROM HopDong WHERE idKhachThue = ? AND idPhong = ? AND trangThaiThue = ?";
                PreparedStatement ps1 = conn.prepareStatement(query1);
                ps1.setString(1, idKT);
                ps1.setString(2, idPhong);
                ps1.setInt(3, 1);
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next() && rs1.getInt(1) > 0) {
                    canInsert = false; // idKhachThue đã có trong idPhong với trạng thái thuê là 1
                } 

                rs1.close();
                ps1.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return canInsert;
    }
    
    public static boolean isLPExists(Connection conn, String idNT) {
        boolean exists = false;
        try {
            // Kiểm tra xem idLoaiPhong có tồn tại trong bảng LoaiPhong hay không
            String exist = "SELECT COUNT(*) FROM LoaiPhong WHERE idNhaTro = ?";
            PreparedStatement psE = conn.prepareStatement(exist);
            psE.setString(1, idNT);
            ResultSet rsE = psE.executeQuery();
            if (rsE.next() && rsE.getInt(1) > 0) {
                exists = true;
            }
            rsE.close();
            psE.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }
    
    public static boolean isIdDienExists(String idDien) {
        boolean exists = false;
        try {
            Connection conn = ketNoiDB.ketNoiDB.layKetNoi();

            // Kiểm tra xem idDien có tồn tại trong bảng ElectricMeter hay không
            String exist = "SELECT COUNT(*) FROM dbo.ElectricMeter WHERE idDien = ?";
            PreparedStatement psE = conn.prepareStatement(exist);
            psE.setString(1, idDien);
            ResultSet rsE = psE.executeQuery();
            if (rsE.next() && rsE.getInt(1) == 0) {
                // idDien không tồn tại trong bảng ElectricMeter, trả về false
                rsE.close();
                psE.close();
                conn.close();
                return false;
            }

            // Kiểm tra xem idDien đã được sử dụng bởi phòng nào chưa
            String query = "SELECT COUNT(*) FROM Phong WHERE idDien = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, idDien);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                exists = true;
            }
            System.out.println(exists);
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    public static boolean isIdNuocExists(String idNuoc) {
        boolean exists = false;
        try {
            Connection conn = ketNoiDB.ketNoiDB.layKetNoi();

            // Kiểm tra xem idNuoc có tồn tại trong bảng WaterMeter hay không
            String exist = "SELECT COUNT(*) FROM dbo.WaterMeter WHERE idNuoc = ?";
            PreparedStatement psE = conn.prepareStatement(exist);
            psE.setString(1, idNuoc);
            ResultSet rsE = psE.executeQuery();
            if (rsE.next() && rsE.getInt(1) == 0) {
                // idNuoc không tồn tại trong bảng WaterMeter, trả về false
                rsE.close();
                psE.close();
                conn.close();
                return false;
            }
            rsE.close();
            psE.close();

            // Kiểm tra xem idNuoc đã được sử dụng bởi phòng nào chưa
            String query = "SELECT COUNT(*) FROM Phong WHERE idNuoc = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, idNuoc);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                exists = true;
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    
}
