package masterBfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lufengxiang
 * @since 2021/7/2
 **/
public class Serach {
    //岛屿的最大面积
    private Integer max = 0;

    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    int res = dfs(grid, i, j, 0);
                    if (res > max) {
                        max = res;
                    }
                }
            }
        }
        return max;
    }

    private int dfs(int[][] grid, int i, int j, int count) {
        int row = grid.length;
        int col = grid[0].length;
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        count += dfs(grid, i + 1, j, count)
                + dfs(grid, i - 1, j, count)
                + dfs(grid, i, j + 1, count)
                + dfs(grid, i, j - 1, count)
                + 1;
        return count;
    }

    public int maxAreaOfIslandBfs(int[][] grid) {
        int ans = 0;
        for (int i = 0; i != grid.length; ++i) {
            for (int j = 0; j != grid[0].length; ++j) {
                int cur = 0;
                Queue<Integer> queueI = new LinkedList<>();
                Queue<Integer> queueJ = new LinkedList<>();
                queueI.offer(i);
                queueJ.offer(j);
                while (!queueI.isEmpty()) {
                    int curI = queueI.poll(), curJ = queueJ.poll();
                    if (curI < 0 || curJ < 0 || curI == grid.length || curJ == grid[0].length || grid[curI][curJ] != 1) {
                        continue;
                    }
                    ++cur;
                    grid[curI][curJ] = 0;
                    int[] di = {0, 0, 1, -1};
                    int[] dj = {1, -1, 0, 0};
                    for (int index = 0; index != 4; ++index) {
                        int nextI = curI + di[index], nextJ = curJ + dj[index];
                        queueI.offer(nextI);
                        queueJ.offer(nextJ);
                    }
                }
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }
}
