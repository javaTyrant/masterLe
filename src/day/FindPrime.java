package day;

/**
 * @author lufengxiang
 * @since 2021/7/15
 **/
public class FindPrime {
    //普通筛选法:素数的倍数一定不是素数
    static void sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        for (int i = 0; i <= n; i++)
            prime[i] = true;
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }
        for (int i = 2; i <= n; i++) {
            if (prime[i])
                System.out.print(i + " ");
        }
    }

    // Driver Program to test above function
    public static void main(String[] args) {
        int n = 100;
        System.out.print("Following are the prime numbers ");
        System.out.println("smaller than or equal to " + n);
        sieveOfEratosthenes(n);
    }
}
