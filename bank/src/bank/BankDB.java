package bank;
// 은행 db
import java.io.FileReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BankDB {

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public BankDB()  {
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
			System.out.println("데이터 베이스 연결 성공!");
			
			
		}catch(Exception e) {
			System.out.println("데이터 베이스 드라이버 로딩 실패!" + e);
		}

	}
	
	public void insertUser(String id, String pw ,String name, int age, String address) {
		
		try {
			SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
			Calendar time = Calendar.getInstance();
			
			String sql = "INSERT INTO user VALUES('" + id + "','"+ pw +"','"+ name +"','"+ age+"','"+ address +"','"+ format.format(time.getTime()) +"')";
			
			System.out.println("--"+sql);
			
			stmt = conn.createStatement(); 
			int result = stmt.executeUpdate(sql); 
			
			String msg = result > -1 ? "[SUCCESS]": "[FAIL]";
			
			System.out.println(msg+" userinsert");
		}catch(Exception ex) {
			
		}
		try {
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}
	
	public void insertAccount(String id, String accountName ,String accountNum, int pw, int balance) throws SQLException {
		
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Calendar time = Calendar.getInstance();
		
		String sql = "INSERT INTO account VALUES('" + id + "','"+ accountName +"','"+ accountNum+"','"+ pw +"','"+ balance+"','"+format.format(time.getTime()) +"')";
		stmt = conn.createStatement(); 
		int result = stmt.executeUpdate(sql); 
		
		String msg = result > -1 ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg + " accountinsert");
		
		try {
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {}
		

	}
	
	
	public List<String> selectIdAll() throws SQLException {
		
		List<String> idList = new ArrayList<>();
		
	
		
		String sql = "select ID from user";
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql); 
		
		while(rs.next()) {
			idList.add(rs.getString("id"));
		}
		
		String msg = rs != null ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg + " selectIdAll");
		
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {}
		
		return idList;

	}
	
	public HashMap<String, String> selectIdPw(String id) throws SQLException {
		
		HashMap<String, String> idPwMap = new HashMap<>();

		String sql = "select ID, PW from user where id = '" + id +"'";
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql); 
		
		if(rs.next()) {
			idPwMap.put(rs.getString("id"), rs.getString("pw"));
		}
		
		String msg = rs != null ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg + " selectIdPw");
		
		try {
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {}
		
		return idPwMap;

	}
	
	public User selectUser(String id) throws SQLException {
		
		User user = null;

		String sql = "select * from user where id = '" + id +"'";
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql); 
		
		if(rs.next()) {
			user = new User(rs.getString("id"), rs.getString("pw"), rs.getString("name"), rs.getInt("age"));
		}
		
		String msg = rs != null ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg + " selectUser");
		
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {}
		
		return user;

	}
	
	public List<String> selectAccountNumAll() throws SQLException {
		
		List<String> AccountNumList = new ArrayList<>();
		
	
		
		String sql = "select accountNum from account";
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql); 
		
		while(rs.next()) {
			AccountNumList.add(rs.getString("accountNum"));
		}
		
		String msg = rs != null ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg + " selectAccountNumAll");
		
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {}
		
		return AccountNumList;

	}
	
	//내가 가지고 있는 계좌 번호 가지고 올 때 쓰는 메소드
	public List<AccountService> selectAccountAll(String id) throws SQLException {
		
		AccountService account = null;
		List<AccountService> accountList= new ArrayList<>();

		String sql = "select * from account where id = '" + id +"'";
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql); 
		
		while(rs.next()) {
			account = new AccountService( rs.getString("accountnum"), rs.getString("accountname"),rs.getInt("pw"), rs.getInt("balance"));
			accountList.add(account);
		}
		
		String msg = rs != null ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg + " selectAccountAll");
		
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {}
		
		return accountList;

	}
	
	//계좌 번호로 id와 name 반환하는 메소드
	public String[] getIdName(String accountNum) throws SQLException {
		
		String[] idName = new String[2];
		String id = null;
		String name = null;

		
		String sql = "select id, balance from account where accountNum = '" + accountNum +"'";
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql); 
		
		if(rs.next()) {
			id = rs.getString("id");
		}
		
		String sql2 = "select name from user where id = '" + id +"'";
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql2); 
		
		if(rs.next()) {
			name = rs.getString("name");
		}
		
		idName[0] = id;
		idName[1] = name;
		
		String msg = rs != null ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg + " getIdName");
		
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
//			//if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return idName;

	}
	
	public void updateAccountBalance(String accountNum, int balance) throws SQLException {

		String sql = "update account set balance= '"+balance+"' where accountNum = '" + accountNum +"'";
		stmt = conn.createStatement(); 
		
		int result = stmt.executeUpdate(sql); 
		
		String msg = result > -1 ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg+ " updateAccountBalance");
	
		try {
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {}
		
		

	}
	
	public void createHistoryTable(String id) throws SQLException {
		
		String historyName = id + "history";
	
		
		String sql = "create table IF NOT EXISTS " +historyName +"(accountName varchar(30), paymentType varchar(10), use_balance int, balance int, cre_datetime datetime)";
		stmt = conn.createStatement(); 
		
		int result = stmt.executeUpdate(sql); 
		
		String msg = result > -1 ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg+ " createHistoryTable");
	
		try {
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}


	}
	
	public void insertHistoryTable(String id, String accountName, String type, int useBalance, int balance) throws SQLException {
		
		String historyName = id + "history";
		
		String sql = "insert into " + historyName + " values ('"+accountName+"', '" + type+ "', "+useBalance + ", "+balance+", '2022-05-14 15:22:30')";
		
		System.out.println(sql);
		
		stmt = conn.createStatement(); 
		
		int result = stmt.executeUpdate(sql); 
		
		String msg = result > -1 ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg+ " insertHistoryTable");
	
		try {
			
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}


	}
	public List<AccountHistory> selectAccountHistoryAll(String id, String accountName) throws SQLException {
		
		AccountHistory account = null;
		String historyName = id + "history";
		
		List<AccountHistory> accounthistoryList= new ArrayList<>();

//		String sql = "select * from "+ historyName + " where accountName = '"+accountName+"'";
		String sql = "select * from "+ historyName;
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql); 
		
		while(rs.next()) {
			account = new AccountHistory( rs.getString("accountName"), rs.getString("use_balance"), rs.getString("balance"), rs.getString("cre_datetime"));
			accounthistoryList.add(account);
		}
		
		String msg = rs != null ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg + " selectAccountHistoryAll");
		
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();	
		}
		
		return accounthistoryList;

	}
	
	public void transferMethod(String myAccountNum, String youAccountNum, int money) throws SQLException{
		
		
		
		String[] myidName = getIdName(myAccountNum);
		
		String myId = myidName[0];
		String myName = myidName[1];
		
		String[] youidName = getIdName(youAccountNum);
		
		String youId = youidName[0];
		String youName = youidName[1];
		
		updateMyaccount(myId, myAccountNum, money, youName);
		updateYouaccount(youId, youAccountNum, money, myName);

	}
	
	public int getBalance(String accountNum) throws SQLException{
		
		int balance = 0;

		String sql = "select balance from account where accountNum = '" + accountNum +"'";
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql); 
		
		if(rs.next()) {
			balance = rs.getInt("balance");
		}
		
		String msg = rs != null ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg + " getBalance");
		
		try {
			if(stmt != null) stmt.close();
//			//if(conn != null) conn.close();
		}catch(Exception e) {}
		
		
		return balance;
	}
	
	public void updateMyaccount(String id,String accountNum, int money, String youName) throws SQLException{
		
		int balance = getBalance(accountNum);
		
		balance -= money;
		
		updateAccountBalance(accountNum, balance);
		insertHistoryTable(id, youName, "출금", -money, balance); //내 id, 상대방 이름, 출금 금액, 잔액 -> 내 계좌 히스토리 업데이트
		
		
	}
	
	public void updateYouaccount(String id, String accountNum, int money, String myName) throws SQLException{
		
		int balance = getBalance(accountNum);
		
		balance += money;
		
		updateAccountBalance(accountNum, balance);
		insertHistoryTable(id, myName, "입금", money, balance); //상대방 id, 내이름 이름, 입금 금액, 잔액 -> 상대 계좌 히스토리 업데이트
		
		
	}
	
	public boolean checkAccountPassword(String accountNum, int password) throws SQLException{
		
		boolean result = false;
		int dbPassword = 0;
		
		String sql = "select pw from account where accountNum = '" + accountNum +"'";
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql); 
		
		if(rs.next()) {
			dbPassword = rs.getInt("pw");
		}
		
		if(dbPassword == password) {
			result = true;
		}
		
		return result;
		
	}
	
	public void updateUserInfo(String id, String pw, int age) throws SQLException {

		String sql = "update user set pw= '"+pw+"', age = "+age+" where id = '" + id +"'";
		stmt = conn.createStatement(); 
		
		int result = stmt.executeUpdate(sql); 
		
		String msg = result > -1 ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg+ " updateUserInfo");
	
		try {
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {}
		
		

	}
	
	//시도 리스트 반환
	public List<String> selectSidoAll() throws SQLException {
		
		List<String> sidoList = new ArrayList<>();
		
	
		
		String sql = "select distinct sido from zipcode";
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql); 
		
		while(rs.next()) {
			sidoList.add(rs.getString("sido"));
		}
		
		String msg = rs != null ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg + " selectSidoAll");
		
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {}
		
		return sidoList;

	}

	//구군 리스트 반환 - 시도 인자값 필요
	public List<String> selectGugunAll(String sido) throws SQLException {
		
		List<String> gugunList = new ArrayList<>();
		
	
		
		String sql = "select distinct gugun from zipcode where sido = '" + sido + "'";
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql); 
		
		while(rs.next()) {
			gugunList.add(rs.getString("gugun"));
		}
		
		String msg = rs != null ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg + " selectSidoAll");
		
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {}
		
		return gugunList;

	}
	
	public List<String> selectDongAll(String sido, String gugun) throws SQLException {
		
		List<String> dongList = new ArrayList<>();
		
	
		
		String sql = "select distinct dong from zipcode where sido = '" + sido + "' and gugun = '" +gugun+"'";
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql); 
		
		while(rs.next()) {
			dongList.add(rs.getString("dong"));
		}
		
		String msg = rs != null ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg + " selectSidoAll");
		
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {}
		
		return dongList;

	}
	
	public List<String> selectAllFindByAddress(String sido, String gugun, String dong) throws SQLException {
		
		List<String> AddressList = new ArrayList<>();
		
	
		
		String sql = "select * from zipcode where sido = '" + sido + "' and gugun = '" +gugun+"' and dong = '" +dong+"'";
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql); 
		
		
		while(rs.next()) {
			String a = rs.getString("zipcode") + "  " +rs.getString("sido") + "  " +rs.getString("gugun") + "  " +rs.getString("dong") + "  " +rs.getString("bldg");
			AddressList.add(a);
		}
		
		String msg = rs != null ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg + " selectAllFindByAddress");
		
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {}
		
		return AddressList;

	}
	
	//아파트 검색할때
	public List<String> selectAllFindByAddress(String sido, String gugun, String dong, String search) throws SQLException {
		
		List<String> AddressList = new ArrayList<>();
		
		String sql = "select * from zipcode where BLDG like '%" + search+ "%' and sido = '" + sido + "' and gugun = '" +gugun+"' and dong = '" +dong+"'";
		
		if(sido == null) {
			sql = "select * from zipcode where BLDG like '%"+ search+"%'";
		}
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql); 
		
		
		while(rs.next()) {
			String a = rs.getString("zipcode") + "  " +rs.getString("sido") + "  " +rs.getString("gugun") + "  " +rs.getString("dong") + "  " +rs.getString("bldg");
			AddressList.add(a);
		}
		
		String msg = rs != null ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg + " selectAllFindByAddress");
		
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {}
		
		return AddressList;

	}
	//추천 알고리즘
	public String recommanditem(String id) throws SQLException {
		
		String result = null;
		
		int aMonthagoCount = 0;
		int depositmoney = 0;
		int withdrawmoney = 0;
		
		String historyName = id + "history";
		
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM");
		Calendar time = Calendar.getInstance();
		
		time.add(Calendar.MONTH, -1);	
		String aMonthAgo = format.format(time.getTime());
		
		
		String sql = "select DATE_FORMAT(cre_datetime,'%Y-%m') m , count(*) c from "+historyName+" where DATE_FORMAT(cre_datetime,'%Y-%m') = '" + aMonthAgo +"' group by m";
		
		stmt = conn.createStatement(); 
		rs = stmt.executeQuery(sql); 

		if(rs.next()) {
			aMonthagoCount = rs.getInt(2); 
		}
		
		if(aMonthagoCount < 5) {
			result = "new";
		}else {
			sql = "select paymentType, DATE_FORMAT(cre_datetime,'%Y-%m') m , sum(use_balance) s from " + historyName +" where DATE_FORMAT(cre_datetime,'%Y-%m') = '"+aMonthAgo+"' and paymentType = '입금' group by m, paymentType";
	
			stmt = conn.createStatement(); 
			rs = stmt.executeQuery(sql); 
			
			if(rs.next()) {
				depositmoney = rs.getInt(3); 
			}
			
			sql = "select paymentType, DATE_FORMAT(cre_datetime,'%Y-%m') m , sum(use_balance) s from " + historyName +" where DATE_FORMAT(cre_datetime,'%Y-%m') = '"+aMonthAgo+"' and paymentType = '출금' group by m, paymentType";
			
			stmt = conn.createStatement(); 
			rs = stmt.executeQuery(sql); 
			
			if(rs.next()) {
				withdrawmoney = rs.getInt(3); 
			}
			
			if(depositmoney >= Math.abs(withdrawmoney)) {
				result =  "입금";
			}else {
				result =  "출금";
			}
		}
		
		String msg = rs != null ? "[SUCCESS]": "[FAIL]";
		
		System.out.println(msg + " selectAllFindByAddress");
		
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			//if(conn != null) conn.close();
		}catch(Exception e) {}

		return result;
	}
}
