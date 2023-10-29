import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] size = {10, 10};
        int mine_number = 15;
        Algo algo = new Algo(size, mine_number);
        gameLoop(algo);
    }

    public static void gameLoop(Algo algo){
        while (true) {
            algo.printGrid(algo.game_grid);
            algo.input_y = getInput("line", 10);
            algo.input_x = getInput("column", 10);
            if (algo.algo_grid[algo.input_y][algo.input_x] == -1)
                break;
            algo.discoverCaseAround(algo.input_x, algo.input_y);
        }
        System.out.println("You lost");
    }

    public static int getInput(String input_str, int max) {
        int input;
        input = -1;
        Scanner scanner = new Scanner(System.in);
        while (input < 0 || input > max) {
            System.out.print("Input the " + input_str + " : ");
            input = scanner.nextInt();
        }
        return input;
    }
}