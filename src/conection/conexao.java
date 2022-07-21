package conection;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexao {

    public Connection getConexao() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/estoque?serverTimezone=UTC",
                    "root",
                    "123456"

            );
            return conn;
        } catch (Exception e) {
            System.out.println("Erro ao conectar" + e.getMessage());
            return null;
        }
    }
}
