package life;

import java.awt.*;
import java.io.File;

public class GraphicWorld extends World {
    private static final int CELL_SIZE = 10;
    private int x;
    private int y;

    public GraphicWorld(int w, int h) {
        super(w, h);
    }

    public GraphicWorld(int x, int y, int w, int h) {
        super(w, h);
        this.x = x;
        this.y = y;
    }

    public GraphicWorld(String file) {
        super(file);
    }

    public GraphicWorld(File file) {
        super(file);
    }

    public GraphicWorld(String file, int xPos, int yPos) {
        super(file);
        x = xPos;
        y = yPos;
    }

    public void paint(Graphics g, int w, int h) {
        x = (w - width * CELL_SIZE) / 2;
        y = (h - height * CELL_SIZE) / 2;
        paint(g);
    }

    private void paint(Graphics g) {
        for (int i = 0; i <= width; i++) {
            int xPos = x + i * CELL_SIZE;
            g.drawLine(xPos, y, xPos, y + height * CELL_SIZE);
        }

        for (int i = 0; i <= height; i++) {
            int yPos = y + i * CELL_SIZE;
            g.drawLine(x, yPos, x + width * CELL_SIZE, yPos);
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (board[i][j]) {
                    int left = x + i * CELL_SIZE;
                    int top = y + j * CELL_SIZE;

                    g.fillRect(left, top, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    public void setCell(int x, int y) throws IndexOutOfBoundsException {
        Point p = new Point(x, y);
        Rectangle r = new Rectangle(this.x, this.y, width * CELL_SIZE, height * CELL_SIZE);

        if (!r.contains(p))
            throw new IndexOutOfBoundsException("Coordinates are out of bounds");

        int newX = (x - this.x) / CELL_SIZE;
        int newY = (y - this.y) / CELL_SIZE;
        board[newX][newY] = !board[newX][newY];
    }
}
