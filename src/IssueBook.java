import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class IssueBook extends FormLayout {
    IssueBook () {
        setSize(400, 500);
        topLabel.setText("Issue book");
        topLabel.setFont(new Font("SF Pro", Font.PLAIN, 20));
        topLabel.setForeground(Color.black);

        JLabel bookId = new JLabel("Book Id:");
        JTextField bookIdField = new JTextField(15);
        compList.add(makeGroup(bookId, bookIdField));


        JLabel name = new JLabel("Student Name:");
        JTextField  nameField = new JTextField(15);
        compList.add(makeGroup(name, nameField));


        JLabel studentId = new JLabel("Student ID:");
        JTextField studentIdField = new JTextField(15);
        compList.add(makeGroup(studentId, studentIdField));

        JLabel contact = new JLabel("Contact No:");
        JTextField contactField = new JTextField(15);
        compList.add(makeGroup(contact, contactField));

        JLabel quantity = new JLabel("Quantity:");
        JTextField quantityField = new JTextField(15);
        compList.add(makeGroup(quantity, quantityField));


        JPanel btnPane = new JPanel();
        JButton addBtn = new JButton("Create issue");
        addBtn.setPreferredSize(new Dimension(100, 50));
        
        
        addBtn.addActionListener((java.awt.event.ActionEvent e) -> {
        	int bookid = Integer.parseInt(bookIdField.getText());
        	int bookquantity = Integer.parseInt(quantityField.getText());
        	int bookCount = new BookDBHandler().bookQuantity(bookid);
        	
        	if (bookquantity > bookCount) {
        		new PopUpMessage("Can not chose more than " + bookCount + " books");
        		return;
        	}
        	IssueBookDBHandler handler = new IssueBookDBHandler();
        	handler.createIssue(bookid, Integer.parseInt(studentIdField.getText()), 
        			nameField.getText(), contactField.getText(), bookquantity);
        	new PopUpMessage("Issue has been created");
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
