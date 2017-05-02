package daoutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lucas.pereira on 06/04/2017.
 */
public class ConnectionFactory {

    private static ConnectionFactory connectionFactory = null;
    //private String connectionStr = "jdbc:sqlserver://localhost:1433;user=sa;password=1Qaz2Wsx;databaseName=ProjetoIntegrador";
    private String connectionStr = "jdbc:mysql://localhost:3306/projetointegrador";
    //private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String driver = "com.mysql.jdbc.Driver";
    private String username = "root";
    private String password = "root";

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null)
            connectionFactory = new ConnectionFactory();

        return connectionFactory;
    }


    // abre uma nova conexão com o banco de dados. Se algum erro for lançado
    // aqui, verifique o erro com atenção e se o banco está rodando
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(connectionStr,username,password);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof ClassNotFoundException) {
                System.err.println("VERIFIQUE SE O DRIVER DO BANCO DE DADOS EST�? NO CLASSPATH");
            } else {
                System.err.println("VERIFIQUE SE O BANCO EST�? RODANDO E SE OS DADOS DE CONEXÃO ESTÃO CORRETOS");
            }
            System.exit(0);
            // o sistema deverá sair antes de chegar aqui...

        }return conn;
    }

    public void closeConnection() throws SQLException {
        try {
            getConnection().close();
        } catch (Exception e) {
            System.out.println(" Erro na conexão " + e.getMessage());
        }
    }
}

