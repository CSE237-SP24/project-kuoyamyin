package bankapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileObj {
	
	private ArrayList<String> fileContent;
	private Scanner fileOutput;
	private File file;
	

	public FileObj(String fileName) {
		file = new File(fileName);
		try {
			this.fileOutput = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(fileName + " scanner not created");
			e.printStackTrace();
		}
		while(fileOutput.hasNextLine()) {
			this.fileContent.add(fileOutput.nextLine());
		}
	}
	
	public ArrayList<String> getContent(){
		return this.fileContent;
	}
	
	public void setContent(ArrayList<String> content){
		try {
			PrintWriter fileWriter = new PrintWriter(file);
			for(int i = 0; i < content.size(); i++) {
				fileWriter.println(content.get(i));
			}
			fileWriter.close();
		} catch (FileNotFoundException e){
			System.out.println("File not updated");
			e.printStackTrace();
		} 
	}


}
