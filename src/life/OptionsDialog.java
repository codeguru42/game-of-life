package life;

import javax.swing.*;
import java.awt.*;

public class OptionsDialog extends JDialog {
    private final JTextField widthText;
    private final JTextField heightText;

    public OptionsDialog(JFrame owner, int x, int y) {
        super(owner, true);

        setTitle("Options Dialog");
        setSize(150, 100);
        setLocation(x, y);

        Container p = getContentPane();
        widthText = new JTextField();
        heightText = new JTextField();
        p.add(new JLabel("Width:"));
        p.add(widthText);
        p.add(new JLabel("Height:"));
        p.add(heightText);
    }

    public Dimension getSize() {
        int h = Integer.parseInt(widthText.getText());
        int w = Integer.parseInt(heightText.getText());

        return new Dimension(h, w);
    }
}
