package bank;
//계좌 객체
public class AccountService {
	
	String accountNum;
	String accountName;
	int accountPw;
	int balance;
	
	public AccountService() {
		
	}
	
	public AccountService(String accountNum, String accountName, int accountPw) {
		this.accountName = accountName;
		this.accountNum = accountNum;
		this.accountPw = accountPw;
		this.balance = 0;
	}
	
	public AccountService(String accountNum, String accountName, int accountPw, int balance) {
		this.accountName = accountName;
		this.accountNum = accountNum;
		this.accountPw = accountPw;
		this.balance = balance;
	}
	public AccountService(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public int getAccountPw() {
		return accountPw;
	}
	public void setAccountPw(int accountPw) {
		this.accountPw = accountPw;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
}
