import javax.swing.*;
import java.awt.*;

public class LibrarianLoginForm extends FormLayout {
    LibrarianLoginForm() {
        setSize(400, 250);
        topLabel.setText("Librarian Login Form");
        topLabel.setFont(new Font("SF Pro", Font.PLAIN, 18));
        topLabel.setForeground(Color.black);
        JLabel enterName = new JLabel("Enter Name:");
        JTextField nameField = new JTextField(15);
        compList.add(makeGroup(enterName, nameField));

        JLabel enterPass = new JLabel("Enter Password:");
        JPasswordField passField = new JPasswordField(15);

        compList.add(makeGroup(enterPass, passField));

        JPanel btnPane = new JPanel();
        JButton login = new JButton("Login");
        login.setPreferredSize(new Dimension(100, 50));
        
        
        login.addActionListener((java.awt.event.ActionEvent event) -> {
        	boolean check = new LibrarianDBHandler().checkUser(nameField.getText(), passField.getText());
        	if (!check) {
        		new PopUpMessage("Invalid Credentials!");
        	}
        	else {
        		dispose();
        		new LibrarianView();
        	}
        });
        
        btnPane.add(login);

        compList.add(btnPane);
        render();
        setVisible(true);
    }
}
