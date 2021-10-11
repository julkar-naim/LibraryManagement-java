import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LibrarianView extends CenteredLayout {
    LibrarianView () {
        topLabel.setText("Librarian Section");
        topLabel.setFont(new Font("SF Pro", Font.PLAIN, 25));
        topLabel.setForeground(Color.black);
        Dimension btnDimension = new Dimension(170, 45);

        topInsets = new Insets(40, 0, 0, 0);

        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton addBooks = new JButton("Add Books");
        addBooks.setPreferredSize(btnDimension);
        
        addBooks.addActionListener((ActionEvent e) -> {
        	new AddBook();
        });

        
        compList.add(addBooks);

        JButton viewBooks = new JButton("View Books");
        viewBooks.setPreferredSize(btnDimension);
        
        
        viewBooks.addActionListener((ActionEvent e) -> {
        	String[] headers = {"ID", "Coll No", "Name", "Author", "Publisher", "Quantity", "Issued", "Add date"};
        	DataTable table = new DataTable(new BookDBHandler().getBook(), headers);
        	table.render();
        });
        
        compList.add(viewBooks);

        JButton issueBooks = new JButton("Issue Books");
        issueBooks.setPreferredSize(btnDimension);
        
        issueBooks.addActionListener((ActionEvent e) -> {
        	new IssueBook();
        });
        
        
        compList.add(issueBooks);

        JButton viewIssuedBooks = new JButton("View Issued Books");
        viewIssuedBooks.setPreferredSize(btnDimension);
        
        
        viewIssuedBooks.addActionListener((ActionEvent e) -> {
        	String[] headers = {"Issue ID", "Book Id", "Student Id", "Student Name", "Student Contact", "Quantity", "Issue Date", "Return date"};
        	DataTable table = new DataTable(new IssueBookDBHandler().getIssues(), headers);
        	table.setSize(1000, 300);
        	table.render();
        });
        
        
        compList.add(viewIssuedBooks);

        JButton returnBooks = new JButton("Return Books");
        returnBooks.setPreferredSize(btnDimension);
        
        
        returnBooks.addActionListener((ActionEvent e) -> {
        	new ReturnBook();
        });
        
        compList.add(returnBooks);

        JButton logout = new JButton("Logout");
        logout.setPreferredSize(btnDimension);
        
        logout.addActionListener((ActionEvent e) -> {
        	dispose();
        	new UserModeView();
        });
        
        compList.add(logout);

        render();
        setVisible(true);
    }
}
