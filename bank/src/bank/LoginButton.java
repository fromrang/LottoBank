package bank;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
//로그인페이지
public class LoginButton extends Frame implements ActionListener, MouseListener{
	static UserCreateButton ub;
	TextArea idField;
	TextArea pwField;
	Button nextB;
	Panel p, p2, p3, p4;
	JLabel l, loginLabel;
	JButton b, c_b, id_b;
	Label l2;
	Border nextBorder;
	public LoginButton() {
		
		p = new Panel(new GridLayout(2,1));
		p2 = new Panel(new GridLayout(1,2));
		p4 = new Panel(new BorderLayout());
		p3 = new Panel(new GridLayout(8,1));
		
		ImageIcon image = new ImageIcon(getClass().getResource("titleimage.png"));

		
		idField = new TextArea("아이디를 입력해주세요:)", 1, 40, TextArea.SCROLLBARS_NONE);
		l = new JLabel("", image, JLabel.CENTER);
		l2 = new Label("");
		loginLabel = new JLabel("LOGIN");
		loginLabel.setForeground(new Color(051, 153, 153));
		loginLabel.getFont().deriveFont(100.0f);
		
		b = new JButton("계속하기");
		nextBorder = BorderFactory.createLineBorder(new Color(051, 153, 153));
		b.setForeground(new Color(051, 153, 153)); //버튼 글자색 바꾸기
		b.setContentAreaFilled(false);
		b.setFocusPainted(false);
		b.setBorder(nextBorder);
		
		c_b = new JButton("회원 가입");
		c_b.setForeground(new Color(255, 255, 255));//버튼 글자색 바꾸기
		c_b.setBackground(new Color(051, 153, 153)); 
		c_b.setBorderPainted(false); //외곽선 없애기
		c_b.setFocusPainted(false); // 선택되었을때 생기는 테두리 사용 안함
		
		id_b = new JButton("아이디를 잊어버리셨나요?");
		id_b.setForeground(new Color(051, 153, 153));//버튼 글자색 바꾸기
		id_b.setContentAreaFilled(false); // 내용 영역 채우기 안함
		id_b.setBorderPainted(false); //외곽선 없애기
		id_b.setFocusPainted(false); // 선택되었을때 생기는 테두리 사용 안함
		id_b.setHorizontalAlignment(SwingConstants.LEFT);
		
		p2.add(c_b);
		p2.add(b);
		
		p3.add(new Label(""));
		p3.add(loginLabel);
		p3.add(idField);
		p3.add(b);
		p3.add(id_b);//아이디를 잊어버리셨나요?
		p3.add(l2);
		p3.add(new Label(""));
		p3.add(c_b);
		
		p4.add(p3, "Center");
		p4.add(new Label(""), "North");
		p4.add(new Label(""), "South");
		p4.add(new Label(""), "East");
		p4.add(new Label(""), "West");

		p.add(l);
		p.add(p4);
//		p.add(p2);
//		p.add(l2);
		add(p);
		
		b.addActionListener(this);
		c_b.addActionListener(this);
		idField.addMouseListener(this);
		
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
		
		if(next == b) {
			try {
				login();
			}catch(Exception ex) {}
			
		}
		else if(next==c_b) {
			ub = new UserCreateButton();
		}
	}
	
	public void login() throws Exception{ 
		
		BankDB bankdb = new BankDB();
		List<String> idList = bankdb.selectIdAll();
		
		if (idList.size() == 0) {
			l2.setText("회원정보가 없습니다. 회원가입후 이용해주세요");
		}

		String in_id = idField.getText();

		if(idList.contains(in_id)) {
			new PwButton(in_id);
			this.setVisible(false);
			
		}else {
			l2.setText("회원정보가 없습니다. 회원가입후 이용해주세요");
		}
			
					
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object obj = e.getSource();
		if(obj == idField) {
			if(idField.getText().equals("아이디를 입력해주세요:)")) {
				idField.setText("");
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
