package bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileInfo{
	
	File file;
	FileWriter filewriter;
	BufferedWriter bw;
	PrintWriter pw;
	
	public FileInfo() throws Exception {
	
	file = new File("D:/work/historytest.txt");
	filewriter = new FileWriter(file, true);
	bw = new BufferedWriter(filewriter);
	pw = new PrintWriter(bw, true);
	
	
	}
	
	public void writeFile(String PaymentHistory) throws IOException {
		
		pw.println(PaymentHistory);
		pw.close();
		
	}

}
