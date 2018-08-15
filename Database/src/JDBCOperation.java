import com.mysql.jdbc.Connection;
import com.mysql.jdbc.JDBC42PreparedStatement;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
public class JDBCOperation {
	private static Connection getConn() throws SQLException{
		String url = "jdbc:mysql://localhost/test?user=root&password=";
		java.sql.Connection conn = DriverManager.getConnection(url);
		return (Connection) conn;
	}
	public static int insertCustomers(String x1,String x2,String x3,String x4) throws SQLException{
		Connection conn=getConn();
		int i=0;
		String sql = "insert into customer values (?,?,?,?)";
		PreparedStatement pst = null;
		try{
			pst=(PreparedStatement)conn.prepareStatement(sql);
			pst.setString(1, x1);
			pst.setString(2, x2);
			pst.setString(3, x3);
			pst.setString(4, x4);
			i=pst.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			pst.close();
			conn.close();
		}
		return i;
	}
	public static int insertMember(String x1,String x2,String x3) throws SQLException{
		Connection conn=getConn();
		int i=0;
		String sql = "insert into member values (?,?,?)";
		PreparedStatement pst = null;
		try{
			pst=(PreparedStatement)conn.prepareStatement(sql);
			pst.setString(1, x1);
			pst.setString(2, x2);
			pst.setString(3, x3);
			i=pst.executeUpdate();	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			pst.close();
			conn.close();
		}
		return i;
	}
	public static int insertCar(String x1,String x2,String x3,String x4) throws SQLException{
		Connection conn=getConn();
		int i=0;
		String sql = "insert into car values (?,?,?,?)";
		PreparedStatement pst = null;
		try{
			pst=(PreparedStatement)conn.prepareStatement(sql);
			pst.setString(1, x1);
			pst.setString(2, x2);
			pst.setString(3, x3);
			pst.setString(4, x4);
			i=pst.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			pst.close();
			conn.close();
		}
		return i;
	}
	public static int insertModel(String x1,String x2,String x3) throws SQLException{
		Connection conn=getConn();
		int i=0;
		String sql = "insert into model values (?,?,?)";
		PreparedStatement pst = null;
		try{
			pst=(PreparedStatement)conn.prepareStatement(sql);
			pst.setString(1, x1);
			pst.setString(2, x2);
			pst.setString(3, x3);
			i=pst.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			pst.close();
			conn.close();
		}
		return i;
	}
	public static int getCars() throws SQLException{
		Connection conn=getConn();
		int i=0;
		String sql = "SELECT * FROM car INNER JOIN model WHERE registration_number = 12345678";
		Statement st = (Statement) conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		while(rs.next())
		{
			int number = rs.getInt("registration_number");
			String x1 = rs.getString("made_year");
			String x2 = rs.getString("status");
			String x3 = rs.getString("model_name");
			System.out.println(number+" "+x1+" "+x2+" "+x3);
		}
		rs.close();
		conn.close();
		return i;
	}
	public static int getRnumber() throws SQLException{
		Connection conn=getConn();
		int i=0;
		String sql = "SELECT registration_number FROM car WHERE status = 'rent'";
		Statement st = (Statement) conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		while(rs.next())
		{
			int number = rs.getInt("registration_number");
			System.out.println(number);
		}
		rs.close();
		conn.close();
		return i;
	}
	public static void main(String[] args) throws SQLException {
		JDBCOperation.insertCustomers("15372203","dw","87583166","Beijing");
		JDBCOperation.insertMember("158","2015-01-01","15372203");
		JDBCOperation.insertModel("BMW X5", "BMW", "5");
		JDBCOperation.insertCar("12345678", "2010-03-01", "rent","BMW X5");
		JDBCOperation.getCars();
		JDBCOperation.getRnumber();
	}
}
