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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
// 회원가입 페이지
public class UserCreateButton extends Frame implements ActionListener, MouseListener{
	TextArea idTa, pwTa, nameTa, ageTa, addressTa;
	Label idLb, idWnLb, pwLb, pwWnLb, nameLb, nameWnLb, ageLb, ageWnLb, titleLb, lb1, lb2;
	JButton idckBt, nextBt;
	Panel p, p_1,c_p, id_p;
	JLabel logoLabel, loginLabel;
	
	
	Border nextBorder;
	
	public UserCreateButton(){
		p = new Panel(new BorderLayout());
		p_1 = new Panel(new GridLayout(13,1));
		Panel c_p = new Panel(new GridLayout(12,1));
		Panel id_p = new Panel(new BorderLayout());
//		p.setLayout(new GridLayout(17,1));
		
		logoLabel = new JLabel("Lotto bank", JLabel.CENTER);
		logoLabel.setOpaque(true);
		logoLabel.setForeground(new Color(255, 255, 255));
		logoLabel.setBackground(new Color(051, 153, 153));
		
		loginLabel = new JLabel("LOGIN");
		loginLabel.setForeground(new Color(051, 153, 153));
		loginLabel.getFont().deriveFont(100.0f);
		
		idTa = new TextArea("아이디를 입력해주세요.", 1, 40, TextArea.SCROLLBARS_NONE);
		pwTa = new TextArea("비밀번호를 입력해주세요.", 1, 40, TextArea.SCROLLBARS_NONE);
		nameTa = new TextArea("이름을 입력해주세요.", 1, 40, TextArea.SCROLLBARS_NONE);
		ageTa = new TextArea("나이를 입력해주세요.", 1, 40, TextArea.SCROLLBARS_NONE);
		addressTa = new TextArea("주소를 입력해주세요.", 1, 40, TextArea.SCROLLBARS_NONE);
		
		idWnLb = new Label("");
		pwWnLb = new Label("");
		nameWnLb = new Label("");
		ageWnLb = new Label("");
		
		idckBt = new JButton("id 중복확인");
		
		nextBt = new JButton("회원 가입 완료");
		nextBorder = BorderFactory.createLineBorder(new Color(051, 153, 153));
		nextBt.setForeground(new Color(051, 153, 153)); //버튼 글자색 바꾸기
		nextBt.setContentAreaFilled(false);
		nextBt.setFocusPainted(false);
		nextBt.setBorder(nextBorder);
		
		id_p.add(idTa, "Center");
		id_p.add(idckBt, "East");
		
		p_1.add(logoLabel);
		p_1.add(loginLabel);
		p_1.add(id_p);
		p_1.add(idWnLb);
		
		p_1.add(pwTa);
		p_1.add(pwWnLb);
		
		p_1.add(nameTa);
		p_1.add(nameWnLb);
		
		p_1.add(ageTa);
		p_1.add(ageWnLb);
		
		p_1.add(addressTa);
		p_1.add(new Label(""));
		
		p_1.add(nextBt);
		
		
		
		
		p.add(p_1, "Center");
		p.add(new Label(""), "South");
		p.add(new Label(""), "East");
		p.add(new Label(""), "West");
		
		idTa.addMouseListener(this);
		pwTa.addMouseListener(this);
		nameTa.addMouseListener(this);
		ageTa.addMouseListener(this);
		addressTa.addMouseListener(this);

		idckBt.addActionListener(this);
		nextBt.addActionListener(this);
		
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
		
		BankDB bankdb = new BankDB();
		
		
		Object obj = e.getSource();
		
		JButton b = (JButton)obj;
		
		String ButtonName = b.getActionCommand();
		
		String id = idTa.getText().trim();
		String pw = pwTa.getText().trim();
		String name = nameTa.getText().trim();
		String address = addressTa.getText().trim();
		
		String age = ageTa.getText().trim();
		
		String idregExp = "\\w+";
		String pwregExp = "^[a-z]+\\d+";
		String nameregExp = "[가-힣]+";
		String ageregExp = "[0-9]{1,3}";
	
		switch(ButtonName) {
		case "id 중복확인": //db id 값 리스트로 만들기
			
			try {
				List<String> idList = bankdb.selectIdAll();
				if(idList.contains(id)) {
					idWnLb.setText("중복된 아이디 입니다.");
					
				}else if(id.equals("아이디를 입력해주세요.")) {
					idWnLb.setText("아이디를 입력해주세요.");
				}else {
					idWnLb.setText("사용가능한 아이디입니다.");
				}
				break;
			}catch(Exception ex) {
				break;
			}
		
		case "회원 가입 완료":
			if (!Pattern.matches(idregExp, id)) {
				idWnLb.setText("id에는 특수문자를 사용할 수 없으며, 문자와 숫자만을 사용하여야 합니다.");
			}
			else if(!Pattern.matches(pwregExp, pw)) {
				pwWnLb.setText("비밀번호는 소문자와 숫자를 사용하여야 합니다.");
			}
			else if(!Pattern.matches(nameregExp, name)) {
				nameWnLb.setText("한글 이름만 허용합니다."); 
			}
			else if(!Pattern.matches(ageregExp, age)) {
				ageWnLb.setText("나이는 숫자만 입력 가능합니다.");
			}
			else if(idWnLb.getText().equals("사용가능한 아이디입니다.")){
				
				int ageInt = Integer.parseInt(ageTa.getText().trim());
				Menu.useres.add(new User(id, pw, name, ageInt));
				
				
				try {
					BankDB dbex51 =new BankDB();
					dbex51.insertUser(id, pw, name, ageInt, address);
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
				
				
				this.setVisible(false);
			}
			
			
			break;
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj == idTa) {
			if(idTa.getText().equals("아이디를 입력해주세요.")) {
				idTa.setText("");
			}
		}else if(obj == pwTa) {
			if(pwTa.getText().equals("비밀번호를 입력해주세요.")) {
				pwTa.setText("");
			}
		}else if(obj == nameTa) {
			if(nameTa.getText().equals("이름을 입력해주세요.")) {
				nameTa.setText("");
			}
		}else if(obj == ageTa) {
			if(ageTa.getText().equals("나이를 입력해주세요.")) {
				ageTa.setText("");
			}
		}else if(obj == addressTa) {
			new AddressPage();
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
