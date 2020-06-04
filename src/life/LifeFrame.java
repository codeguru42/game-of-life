package life;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class LifeFrame extends JFrame {
    private final LifePanel panel;
    private final JFileChooser fileDialog;
    private final OptionsDialog optionsDialog;

    public static void main(String[] args) {
        new LifeFrame().show();
    }

    public LifeFrame() {
        setTitle("The Game of Life");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(d.width / 4, d.height / 4, d.width /2, d.height / 2);

        panel = new LifePanel();
        getContentPane().add(panel);

        fileDialog = new JFileChooser(".");
        fileDialog.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".dat") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Text file";
            }
        });

        int x = getX() + getHeight() / 2;
        int y = getY() + getWidth() /2;

        optionsDialog = new OptionsDialog(this, x, y);

        createMenu();
    }

    private void createMenu() {
        MenuBuilder mb = new MenuBuilder(this);
        Action[] a = new Action[3];

        final JFrame me = this;
        a[0] = new AbstractAction("Open") {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.stopTimer();
                int result = fileDialog.showOpenDialog(me);
                if (result == JFileChooser.APPROVE_OPTION) {
                    panel.newWorld(fileDialog.getSelectedFile());
                }
            }
        };

        a[1] = new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.stopTimer();
                int result = fileDialog.showSaveDialog(me);
                if (result == JFileChooser.APPROVE_OPTION) {
                    panel.saveWorld(fileDialog.getSelectedFile());
                }
            }
        };

        a[2] = new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        mb.addMenu("File", a);

        a = new AbstractAction[1];
        a[0] = new AbstractAction("Options...") {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionsDialog.show();
            }
        };

        mb.addMenu("Edit", a);
    }
}
