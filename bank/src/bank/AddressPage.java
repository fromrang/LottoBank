package bank;
//주소 페이지
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class AddressPage extends Frame implements ActionListener, ItemListener {
	
	JPanel p, ip, p1, p1_1, p1_2, p1_3, p1_4, p1_5, p2, p3, p3_1, p3_2;
	Label sidoL, gugunL, dongL;
	Choice sidoC, gugunC, dongC;
	Button searchB, nextB;
	String sido, gugun, dong;
	TextArea bldgT, allAddressT, abT1, abT2;
	Border nextBorder;
	java.awt.List allAddressLs = new java.awt.List();
	
	String address1, address2;
	
	public AddressPage() {
		p = new JPanel(new BorderLayout());
		ip = new JPanel(new GridLayout(3,1));
		
		p1 = new JPanel(new BorderLayout());
		p1_1 = new JPanel(new GridLayout(2, 1));
		p1_2 = new JPanel(new GridLayout(1, 6));
		p1_4 = new JPanel(new GridLayout(2,1));
		p1_3 = new JPanel(new BorderLayout());
		p1_5 = new JPanel(new GridLayout(2,1));
		
		p2 = new JPanel(new BorderLayout());
		p3 =  new JPanel(new BorderLayout());
		p3_1 = new JPanel(new GridLayout(6, 1));
		p3_2 = new JPanel(new GridLayout(1, 3));

		searchB = new Button("검색");
		nextB = new Button("주소 입력");
		
		sidoL = new Label("시도", Label.CENTER);
		gugunL = new Label("군구", Label.CENTER);
		dongL = new Label("동", Label.CENTER);
		
		sidoC = new Choice();
		gugunC = new Choice();
		dongC = new Choice();
		
		bldgT = new TextArea("검색해주세요", 1, 40, TextArea.SCROLLBARS_NONE);
		abT2 = new TextArea("", 1, 40, TextArea.SCROLLBARS_NONE);
		abT1 = new TextArea("", 1, 40, TextArea.SCROLLBARS_NONE); 
		
		BankDB bankdb = new BankDB();
		
		try {
			List<String> sidoList = bankdb.selectSidoAll();
			
			for(String sido: sidoList) {
				sidoC.add(sido);
			}
	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		p1_2.add(sidoL);
		p1_2.add(sidoC);
		p1_2.add(gugunL);
		p1_2.add(gugunC);
		p1_2.add(dongL);
		p1_2.add(dongC);
		
		p1_4.add(p1_2);
		p1_4.add(bldgT);
		
		p1_3.add(p1_4, "Center");
		p1_3.add(searchB, "East");
		
		
//		p1_3.add(new Label("찾으시는 시도와 군구를 선택 후 주소를 입력해주세요."));
//		p1_3.add(new Label(""));
		
		p1_5.add(new Label("찾으시는 시도와 군구를 선택 후 주소를 입력해주세요."));
		p1_5.add(new Label(""));
		
		p1_1.add(p1_3);
		p1_1.add(p1_5);
		

		p1.add(p1_1, "Center");
		p1.add(new Label(""), "East");
		p1.add(new Label(""), "West");
		p1.add(new Label(""), "North");
		p1.add(new Label(""), "South");
		
		nextBorder = BorderFactory.createLineBorder(new Color(051, 153, 153));
		p1.setBorder(nextBorder);
		p1.setBackground(new Color(255,255,255));
		
		p2.add(allAddressLs, "Center");
		p2.add(new Label("주소를 클릭하시면 자동으로 입력됩니다."), "North");
		p2.add(new Label(""), "East");
		p2.add(new Label(""), "West");
		p2.add(new Label(""), "South");
		
		p3_2.add(new Label(""));
		p3_2.add(nextB);
		p3_2.add(new Label(""));
		
		p3_1.add(new Label("기본주소"));
		p3_1.add(abT1);
		p3_1.add(new Label("상세주소를 입력해주세요."));
		p3_1.add(abT2);
		p3_1.add(new Label(""));
		p3_1.add(p3_2);
		
		p3.add(p3_1, "Center");
		
		ip.add(p1);
		ip.add(p2);
		ip.add(p3);
		
		p.add(ip, "Center");
		p.add(new Label(""), "East");
		p.add(new Label(""), "West");
		p.add(new Label(""), "North");
		p.add(new Label(""), "South");
		
		sidoC.addItemListener(this);
		gugunC.addItemListener(this);
		dongC.addItemListener(this);
		
		searchB.addActionListener(this);
		nextB.addActionListener(this);
		allAddressLs.addItemListener(this);
		
		
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
		String search = null;
		
		if (obj == searchB) {
			
			search = bldgT.getText();
			if(search.equals("")) {
				JOptionPane.showMessageDialog(this, "주소를 입력해주세요", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			try {
				List<String> allAddressList = bankdb.selectAllFindByAddress(sido, gugun, dong, search);
				allAddressLs.removeAll();
				for(String allAddress: allAddressList) {
					
					allAddressLs.add(allAddress);
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}else if(obj == nextB){
			address2 = abT2.getText();
			LoginButton.ub.addressTa.setText(address1 + " " + address2);
			this.setVisible(false);
		}
		
		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		
		BankDB bankdb = new BankDB();
		Object obj = e.getSource();
		
		
		
		
		if (obj instanceof Choice) {
			Choice action_cho = (Choice)obj;
			
			if(action_cho == sidoC ) {	
				sido = action_cho.getSelectedItem();
				
			
				try {
					List<String> gugunList = bankdb.selectGugunAll(sido);
					gugunC.removeAll();
					for(String gugun: gugunList) {
						
						gugunC.add(gugun);
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}

			}else if(action_cho == gugunC) {
				gugun = action_cho.getSelectedItem();
				
				try {
					List<String> dongList = bankdb.selectDongAll(sido, gugun);
					dongC.removeAll();
					for(String dong: dongList) {
						dongC.add(dong);
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}else if(action_cho == dongC) {
				dong = action_cho.getSelectedItem();
				try {
					List<String> allAddressList = bankdb.selectAllFindByAddress(sido, gugun, dong);
					allAddressLs.removeAll();
					for(String allAddress: allAddressList) {
						
						allAddressLs.add(allAddress);
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}	
				

			
			
		}else if(obj instanceof java.awt.List) {
			java.awt.List action_cho = (java.awt.List)obj;
			address1 = action_cho.getSelectedItem();
			abT1.setText(address1);

		}
		
	}

}
