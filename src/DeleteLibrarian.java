import javax.swing.*;
import java.awt.*;

public class DeleteLibrarian extends FormLayout {
    DeleteLibrarian() {
        setSize(400, 275);

        JLabel enterId = new JLabel("Enter ID:");
        JTextField idField = new JTextField(15);
        compList.add(makeGroup(enterId, idField));
        // Event handling will be done later


        JPanel btnPane = new JPanel();
        JButton delete = new JButton("Delete");
        delete.setPreferredSize(new Dimension(100, 50));

        delete.addActionListener((java.awt.event.ActionEvent e) -> {
        	int id = Integer.parseInt(idField.getText());
        	int success = new LibrarianDBHandler().deleteLibrarian(id);
        	if (success == 0) {
        		new PopUpMessage("Id does not exists");
        	} else {
        		new PopUpMessage("Librarian deleted successfully");
        	}
        });

        btnPane.add(delete);

//        JPanel backPane = new JPanel();
//        JButton back = new JButton("Back");
//        backPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        backPane.add(back);

        compList.add(btnPane);
//        compList.add(backPane);
        render();
        setVisible(true);
    }
}
