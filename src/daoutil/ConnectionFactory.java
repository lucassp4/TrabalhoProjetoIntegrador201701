package daoutil;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by lucas.pereira on 06/04/2017.
 */
public class ConnectionFactory {


        private Connection con = null;

        public Connection getConnection() {
            try {
               // Class.forName("com.mysql.jdbc.Driver");
            	DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetoIntegrador", "root", "root");
            } catch (Exception e) {
                System.out.println("Erro na conex�o: " + e.getMessage());
            }
            return con;
        }

        public void setClose() {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Erro na conex�o: " + e.getMessage());
            }
        }

    }