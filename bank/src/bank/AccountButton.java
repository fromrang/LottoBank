package bank;
//계좌 생성
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class AccountButton extends Frame implements ActionListener{
	
	Panel p, p1_1, p2;
	Label nameL, pwL, pwCL, pwCL2;
	TextArea nameTx, pwTx, pwCLTx;
	Button checkB;

	public AccountButton() {
		
		p = new Panel(new BorderLayout());
		p1_1 = new Panel(new GridLayout(6,1));
		p2 = new Panel(new BorderLayout());
		
		nameL = new Label("계좌 이름");
		pwL = new Label("계좌 비밀번호");
		pwCL = new Label("계좌 비밀번호 확인");
		pwCL2 = new Label("");
		
		nameTx = new TextArea("", 1, 40, TextArea.SCROLLBARS_NONE);
		pwTx = new TextArea("", 1, 40, TextArea.SCROLLBARS_NONE);
		pwCLTx = new TextArea("", 1, 40, TextArea.SCROLLBARS_NONE);
		
		checkB = new Button("확인");
		
		p1_1.add(nameL);
		p1_1.add(nameTx);
		p1_1.add(pwL);
		p1_1.add(pwTx);
		p1_1.add(pwCL);
		p1_1.add(pwCLTx);
		
		p2.add(pwCL2, "North");
		p2.add(checkB, "Center");
		
		checkB.addActionListener(this);
		
		p.add(p1_1, "Center");
		p.add(p2, "South");
		
		add(p);
		
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

		String in_accountName = nameTx.getText();
		String in_accountpw = pwTx.getText();
		String in_accountpwCk = pwCLTx.getText();
		String accountNum = createAccountNum();
		
		String pwregExp = "[0-9]{4}";
		
		
		if(!Pattern.matches(pwregExp, in_accountpw)) {
			pwCL2.setText("비밀번호는 숫자 4자리만 가능합니다.");
		}
		else if(!in_accountpw.equals(in_accountpwCk)) {
			pwCL2.setText("비밀번호가 같지 않습니다.");
		}else {
			//버튼 누를때
			Menu.user.setAccount(new AccountService(accountNum, in_accountName, Integer.valueOf(in_accountpw)));
			try { // account db에 계좌 이름, 계좌번호, id, 비밀번호, 잔액 0으로 인서트
				BankDB dbex51 =new BankDB();
				dbex51.insertAccount(Menu.user.getId(), in_accountName, accountNum, Integer.valueOf(in_accountpw), 0);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		
			PwButton.mb.p2_1.removeAll();
			PwButton.mb.p2_1.setLayout(new GridLayout(3,1));
		
			PwButton.mb.accountC = new Choice();
			PwButton.mb.balanceLabel = new JLabel("", JLabel.RIGHT);
			
			
			for(int i =0; i<Menu.user.getAccount().size() ; i++ ) {
				
				String accountName = ((AccountService)Menu.user.getAccount().get(i)).getAccountName();
				int balance = ((AccountService)Menu.user.getAccount().get(i)).getBalance();
				
				PwButton.mb.accountC.add(accountName);
				PwButton.mb.balanceLabel.setText(balance + "원");
				
			}
			PwButton.mb.p2_1.add(PwButton.mb.accountC); // 드롭다운 만들기
			PwButton.mb.p2_1.add(PwButton.mb.balanceLabel);
			PwButton.mb.p2_1.add(PwButton.mb.p2_2);
			PwButton.mb.p2_1.setBackground(new Color(255,255,255));
			
			PwButton.mb.p2.add(PwButton.mb.p2_1, "Center");
			PwButton.mb.p2.add(new Label(""), "North");
			PwButton.mb.p2.add(new Label(""), "East");
			PwButton.mb.p2.add(new Label(""), "West");
			PwButton.mb.p2.add(new Label(""), "South");
			
			PwButton.mb.nextBorder = BorderFactory.createLineBorder(new Color(051, 153, 153));
			PwButton.mb.p2.setBorder(PwButton.mb.nextBorder);
			PwButton.mb.p2.setBackground(new Color(255,255,255));
			
			
			PwButton.mb.p3.removeAll();
			PwButton.mb.p3.setLayout(new GridLayout(3,1));
			PwButton.mb.p3.add(new Label(""));
			PwButton.mb.p3.add(PwButton.mb.p3_1);
			PwButton.mb.p3.add(new Label(""));
	
			
			this.setVisible(false);
		}

	}
	
	public String createAccountNum() { //계좌번호중복체크
		
		BankDB bankdb = new BankDB();
		
		
		String accountNum = setAccount();
		
		try {
		
			while(true) {
				List<String> AccountNumList = bankdb.selectAccountNumAll();
				if(AccountNumList.contains(accountNum)) {
					accountNum = setAccount();
					
				}else {
					break;
				}
			}
		}catch(Exception ex) {}

		
		return accountNum;
	}
	
	public String setAccount() {
		
		Random random = new Random();
		
		String account = "";
				
		for(int i = 0; i < 14; i++) {
			int a = (int) (Math.random() * 9) + 1;
			account += Integer.toString(a);
			if (i ==3 | i == 7 | i == 11) {
				account += '-';
			}else {
				continue;
			}
		}

		return account;
	}

	public String getAccount() {
		return "--" + ((AccountService)Menu.user.getAccount().get(0)).getAccountName() ;
	}	

}
