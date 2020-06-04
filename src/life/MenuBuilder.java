package life;

import javax.swing.*;

public class MenuBuilder {
    private final JMenuBar menuBar;

    public MenuBuilder(JFrame f) {
        menuBar = new JMenuBar();
        f.setJMenuBar(menuBar);
    }

    public void addMenu(String name, Action[] actions) {
        JMenu menu = new JMenu(name);
        menuBar.add(menu);

        for (Action action : actions) {
            if (action == null)
                menu.addSeparator();
            else
                menu.add(action);
        }
    }
}
