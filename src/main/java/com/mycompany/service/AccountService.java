package com.mycompany.service;

import java.sql.Connection;
import com.mycompany.model.Account;
import com.mycompany.model.ModelLogin;
import com.mycompany.qlnt.SharedData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Random;
import ketNoiDB.AccountDAO;
import ketNoiDB.ketNoiDB;

public class AccountService {
    private Connection conn;
    
    private AccountDAO accountDAO = new AccountDAO();

    public AccountService() {
        conn = ketNoiDB.layKetNoi();
    }
    
    public Account login(ModelLogin login) throws SQLException
    {
        Account data = null;
        String query = "SELECT TOP 1 idAccount, username, password,email, idRole FROM dbo.Account WHERE username =? AND password =? AND status ='Verified'";
        PreparedStatement p = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        p.setString(1,login.getUsername());
        p.setString(2,login.getPassword());
        ResultSet r = p.executeQuery();
        if(r.first())
        {
            String idAccount = r.getString("idAccount");
            String username = r.getString("username");
            String password = r.getString("password");
            String email = r.getString("email");
            String idRole = r.getString("idRole");
            data = new Account(idAccount, username,password,email, idRole);
            SharedData.getInstance().setIdAccount(idAccount);
        }
        return data;    
    }    
    
    public void insertAccount(Account user)throws SQLException
    {
        String verifyCode = generateVerifyCode();

        String query1 = "INSERT INTO `Account` (idAccount, username, password, email, idRole, verifyCode) values ('ID',?,?,?,?,?)";

        String query = "INSERT INTO Account(idAccount, username, password, email, idRole, verifyCode) values(?,?,?,?,?,?)";
        
        PreparedStatement p = conn.prepareStatement(query);
        
        p.setString(1, "");
        p.setString(2, user.getUsername());
        p.setString(3, user.getPassword());
        p.setString(4, user.getEmail());
        p.setString(5, user.getIdRole());
        p.setString(6, verifyCode);
        p.execute();
        
        Account currentAccount = accountDAO.getLastAccount(user.getUsername());
        user.setIdAccount(currentAccount.getIdAccount());
        user.setVerifyCode(verifyCode);

        p.close();

    }
    public void insertNVQL(Account user)throws SQLException
    {
        String query = "INSERT INTO NVQuanLy(idQL, email, idAccount) values(?,?,?)";
        
        PreparedStatement p = conn.prepareStatement(query);
        
        p.setString(1, "");
        p.setString(2, user.getEmail());
        p.setString(3, user.getIdAccount());
        p.execute();
        p.close();

    }
    

    private String generateVerifyCode() throws SQLException {
        DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random(); 
        String code = df.format(ran.nextInt(1000000));
        while(isDuplicateCode(code))
        {
            code = df.format(ran.nextInt(1000000));
        }
        return code;
    }
    
    
    private boolean isDuplicateCode(String code) throws SQLException
    {
        boolean duplicate = false;
        try (PreparedStatement p = conn.prepareStatement("SELECT idAccount FROM Account WHERE verifyCode = ? ")) {
            p.setString(1, code);
            try (ResultSet result = p.executeQuery()) {
                if(result.next())
                {
                    duplicate = true;
                }
                result.close();
            }
            p.close();
        }
       
        return duplicate;
    }
   
   public boolean isDuplicateUser(String user)throws SQLException
   {
       boolean duplicate = false;
        try (PreparedStatement p = conn.prepareStatement("SELECT idAccount FROM Account WHERE username=? AND status='Verified'")) {
            p.setString(1, user);
           try (ResultSet r = p.executeQuery()) {
               System.out.println(r.getStatement());
               if(r.next())
               {
                   duplicate = true;
               }
               r.close();
           }
           p.close();
        }
        System.out.println(duplicate);
        return duplicate;
   }
   
   public boolean isDuplicateEmail(String user)throws SQLException
   {
       boolean duplicate = false;
        try (PreparedStatement p = conn.prepareStatement("SELECT idAccount FROM Account WHERE email = ? AND status = 'Verified'")) {
            p.setString(1, user);
           try (ResultSet r = p.executeQuery()) {
               if(r.next())
               {
                   duplicate = true;
               }
               r.close();
           }
           p.close();
           
        }
        return duplicate;
   }
   
   public void doneVerify(String idAccount)throws SQLException
   {
        try (PreparedStatement p = conn.prepareStatement("UPDATE Account SET verifyCode = '' , status = 'Verified' WHERE idAccount = ? ")) {
            p.setString(1, idAccount);
            p.execute();
            p.close();
        }
   }
   
   public boolean verifyCodeWithAccount(String idAccount, String code) throws SQLException {
        boolean isVerify = false;
        PreparedStatement p = conn.prepareStatement("SELECT idAccount FROM Account WHERE idAccount = ? AND verifyCode = ?");
        p.setString(1, idAccount);
        p.setString(2, code);
        ResultSet r = p.executeQuery();
        
        if (r.next() == true) {
            isVerify = true;
        }
//        System.out.println(r.getString("verifyCode"));

        r.close();
        p.close();
    return isVerify;
}
}
