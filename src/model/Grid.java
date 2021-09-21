package model;

public class Grid {

    private final int WIDTH = 10, HEIGHT = 20;
    private Integer[][] matrix ;

    public Grid() {
        matrix = new Integer[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++)
            for (int j = 0; j < HEIGHT; j++)
                matrix[i][j] = 0;
    }
}
