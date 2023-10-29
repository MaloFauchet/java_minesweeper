public class Algo {
    final char EMPTY_CELL = '_';

    int[] size;  // size of the grid. first is num of rows. second is num of columns
    int mine_number;  // number of mines
    int[][] algo_grid;  // the algo grid. contains mine positions and the numbers of mines next to a case
    int[][] game_grid;  // the game grid. the one printed and updated in function of user input


    public Algo(int[] grid_size, int p_mine_number){
        size = grid_size;
        mine_number = p_mine_number;
        algo_grid = new int[size[0]][size[1]];
        game_grid = new int[size[0]][size[1]];

        initGrid(algo_grid, 0);
        initGrid(game_grid, EMPTY_CELL);
        plantMine();
        printGrid(algo_grid);
        printGrid(game_grid);
    }

    public void initGrid(int[][] grid, int value) {
        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < size[1]; j++) {
                grid[i][j] = value;
            }
        }
    }

    public void plantMine() {
        int mine_planted = 0;
        int x, y;

        while (mine_planted < mine_number) {
            x = getRandomInt(size[1]);
            y = getRandomInt(size[0]);
            if (algo_grid[y][x] != -1) {
                algo_grid[y][x] = -1;
                mine_planted++;
            }
        }
    }

    public void printGrid(int[][] grid) {
        for (int i = 0; i < size[0]; i++) {
            if (i==0) {
                System.out.println("   |  0. 1. 2. 3. 4. 5. 6. 7. 8. 9.");
                System.out.println("___|_______________________________");
            }
            for (int j = 0; j < size[1]; j++) {
                if (j == 0) {
                    System.out.print(i+". |");
                }
                if (grid[i][j] > 8) {
                    System.out.format("%3c", grid[i][j]);
                } else {
                    System.out.format("%3d", grid[i][j]);
                }
            }
            System.out.println();
        }
    }

    // return a random number between 0 and max_num(excluded)
    private static int getRandomInt(int max_num) {
        return (int)(Math.random() * (max_num));
    }
}
