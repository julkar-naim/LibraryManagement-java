import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddBook extends FormLayout {
    AddBook () {
        setSize(400, 500);
        topLabel.setText("Add book");
        topLabel.setFont(new Font("SF Pro", Font.PLAIN, 20));
        topLabel.setForeground(Color.black);

        JLabel colNo = new JLabel("Call No:");
        JTextField colNoField = new JTextField(15);
        compList.add(makeGroup(colNo, colNoField));


        JLabel name = new JLabel("Name:");
        JTextField  nameField = new JTextField(15);
        compList.add(makeGroup(name, nameField));


        JLabel author = new JLabel("Author:");
        JTextField authorField = new JTextField(15);
        compList.add(makeGroup(author, authorField));

        JLabel publisher = new JLabel("Publisher:");
        JTextField publisherField = new JTextField(15);
        compList.add(makeGroup(publisher, publisherField));

        JLabel quantity = new JLabel("Quantity:");
        JTextField quantityField = new JTextField(15);
        compList.add(makeGroup(quantity, quantityField));

        JPanel btnPane = new JPanel();
        JButton addBtn = new JButton("Add");
        addBtn.setPreferredSize(new Dimension(100, 50));
        
        
        addBtn.addActionListener((java.awt.event.ActionEvent e) -> {
        	BookDBHandler handler = new BookDBHandler();
        	handler.addBook(colNoField.getText(), 
        			nameField.getText(), 
        			authorField.getText(),
        			publisherField.getText(),
        			Integer.parseInt(quantityField.getText())
        		);
        	new PopUpMessage("Book has been added!");
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
