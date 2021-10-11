import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminView extends CenteredLayout {
    AdminView () {
        topLabel.setText("Admin Section");
        topLabel.setFont(new Font("SF Pro", Font.PLAIN, 25));
        topLabel.setForeground(Color.black);
        Dimension btnDimension = new Dimension(170, 45);

        topInsets = new Insets(40, 0, 0, 0);

        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton addBooks = new JButton("Add Librarian");
        addBooks.setPreferredSize(btnDimension);
        
        addBooks.addActionListener((ActionEvent e) -> {
        	new AddLibrarian();
        });

        
        compList.add(addBooks);

        JButton viewLibrarian = new JButton("View Librarian");
        viewLibrarian.setPreferredSize(btnDimension);
        
        
        viewLibrarian.addActionListener((ActionEvent e) -> {
        	String[] headers = {"ID", "Name", "Password", "Email", "Address", "City", "Contact Info"};
        	DataTable table = new DataTable(new LibrarianDBHandler().getLibrarian(), headers);
        	table.render();
        });
        
        compList.add(viewLibrarian);

        JButton delLibrarian = new JButton("Delete Librarian");
        delLibrarian.setPreferredSize(btnDimension);
        
        delLibrarian.addActionListener((ActionEvent e) -> {
        	new DeleteLibrarian();
        });
        
        
        compList.add(delLibrarian);

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
