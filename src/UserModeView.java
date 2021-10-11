import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserModeView extends CenteredLayout {
    UserModeView () {
        topLabel.setText("Library Management");
        topLabel.setFont(new Font("SF Pro", Font.PLAIN, 22));
        topLabel.setForeground(Color.gray);

        topInsets = new Insets(40, 0, 0, 0);

        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton adminBtn = new JButton("Admin Login");
        adminBtn.setPreferredSize(new Dimension(150, 55));
        
        adminBtn.addActionListener((ActionEvent event) -> {
        	dispose();
        	new AdminLoginForm();
        });

        JButton lBtn = new JButton("Librarian Login");
        lBtn.setPreferredSize(new Dimension(150, 55));
        
        lBtn.addActionListener((ActionEvent event) -> {
        	dispose();
        	new LibrarianLoginForm();
        });


        compList.add(adminBtn);
        compList.add(lBtn);

        render();
        setVisible(true);
    }
}
