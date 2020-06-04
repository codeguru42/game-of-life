package life;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LifePanel extends JPanel {
    private final WorldPanel w;
    private Timer t;

    public LifePanel() {
        super(new BorderLayout());

        w = new WorldPanel();
        add(w, BorderLayout.CENTER);

        JButton nextGen = new JButton("Next Generation");
        nextGen.addActionListener(e -> {
            if (!t.isRunning())
                w.nextGen();
        });

        JButton restart = new JButton("Restart");
        restart.addActionListener(e -> {
            w.reset();
            t.stop();
        });

        JButton go = new JButton("Go");
        go.addActionListener(e -> t.start());

        JButton stop = new JButton("Stop");
        stop.addActionListener(e -> t.stop());

        t = new Timer(500, e -> w.nextGen());

        JPanel buttons = new JPanel();
        buttons.add(nextGen);
        buttons.add(restart);
        buttons.add(go);
        buttons.add(stop);
        add(buttons, BorderLayout.SOUTH);
    }

    public void stopTimer() {
        t.stop();
    }

    public void newWorld(File file) {
        w.newWorld(file);
    }

    public void saveWorld(File file) {
        w.saveWorld(file);
    }
}
