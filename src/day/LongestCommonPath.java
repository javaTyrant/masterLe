package day;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lufengxiang
 * @since 2021/7/17
 **/
public class LongestCommonPath {
    //1923. 最长公共子路径
    int N = 100010;
    long[] h = new long[N];
    long[] p = new long[N];
    int[][] path;
    Map<Long, Integer> cnt = new HashMap<>();
    Map<Long, Integer> inner = new HashMap<>();

    public int longestCommonSubpath(int n, int[][] paths) {
        this.path = paths;
        int l = 0;
        int r = N;
        for (int[] ints : paths) {
            r = Math.min(r, ints.length);
        }
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid))
                l = mid;
            else
                r = mid - 1;
        }
        return l;
    }

    private boolean check(int mid) {
        cnt.clear();
        inner.clear();
        p[0] = 1;
        for (int j = 0; j < path.length; j++) {
            int n = path[j].length;
            for (int i = 1; i <= n; i++) {
                p[i] = p[i - 1] * 133331;
                h[i] = h[i - 1] * 133331 + path[j][i - 1];
            }
            for (int i = mid; i <= n; i++) {
                long val = get(i - mid + 1, i);
                if (!inner.containsKey(val) || inner.get(val) != j) {
                    inner.put(val, j);
                    cnt.put(val, cnt.getOrDefault(val, 0) + 1);
                }
            }
        }
        int max = 0;
        for (long key : cnt.keySet()) {
            max = Math.max(max, cnt.get(key));
        }
        return max == path.length;
    }

    public long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}
