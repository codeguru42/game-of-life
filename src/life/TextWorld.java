package life;

public class TextWorld extends World {
    public static void main(String[] args) {
        TextWorld world;

        if (args.length == 0) {
            world = new TextWorld();
        } else {
            world = new TextWorld(args[0]);
        }

        for (int i = 0; world.countLife() > 0; i++) {
            System.out.println("Generation " + i);
            System.out.println("" + world);

            world.nextGen();
        }
    }

    public TextWorld() {
        super();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = true;
            }
        }
    }

    public TextWorld(String f) {
        super(f);
    }

    @Override
    public String toString() {
        StringBuffer out = new StringBuffer();

        for (int i = 0; i < 2 * width + 1; i++) {
            out.append('-');
        }
        out.append('\n');

        for (int i = 0; i < height; i++) {
            out.append('|');
            for (int j = 0; j < width; j++) {
                if (board[j][i])
                    out.append("*|");
                else
                    out.append(" |");
            }

            out.append('\n');
        }

        for (int i = 0; i < 2 * width + 1; i++) {
            out.append('-');
        }
        out.append('\n');

        return "" + out;
    }
}
