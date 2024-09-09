
package ketNoiDB;

import com.mycompany.model.Account;
import com.mycompany.model.KhachThue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HUNG
 */
public class KhachThueDAO {
    
    
    public List<KhachThue> getList(Account user) 
    {
        try {
            Connection cons = ketNoiDB.layKetNoi();
            String query = "SELECT k.* FROM dbo.KhachThue k \n" +
                            "JOIN dbo.HopDong h ON k.IDKhachThue = h.IDKhachThue\n" +
                            "JOIN dbo.Phong p ON p.idPhong = h.idPhong\n" +
                            "JOIN dbo.LoaiPhong l ON l.idLoaiPhong = p.idLoaiPhong\n" +
                            "JOIN dbo.NhaTro nt ON nt.idNhaTro = l.idNhaTro\n" +
                            "JOIN dbo.NVQuanLy nv ON nv.idQL = nt.idQL\n" +
                            "JOIN dbo.Account a ON a.idAccount = nv.idAccount\n" +
                            "WHERE a.idAccount = ?";
            List<KhachThue> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareStatement(query);
            ps.setString(1, user.getIdAccount());
            ResultSet result = ps.executeQuery();
            while(result.next())
            {
                KhachThue khach = new KhachThue();
                khach.setIdKhachThue(result.getString("IDKhachThue"));
                khach.setFullname(result.getString("fullname"));
                khach.setEmail(result.getString("email"));
                khach.setNumberPhone(result.getString("numberPhone"));
                khach.setCmnd(result.getString("cmnd"));
                khach.setNgaySinh(result.getDate("ngaysinh"));
                khach.setNgayCapNhat(result.getDate("ngayCapNhat"));
                khach.setIdAccount(result.getString("idAccount"));
                list.add(khach);
            }
            ps.close();
            result.close();
            cons.close();  
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
        
    public static void main(String[] args) {
        KhachThueDAO ktdao = new KhachThueDAO();
        Account a = new Account();
        a.setIdAccount("AC005");
        System.out.println(ktdao.getList(a));
    }
    
}
