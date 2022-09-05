package bank;
// 사용자 객체
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class User {
	private String id;
	private String pw;
	
	private String name;
	private int age;
	
	private List<AccountService> account = new ArrayList<>();
	private int balance;
	private int accountPw;
		
	public User() {
		
	}
	
	public User(String id, String pw, String name, int age) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		
		this.balance = 0;
		
		System.out.println(this.name+"님 회원가입 완료");
		
//		saveUser();
		
	}
	
	
	
	public int getAccountPw() {
		return accountPw;
	}

	public void setAccountPw(int accountPw) {
		this.accountPw = accountPw;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List getAccount() {
		return account;
	}
	public void setAccount(AccountService account) {
		this.account.add(account);
	}
	public void setAccount(List<AccountService> accountList) {
		this.account = accountList;
	}

	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void saveUser() {
		File file = new File("D:/work/userlist.txt");
		FileWriter filewriter;
		try {
			filewriter = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(filewriter);
			PrintWriter pw = new PrintWriter(bw, true); 
		
			
			
			pw.println(this.id + "|" + this.pw + "|" + this.name + "|" + this.age + "|" + this.account + "|" + this.accountPw + "|" + this.balance );
			
			pw.close(); //출력 장치 클로즈
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
	
		
	
}
