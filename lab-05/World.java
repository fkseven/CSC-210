public class World implements TurnTaker {

    private int width;
    private int height;
    private Tile[][] tiles;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[height][width]; // tiles[row][col]
    }

    public void setTile(int x, int y, Tile tile) {
        tiles[y][x] = tile;
    }

    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }

    @Override
    public void takeTurn() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Tile t = tiles[y][x];
                if (t != null) {
                    t.takeTurn();
                }
            }
        }
    }
}