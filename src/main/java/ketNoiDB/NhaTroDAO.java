package ketNoiDB;

import com.mycompany.model.Account;
import com.mycompany.model.NhaTro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ketNoiDB.ketNoiDB;


public class NhaTroDAO {
    
    public List<NhaTro> getList(Account user)
    {
        try {
            List<NhaTro> list = new ArrayList<>();
            Connection conn = ketNoiDB.layKetNoi();
            String query = "SELECT nt.idNhaTro, nt.tenNhaTro, nt.diaChi, nt.soPhong,nt.soPhongTrong, nt.ngayCapNhat,nt.idQL FROM NhaTro nt \n" +
                            " JOIN NVQuanLy nv ON nv.idQL = nt.idQL \n" +
                            " JOIN Account a ON a.idAccount = nv.idAccount \n" +
                            " WHERE nv.idAccount = ?";
            PreparedStatement p = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE);
            p.setString(1, user.getIdAccount());
            ResultSet r = p.executeQuery();
            
            r.first();
            while(r.next())
            {
                NhaTro n = new NhaTro();
                n.setIdNhaTro(r.getString("idNhaTro"));
                n.setTenNhaTro(r.getString("tenNhaTro"));
                n.setDiaChi(r.getString("diaChi"));
                n.setSoPhong(r.getInt("soPhong"));
                n.setSoPhongTrong(r.getInt("soPhongTrong"));
                n.setNgayCapNhat(r.getDate("ngayCapNhat"));
                n.setIdQL(r.getString("idQL"));
                list.add(n);
            }
            p.close();
            r.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<String> getNameList(Account user)
    {
        try {
            List<String> list = new ArrayList<>();
            Connection conn = ketNoiDB.layKetNoi();
            String query = "SELECT nt.idNhaTro, nt.tenNhaTro FROM NhaTro nt "
                    + "JOIN NVQuanLy nv ON nv.idQL = nt.idQL "
                    + "JOIN Account a ON a.idAccount = nv.idAccount "
                    + "WHERE nv.idAccount = ?";
            PreparedStatement p = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY) ;
            ResultSet r = p.executeQuery();
//            r.first();
            while(r.next())
            {
                String name = null;
                name = r.getString("tenNhaTro");
                list.add(name);
            }
            p.close();
            r.close();
            conn.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    public HashMap<String,String> getIdAndName(Account user)
    {
        try {
            HashMap<String,String> map = new HashMap<>();
            Connection conn = ketNoiDB.layKetNoi();
            String query = "SELECT nt.idNhaTro, nt.tenNhaTro FROM NhaTro nt "
                    + "JOIN NVQuanLy nv ON nv.idQL = nt.idQL "
                    + "JOIN Account a ON a.idAccount = nv.idAccount  "
                    + "WHERE nv.idAccount = ?";
            PreparedStatement p = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY) ;
            p.setString(1, user.getIdAccount());
            ResultSet r = p.executeQuery();
            
            p.setString(1, user.getIdAccount());
//            r.first();
            while(r.next())
            {
                String id,name;
                id = r.getString("idNhaTro");
                name = r.getString("tenNhaTro");
                map.put(name,id);
            }
            p.close();
            r.close();
            conn.close();
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }
            
}
