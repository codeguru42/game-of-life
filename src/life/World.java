package life;

import java.awt.*;
import java.io.*;

abstract public class World {
    protected int width;
    protected int height;
    protected boolean[][] board;
    private boolean counted;
    private int count;

    public World() {
        this(10, 10);
    }

    public World(int w, int h) {
        width = w;
        height = h;
        board = new boolean[width][height];
    }

    public World(String file) {
        loadFromFile(new File(file));
    }

    public World(File f) {
        loadFromFile(f);
    }

    public void saveToFile(File f) {
        try {
            FileOutputStream fos = new FileOutputStream(f);
            DataOutputStream dos = new DataOutputStream(fos);

            dos.write(width);
            dos.write(height);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (board[i][j]) {
                        dos.write(i);
                        dos.write(j);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }

    public void loadFromFile(File f) {
        try {
            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);

            width = dis.readInt();
            height = dis.readInt();
            board = new boolean[width][height];
            counted = false;

            while (true) {
                int x = dis.readInt();
                int y = dis.readInt();
                board[x][y] = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }

    public int countNeighbors(int x, int y) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((i != 0) || (j != 0)) {
                    int m = x + i;
                    int n = y + j;

                    if (m < 0)
                        m += width;

                    if (m >= width)
                        m -= width;

                    if (n < 0)
                        n += height;

                    if (n >= height)
                        n -= height;

                    if (board[m][n])
                        count++;
                }
            }
        }

        return count;
    }

    public void nextGen() {
        boolean[][] temp = new boolean[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int n = countNeighbors(i, j);

                if (n == 3) temp[i][j] = true;
                else if ((n == 2) && board[i][j] == true)
                    temp[i][j] = true;
                else
                    temp[i][j] = false;
            }
        }

        counted = false;
        board = temp;
    }

    public int countLife() {
        if (!counted) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (board[i][j]) ++count;
                }

                counted = true;
            }
        }

        return count;
    }

    public Dimension getSize() {
        return new Dimension(height, width);
    }
}
