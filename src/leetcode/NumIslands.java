package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class NumIslands {

    public int numIslandsInBFS(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int num = 0;
        int nr = grid.length;
        int nc = grid[0].length;

        for(int i = 0; i<nr; i++) {
            for(int j =0;j<nc; j++) {
                if(grid[i][j] == '1') {
                    num++;
                    grid[i][j] = '0';
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(i * nc + j);
                    while(!queue.isEmpty()) {
                        int id = queue.remove();
                        int row = id/nc;
                        int col = id%nc;

                        if(row-1>=0 && grid[row-1][col]=='1') {
                            grid[row-1][col]='0';
                            queue.add((row-1) * nc + col);
                        }
                        if(row+1<nr && grid[row+1][col]=='1') {
                            grid[row+1][col]='0';
                            queue.add((row+1) * nc + col);
                        }
                        if(col-1>=0 && grid[row][col-1]=='1') {
                            grid[row][col-1]='0';
                            queue.add(row * nc + col-1);
                        }
                        if(col+1<nc && grid[row][col+1]=='1') {
                            grid[row][col+1]='0';
                            queue.add(row * nc + col+1);
                        }
                    }

                }
            }
        }
        return num;



    }

    public static int numOfIslandsInDFS(char[][] grids) {
        if(grids == null || grids.length == 0) {
            return 0;
        }

        int nr = grids.length;
        int nc = grids[0].length;

        int num = 0;

        for (int row = 0; row < nr ; row++) {
            for (int col = 0; col < nc; col++) {
                if (grids[row][col] == '1') {
                    num++;
                    dfs(grids, row, col);
                }
            }
        }

        return num;

    }

    public static void dfs(char[][] grids, int row, int col) {
        int nr = grids.length;
        int nc = grids[0].length;

        if(row<0 || col <0 || row>=nr || col>=nc || grids[row][col]=='0') {
            return ;
        }
        grids[row][col] = '0';
        dfs(grids, row+1, col);
        dfs(grids, row-1, col);
        dfs(grids, row, col+1);
        dfs(grids, row, col-1);
    }


    public static void main(String[] args) {

    }

}
