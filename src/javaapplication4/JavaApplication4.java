/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 *
 * @author nayana.s
 */
public class JavaApplication4 {
    static final int row = 12;
    static final int column = 10;

    static final int visited[][] = new int [row][column];

    static final int result[][] = new int [row][column];

    static int COUNT;

    static boolean is_valid(int x, int y,
                            int key,
                            int[][] grid)
    {
        if (x < row && y < column &&
            x >= 0 && y >= 0)
        {
            if (visited[x][y] == 0 &&
                grid[x][y] == key)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    static void BFS(int x, int y, int i,
                    int j, int[][] grid)
    {
        if (x != y)
            return;

        visited[i][j] = 1;
        COUNT++;

        int x_move[] = { 0, 0, 1, -1 };
        int y_move[] = { 1, -1, 0, 0 };

        for (int u = 0; u < 4; u++)
            if ((is_valid(i + y_move[u],
                 j + x_move[u], x, grid)) == true)
                BFS(x, y, i + y_move[u],
                          j + x_move[u], grid);
    }

    static void reset_visited()
    {
        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++)
                visited[i][j] = 0;
    }

    static void reset_result(int key,
                             int[][] grid)
    {
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < column; j++)
            {
                if (visited[i][j] ==1 &&
                    grid[i][j] == key)
                    result[i][j] = visited[i][j];
                else
                    result[i][j] = 0;
            }
        }
    }

    static void print_result(int res)
    {
        System.out.println ("The largest connected " +
                        "component of the grid is :" +
                                                res );

        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < column; j++)
            {
                if (result[i][j] != 0)
                    System.out.print(result[i][j] + " ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    static void computeLargestConnectedGrid(int[][] grid)
    {
        int current_max = Integer.MIN_VALUE;

        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < column; j++)
            {
                reset_visited();
                COUNT = 0;

                if (j + 1 < column)
                    BFS(grid[i][j], grid[i][j + 1],
                                        i, j, grid);

                if (COUNT >= current_max)
                {
                    current_max = COUNT;
                    reset_result(grid[i][j], grid);
                }
                reset_visited();
                COUNT = 0;

                if (i + 1 < row)
                    BFS(grid[i][j],
                        grid[i + 1][j], i, j, grid);

                if (COUNT >= current_max)
                {
                    current_max = COUNT;
                    reset_result(grid[i][j], grid);
                }
            }
        }
        print_result(current_max);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] grid = {{'B', 'B', 'G', 'B', 'B', 'R', 'R', 'B', 'R', 'B'},
                     {'B', 'R', 'R', 'R', 'G', 'R', 'R', 'R', 'G', 'B'},
                     {'G', 'G', 'G', 'G', 'R', 'G', 'R', 'G', 'G', 'G'},
                     {'G', 'G', 'G', 'B', 'G', 'B', 'B', 'B', 'R', 'G'},
                     {'B', 'B', 'B', 'R', 'G', 'R', 'B', 'R', 'G', 'R'},
                     {'R', 'B', 'B', 'R', 'R', 'B', 'B', 'R', 'G', 'R'},
                     {'R', 'G', 'B', 'R', 'B', 'R', 'G', 'B', 'B', 'B'},
                     {'B', 'G', 'R', 'R', 'R', 'R', 'B', 'B', 'R', 'R'},
                     {'R', 'R', 'G', 'B', 'G', 'G', 'B', 'R', 'R', 'G'},
                     {'R', 'B', 'B', 'B', 'B', 'B', 'R', 'B', 'B', 'G'},
                     {'B', 'R', 'B', 'B', 'R', 'B', 'B', 'B', 'R', 'R'},
                     {'B', 'R', 'B', 'B', 'G', 'B', 'B', 'B', 'G', 'R'}};
 
        computeLargestConnectedGrid(grid);
        
    }
    
}
