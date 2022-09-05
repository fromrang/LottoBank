package bank;
//계좌 내역 객체
public class AccountHistory {
	String accountName;
	String use_balance;
	String balance;
	String cre_datetime;
	
	public AccountHistory(String accountName, String use_balance, String balance, String cre_datetime) {
		this.accountName  = accountName;
		this.use_balance = use_balance;
		this.balance = balance;
		this.cre_datetime = cre_datetime;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getUse_balance() {
		return use_balance;
	}

	public void setUse_balance(String use_balance) {
		this.use_balance = use_balance;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getCre_datetime() {
		return cre_datetime;
	}

	public void setCre_datetime(String cre_datetime) {
		this.cre_datetime = cre_datetime;
	}

	
	
	
}
