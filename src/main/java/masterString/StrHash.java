package masterString;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author lufengxiang
 * @since 2021/7/5
 **/
public class StrHash {
    static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        //先读一行切分开
        String[] s = read.readLine().split(" ");
        //字符串长度
        int n = Integer.parseInt(s[0]);
        //询问的次数
        int t = Integer.parseInt(s[1]);
        //读取字符串
        String w = read.readLine();
        //
        int P = 131;
        long[] h = new long[n + 10];
        long[] p = new long[n + 10];
        p[0] = 1;
        //初始化.
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + w.charAt(i - 1);
        }
        while (t-- > 0) {
            //
            s = read.readLine().split(" ");
            int l1 = Integer.parseInt(s[0]);
            int r1 = Integer.parseInt(s[1]);
            int l2 = Integer.parseInt(s[2]);
            int r2 = Integer.parseInt(s[3]);
            //
            String out = h[r1] - h[l1 - 1] * p[r1 - l1 + 1] == h[r2] - h[l2 - 1] * p[r2 - l2 + 1] ?
                    "Yes" : "No";
            log.write(out + "\n");
        }
        log.flush();
    }

    //实现str
    //accdb  cdb
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        if (n == 0) return 0;
        int pn = 0;
        while (pn < m - n + 1) {
            while (pn < m - n + 1 && haystack.charAt(pn) != needle.charAt(0)) pn++;
            int maxLen = 0;
            int pl = 0;
            while (pl < n && pn < m && haystack.charAt(pn) == needle.charAt(pl)) {
                maxLen++;
                pl++;
                pn++;
            }
            if (maxLen == n) return pn - n;
            //回溯
            pn = pn - maxLen + 1;
        }
        return -1;
    }
}
