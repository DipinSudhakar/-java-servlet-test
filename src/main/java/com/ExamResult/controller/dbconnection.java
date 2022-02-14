package com.ExamResult.controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbconnection {
	Statement stmt;
	dbconnection(){
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/be_final_year","root","");
			 stmt = con.createStatement();
	}
	catch(Exception E) {
		
	}
}
	//executeUpdate
	public void update(String s) throws SQLException {
		
		stmt.executeUpdate(s);
	}
	//executeQuery
	public ResultSet select(String s) throws SQLException {
		
			ResultSet rs = stmt.executeQuery(s);
			return rs;
	} 
	}
