package ketNoiDB;

import com.mycompany.model.Account;
import com.mycompany.model.DichVu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DichVuDAO {
    
//    Account user
    public List<DichVu> getList(Account user)
    {
        try {
            List<DichVu> list = new ArrayList<>();
        
            Connection conn = ketNoiDB.layKetNoi();
            String query = "SELECT dv.idDichVu, dv.tenDichVu, dv.gia, dv.ghiChu, dv.ngayCapNhat,dv.idNhaTro FROM dbo.DichVu dv"
                    + " JOIN dbo.NhaTro nt ON dv.idNhaTro = nt.idNhaTro"
                    + " JOIN dbo.NVQuanLy ql ON ql.idQL = nt.idQL"
                    + " JOIN dbo.Account a ON ql.idAccount = a.idAccount"
                    + " WHERE a.idAccount = ?";
            PreparedStatement p = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE);
            p.setString(1, user.getIdAccount());

            ResultSet r = p.executeQuery();
            while(r.next())
            {
                DichVu dichvu = new DichVu();
                dichvu.setIdDichVu(r.getString("idDichVu"));
                dichvu.setTenDichVu(r.getString("tenDichVu"));
                dichvu.setGia(r.getFloat("gia"));
                dichvu.setLoaiDichVu(r.getString("ghiChu"));
                dichvu.setNgayCapNhat(r.getDate("ngayCapNhat"));
                dichvu.setIdNhaTro(r.getString("idNhaTro"));

                list.add(dichvu);

            }
            p.close();
            r.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public List<DichVu> getListForIDNhaTro (String idNhaTro)
    {
        
        try {
            List<DichVu> list = new ArrayList<>();
        
            Connection conn = ketNoiDB.layKetNoi();
            String query = "SELECT * FROM DichVu WHERE idNhaTro = ?";
            PreparedStatement p = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE);
            p.setString(1, idNhaTro);

            ResultSet r = p.executeQuery();
            while(r.next())
            {
                DichVu dichvu = new DichVu();
                dichvu.setIdDichVu(r.getString("idDichVu"));
                dichvu.setTenDichVu(r.getString("tenDichVu"));
                dichvu.setGia(r.getFloat("gia"));
                dichvu.setLoaiDichVu(r.getString("loaiDichVu"));
                dichvu.setNgayCapNhat(r.getDate("ngayCapNhat"));
                dichvu.setIdNhaTro(r.getString("idNhaTro"));

                list.add(dichvu);

            }
            p.close();
            r.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public int createOrUpdate(DichVu dv) 
    {
        try {
            Connection conn = ketNoiDB.layKetNoi();
            String query = "DECLARE @ID varchar(15) = ?;\n" +
                            "DECLARE @Ten NVARCHAR(100) = ?;\n" +
                            "DECLARE @Gia FLOAT = ?;\n" +
                            "DECLARE @loaiDichVu NVARCHAR(255) = ?;\n" +
                            "DECLARE @IDNT varchar(15) = ?;\n" +
                            "\n" +
                            "BEGIN TRAN\n" +
                            "\n" +
                            "IF EXISTS (SELECT 1 FROM dbo.DichVu WITH (UPDLOCK, SERIALIZABLE) WHERE idDichVu = @ID)\n" +
                            "BEGIN\n" +
                            "    -- Nếu bản ghi đã tồn tại, thực hiện cập nhật\n" +
                            "    UPDATE DichVu\n" +
                            "    SET tenDichVu = @Ten,\n" +
                            "        gia = @Gia,\n" +
                            "        loaiDichVu = @loaiDichVu,\n" +
                            "		idNhaTro = @IDNT\n" +
                            "    WHERE idDichVu = @ID;\n" +
                            "END\n" +
                            "ELSE\n" +
                            "BEGIN\n" +
                            "    -- Nếu bản ghi chưa tồn tại, thực hiện chèn\n" +
                            "    INSERT INTO DichVu (idDichVu, tenDichVu, gia, loaiDichVu, idNhaTro)\n" +
                            "    VALUES (@ID, @Ten, @Gia, @loaiDichVu,@IDNT);\n" +
                            "END\n" +
                            "\n" +
                            "COMMIT TRAN";
            PreparedStatement p = conn.prepareStatement(query);

            p.setString(1, dv.getIdDichVu());
            p.setString(2, dv.getTenDichVu());
            p.setFloat(3, dv.getGia());
            p.setString(4, dv.getLoaiDichVu());
//            p.setString(5, "");
            p.setString(5, dv.getIdNhaTro());
            p.execute();
//            ResultSet r = p.getGeneratedKeys();
//            String GENERATED_KEY = "";
//            if(r.next())
//            {
//                GENERATED_KEY = r.getString(1);
//            }
            p.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    public int deleteForID(String id)
    {
        try {
            Connection conn = ketNoiDB.layKetNoi();
            String query = "DELETE dbo.DichVu WHERE idDichVu = ?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setString(1, id);
            p.execute();
            p.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // idNhatro is id of house where to be delete
    public int deleteAllServiceElectric(String idNhaTro)
    {
        try {
             Connection conn = ketNoiDB.layKetNoi();
             String query = "DELETE d FROM dbo.DichVu d JOIN dbo.NhaTro nt ON d.idNhaTro = nt.idNhaTro WHERE  d.loaiDichVu = 'electric'  AND d.idNhaTro = ?";
             PreparedStatement p = conn.prepareStatement(query);
             p.setString(1, idNhaTro);
             p.execute();
             p.close();
             conn.close();
             return 1;
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
        // idNhatro is id of house where to be delete
    public int deleteAllServiceWater(String idNhaTro)
    {
        try {
             Connection conn = ketNoiDB.layKetNoi();
             String query = "DELETE d FROM dbo.DichVu d JOIN dbo.NhaTro nt ON d.idNhaTro = nt.idNhaTro WHERE  d.loaiDichVu = 'water'  AND d.idNhaTro = ?";
             PreparedStatement p = conn.prepareStatement(query);
             p.setString(1, idNhaTro);
             p.execute();
             p.close();
             conn.close();
             return 1;
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
}
