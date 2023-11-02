package jdbc1;
import java.sql.*;

public class student {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("hi");
		String driver="oracle.jdbc.driver.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521/xepdb1";
		String user="system";
		String pwd="tiger";
		System.out.println("0");
		Class.forName(driver);
		Connection conn=DriverManager.getConnection(jdbc_url,user,pwd);
		System.out.println("1");
		CallableStatement cst=conn.prepareCall("{call procedure1(?,?,?)}");
		System.out.println("2");
		cst.setInt(1, 10);
		cst.setInt(2, 20);
		System.out.println("3");
		cst.registerOutParameter(3, Types.INTEGER);
		System.out.println("4");
		cst.execute();
		System.out.println("5");
		int b=cst.getInt(3);
		System.out.println("Result:"+b);
		conn.close();

	}

}
