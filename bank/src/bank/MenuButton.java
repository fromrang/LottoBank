package bank;
//메뉴 페이지
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MenuButton extends Frame implements ActionListener, ItemListener{

	Panel p, iP, p1, p1_1, p2_2, p2_3, p2_4;
	JPanel p2, p2_1,p3_1, p3;
	JLabel logoLabel;
	JLabel nameLabel, balanceLabel;
	Label accountNLabel,  l;
	JButton transferB, historyB, depositB, withdrawB, createAccount1,createAccount2, promotion, c_b, newuserB, newuserB2;
	Choice accountC;
	Border nextBorder;
	
	public MenuButton() {
		
		ImageIcon newuserimage = new ImageIcon(getClass().getResource("newuser1.png"));
		Image img = newuserimage.getImage();
		Image changeimg = img.getScaledInstance(180, 140, Image.SCALE_SMOOTH);
		ImageIcon changeicon = new ImageIcon(changeimg);
		
		ImageIcon newuserimage2 = new ImageIcon(getClass().getResource("newuser2.png"));
		Image img2 = newuserimage2.getImage();
		Image changeimg2 = img2.getScaledInstance(180, 140, Image.SCALE_SMOOTH);
		ImageIcon changeicon2 = new ImageIcon(changeimg2);
		
		ImageIcon eventimage = new ImageIcon(getClass().getResource("event.png"));
		Image img3 = eventimage.getImage();
		Image changeimg3 = img3.getScaledInstance(380, 150, Image.SCALE_SMOOTH);
		ImageIcon changeicon3 = new ImageIcon(changeimg3);
		
		ImageIcon depositimage = new ImageIcon(getClass().getResource("deposit.png"));
		Image img4 = depositimage.getImage();
		Image changeimg4 = img4.getScaledInstance(380, 150, Image.SCALE_SMOOTH);
		ImageIcon changeicon4 = new ImageIcon(changeimg4);
		
		ImageIcon withdrawimage = new ImageIcon(getClass().getResource("withdraw.png"));
		Image img5 = withdrawimage.getImage();
		Image changeimg5 = img5.getScaledInstance(380, 150, Image.SCALE_SMOOTH);
		ImageIcon changeicon5 = new ImageIcon(changeimg5);
		
		JLabel eventL = new JLabel(changeicon3);
		
		
		
		
		p = new Panel(new BorderLayout());
		iP = new Panel(new GridLayout(4,1));
		p1 = new Panel(new GridLayout(2, 1));
		p2 = new JPanel(new BorderLayout());
		p2_1 = new JPanel(new GridLayout(3,1));
		p3 = new JPanel(new GridLayout(3, 1));
		p3_1 = new JPanel(new GridLayout(1, 4));
		p1_1 = new Panel(new BorderLayout());
		
		p2_2 = new Panel(new GridLayout(1, 2));
		p2_3 = new Panel(new GridLayout(1, 2));
		p2_4 = new Panel(new GridLayout(1, 2));
		
		logoLabel = new JLabel("Lotto bank", JLabel.CENTER);
		logoLabel.setOpaque(true);
		logoLabel.setForeground(new Color(255, 255, 255));
		logoLabel.setBackground(new Color(051, 153, 153));
		
		c_b = new JButton("!");
		c_b.setForeground(new Color(255, 255, 255));//버튼 글자색 바꾸기
		c_b.setBackground(new Color(051, 153, 153)); 
		c_b.setBorderPainted(false); //외곽선 없애기
		c_b.setFocusPainted(false);
		
		nameLabel = new JLabel(Menu.user.getName() + "님 반갑습니다.");
		nameLabel.getFont().deriveFont(100.0f);
		
		balanceLabel = new JLabel("", JLabel.RIGHT);
		balanceLabel.getFont().deriveFont(100.0f);
		l = new Label("");
		
		transferB = new JButton("계좌 이체");
		historyB = new JButton("결제 내역");
		depositB = new JButton("입금");
		withdrawB = new JButton("출금");
		createAccount1 = new JButton("계좌 생성");
		createAccount2 = new JButton("계좌 만들러 가기");
		promotion = new JButton("프로모션");
		
		p1_1.add(logoLabel, "Center");
		p1_1.add(c_b, "East");
		
		p1.add(p1_1);		
		p1.add(nameLabel);
		
		
		
		p2_2.add(transferB);
		p2_2.add(historyB);
		
		p3_1.add(depositB);
		p3_1.add(withdrawB);
		p3_1.add(createAccount1);
		p3_1.add(promotion);
		
		p3.add(new Label(""));
		p3.add(p3_1);
		p3.add(new Label(""));
		p3.setBackground(new Color(255,255,255));
		
		
				
		if(Menu.user.getAccount().isEmpty()){
			
			eventL = new JLabel(changeicon3);
			
			p2 = new JPanel(new BorderLayout());
			
			nextBorder = BorderFactory.createLineBorder(new Color(051, 153, 153));
			p2.setBorder(nextBorder);
			p2.setBackground(new Color(255,255,255));
			
			p2_1 = new JPanel(new GridLayout(4, 1));
			p2_1.setBackground(new Color(255,255,255));
			
			p2_1.add(new Label(""));
			p2_1.add(new Label("등록된 계좌가 없습니다.", Label.CENTER));
			p2_1.add(createAccount2);
			p2_1.add(new Label(""));
			
			p2.add(p2_1, "Center");
			p2.add(new Label(""), "North");
			p2.add(new Label(""), "South");
			p2.add(new Label(""), "East");
			p2.add(new Label(""), "West");
			
//			p3 = new Panel(new GridLayout(2, 1));
			p3 = new JPanel(new GridLayout(1,2));
			p3.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
			p3.setBackground(new Color(255,255,255));
			
			newuserB = new JButton("", changeicon);
			newuserB.setBackground(new Color(255, 255, 255)); 
			newuserB.setBorderPainted(false); //외곽선 없애기
//			newuserL.setFocusPainted(false);
			
			newuserB2 = new JButton("", changeicon2);
			newuserB2.setBackground(new Color(255, 255, 255)); 
			newuserB2.setBorderPainted(false); //외곽선 없애기
			
			
			p3.add(newuserB);
			p3.add(newuserB2);
			
		}else {
			
			BankDB bankdb = new BankDB();
			
			try {
				String result = bankdb.recommanditem(Menu.user.getId());
				System.out.println("--" + result);
				switch(result) {
				case "new":
					eventL = new JLabel(changeicon3);
					break;
				case "입금":
					eventL = new JLabel(changeicon4);
					break;
				case "출금":
					eventL = new JLabel(changeicon5);
					break;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			accountC = new Choice();
			
			for(int i =0; i<Menu.user.getAccount().size() ; i++ ) {
				
				String accountName = ((AccountService)Menu.user.getAccount().get(i)).getAccountName();
				int balance = ((AccountService)Menu.user.getAccount().get(i)).getBalance();
				
				accountC.add(accountName);
				balanceLabel = new JLabel( balance + "원", JLabel.RIGHT);
				
			}
			p2_1.add(accountC); // 드롭다운 만들기
			p2_1.add(balanceLabel);
			p2_1.add(p2_2);
			p2_1.setBackground(new Color(255,255,255));
			
			p2.add(p2_1, "Center");
			p2.add(new Label(""), "North");
			p2.add(new Label(""), "East");
			p2.add(new Label(""), "West");
			p2.add(new Label(""), "South");
			
			nextBorder = BorderFactory.createLineBorder(new Color(051, 153, 153));
			p2.setBorder(nextBorder);
			p2.setBackground(new Color(255,255,255));
			accountC.addItemListener(this);
		}
		
		iP.add(p1);
		iP.add(p2);
		iP.add(p3);
		iP.add(eventL); //new 값에 따라 image 가지고 오기
		
		p.add(iP, "Center");
		p.add(new Label(""), "South");
		p.add(new Label(""), "East");
		p.add(new Label(""), "West");
		
		createAccount1.addActionListener(this);
		createAccount2.addActionListener(this);
		depositB.addActionListener(this);
		withdrawB.addActionListener(this);
		transferB.addActionListener(this);
		historyB.addActionListener(this);
		c_b.addActionListener(this);

		add(p);
		
		setSize(400, 700);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(!(accountC == null)) {
			accountC.addItemListener(this);
		}
		
		if(obj instanceof JButton) {
			JButton b = (JButton)obj;
			
			String ButtonName = b.getActionCommand();
			
			switch(ButtonName){
				case "계좌 생성":
					new AccountButton();
					//결제 내역 history table 만들어 주기
					try{
						BankDB bankdb = new BankDB();
						bankdb.createHistoryTable(Menu.user.getId());
					}
					catch(Exception ex) {
						ex.printStackTrace();
					}
					
					break;
				case "계좌 이체":
					if(accountC == null) {
						//계좌 생성 후 서비스 이용 가능
					}else {
						new TransferButton(accountC.getSelectedIndex());
					}
					break;
				
				case "결제 내역": 
					if(accountC == null) {
					//계좌 생성 후 서비스 이용 가능
					}else {
						new HistoryButton(accountC.getSelectedIndex());
					}
					break;
				case "입금": 
					if(accountC == null) {
						//계좌 생성 후 서비스 이용 가능
					}else {
						new DepositButton(accountC.getSelectedIndex());
					}
					break;
				case "출금":
					if(accountC == null) {
						//계좌 생성 후 서비스 이용 가능
					}else {
						new WithdrawButton(accountC.getSelectedIndex());
					}
					break;
				case "!":
					new AddPage();
					break;
				case "계좌 만들러 가기":
					new AccountButton();
					//결제 내역 history table 만들어 주기
					try{
						BankDB bankdb = new BankDB();
						bankdb.createHistoryTable(Menu.user.getId());
					}
					catch(Exception ex) {
						ex.printStackTrace();
					}
					break;
					
					
						
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object obj = e.getSource();
		if (obj instanceof Choice) {
			Choice action_cho = (Choice)obj;
			for(int i=0; i<action_cho.getItemCount(); i++) {
				if(action_cho.getSelectedIndex() == i) {
					int balance = ((AccountService)Menu.user.getAccount().get(i)).getBalance();
					balanceLabel.setText(balance + "원");
				}
			}
		}
	}

}
