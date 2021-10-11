import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReturnBook extends FormLayout {
    ReturnBook () {
        setSize(400, 300);
        topLabel.setText("Return book");
        topLabel.setFont(new Font("SF Pro", Font.PLAIN, 20));
        topLabel.setForeground(Color.black);


        JLabel issueId = new JLabel("Issue Id:");
        JTextField issueIdField = new JTextField(15);
        compList.add(makeGroup(issueId, issueIdField));

        JPanel btnPane = new JPanel();
        JButton addBtn = new JButton("Add");
        addBtn.setPreferredSize(new Dimension(100, 50));
        
        
        addBtn.addActionListener((java.awt.event.ActionEvent e) -> {
        	int issueid = Integer.parseInt(issueIdField.getText());
        	new IssueBookDBHandler().returnBook(issueid);
        	new PopUpMessage("You might have returned the book! Good job :)");
        });
        
        
        btnPane.add(addBtn);

//        JPanel backPane = new JPanel();
//        JButton back = new JButton("Back");
//        backPane.add(back);

        compList.add(btnPane);
//        compList.add(backPane);
        render();
        setVisible(true);
    }
}
