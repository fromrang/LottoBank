package bank;
//회원정보 변경 페이지
import java.awt.BorderLayout;
import java.awt.Button;
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
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class MyPage extends Frame implements ActionListener{
	
	Panel eP, iP, p1, p2, p3, p4;
	JLabel myPageLabel, myAccount;
	Label nowpassword, newpassword, newpasswordcheck, age, l1, l2, l3;
	TextArea nowpasswordT, newpasswordT, newpasswordcheckT, ageT;
	Button accountDelete; 
	JButton check;
	JScrollBar jb;
	JScrollPane jp;
	Border nextBorder;
	
	public MyPage() {
		eP = new Panel(new BorderLayout());
		iP = new Panel(new GridLayout(7,1));
		p1 = new Panel(new GridLayout(2,2));
		p2 = new Panel(new GridLayout(2,2));
		p4 = new Panel(new GridLayout(1,3));
		
		myPageLabel = new JLabel("마이페이지");
		myPageLabel.setForeground(new Color(051, 153, 153));
		myPageLabel.getFont().deriveFont(100.0f);
		
		myAccount = new JLabel("내 계좌");
		myAccount.setForeground(new Color(051, 153, 153));
		myAccount.getFont().deriveFont(100.0f);
		
		nowpassword = new Label("현재 비밀번호");
		newpassword = new Label("새 비밀번호");
		newpasswordcheck = new Label("새 비밀번호 확인");
		age = new Label("나이");
		
		nowpasswordT = new TextArea("", 1, 40, TextArea.SCROLLBARS_NONE); 
		newpasswordT = new TextArea("", 1, 40, TextArea.SCROLLBARS_NONE); 
		newpasswordcheckT = new TextArea("", 1, 40, TextArea.SCROLLBARS_NONE); 
		ageT = new TextArea("", 1, 40, TextArea.SCROLLBARS_NONE);
		
		check = new JButton("수정하기");
		nextBorder = BorderFactory.createLineBorder(new Color(051, 153, 153));
		check.setForeground(new Color(051, 153, 153)); //버튼 글자색 바꾸기
		check.setContentAreaFilled(false);
		check.setFocusPainted(false);
		check.setBorder(nextBorder);
		
		p4.add(new Label("계좌 이름"));
		p4.add(new Label("계좌 번호"));
		p4.add(new Label("잔액"));
		
		List<AccountService> account = Menu.user.getAccount();
		Panel p3 = new Panel(new GridLayout(Menu.user.getAccount().size(),1));
		
		for(int i=0; i < Menu.user.getAccount().size(); i++) {
			l1 = new Label((account.get(i)).getAccountName());
			l2 = new Label((account.get(i)).getAccountNum());
			l3 = new Label(String.valueOf(((account.get(i)).getBalance())));
			
			Panel p2= new Panel(new GridLayout(1,3));
			
			p2.add(l1);
			p2.add(l2);
			p2.add(l3);
			
			p3.add(p2);
			
			
		}
		
		jp = new JScrollPane(p3);
		jb = jp.getVerticalScrollBar();
		
		p1.add(nowpassword);
		p1.add(nowpasswordT);
		p1.add(newpassword);
		p1.add(newpasswordT);
		
		p2.add(newpasswordcheck);
		p2.add(newpasswordcheckT);
		p2.add(age);
		p2.add(ageT);
		
		
		iP.add(myPageLabel);
		iP.add(p1);
		iP.add(p2);
		iP.add(myAccount);
		iP.add(p4);
		iP.add(jp);
		iP.add(check);
		
		eP.add(iP, "Center");
		eP.add(new Label(""), "North");
		eP.add(new Label(""), "South");
		eP.add(new Label(""), "East");
		eP.add(new Label(""), "West");
		
		check.addActionListener(this);
		
		add(eP);
		
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
		String pwregExp = "^[a-z]+\\d+";
		String ageregExp = "[0-9]+";
		
		boolean result = Menu.user.getPw().equals(nowpasswordT.getText());
		
		if(result) {
			if(newpasswordT.getText().equals(newpasswordcheckT.getText()) & Pattern.matches(pwregExp, newpasswordT.getText())){
				BankDB bankdb = new BankDB();
				
				if(!Pattern.matches(ageregExp, ageT.getText())){
					JOptionPane.showMessageDialog(this, "나이에는 숫자값만 들어갈 수 있습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					bankdb.updateUserInfo(Menu.user.getId(), newpasswordT.getText(), Integer.valueOf(ageT.getText()));
					Menu.user.setPw(newpasswordT.getText());
					Menu.user.setAge(Integer.valueOf(ageT.getText()));
					this.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}else {
				JOptionPane.showMessageDialog(this, "새 비밀번호가 일치하지 않거나 기준에 맞지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(this, "기존 비밀번호가 일치하지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE);
		}
		
	}

}
