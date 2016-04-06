package snapcity.dao.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ConectionFactory {
	public static Connection getConnection() throws SQLException {
		Connection c = null;
		      try {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/snapcity","postgres","snap");
		      
		      System.out.println("Postgree conectado");
		      
		      } catch (Exception e) {
			         e.printStackTrace();
			         System.err.println(e.getClass().getName()+": "+e.getMessage());
			         System.exit(0);
			      }
		 return c;
	}

}
