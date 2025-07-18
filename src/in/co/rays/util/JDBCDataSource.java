package in.co.rays.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class JDBCDataSource {
	private static JDBCDataSource jds = null;
	private static ComboPooledDataSource cpds = null;
	
	
	private JDBCDataSource() {
		try {
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
			cpds.setJdbcUrl("jdbc:mysql://localhost:3306/my_project");
			cpds.setUser("root");
			cpds.setPassword("root");
			cpds.setInitialPoolSize(5);
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(30);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static JDBCDataSource getInstance() {
		if(jds == null) {
			jds = new JDBCDataSource();
		}
		return jds;
	}
	
	public static Connection getConnection() throws SQLException {
		try {
			getInstance();
			return JDBCDataSource.cpds.getConnection();
		}catch(SQLException exception) {
			exception.printStackTrace();
		}
		return null;
	}
	
	public static void closeConnection(Connection conn) throws SQLException {
		if(conn!=null) {
			conn.close();
		}
	}
	
	public static void closeConnection(Connection conn,Statement stmt) throws SQLException {
		if(conn!=null) {
			conn.close();
		}
		if(stmt!=null) {
			stmt.close();
		}
	}
	
	public static void closeConnection(Connection conn,Statement stmt,ResultSet rs) throws SQLException {
		if(conn!=null) {
			conn.close();
		}
		if(stmt!=null) {
			stmt.close();
		}
		if(rs!=null) {
			rs.close();
		}
	}

}
