
package ketNoiDB;

import com.mycompany.model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HUNG
 */
public class AccountDAO {
    
    public Account getAccount(String username) throws SQLException {
        Account user = new Account();
        Connection conn = ketNoiDB.layKetNoi();
        String query = "SELECT * FROM Account WHERE username = ?";
        PreparedStatement p = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        p.setString(1, username);
        ResultSet result = p.executeQuery();

        // Kiểm tra xem ResultSet có chứa bản ghi không trước khi di chuyển con trỏ
        if (result.first() ) {
            user.setIdAccount(result.getString("idAccount"));
            user.setUsername(result.getString("username"));
            user.setPassword(result.getString("password"));
            user.setEmail(result.getString("email"));
            user.setNgayTao(result.getDate("createDay"));
            user.setNgayCapNhat(result.getDate("updateDate"));
            user.setIdRole(result.getString("idRole"));
            user.setVerifyCode(result.getString("verifyCode"));
        }

        return user;
    }
    
    public Account getLastAccount(String username) throws SQLException{
        Account user = new Account();
        Connection conn = ketNoiDB.layKetNoi();
        String query = "SELECT * FROM Account WHERE username = ?";
        PreparedStatement p = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        p.setString(1, username);
        ResultSet result = p.executeQuery();

        // Kiểm tra xem ResultSet có chứa bản ghi không trước khi di chuyển con trỏ
        
        if (result.last()  ) {
            user.setIdAccount(result.getString("idAccount"));
            user.setUsername(result.getString("username"));
            user.setPassword(result.getString("password"));
            user.setEmail(result.getString("email"));
            user.setNgayTao(result.getDate("createDay"));
            user.setNgayCapNhat(result.getDate("updateDate"));
            user.setIdRole(result.getString("idRole"));
            user.setVerifyCode(result.getString("verifyCode"));
        }

        return user;
    }
}
