 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ketNoiDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author quang
 */
public class ketNoiDB {

    public static Connection layKetNoi() { 
        Connection ketNoi = null;
        String uRL = "jdbc:sqlserver://localhost:1433;databaseName=motel1;encrypt=true;trustServerCertificate=true;";
        String userName = "sa";
        String password = "khoapro";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //com.microsoft.sqlserver.jdbc.SQLServerDriver
            ketNoi = DriverManager.getConnection(uRL, userName, password);
            //System.out.println("Ket noi CSDL thanh cong");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Khong ket noi duoc voi CSDL");
        }
        return ketNoi;
    }
    
//    public static void main(String[] args) {
//        Connection kn = layKetNoi();
//       if(kn==null){
//           System.out.println("NO");
//        }else
//            System.out.println("YES");
//    }
}


