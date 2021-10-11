import javax.swing.*;
import java.awt.*;

public class PopUpMessage extends CenteredLayout {
    PopUpMessage (String message) {
        setTitle("Message");
        topLabel.setText(message);

        topInsets = new Insets(40, 0, 0, 0);
        middleInsets = new Insets(0, 0, 0, 0);

        setSize(400, 150);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton okBtn = new JButton("Ok");
        okBtn.addActionListener((java.awt.event.ActionEvent e) -> {
        	dispose();
        });
        compList.add(okBtn);


        render();
        setVisible(true);
    }
}
