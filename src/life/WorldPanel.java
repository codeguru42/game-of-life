package life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class WorldPanel extends JPanel {
    private int across = 10;
    private int down = 10;
    private int genCount;
    private final JLabel genLabel;
    private GraphicWorld world;

    public WorldPanel() {
        genCount = 0;
        genLabel = new JLabel("Generation " + genCount);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    world.setCell(e.getX(), e.getY());
                    repaint();
                } catch (IndexOutOfBoundsException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (world == null) newWorld();
        world.paint(g, getWidth(), getHeight());
    }

    public void nextGen() {
        world.nextGen();
        ++genCount;
        updateLabel();
        repaint();
    }

    public void reset() {
        newWorld();
    }

    public void newWorld(File file) {
        world = new GraphicWorld(file);
        genCount = 0;
        updateLabel();
        updateSize();
        repaint();
    }

    public void saveWorld(File file) {
        world.saveToFile(file);
    }

    private void newWorld() {
        world = new GraphicWorld(across, down);
        genCount = 0;
        updateLabel();
        repaint();
    }

    private void updateLabel() {
        genLabel.setText("Generation " + genCount);
    }

    private void updateSize() {
        Dimension d = world.getSize();
        across = d.width;
        down = d.height;
    }
}
