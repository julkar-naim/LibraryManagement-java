import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AdminLoginForm extends FormLayout {
    AdminLoginForm() {
        setSize(400, 250);
        topLabel.setText("Admin Login Form");
        JLabel enterName = new JLabel("Enter Name:");
        JTextField nameField = new JTextField(15);
        compList.add(makeGroup(enterName, nameField));
        // Event handling will be done later

        JLabel enterPass = new JLabel("Enter Password:");
        JPasswordField passField = new JPasswordField(15);

        compList.add(makeGroup(enterPass, passField));
        // Event handling will be done later

        JPanel btnPane = new JPanel();
        JButton login = new JButton("Login");
        login.setPreferredSize(new Dimension(100, 50));
        
        login.addActionListener((java.awt.event.ActionEvent event) -> {
        	boolean adminExist = new AdminDBHandler().checkAdmin(nameField.getText(), passField.getText());
        	if (adminExist) {
        		dispose();
        		new AdminView();
        	} else {
        		new PopUpMessage("User does not exist or password might be wrong");
        	}
        });
        
        
        btnPane.add(login);

        compList.add(btnPane);
        render();
        setVisible(true);
    }
}
