package game;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Language {
	private String chosenLanguage;
	
	public Language(){
		
	}
	
	public String chooseLanguage(){
		JFrame frame = new JFrame();
		Object[] languageOptions = {"Dansk", "Engelsk"};
		
		int question = JOptionPane.showOptionDialog(frame,
			    "Skal spillet startes på dansk eller engelsk??",
			    "Sprog indstillinger",
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    languageOptions,
			    languageOptions[1]);
		
		
		if(question == 0){
			chosenLanguage = (String) languageOptions[0];
		} else if(question == 1){
			chosenLanguage = (String) languageOptions[1];
		}
		
		return(chosenLanguage);
	}
	
	public String[] loadLanguageFile(){
		String[] line = null;
		try{
			File file = null;
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			DataInputStream dis = null;
			
			if(chosenLanguage.equals("Dansk")){
				file = new File("DA.txt");
			} else if(chosenLanguage.equals("Engelsk")){
				file = new File("EN.txt");
			}
			
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			
			String lines;
			
			lines = dis.readLine();
			
			line =  lines.split(":");
			
			fis.close();
			bis.close();
			dis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	public void setLanguage(String a) {
		chosenLanguage = a;
	}
	public String getLanguage() {
		return chosenLanguage;
	}
}

