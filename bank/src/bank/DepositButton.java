package bank;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//입금 창
public class DepositButton extends Frame implements ActionListener{
	
	Panel p1, p1_1,p2;
	TextArea money;
	Button checkB;
	Choice accountC;
	Label l, l2;
	
	String accountName;
	int balance;
	String accountNum;
	AccountService account;
	
	FileInfo fileinfo;

	
	public DepositButton(int accountidx) {
		
		p1 = new Panel(new BorderLayout()); //전체
		p2 = new Panel(new GridLayout(5,1)); //돈이랑 버튼
		p1_1 = new Panel(new BorderLayout()); //버튼
		
		money = new TextArea("", 1, 40, TextArea.SCROLLBARS_NONE);
		
		checkB = new Button("입금");
		
		l = new Label("");
		l2 = new Label("");
		
		p1_1.add(new Label(""), "Center");
		p1_1.add(checkB, "East");
		
		account = (AccountService)Menu.user.getAccount().get(accountidx);
		accountName = account.getAccountName();
		accountNum = account.getAccountNum();
		balance = account.getBalance();
		
		l2.setText(accountName);
		l.setText(accountNum);
		
		p2.add(l2);
		p2.add(l);
		p2.add(money);
		p2.add(p1_1);
		
		p1.add(p2, "Center");
		
		checkB.addActionListener(this);
		add(p1);
		
		setSize(400, 700);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int money = Integer.valueOf(this.money.getText());
		
		balance += money;
		
		//account DB에 있는 balance값 바꿔주기, 입금, 출금, 계좌이체 모두 동일
		
		try {
			BankDB bankdb1 = new BankDB();
			bankdb1.updateAccountBalance(accountNum, balance);
		}catch(Exception ex){}
		
		try {
			BankDB bankdb2 = new BankDB();
			bankdb2.insertHistoryTable(Menu.user.getId(),accountName, "입금", money, balance);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
		account.setBalance(balance);
		PwButton.mb.balanceLabel.setText(balance + "원");
		this.setVisible(false);
		
		
	
	}
	

	
}
