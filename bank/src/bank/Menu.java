package bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
	static Menu menu;
	static List<User> useres = new ArrayList<>();
	static List<String> accounts = new ArrayList<>();
	static User user ;

	
	public Menu () {}

	public static void main(String[] args) {
		new LoginButton();
	}

}
