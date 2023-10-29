public class Algo {
    final char EMPTY_CELL = '_';

    int[] size;  // size of the grid. first is num of rows. second is num of columns
    int mine_number;  // number of mines
    int[][] algo_grid;  // the algo grid. contains mine positions && the numbers of mines next to a case
    int[][] game_grid;  // the game grid. the one printed and updated in function of user input
    int[] position_checked;  // contains all the case already discovered. 0 : x0, 1: y0;  2: x1, 3: y1...
    int position_checked_index;  // index of the last x in position_checked
    int input_x;
    int input_y;


    public Algo(int[] grid_size, int p_mine_number){
        size = grid_size;
        mine_number = p_mine_number;
        algo_grid = new int[size[0]][size[1]];
        game_grid = new int[size[0]][size[1]];
        position_checked = new int[size[0]*size[1] *2];
        position_checked_index = 0;

        initGrid(algo_grid, 0);
        initGrid(game_grid, EMPTY_CELL);
        plantMine();
        getMineAround();
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

    public void getMineAround(){
        int len_i = size[0] - 1;
        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < size[1]; j++) {
                // skip if it contains a mine
                if (algo_grid[i][j] == -1)
                    continue;

                if (i > 0 && algo_grid[i - 1][j] == -1)
                    algo_grid[i][j] = algo_grid[i][j] + 1;
                // Check down
                if (i < len_i && algo_grid[i + 1][j] == -1)
                    algo_grid[i][j] = algo_grid[i][j] + 1;
                // Check left
                if (j > 0 && algo_grid[i][j - 1] == -1)
                    algo_grid[i][j] = algo_grid[i][j] + 1;
                // Check right
                if (j < len_i && algo_grid[i][j + 1] == -1)
                    algo_grid[i][j] = algo_grid[i][j] + 1;
                // Check top-left
                if (i > 0 && j > 0 && algo_grid[i - 1][j - 1] == -1)
                    algo_grid[i][j] = algo_grid[i][j] + 1;
                // Check top-right
                if (i > 0 && j < len_i && algo_grid[i - 1][j + 1] == -1)
                    algo_grid[i][j] = algo_grid[i][j] + 1;
                // Check below-left
                if (i < len_i && j > 0 && algo_grid[i + 1][j - 1] == -1)
                    algo_grid[i][j] = algo_grid[i][j] + 1;
                // Check below-right
                if (i < len_i && j < len_i && algo_grid[i + 1][j + 1] == -1)
                    algo_grid[i][j] = algo_grid[i][j] + 1;
            }
        }
    }

    public void discoverCaseAround(int x, int y) {
        if (isInPositionChecked(x, y))
            return;

        position_checked_index += 2;
        position_checked[position_checked_index] = x;
        position_checked[position_checked_index+1] = y;

        int case_value = algo_grid[y][x];

        if (case_value == 0) {
            // check up
            if (y != 0) {
                discoverCaseAround(x, y-1);
                //check up left
                if (x != 0)
                    discoverCaseAround(x-1, y-1);
                //check up right
                if (x != size[0]-1)
                    discoverCaseAround(x+1, y-1);
            }

            // check left
            if (x != 0)
                discoverCaseAround(x-1, y);
            //check right
            if (x != size[0]-1)
                discoverCaseAround(x+1, y);

            // check down
            if (y != size[1]-1) {
                discoverCaseAround(x, y+1);
                //check down left
                if (x != 0)
                    discoverCaseAround(x-1, y+1);
                if (x != size[0]-1)
                    discoverCaseAround(x+1, y+1);
            }
        }
        updatePosition(x, y);
    }

    public boolean isInPositionChecked(int x, int y) {
        for (int i = 0; i < position_checked_index; i+=2) {
            if (position_checked[i]==x && position_checked[i+1]==y)
                return true;
        }
        return false;
    }

    public void updatePosition(int x, int y) {
        game_grid[y][x] = algo_grid[y][x];
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

    // return a random int between 0 and max_num(excluded)
    private static int getRandomInt(int max_num) {
        return (int)(Math.random() * (max_num));
    }
}
