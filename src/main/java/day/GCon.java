package day;

import java.util.Scanner;

/**
 * @author lufengxiang
 * @since 2021/7/14
 **/
public class GCon {
    //
    static int N = 1000010;
    //质数
    static int[] primes = new int[N];
    //
    static int cnt = 0;
    //
    static boolean[] st = new boolean[N];

    //埃拉托斯特尼筛法 -> 线性筛法
    static void init() {
        for (int i = 2; i <= N - 1; i++) {
            //
            if (!st[i]) primes[cnt++] = i;
            //
            for (int j = 0; primes[j] * i <= N - 1; j++) {
                st[primes[j] * i] = true;
                if (i % primes[j] == 0) break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        init();
        while (true) {
            int n = scan.nextInt();
            if (n == 0) break;
            for (int i = 1; ; i++) {
                int a = primes[i];
                int b = n - a;
                if (!st[b]) {
                    System.out.println(n + " = " + a + " + " + b);
                    break;
                }
            }
        }
    }
}
