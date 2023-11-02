package jdbc1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class student2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String driver="oracle.jdbc.driver.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521/xepdb1";
		String user="system";
		String pwd="tiger";
		System.out.println("0");
		Class.forName(driver);
		Connection conn=DriverManager.getConnection(jdbc_url,user,pwd);
		CallableStatement cst=conn.prepareCall("{call vasanth1(?,?)}");
		cst.setInt(1, 1);
		cst.registerOutParameter(2, Types.VARCHAR);
		cst.execute();
		System.out.println("name:"+cst.getString(2));
		

	}

}
