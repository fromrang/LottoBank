package bank;

import java.io.FileReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class BankdbCR {
	
	public static void main(String[] args) {
		Connection conn =null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = select();
		try {
			
			Properties properties = new Properties();
			
			String path = BankDB.class.getResource("bankMysql.properties").getPath();
			path = URLDecoder.decode(path, "utf-8");
			
			properties.load(new FileReader(path));
			
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
		
		
			Class.forName(driver);
//			System.out.println("데이터 베이스 드라이버 로딩 성공!");
			
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
			int result = stmt.executeUpdate(sql);
			String msg = result > -1 ? "성공": "실패";
			
			
//			rs = stmt.executeQuery(sql);
//			ResultSetMetaData rsmd = rs.getMetaData();
//			int cols = rsmd.getColumnCount();
//			for(int i = 1; i <=cols; i++) {
//				String columsName = rsmd.getColumnName(i);
//				System.out.print(columsName + "\t");
//			}
//			System.out.println("\n---------------------------------------------------");
//			while(rs.next()) {
//				for(int i=1; i<=cols; i++) {
//					System.out.print(rs.getString(i) + "\t");
//				}
//				System.out.println();
//			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String select() {
		//크로스 조인
		String sql = "select * from jntest1 cross join jntest2";
		sql = "select * from jntest1, jntest2"; // 묵시적으로 표현 가능
		
		//equi 조인
		sql = "select * from jntest1 t1 inner join jntest2 t2 on t1.id = t2.id";
		sql = "select * from jntest1 t1, jntest2 t2 where t1.id = t2.id"; // 묵시적으로 표현
		
		//left 조인
		sql = "select * from jntest1 t1 left outer join jntest2 t2 on t1.id = t2.id";
		
		//right 조인
		sql = "select * from jntest1 t1 right outer join jntest2 t2 on t1.id = t2.id";
		
		sql = "delete from user";
		
		sql = "delete from aaa1history";
		
		return sql;
	}

}
