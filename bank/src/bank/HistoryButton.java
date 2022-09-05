package bank;
//결제 내역 페이지
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class HistoryButton extends Frame implements ActionListener{
	JPanel p, p1,p2;
	Label l1, l2, l3, l4;
	
	AccountService account;
	String accountName;
	Border nextBorder;
	Scrollbar bar;
	int n;
	
	List<AccountHistory> accounthistory = null;
	
	public HistoryButton(int accountidx) {
		
		bar=new Scrollbar(Scrollbar.VERTICAL, 0, 50, 0,250);
		
		account = (AccountService)Menu.user.getAccount().get(accountidx);
		accountName = account.getAccountName();
		
		BankDB bankdb = new BankDB();
		
		try {
			accounthistory = bankdb.selectAccountHistoryAll(Menu.user.getId(), accountName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		p = new JPanel(new BorderLayout());
		p1 = new JPanel(new GridLayout(accounthistory.size(),1));
		
		for(int i=0; i < accounthistory.size(); i++) {
			l1 = new Label(accounthistory.get(i).getAccountName(), Label.LEFT);
			l2 = new Label(accounthistory.get(i).getUse_balance(), Label.RIGHT);
			l3 = new Label(accounthistory.get(i).getCre_datetime(), Label.LEFT);
			l4 = new Label("잔액: "+accounthistory.get(i).getBalance(), Label.RIGHT);
			
			JPanel p2= new JPanel(new GridLayout(2,2));
			p2.add(l1);
			p2.add(l2);
			p2.add(l3);
			p2.add(l4);
			
			nextBorder = BorderFactory.createLineBorder(new Color(051, 153, 153));
			p2.setBorder(nextBorder);
			p2.setBackground(new Color(255,255,255));
			p2.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
			
			
			p1.add(p2);
		}
		
		p.add(p1, "Center");
		p.add(new Label(""), "North");
		p.add(new Label(""), "South");
		p.add(new Label(""), "East");
		p.add(new Label(""), "West");
		p.setBackground(new Color(255,255,255));
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
		// TODO Auto-generated method stub
		
	}
	
	
}
