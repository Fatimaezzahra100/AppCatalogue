package metier;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//exemple d'un desing pattern singletton qui va nous permettrent de créer une seule connexion
public class SingletonConnexion {

	// classe qui declare generalement un objet static

	private static Connection connection;

	// block static
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/Catalogue_P", "root", "");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		return connection;
	}
	

}
