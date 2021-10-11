import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddLibrarian extends FormLayout {
    AddLibrarian () {
        setSize(400, 500);
        topLabel.setText("Librarian Login Form");
        topLabel.setFont(new Font("SF Pro", Font.PLAIN, 20));
        topLabel.setForeground(Color.black);

        JLabel enterName = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        compList.add(makeGroup(enterName, nameField));
        // Event handling will be done later

        JLabel enterPass = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);


        JLabel email = new JLabel("Email:");
        JTextField emailField = new JTextField(15);
        compList.add(makeGroup(email, emailField));

        JLabel address = new JLabel("Address:");
        JTextField addressField = new JTextField(15);
        compList.add(makeGroup(address, addressField));

        JLabel city = new JLabel("City:");
        JTextField cityField = new JTextField(15);
        compList.add(makeGroup(city, cityField));

        JLabel contactNo = new JLabel("Contact No");
        JTextField contactNoField = new JTextField(15);
        compList.add(makeGroup(contactNo, contactNoField));

        compList.add(makeGroup(enterPass, passField));
        // Event handling will be done later

        JPanel btnPane = new JPanel();
        JButton login = new JButton("Login");
        login.setPreferredSize(new Dimension(100, 50));
        
        
        login.addActionListener((java.awt.event.ActionEvent e) -> {
        	ArrayList<String> check = new ArrayList<>();
        	check.add(nameField.getText());
        	check.add(emailField.getText());
        	check.add(addressField.getText());
        	check.add(cityField.getText());
        	check.add(contactNoField.getText());
        	check.add(passField.getText());

        	boolean notEmpty = true;

        	for (String str: check) {
        		notEmpty &= !str.isEmpty();
        	}
        	if (notEmpty) {
        		LibrarianDBHandler handler = new LibrarianDBHandler();
        		
        		boolean hasName = handler.checkUserName(nameField.getText());

        		if (hasName) {
        			new PopUpMessage("Librarian name already exist");
        			return;
        		}
        		
        		handler.createLibrarian(nameField.getText(), passField.getText(), 
        				emailField.getText(), addressField.getText(), cityField.getText(), contactNo.getText());
        		new PopUpMessage("Librarin creation successfull");
        	} else {
        		new PopUpMessage("Please fill all the field");
        	}
        });
        
        
        
        btnPane.add(login);

//        JPanel backPane = new JPanel();
//        JButton back = new JButton("Back");
//        backPane.add(back);

        compList.add(btnPane);
//        compList.add(backPane);
        render();
        setVisible(true);
    }
}
