package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SightingDAO {


	
	public List<String> readShapes(){
		
		try {
		Connection conn;
		
		conn = DBConnect.getConnection();
		
		//Lascia sempre uno spazio a ogni fine riga
		//sennò ti esce tutto appiccicato quando
		//le concatena
		String sql="SELECT DISTINCT shape "
				+ "FROM sighting "
				+ "WHERE shape<>'' "
				+ "ORDER BY shape ASC ";
		
		
		
	PreparedStatement st=conn.prepareStatement(sql);
		
			
	ResultSet res=	st.executeQuery(sql);
	
	List<String> formeUFO=new ArrayList<>();
	while(res.next()) {
		String forma=res.getString("shape");
		//nome colonna qui è il nome della
		//colonna della query
		formeUFO.add(forma);
	}
	st.close();
	
	return formeUFO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
			//catturo un'eccezione, non so
			//che farci, gli ribalto un'eccezione
			//non sql, lui non deve sapere niente
			//di jdbc, gliene do una generica
		}
	
		
	}
	
	public int countByShape(String shape) {

		Connection conn;
		try {
	
	conn = DBConnect.getConnection();
		String sql2="SELECT COUNT(*) AS cnt FROM sighting WHERE shape= ? ";
		String shapeScelta="circle";
		
		PreparedStatement st2=conn.prepareStatement(sql2);
		st2.setString(1, shape);
		//devo dare valore parametro
		ResultSet res2=st2.executeQuery();
		//sa già che deve mandare sql2 + parametro settato
		res2.first();
		//c'è un'unica riga, ma io parto sopra la prima,
		//quindi scrivo first per scendere alla prima 
		int count=res2.getInt("cnt");
		//meglio dare un nome significativo alla colonna
		
		
		st2.close();
		return count;
}catch(SQLException e) {
	throw new RuntimeException("Database error in countbyshape",e);
}
	}
}
