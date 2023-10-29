public class Algo {
    int[] size;  // size of the grid. first is num of rows. second is num of columns
    int mine_number;  // number of mines
    int[][] grid;


    public Algo(int[] grid_size, int p_mine_number){
        size = grid_size;
        mine_number = p_mine_number;
        grid = new int[size[0]][size[1]];

        initGrid();
        printGrid();
    }

    public void initGrid() {
        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < size[1]; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void printGrid() {
        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < size[1]; j++) {
                System.out.format("%4d", grid[i][j]);
            }
            System.out.println();
        }
    }

    // return a random number between 0 and max_num(excluded)
    private static int getRandomInt(int max_num) {
        return (int)(Math.random() * (max_num));
    }
}
