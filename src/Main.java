import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.Date;


public class Main {
    public static void main (String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new UserModeView();
			}
    		
    	});
    	successLogger();
    }
    
    
    
	static void successLogger () {
		System.out.println("Program compiled successfully!");
	}
}
















