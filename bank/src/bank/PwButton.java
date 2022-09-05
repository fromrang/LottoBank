package bank;
//비밀번호 입력창
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class PwButton extends Frame implements ActionListener, MouseListener{
	static MenuButton mb;
	Panel p, p1, p2;
	JLabel imageL, startLabel, pwWnLb, l2; 
	TextArea pwField;
	JButton next, pw_b;
	Border nextBorder;
	String id;
	
	
	public PwButton(String id) {
		
		this.id = id;
		
		p = new Panel(new GridLayout(2,1));
		p1 = new Panel(new GridLayout(7,1));
		p2 = new Panel(new BorderLayout());
		
		ImageIcon image = new ImageIcon(getClass().getResource("titleimage.png"));
		imageL = new JLabel("", image, JLabel.CENTER);
		
		l2 = new JLabel(""); //비밀번호가 일치하지 않습니다.
		
		startLabel = new JLabel("LOGIN");
		startLabel.setForeground(new Color(051, 153, 153));
		startLabel.getFont().deriveFont(100.0f);
		
		pwWnLb = new JLabel("");
		
		pwField = new TextArea("비밀번호를 입력해주세요:)", 1, 40, TextArea.SCROLLBARS_NONE);
		
		next = new JButton("시작하기");
		nextBorder = BorderFactory.createLineBorder(new Color(051, 153, 153));
		next.setForeground(new Color(051, 153, 153)); //버튼 글자색 바꾸기
		next.setContentAreaFilled(false);
		next.setFocusPainted(false);
		next.setBorder(nextBorder);
		
		pw_b = new JButton("비밀번호를 잊어버리셨나요?");
		pw_b.setForeground(new Color(051, 153, 153));//버튼 글자색 바꾸기
		pw_b.setContentAreaFilled(false); // 내용 영역 채우기 안함
		pw_b.setBorderPainted(false); //외곽선 없애기
		pw_b.setFocusPainted(false); // 선택되었을때 생기는 테두리 사용 안함
		pw_b.setHorizontalAlignment(SwingConstants.LEFT);
		
		p1.add(new Label(""));
		p1.add(startLabel);
		p1.add(pwField);
		p1.add(next);
		p1.add(pw_b);
		p1.add(l2);
		p1.add(new Label(""));
		
		p2.add(p1, "Center");
		p2.add(new Label(""), "North");
		p2.add(new Label(""), "South");
		p2.add(new Label(""), "East");
		p2.add(new Label(""), "West");
		
		p.add(imageL);
		p.add(p2);
		
		
		pwField.addMouseListener(this);
		next.addActionListener(this);
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
		JButton next = (JButton)obj;
		
		if(next == this.next) {
			try {
				login();
			}catch(Exception ex){}
		}
	}
	public void login() throws Exception{ 
		
		BankDB bankdb = new BankDB();
		
		HashMap<String, String> idPwMap = bankdb.selectIdPw(this.id);
		
		String usr_pw = idPwMap.get(this.id);
		
		String in_pw = pwField.getText();
		
		//로그인 후 db에 있는 계좌 정보 menu.user.account 리스트에 객체 add
		if(in_pw.equals(usr_pw)) {
			
			Menu.user = bankdb.selectUser(this.id); 
			Menu.user.setAccount(bankdb.selectAccountAll(this.id));
			
			mb = new MenuButton();
			this.setVisible(false);
			
		}else {
			l2.setText("비밀번호가 일치하지 않습니다.");
			
		}
		
					
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object obj = e.getSource();
		if(obj == pwField) {
			if(pwField.getText().equals("비밀번호를 입력해주세요:)")) {
				pwField.setText("");
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
