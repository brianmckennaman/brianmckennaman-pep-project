package DAO;

import Model.Account;
import Util.ConnectionUtil;
import java.sql.*;

public class AccountDAO {
    // user registration
    public Account userRegistration(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO account(username, password) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, account.username);
            preparedStatement.setString(2, account.password);

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()) {
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
