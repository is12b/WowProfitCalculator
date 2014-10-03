package dk.is12b.dbLayer;
import java.sql.*;

public class DBGetMax {

	public DBGetMax() {

	}

	public static int getMaxId(String query) {
		ResultSet results;
		int id = -1;
		try {
			Statement stmt = DBConnection.getInstance().getConnection().createStatement();
			results = stmt.executeQuery(query);
			if (results.next()) {
				id = results.getInt(1);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return id;
	}
}
