package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author JOÃO VITOR
 */
public class Conexao {

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/controlecomercio", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
