package bank;
//계좌이체 페이지
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
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class TransferButton extends Frame implements ActionListener{
	
	Panel p, p1, p1_1, p1_2, p1_3, balP;
	Label mAname, mAnum, mAbal, yAnumL, yAbalL, yBookL, mBookL, mAbalL;
	TextArea yAnumT, yAbalT, yBookT, mBookT;
	Button next;
	
	String accountName;
	int balance;
	String accountNum;
	AccountService account;
	
	public TransferButton(int accountidx) {
		
		account = (AccountService)Menu.user.getAccount().get(accountidx);
		accountName = account.getAccountName();
		accountNum = account.getAccountNum();
		balance = account.getBalance();
		
		p = new Panel(new BorderLayout());
		p1 = new Panel(new GridLayout(3,1));
		p1_1 = new Panel(new GridLayout(3,1));
		p1_2 = new Panel(new GridLayout(4,1));
		p1_3 = new Panel(new GridLayout(2,2));
		balP = new Panel(new GridLayout(1,2));
		
		mAname = new Label(accountName); // 내 계좌 이름 추가
		mAnum = new Label(accountNum); // 내 계좌 번호 추가
		mAbal = new Label("출금 가능 금액", Label.LEFT);
		mAbalL = new Label(String.valueOf(balance)+"원", Label.RIGHT); // 내 잔액 추가
		yAnumL = new Label("계좌 번호를 입력해주세요");
		yAbalL = new Label("이체 할 금액을 입력해주세요");
		yBookL = new Label("받는 통장 표시");
		mBookL = new Label("내 통장 표시");
		
		yAnumT = new TextArea("", 1, 40, TextArea.SCROLLBARS_NONE);
		yAbalT = new TextArea("", 1, 40, TextArea.SCROLLBARS_NONE);
		yBookT = new TextArea(Menu.user.getName(), 1, 40, TextArea.SCROLLBARS_NONE); // 내 이름 들어가도록 수정
		mBookT = new TextArea("기본값은 계좌 이체 하시는 분 이름입니다.", 1, 40, TextArea.SCROLLBARS_NONE);
		
		next = new Button("다음");
		
		balP.add(mAbal);
		balP.add(mAbalL);

		p1_1.add(mAname);
		p1_1.add(mAnum);
		p1_1.add(balP);
		
		p1_2.add(yAnumL);
		p1_2.add(yAnumT);
		p1_2.add(yAbalL);
		p1_2.add(yAbalT);
		
		p1_3.add(yBookL);
		p1_3.add(yBookT);
		p1_3.add(mBookL);
		p1_3.add(mBookT);
		
		p1.add(p1_1);
		p1.add(p1_2);
		p1.add(p1_3);
		
		p.add(p1, "Center");
		p.add(new Label(""), "North");
		p.add(next, "South");
		p.add(new Label(""), "East");
		p.add(new Label(""), "West");
		
		next.addActionListener(this);
		
		add(p);
		
		setSize(400, 600);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String yaccountNum = yAnumT.getText();
		
		String moneyregExp = "[0-9]+";
		if(!Pattern.matches(moneyregExp, yAbalT.getText())) {
			JOptionPane.showMessageDialog(this, "금액에 숫자만 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int money = Integer.valueOf(yAbalT.getText());
		BankDB  bankdb = new BankDB();
		
		try {
			List<String> AccountNumList = bankdb.selectAccountNumAll();
			if(!AccountNumList.contains(yaccountNum)) {
				JOptionPane.showMessageDialog(this, "존재하지 않는 계좌 번호 입니다.", "경고", JOptionPane.WARNING_MESSAGE);
			}
			else if (money > balance) {
				JOptionPane.showMessageDialog(this, "잔액이 부족합니다.", "경고", JOptionPane.WARNING_MESSAGE);
				
			}else {
				String password = (String) JOptionPane.showInputDialog(this, "비밀번호를 입력해주세요", "비밀번호 입력창", JOptionPane.PLAIN_MESSAGE, null, null, null);
				
				if(!Pattern.matches(moneyregExp, password)) {
					JOptionPane.showMessageDialog(this, "비밀번호는 숫자만 가능합니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int password1 = Integer.valueOf(password);
				
				boolean result = bankdb.checkAccountPassword(accountNum, password1);
				if(result) {
					balance -= money;
					bankdb.transferMethod(accountNum, yaccountNum, money);
					account.setBalance(balance);
					PwButton.mb.balanceLabel.setText(balance + "원");
					this.setVisible(false);
					this.setVisible(false);
				}else JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			}
		
		
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	 

}
