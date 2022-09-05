package bank;
// 마이페이지
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;


public class AddPage  extends Frame implements ActionListener{
	
	Panel p;
	Button correctionB, accountB, logoutB;
	public AddPage() {
		p = new Panel(new GridLayout(3,1));
		
		correctionB = new Button("회원 정보 변경");
		accountB = new Button("새로운 계좌 만들기");
		logoutB = new Button("로그아웃");
		
		p.add(accountB);
		p.add(correctionB);
		p.add(logoutB);
		
		correctionB.addActionListener(this);
		accountB.addActionListener(this);
		logoutB.addActionListener(this);
		
		add(p);
		
		setSize(200, 300);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		Button b = (Button)obj;
		
		String ButtonName = b.getActionCommand();
		
		switch(ButtonName) {
			case "회원 정보 변경":
				new MyPage();
				this.setVisible(false);
				break;
			case "새로운 계좌 만들기":
				new AccountButton();
				BankDB bankdb = new BankDB();
				try {
					bankdb.createHistoryTable(Menu.user.getId());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				this.setVisible(false);
				break;
			case "로그아웃":
				Menu.user = null; 
				
				new LoginButton();
				this.setVisible(false);
				PwButton.mb.setVisible(false);
				break;
		}
	}
	
}
