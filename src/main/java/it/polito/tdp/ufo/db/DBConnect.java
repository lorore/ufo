package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {


	//statico perchè chiama driver manager che è
	//statico
	public static Connection getConnection() throws SQLException {
		String jdbcURL="jdbc:mysql://localhost/ufo_sightings?user=root&password=napoli";

		return DriverManager.getConnection(jdbcURL);
	}
}
