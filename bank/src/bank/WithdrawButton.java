package bank;
//출금 페이지
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class WithdrawButton extends Frame implements ActionListener {
	
	Panel p1, p2, p1_1;
	TextArea money;
	Button checkB;
	Label accountNameL, accountNumL, checkL;
	
	String accountName;
	int balance;
	String accountNum;
	AccountService account;
	
	public WithdrawButton(int accountidx) {
		
		account = (AccountService)Menu.user.getAccount().get(accountidx);
		accountName = account.getAccountName();
		accountNum = account.getAccountNum();
		balance = account.getBalance();
		
		p1 = new Panel(new BorderLayout()); //전체
		p2 = new Panel(new GridLayout(5,1)); //돈이랑 버튼
		p1_1 = new Panel(new BorderLayout()); //버튼
		
		money = new TextArea("", 1, 40, TextArea.SCROLLBARS_NONE);
		
		checkB = new Button("출금");
		
		
		
		accountNameL = new Label(accountName);
		accountNumL = new Label(accountNum);
		checkL = new Label("");
		
		p1_1.add(new Label(""), "Center");
		p1_1.add(checkB, "East");
		
		p2.add(accountNameL);
		p2.add(accountNumL);
		p2.add(money);
		p2.add(checkL);
		p2.add(p1_1);
		
		p1.add(p2);
		
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
		BankDB bankdb = new BankDB();
		int money = Integer.valueOf(this.money.getText());
		
		try {
			if (balance < money) {
				JOptionPane.showMessageDialog(this, "잔액이 부족합니다.", "경고", JOptionPane.WARNING_MESSAGE);
			}else {
				String password = (String) JOptionPane.showInputDialog(this, "비밀번호를 입력해주세요", "비밀번호 입력창", JOptionPane.PLAIN_MESSAGE, null, null, null);
				int password1 = Integer.valueOf(password);
				boolean result = bankdb.checkAccountPassword(accountNum, password1);
				
				if(result) {
					balance -= money;
					bankdb.updateAccountBalance(accountNum, balance);
					bankdb.insertHistoryTable(Menu.user.getId(),accountName, "출금", -money, balance);
					account.setBalance(balance);
					PwButton.mb.balanceLabel.setText(balance + "원");
					this.setVisible(false);
				}else JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
			
	}
		
}
